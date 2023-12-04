import re
from collections import defaultdict

# Read the content of the "data4.txt" file and store it in the variable D
D = open("data4.txt").read().strip()
lines = D.split('\n')
p1 = 0
N = defaultdict(int)

# Iterate through each line in the file
for i, line in enumerate(lines):
    N[i] += 1

    # Print statements for understanding part 2
    '''
    print(f"keys_{i} += 1")
    print("")
    print(N)
    print(N.values())
    '''

    # Split the line into the first part and the rest
    first, rest = line.split('|')
    id_, card = first.split(':')
    card_nums = [int(x) for x in card.split()]
    rest_nums = [int(x) for x in rest.split()]
    val = len(set(card_nums) & set(rest_nums))

    # Calculate part 1
    if val > 0:
        p1 += 2**(val-1)

    # Calculate part 2 and update dictionary
    for j in range(val):
        N[i+1+j] += N[i]
        '''
        # Print statements to understand part 2
        print(f"key_{i+1+j} += {N[i]}")
    print("")
    print(N)
    print(N.values())
    print("")
    '''

# Display the result for part 1
print(p1)

# Display the result for part 2 (sum of all values in the dictionary)
print(sum(N.values()))

'''
PART 1:
If we have:
1: 1 2 3 | 2 3 4
2: 2 3 4 | 3 4 5
3: 3 4 5 | 4 5 6

Then,
ticket 1: wins 2
ticket 2: wins 2
ticket 3: wins 2

2 + 2 + 2 = 6

PART 2:
It is a bonus system that each sub-ticket attributes to each higher ticket.

My sub-key 0 is incremented by 1 to find => key 0: 1
so I add 1 to key 1, and 1 to key 2
SO
key 0: 1
key 1: 1
key 2: 1

I increment my key 1 by 1 to find => key 1: 2
so I add 2 to key 2, and 2 to key 3
SO
key 0: 1
key 1: 2
key 2: 3
key 3: 2

I increment key 2 by 1 to find => key 2: 4
so I add 4 to key 3, and 4 to the new key 4 (special since it is not supposed to exist, we just need its points)
SO
key 0: 1
key 1: 2
key 2: 4
key 3: 6
key 4: 4

the sum of the keys gives 17
it is indeed the number we were supposed to find
'''

