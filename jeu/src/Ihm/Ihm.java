package Ihm;

import java.util.Scanner;

public class Ihm {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private final Scanner scanner;
    public Ihm(){
        this.scanner=new Scanner(System.in);
    }

    /**
     *
     * affiche la carte de jeu
     *
     * @param carte la carte du jeu a afficher
     */
    public void afficherCarte(char[][] carte){
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte[i].length; j++) {
                System.out.print(getCouleurCase(carte[i][j]));
            }
            System.out.println(); // Passe à la ligne suivante après chaque ligne de la carte
        }
    }

    /**
     *
     * demande au joueur de saisir le theme souhaite
     *
     * @return l'entier correpondant au  choix fait par le joueur, -1 si rien
     */
    public int demanderTheme(){

        boolean choixIncorrect = true;
        int choix = -1;
        while(choixIncorrect) {
            System.out.println("Choisir un theme:");
            System.out.println(" 1: theme foret");
            System.out.println(" 2: theme Jungle");
            System.out.println("Veuillez saisir le numero correspondant au theme:");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (0 < choix && choix <= 2) {
                    choixIncorrect = false;
                    return choix;
                }
            }
            System.out.println("Veuillez saisir un entier valide.");
            scanner.nextLine();
        }
        return -1;
    }

    /**
     *
     * demande au joueur de choisir un action
     *
     * @return l'entier qui correspond a l'action demandee par le joueur -1 si rien
     */
    public int demanderActionJoueur(){
        boolean choixIncorrect = true;
        int choix = -1;
        while(choixIncorrect) {
            System.out.println("Veuillez choisir un action:");
            System.out.println("1 : Se deplacer");
            System.out.println("2 : Ramasser un objet");
            System.out.println("3 : Poser un objet");
            System.out.println("4 : Interagir avec un animal");
            System.out.println("5 : Quitter le jeu");
            System.out.println();
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (0<  choix && choix <= 5) {
                    choixIncorrect = false;
                    return choix;
                }
            }
            System.out.println("L'entier que vous avez saisit ne correspond a aucune action.Veuillez choisir un entier valide.");
            scanner.nextLine();
        }
        return -1;
    }

    /**
     *
     * demande au joueur s'il souhaite charger une carte ou creer une
     *
     * @return l'entier correspondant au choix,-1 pour quitter ou aucun choix
     */
    public int demanderCreationCarte(){
        boolean choixIncorrect = true;
        int choix = -1;
        while (choixIncorrect){
            System.out.println("Pour demarrer une partie,voulez vous:");
            System.out.println("1 : creer une nouvelle carte");
            System.out.println("2 : charger une carte via un fichier .txt");
            System.out.println("3 : quitter le jeu");
            System.out.println("Veuillez saisir l'entier qui correspond a votre choix.");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (0 < choix && choix <= 3) {
                    choixIncorrect = false;
                    return choix;
                }
            }
            System.out.println("L'entier que vous avez choisi ne correspond a aucun choix.Veuillez saisir un entier valide.");
            scanner.nextLine();
        }
        return -1;
    }

    /**
     * demande au joueur la direction dans laquelle il souhaite se deplacer
     *
     * @return la chaîne de caractères représentant la direction
     */
    public String demanderDirection(){
        boolean choixIncorrect = true;
        while(choixIncorrect){
            System.out.println("Dans quelle direction voulez vous se deplacer ?");
            System.out.println("H pour Haut");
            System.out.println("B pour Bas");
            System.out.println("G pour Gauche");
            System.out.println("D pour Droite");
            System.out.println("Q pour Quitter le jeu");
            System.out.println("Entrez une direction (H/B/G/D/Q) :");
            String direction = null;
            if ( scanner.hasNext() ){
                direction= scanner.next().toUpperCase();
                if(direction.matches("[HBGD]")) {
                    choixIncorrect=false;
                    return direction;
                }
            }
            System.out.println("Direction invalide.");
            scanner.nextLine();//pour eviter une boucle infinie
            }
        return null;
    }


    private String getCouleurCase(char c) {
        switch (c) {
            case '@': // personnage
                return ANSI_YELLOW + c + ANSI_RESET;
            case 'E': // ecureuil
                return ANSI_BLUE + c + ANSI_RESET; // Par défaut rassasié
            case 'A': // erbre
                return ANSI_GREEN + c + ANSI_RESET;
            case 'B': // buisson
                return ANSI_YELLOW + c + ANSI_RESET;
            case 'G': // gland
                return ANSI_PURPLE + c + ANSI_RESET;
            case 'C': // Champignon
                return ANSI_RED + c + ANSI_RESET;
            default: // Zone vide ou autre caractère
                return ANSI_WHITE + c + ANSI_RESET;
        }
    }

}

