import sys

# Open the file "data1.txt" and read its content
D = open("Data.txt").read().strip()
# print(D)

# Initialization of variables
p1 = 0  # Variable for the first part of the puzzle
p2 = 0  # Variable for the second part of the puzzle

# For each line in the file
for line in D.split('\n'):
    # Create two lists to store the digits for p1 and p2
    p1_digits = []
    p2_digits = []

    # Iterate through each character in the line
    for i, c in enumerate(line):
        # If the character is a digit, add it to the p1_digits and p2_digits lists
        if c.isdigit():
            p1_digits.append(c)
            p2_digits.append(c)

        # Search for the words 'one' to 'nine' for the second part (p2)
        for d, val in enumerate(['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']):
            if line[i:].startswith(val):
                # print(line) # compare lines and where they are cut
                # print(line[i:])
                # Add the associated value (1 to 9) to the p2_digits list
                p2_digits.append(str(d + 1))

    # Calculate the final sums for p1 and p2
    p1 += int(p1_digits[0] + p1_digits[-1])
    p2 += int(p2_digits[0] + p2_digits[-1])

    # print(p2_digits[0]+" "+p2_digits[-1]) # display the two numbers added
    # print(p2_digits) # display all lists to better understand

# Display the final results
print(p1)
print(p2)
