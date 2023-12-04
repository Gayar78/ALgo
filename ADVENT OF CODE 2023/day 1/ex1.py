import sys

# Ouvre le fichier "donne1.txt" et lit son contenu
D = open("donne1.txt").read().strip()
#print(D)

# Initialisation des variables
p1 = 0  # Variable pour la première partie du puzzle
p2 = 0  # Variable pour la deuxième partie du puzzle

# Pour chaque ligne du fichier
for line in D.split('\n'):
    # Création des deux listes pour stocker les chiffres de p1 et p2
    p1_digits = []
    p2_digits = []

    # Parcours chaque caractère de la ligne
    for i, c in enumerate(line):
        # Si le caractère est un chiffre, l'ajoute aux listes p1_digits et p2_digits
        if c.isdigit():
            p1_digits.append(c)
            p2_digits.append(c)

        # Recherche des mots 'one' à 'nine' pour la deuxième partie (p2)
        for d, val in enumerate(['one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']):
            if line[i:].startswith(val):
                #print(line) # compare les lignes et les moments où on coupe ces lignes
                #print(line[i:])
                # Ajoute la valeur associée (1 à 9) à la liste p2_digits
                p2_digits.append(str(d + 1))

    # Calcul des sommes finales pour p1 et p2
    p1 += int(p1_digits[0] + p1_digits[-1])
    p2 += int(p2_digits[0] + p2_digits[-1])
    
    #print(p2_digits[0]+" "+p2_digits[-1]) #affiche les deux nombres additionnés
    
    #print(p2_digits) #affiche toutes les liste pour bien voir

# Affiche les résultats finaux
print(p1)
print(p2)
