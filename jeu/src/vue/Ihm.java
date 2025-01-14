package vue;

import modele.Carte;
import modele.parties.Partie;
import modele.Personnage;

import java.io.File;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Ihm {




    Carte carte = Carte.getInstance();


    /**
     * demande au joueur de saisir le theme souhaite
     *
     * @return l'entier correpondant au  choix fait par le joueur, -1 si rien
     */
    public int demanderTheme() {
        Scanner scanner = new Scanner(System.in);
        boolean choixIncorrect = true;
        int choix = -1;
        while (choixIncorrect) {
            System.out.println("Choisir un theme:");
            System.out.println(" 1:  foret sans danger");
            System.out.println(" 2:  jungle sans danger");
            System.out.println(" 3:  foret avec danger");
            System.out.println(" 4:  jungle avec danger");
            System.out.println(" 5: Quitter");
            System.out.println("Veuillez saisir le numero correspondant au theme:");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (0 < choix && choix <= 5) {
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
     * demande au joueur de choisir une action
     *
     * @return l'entier qui correspond a l'action demandee par le joueur -1 si rien
     */
    public int demanderActionJoueur() {
        Scanner scanner = new Scanner(System.in);

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

        Scanner scanner = new Scanner(System.in);
        boolean choixIncorrect = true;
        int choix = -1;

        while (choixIncorrect) {
            System.out.println("Pour demarrer une partie, voulez-vous :");
            System.out.println("1 : creer une nouvelle carte");
            System.out.println("2 : charger une carte via un fichier .txt");
            System.out.println("3 : quitter le jeu");
            System.out.println("Veuillez saisir l'entier qui correspond a votre choix.");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (choix >= 1 && choix <= 3) {
                    choixIncorrect = false; // Sort de la boucle si le choix est valide
                } else {
                    System.out.println("Le choix saisi n'est pas dans la plage attendue (1, 2 ou 3). Veuillez reessayer.");
                }
            } else {
                // Gestion des entrées non valides
                System.out.println("L'element saisi n'est pas un entier. Veuillez saisir un entier valide.");
                scanner.nextLine(); // Vider complètement la ligne du buffer
            }
        }


        return choix;

    }

    /**
     * demande au joueur la direction dans laquelle il souhaite se deplacer
     *
     * @return la chaîne de caractères représentant la direction
     */
    public String demanderDirection() {
        Scanner scanner = new Scanner(System.in);

        boolean choixIncorrect = true;
        while (choixIncorrect) {
            System.out.println("Dans quelle direction voulez vous effectuer votre action ?");
            System.out.println("H pour Haut");
            System.out.println("B pour Bas");
            System.out.println("G pour Gauche");
            System.out.println("D pour Droite");
            System.out.println("R pour Revenir en arriere");
            System.out.println("Entrez une direction (H/B/G/D/R) :");
            String direction = null;
            if (scanner.hasNext()) {
                direction = scanner.next().toUpperCase();
                if (direction.matches("[HBGDR]")) {
                    choixIncorrect = false;
                    return direction;
                }
            }
            System.out.println("Direction invalide.");
            scanner.nextLine();

        }
        return null;
    }



    /**
     * Demande au joueur de saisir le chemin d'un fichier à charger.
     *
     * @return le chemin du fichier ou 'q' si l'utilisateur choisit de quitter.
     */
    public String demanderFichier() {
        Scanner scanner = new Scanner(System.in);

        String cheminFichier = "";
        boolean cheminValide = false;

        /*
         Si un residu de ligne existe dans le tampon avant l'appel à cette méthode, il sera consomme.
          Cela evite que la boucle s'execute immédiatement sans attendre une nouvelle saisie.
         */


        while (!cheminValide) {
            System.out.println("Veuillez saisir le chemin du fichier (ou 'q' pour quitter) :");
            cheminFichier = scanner.nextLine().trim();

            if (cheminFichier.equalsIgnoreCase("q")) {
                System.out.println("Vous avez choisi de quitter.");
                return cheminFichier;  // Si l'utilisateur veut quitter
            }

            File fichier = new File(cheminFichier);
            if (fichier.exists() && fichier.isFile()) {
                cheminValide = true;
                return cheminFichier;
            } else {
                System.out.println("Le fichier n'existe pas ou le chemin est incorrect. Veuillez réessayer.");
            }
        }

        return "q";
    }


    /**
     * Demande au joueur de saisir une coordonnée pour la carte.
     *
     * @param message le message à afficher pour guider le joueur dans sa saisie.
     * @return l'entier représentant la coordonnée choisie par le joueur.
     */
    public int demanderCoordonnes(String message) {
        Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Entrée invalide. Veuillez saisir un entier positif ou 0 pour quitter.");
                    scanner.nextLine();
                }
            }
        }
        return coordonne;
    }

    /**
     * Affiche un message à l'utilisateur.
     *
     * @param message le message à afficher.
     */
    public void afficherMessage(String message){
        System.out.println(message);
    }


    /**
     * Demande au joueur quel objet de son inventaire il souhaite déposer.
     *
     * @param p l'objet `Personnage` représentant le joueur et son inventaire.
     * @return le nom de l'objet à déposer ou "R" si le joueur souhaite revenir en arrière.
     */
    public String demanderObjetADeposer(Personnage p){
        Scanner scanner = new Scanner(System.in);

        boolean choixIncorrect = true;
        String res = "";
        for(String item : p.getInventaire().keySet()){
            res += item + " en poche : " + p.getInventaire().get(item) + '\n';
        }
        res+= "\nR pour revenir en arriere";
        System.out.println(res);
        while(choixIncorrect) {
            if (scanner.hasNext()) {
                String reponse = scanner.next().toUpperCase();
                if (reponse.equals("R")){
                    return "R";
                }
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

    /**
     * Affiche un message d'erreur suivi d'un délai d'attente de 1 seconde, puis affiche l'état actuel de la partie.
     *
     * @param e l'exception qui a été levée.
     * @param partie l'objet `Partie` représentant l'état du jeu à afficher après l'erreur.
     */
    public void afficherAvecSleep(Exception e, Partie partie){
        afficherMessage("Erreur : " + e.getMessage());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException f){
            System.out.println("le temps d'attente est interrompu");
        }
        afficherMessage(partie.toString());
    }


}
