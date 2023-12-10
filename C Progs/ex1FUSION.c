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
#endif


#define SIZE 10000

int tab[SIZE];
pthread_mutex_t mutex;

typedef struct {
    int left;
    int right;
} thread_args;

void merge(int left, int mid, int right) {
    int n1 = mid - left + 1;
    int n2 = right - mid;
    int Left[n1], Right[n2];

    for (int i = 0; i < n1; i++)
        Left[i] = tab[left + i];
    for (int j = 0; j < n2; j++)
        Right[j] = tab[mid + 1 + j];

    int i = 0, j = 0, k = left;
    while (i < n1 && j < n2) {
        if (Left[i] <= Right[j]) {
            tab[k] = Left[i];
            i++;
        } else {
            tab[k] = Right[j];
            j++;
        }
        k++;
    }

    // Copier les éléments restants de Left[], s'il y en a
    while (i < n1) {
        tab[k] = Left[i];
        i++;
        k++;
    }

    // Copier les éléments restants de Right[], s'il y en a
    while (j < n2) {
        tab[k] = Right[j];
        j++;
        k++;
    }
}



void *merge_sort(void *args) {
    // Fonction de tri fusion récursif avec threads
    thread_args *params = (thread_args *)args;
    int left = params->left;
    int right = params->right;

    if (left < right) {
        int mid = left + (right - left) / 2;

        pthread_t left_thread, right_thread;
        thread_args left_args = {left, mid};
        thread_args right_args = {mid + 1, right};

        pthread_create(&left_thread, NULL, merge_sort, (void *)&left_args);
        pthread_create(&right_thread, NULL, merge_sort, (void *)&right_args);

        pthread_join(left_thread, NULL);
        pthread_join(right_thread, NULL);

        pthread_mutex_lock(&mutex);
        merge(left, mid, right);
        pthread_mutex_unlock(&mutex);
    }

    pthread_exit(NULL);
}

void shuffle() {
    int i, j, temp;
    for (i = SIZE -1; i > 0; i-- ) {
        j = random() % (i + 1);
        temp = tab[i];
        tab[i] = tab[j];
        tab[j] = temp;
    }
}

int isSorted(int arr[], int size) {
    for (int i = 0; i < size - 1; i++) {
        if (arr[i] != arr[i + 1]-1) {
            return i; // La liste n'est pas triée
        }
    }
    return 0; // La liste est triée
}

int main() {
    pthread_t merge_thread;
    int i;

    // Initialisation du tableau
    srandom(time(NULL));
    for (i = 0; i < SIZE; i++) tab[i] = i + 1;
    for (i = 0; i < 3000; i++) {
        shuffle();
    }
    for (i = 0; i < SIZE; i++) printf("%d ", tab[i]);
    putchar('\n');

    // Initialisation du mutex
    pthread_mutex_init(&mutex, NULL);

    // Création du thread principal de tri fusion
    thread_args args = {0, SIZE - 1};
    pthread_create(&merge_thread, NULL, merge_sort, (void *)&args);
    pthread_join(merge_thread, NULL);

    // Destruction du mutex après utilisation
    pthread_mutex_destroy(&mutex);

    // Affichage du tableau trié
    for (i = 0; i < SIZE; i++) printf("%d ", tab[i]);
    putchar('\n');
    int a = isSorted(tab, SIZE);
    putchar('\n');
    printf("%d \n",a);

    return 0;
}