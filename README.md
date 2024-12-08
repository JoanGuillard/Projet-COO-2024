#  Projet COO 2024 - Jeu Sans Danger

## Description
Ce projet implémente un jeu textuel en Java où le joueur peut interagir avec un le systeme, en utilisant différentes structures de données et designs patterns pour garantir une architecture modulaire et extensible.

##   Patterns utilisés
###  1. Pattern Singleton
      Le Pattern Singleton est utilisé pour garantir qu'il n'existe qu'une seule instance de la carte dans le jeu et qu'une seule instance des états. 
      Cela permet d'éviter la duplication de la carte à chaque fois que le joueur crée ou charge une nouvelle partie ou qu'on change d'état, assurant ainsi la cohérence et l'unicité de la carte tout au long du jeu.
      Avantages :
      Avoir une seule instance partagée entre toutes les parties du jeu.
      Consistance : Les modifications de la carte sont cohérentes à travers le jeu.

###   2. Pattern Fabrique Abstraite
      Le Pattern Fabrique Abstraite est utilisé pour créer des familles de parties. Dans notre projet, cela permet de créer facilement différentes variantes de parties, comme la famille
      SansDanger avec ses sous-classes (SansDangerPartieForet et SansDangerPartieJungle). Ce pattern nous aide à respecter le principe ouvert/fermé, permettant d'ajouter de nouvelles familles (comme AvecDanger) sans modifier le code existant.
      Avantages :
      Modularité : La fabrique abstraite permet de créer des objets (parties) de manière flexible selon le thème choisi (forêt, jungle, etc.).
      Extensibilité : L'ajout de nouvelles variantes devient simple, sans toucher aux classes existantes.
      Respect du principe ouvert/fermé : Il devient facile d'ajouter de nouvelles variantes sans altérer le code existant.

###   3. Pattern Patron de Méthode
      Le Pattern Patron de Méthode définit une structure uniforme pour la création des différentes parties. Chaque type de partie (par exemple, SansDangerPartieForet et SansDangerPartieJungle) 
      utilise cette structure tout en personnalisant certains comportements, comme la création d'éléments spécifiques à chaque environnement.
      Avantages :
      Réutilisation du code : La structure générale de l'algorithme est définie dans la classe parent, permettant aux sous-classes de personnaliser certaines étapes sans redonder de code.
      Simplicité d'extension : Le patron de méthode permet d'ajouter de nouvelles variantes de parties sans modifier l'algorithme global.

###   4.Pattern État
      Le Pattern État est utilisé pour modéliser les différents états des animaux (par exemple, affamé et rassasié) et gérer leur comportement selon ces états. Ce pattern permet de simplifier 
      le code en évitant l'usage de nombreuses conditions (if/else), en encapsulant les comportements dans des objets représentant chaque état.
      Avantages :
      Flexibilité : L'état des objets peut changer dynamiquement, permettant de modifier leur comportement en fonction de cet état.
      Extensibilité : Ajouter de nouveaux états devient facile sans perturber le reste du système.
      Réduction des conditions imbriquées : L'utilisation de ce pattern permet de rendre le code plus lisible et maintenable.
