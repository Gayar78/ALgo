#ifdef _WIN32
#include <windows.h>
#include <winsock2.h>
#include <ws2tcpip.h>
#else
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <signal.h>
#include <pthread.h>
#include <time.h>
#endif

#define SIZE 100000
#define THRESHOLD 1000 // Limite de taille pour utiliser le tri par insertion

typedef struct
{
    int *array;
    int start;
    int end;
} ThreadArgs;

void insertion_sort(int *array, int start, int end)
{
    for (int i = start + 1; i <= end; i++)
    {
        int key = array[i];
        int j = i - 1;
        while (j >= start && array[j] > key)
        {
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = key;
    }
}

void merge(int *array, int start, int mid, int end)
{
    int i = start, j = mid + 1, k = 0;
    int temp[end - start + 1];

    while (i <= mid && j <= end)
    {
        if (array[i] < array[j])
        {
            temp[k++] = array[i++];
        }
        else
        {
            temp[k++] = array[j++];
        }
    }

    while (i <= mid)
    {
        temp[k++] = array[i++];
    }

    while (j <= end)
    {
        temp[k++] = array[j++];
    }

    for (i = start; i <= end; i++)
    {
        array[i] = temp[i - start];
    }
}

void *merge_sort(void *args)
{
    ThreadArgs *arguments = (ThreadArgs *)args;
    int start = arguments->start;
    int end = arguments->end;
    int mid = (start + end) / 2;

    if (start < end)
    {
        if (end - start + 1 <= THRESHOLD)
        {
            insertion_sort(arguments->array, start, end);
            return NULL;
        }

        ThreadArgs argsLeft = {arguments->array, start, mid};
        ThreadArgs argsRight = {arguments->array, mid + 1, end};

        pthread_t tid1, tid2;
        pthread_create(&tid1, NULL, merge_sort, &argsLeft);
        pthread_create(&tid2, NULL, merge_sort, &argsRight);

        pthread_join(tid1, NULL);
        pthread_join(tid2, NULL);

        merge(arguments->array, start, mid, end);
    }

    return NULL;
}

void shuffle(int *array, int size) {
    for (int i = size - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

int main() {
    srand(time(NULL));

    int *array = malloc(SIZE * sizeof(int));
    if (!array) {
        perror("Failed to allocate memory for array");
        exit(EXIT_FAILURE);
    }

    // Remplissage du tableau avec des nombres allant de 0 à 99999
    for (int i = 0; i < SIZE; i++) {
        array[i] = i;
    }

    // Mélanger le tableau
    shuffle(array, SIZE);

    ThreadArgs args = {array, 0, SIZE - 1};
    merge_sort(&args);

    for (int i = 0; i < SIZE; i++) {
        printf("%d", array[i]);
    }
    printf("\n");

    free(array);
    return 0;
}