import re
from collections import defaultdict
D = open("donne4.txt").read().strip()
D = "1: 1 2 3 | 2 3 4\n2: 2 3 4 | 3 4 5\n3: 3 4 5 | 4 5 6"
lines = D.split('\n')
p1 = 0
N = defaultdict(int)
for i,line in enumerate(lines):
  N[i] += 1
  #Pour comprendre la partie 2
  print(f"keys_{i} += 1")
  print("")
  print(N)
  print(N.values())
  first, rest = line.split('|')
  id_, card = first.split(':')
  card_nums = [int(x) for x in card.split()]
  rest_nums = [int(x) for x in rest.split()]
  val = len(set(card_nums) & set(rest_nums))
  if val > 0:
    p1 += 2**(val-1)
  for j in range(val):
    N[i+1+j] += N[i]
    #Pour comprendre comment se déroule la partie 2
    print(f"key_{i+1+j} += {N[i]}")
print("")
print(N)
print(N.values())
print("")
print(p1)
print(sum(N.values())) # partie 2

'''
C'est un système de bonus que chaque sous ticket attribut à chaque ticket supérieur

ma sous clée 0 est incrémente de 1 pour trouver => clée0 : 1
donc j'ajoute donc 1 à clée 1, et 1 à clée 2
DONC 
clée 0 : 1
clée 1 : 1
clée 2 : 1

j'incrémente ma clée 1 de 1 pour trouver => clée1 : 2
donc j'ajoute 2 à clée 2, et 2 à clée 3
DONC
clée 0 : 1
clée 1 : 2
clée 2 : 3
clée 3 : 2

j'incrémente clée 2 de 1 pour trouver => clée2 : 4
donc j'ajoute 4 à clée 3, et 4 à la nouvelle clée 4 (spécial puisqu'elle n'est pas sensée exister, on a juste besoin de ses points)
DONC
clée 0 : 1
clée 1 : 2
clée 2 : 4
clée 3 : 6
clée 4 : 4


la somme des clées donne 17
c'est bien le nombre qu'on devait trouver
'''