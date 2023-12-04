import sys
import re
from collections import defaultdict

# Read the content of the "data3.txt" file and store it in the variable D
D = open("data3.txt").read().strip()
lines = D.split('\n')
G = [[c for c in line] for line in lines]
R = len(G)
C = len(G[0])

p1 = 0
nums = defaultdict(list)

# Iterate through each row
for r in range(len(G)):
    gears = set()  # positions of '*' characters next to the current number
    n = 0
    has_part = False

    # Iterate through each column in the row
    for c in range(len(G[r])+1):
        if c<C and G[r][c].isdigit():
            n = n*10 + int(G[r][c])
            
            # Iterate through neighboring positions to find gears
            for rr in [-1, 0, 1]:
                for cc in [-1, 0, 1]:
                    if 0 <= r+rr < R and 0 <= c+cc < C:
                        ch = G[r+rr][c+cc]
                        if not ch.isdigit() and ch != '.':
                            has_part = True
                        if ch == '*':
                            gears.add((r+rr, c+cc))
        elif n > 0:
            # Update gear positions and calculate p1 if there is a part
            for gear in gears:
                nums[gear].append(n)
            if has_part:
                p1 += n
            n = 0
            has_part = False
            gears = set()

# Display the result for p1
print(p1)

# Calculate and display the result for p2
p2 = 0
for k, v in nums.items():
    if len(v) == 2:
        p2 += v[0] * v[1]
print(p2)
