import sys
from collections import defaultdict

# Lire le contenu du fichier "donne2.txt" et le stocker dans la variable D
D = open("donne2.txt").read().strip()

# Initialiser les variables p1 et p2 à 0
p1 = 0
p2 = 0

# Parcourir chaque ligne du fichier
for line in D.split('\n'):
    # Initialiser une variable booléenne "ok" à True
    ok = True
    
    # Séparer l'ID et le reste de la ligne par le caractère ":"
    id_, line = line.split(':')
    
    # Initialiser un dictionnaire par défaut pour stocker les occurrences maximales des couleurs
    V = defaultdict(int)
    
    # Parcourir chaque événement dans la ligne
    for event in line.split(';'):
        # Parcourir chaque balle dans l'événement
        for balls in event.split(','):
            # Extraire le nombre et la couleur de chaque balle
            n, color = balls.split()
            n = int(n)
            
            # Mettre à jour le nombre maximal pour cette couleur
            V[color] = max(V[color], n)

            
            # Vérifier si le nombre est supérieur à une certaine limite en fonction de la couleur
            if int(n) > {'red': 12, 'green': 13, 'blue': 14}.get(color, 0):
                ok = False  # Si le nombre dépasse la limite, définir "ok" sur False
            #print(color,":",n,"or",V[color],"=", max(V[color], n), "=>",ok) # affiche tout
            
    # Calculer un score basé sur les occurrences maximales de chaque couleur
    score = 1
    #print(V.values()) #donne les occurences maximals pour chaque valeur
    for v in V.values():
        score *= v
        #affiche le score pendant son évolution #pour toutes les valeur de "ok"
    
    # Ajouter le score calculé à p2
    p2 += score#somme des différents score dans p2 #pour toutes les valeur de "ok"
    
    # Ajouter l'ID à p1 si "ok" est True
    if ok:
        p1 += int(id_.split()[-1])#somme des différents score dans p1 #pour toutes les valeur de "ok"

# Afficher les résultats
print(p1)
print(p2)

#p2 est forcément plus grand que p1
