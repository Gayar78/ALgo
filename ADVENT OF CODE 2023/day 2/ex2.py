import sys
from collections import defaultdict

# Read the content of the "Data.txt" file and store it in the variable D
D = open("Data.txt").read().strip()

# Initialize variables p1 and p2 to 0
p1 = 0
p2 = 0

# Iterate through each line of the file
for line in D.split('\n'):
    # Initialize a boolean variable "ok" to True
    ok = True
    
    # Split the ID and the rest of the line by the ":" character
    id_, line = line.split(':')
    
    # Initialize a default dictionary to store the maximum occurrences of colors
    V = defaultdict(int)
    
    # Iterate through each event in the line
    for event in line.split(';'):
        # Iterate through each ball in the event
        for balls in event.split(','):
            # Extract the number and color of each ball
            n, color = balls.split()
            n = int(n)
            
            # Update the maximum number for this color
            V[color] = max(V[color], n)

            # Check if the number exceeds a certain limit based on the color
            if int(n) > {'red': 12, 'green': 13, 'blue': 14}.get(color, 0):
                ok = False  # If the number exceeds the limit, set "ok" to False
            # print(color, ":", n, "or", V[color], "=", max(V[color], n), "=>", ok)  # display everything
            
    # Calculate a score based on the maximum occurrences of each color
    score = 1
    # print(V.values())  # gives the maximum occurrences for each value
    for v in V.values():
        score *= v
        # display the score during its evolution #for all values of "ok"
    
    # Add the calculated score to p2
    p2 += score  # sum of the different scores in p2 #for all values of "ok"
    
    # Add the ID to p1 if "ok" is True
    if ok:
        p1 += int(id_.split()[-1])  # sum of the different scores in p1 #for all values of "ok"

# Display the results
print(p1)
print(p2)

# p2 is always greater than p1
