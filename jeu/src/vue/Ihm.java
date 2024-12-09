package vue;

import modele.Carte;
import modele.Personnage;

import java.io.File;

import java.util.Scanner;

public class Ihm {

    private final Scanner scanner;

    public Ihm() {
        this.scanner = new Scanner(System.in);
    }
    Carte carte = Carte.getInstance();


    /**
     * demande au joueur de saisir le theme souhaite
     *
     * @return l'entier correpondant au  choix fait par le joueur, -1 si rien
     */
    public int demanderTheme() {

        boolean choixIncorrect = true;
        int choix = -1;
        while (choixIncorrect) {
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
     * demande au joueur de choisir un action
     *
     * @return l'entier qui correspond a l'action demandee par le joueur -1 si rien
     */
    public int demanderActionJoueur() {
        boolean choixIncorrect = true;
        int choix = -1;
        while (choixIncorrect) {
            System.out.println("Veuillez choisir un action:");
            System.out.println("1 : Se deplacer");
            System.out.println("2 : Ramasser un objet");
            System.out.println("3 : Poser un objet");
            System.out.println("4 : Interagir avec un animal");
            System.out.println("5 : Quitter le jeu");
            System.out.println();
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (0 < choix && choix <= 5) {
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
     * demande au joueur s'il souhaite charger une carte ou creer une
     *
     * @return l'entier correspondant au choix,-1 pour quitter ou aucun choix
     */
    public int demanderCreationCarte() {
        boolean choixIncorrect = false;
        int choix = 0;
        while (!choixIncorrect) {
            System.out.println("Pour demarrer une partie,voulez vous:");
            System.out.println("1 : creer une nouvelle carte");
            System.out.println("2 : charger une carte via un fichier .txt");
            System.out.println("3 : quitter le jeu");
            System.out.println("Veuillez saisir l'entier qui correspond a votre choix.");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (0 <= choix && choix <= 3) {
                    choixIncorrect = true;
                    return choix;
                }
            }
            System.out.println("L'entier que vous avez choisi ne correspond a aucun choix.Veuillez saisir un entier valide.");
            scanner.nextLine();
        }
        return choix;
    }

    /**
     * demande au joueur la direction dans laquelle il souhaite se deplacer
     *
     * @return la chaîne de caractères représentant la direction
     */
    public String demanderDirection() {
        boolean choixIncorrect = true;
        while (choixIncorrect) {
            System.out.println("Dans quelle direction voulez vous se deplacer ?");
            System.out.println("H pour Haut");
            System.out.println("B pour Bas");
            System.out.println("G pour Gauche");
            System.out.println("D pour Droite");
            System.out.println("Q pour Quitter le jeu");
            System.out.println("Entrez une direction (H/B/G/D/Q) :");
            String direction = null;
            if (scanner.hasNext()) {
                direction = scanner.next().toUpperCase();
                if (direction.matches("[HBGD]")) {
                    choixIncorrect = false;
                    return direction;
                }
            }
            System.out.println("Direction invalide.");
            scanner.nextLine();//pour eviter une boucle infinie
        }
        return null;
    }




    public String demanderFichier() {
        String cheminFichier = "";
        boolean cheminValide = false;

        while (!cheminValide) {
            System.out.println("Veuillez saisir le chemin du fichier (ou 'q' pour quitter) :");
            scanner.nextLine();
            cheminFichier = scanner.nextLine().trim();  // Utilisation de nextLine() pour récupérer toute la ligne saisie
            if (cheminFichier.equalsIgnoreCase("q")) {
                System.out.println("Vous avez choisi de quitter.");
                return cheminFichier;
            }
            File fichier = new File(cheminFichier);
            if (fichier.exists() && fichier.isFile()) {
                cheminValide = true;
                break;
            } else {
                System.out.println("Le fichier n'existe pas ou le chemin est incorrect. Veuillez réessayer.");
            }
        }

        return cheminFichier;
    }



    public int demanderCoordonnes(String message) {
        boolean choixCorrecte = false;
        int coordonne = 0;
        while (!choixCorrecte) {
            System.out.println("Veuillez saisir la " + message + " souhaitee pour la carte (un entier positif ou 0 pour quitter) :");
            if (scanner.hasNextInt()) {
                coordonne = scanner.nextInt();
                if (coordonne > 0) {
                    choixCorrecte = true;
                } else if (coordonne == 0) {
                    System.out.println("Vous avez choisi de quitter.");
                    return coordonne;
                } else {
                    System.out.println("La " + message + " doit être strictement positive.");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez saisir un entier positif ou 0 pour quitter.");
                scanner.nextLine();
            }
        }
        return coordonne;
    }
    public void afficherMessage(String message){
        System.out.println(message);
    }

    public String demanderObjetADeposer(Personnage p){
        boolean choixIncorrect = true;
        String res = "";
        for(String item : p.getInventaire().keySet()){
            res += item + " en poche : " + p.getInventaire().get(item) + '\n';
        }
        System.out.println(res);
        while(choixIncorrect) {
            if (scanner.hasNext()) {
                String reponse = scanner.next().toUpperCase();
                if(p.isInInventaire(reponse)){
                    choixIncorrect = false;
                    return reponse;
                }else{
                    System.out.println("Veuillez saisir un élément présent dans votre inventaire");
                }
            }
        }
        return "";
    }

}
