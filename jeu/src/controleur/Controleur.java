package controleur;

import modele.*;
import vue.Ihm;

public abstract class Controleur {
    protected Ihm ihm;
    protected Personnage personnage;
    protected Carte carte;

    public Controleur() {
        this.ihm = new Ihm();
        this.personnage = new Personnage();
        this.carte = Carte.getInstance();
    }

    public void lancerPartie() {

            int choixTheme = ihm.demanderTheme();
            Partie partie;
            Controleur controleur;

            switch (choixTheme) {
                case 1:
                    controleur = new ControleurForet();
                    partie = controleur.creerPartie();
                    controleur.jouerPartie(partie,controleur);
                    break;
                case 2:
                    controleur = new ControleurJungle();
                    partie = controleur.creerPartie();
                    controleur.jouerPartie(partie,controleur);
                    break;
                default:
                    ihm.afficherMessage("Le jeu est fini.");
                    break;

        }
    }

    public abstract Partie creerPartie();
    public abstract void jouerTour(Partie partie);

    public void jouerPartie(Partie partie,Controleur controleur) {

            int choixCreation = ihm.demanderCreationCarte();
            switch (choixCreation) {
                case 1:
                    partie.creerNouvelleCarte(ihm.demanderCoordonnes("abscisse"), ihm.demanderCoordonnes("ordonnee"));
                    controleur.jouerTour(partie);
                    break;
                case 2:
                    partie.chargerCarte(ihm.demanderFichier());
                    controleur.jouerTour(partie);
                    break;
                default:
                    break;
            }
        }
    }

