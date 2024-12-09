package controleur;

import modele.*;
import vue.Ihm;

public class Controleur {
    private boolean arreterJeu;
    private Ihm ihm;
    private Personnage personnage;
    private Carte carte;

    public Controleur() {
        this.arreterJeu = false;
        this.ihm = new Ihm();
        this.personnage = new Personnage();
        this.carte = Carte.getInstance();
    }

    public void jouerPartie() {
        while (!arreterJeu) {
            int choixCreation = ihm.demanderCreationCarte();

            if (choixCreation == 1) {
                int choixTheme = ihm.demanderTheme();
                Partie partie;

                switch (choixTheme) {
                    case 1:
                        partie = new SansDangerPartieForet(personnage);
                        if (!creerNouvelleCarte(partie)) {
                            ihm.afficherMessage("Le jeu est fini.");
                            arreterJeu = true;
                            break;
                        } else {
                            jouerTour(partie);
                        }
                        break;

                    case 2:
                        partie = new SansDangerPartieJungle(personnage);
                        if (!creerNouvelleCarte(partie)) {
                            ihm.afficherMessage("Le jeu est fini.");
                            arreterJeu = true;
                            break;
                        } else {
                            jouerTour(partie);
                        }
                        break;

                    default:
                        ihm.afficherMessage("Le jeu est fini.");
                        arreterJeu = true;
                        break;
                }
            } else if (choixCreation == 2) {
                int theme = ihm.demanderTheme();
                String cheminFichier = ihm.demanderFichier();
                Partie partie;
                if (cheminFichier.equals("q")) {
                    ihm.afficherMessage("Le jeu est fini.");
                    arreterJeu = true;
                } else {
                    if (theme==0) {
                        ihm.afficherMessage("Le jeu est fini.");
                        arreterJeu=true;
                        break;
                    } else if (theme==1){
                        partie = new SansDangerPartieForet(personnage);
                    }else{
                        partie = new SansDangerPartieJungle(personnage);
                    }
                    partie.chargerCarte(cheminFichier);
                    jouerTour(partie);
                }
            }
            arreterJeu=true;
            }
        }


    private boolean creerNouvelleCarte(Partie partie) {
        int largeur = ihm.demanderCoordonnes("largeur");
        if (largeur == 0) return false;

        int hauteur = ihm.demanderCoordonnes("hauteur");
        if (hauteur == 0) return false;

        //partie.creerNouvelleCarte(largeur, hauteur);
        return true;
    }
    private void jouerTour(Partie partie) {
       /* boolean continuerTour = true;

        while (continuerTour && !arreterJeu) {
            // Afficher la carte actuelle
            ihm.afficherCarte(carte.getCarte());
            ihm.afficherMessage("C'est à vous de jouer !");

            // Demander au joueur de choisir une action
            int choixAction = ihm.demanderActionJoueur();

            switch (choixAction) {
                case 1:
                    // Déplacement du personnage
                    String direction = ihm.demanderDirection();
                    if (direction.equals("Q")) {
                        arreterJeu = true;
                        ihm.afficherMessage("Vous avez choisi de quitter le jeu. Merci d'avoir joué !");
                    } else {
                        partie.deplacerPersonnage(direction);
                        ihm.afficherMessage("Vous vous êtes déplacé vers : " + direction);
                    }
                    break;

                case 2:
                    // Ramasser un objet
                    partie.ramasserObjet();
                    ihm.afficherMessage("Vous avez ramassé un objet.");
                    break;

                case 3:
                    // Poser un objet
                    partie.deposerObjetPersonnage();
                    ihm.afficherMessage("Vous avez posé un objet.");
                    break;

                case 4:
                    // Interagir avec un animal
                    partie.frapperAnimalPersonnage(//coordonnee de l'animal);
                    ihm.afficherMessage("Vous avez interagi avec un animal.");
                    break;

                case 5:
                    // Quitter le jeu
                    arreterJeu = true;
                    ihm.afficherMessage("Vous avez choisi de quitter le jeu. Merci d'avoir joué !");
                    break;

                default:
                    ihm.afficherMessage("Action non reconnue. Veuillez réessayer.");
                    break;
            }

            // Afficher la carte modifiée après chaque action
            ihm.afficherCarte(partie.getCarte());
        }*/
        System.out.println("le jeu commence");
    }

}
