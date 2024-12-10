package controleur;

import modele.Carte;
import modele.Partie;
import modele.Personnage;
import modele.SansDangerPartieForet;
import modele.SansDangerPartieJungle;
import vue.Ihm;

import java.util.Random;

public class Controleur {
    protected Ihm ihm;
    protected Carte carte;
    protected Personnage personnage;

    public Controleur(Ihm ihm) {
        this.ihm = ihm;
        this.carte = Carte.getInstance();
        this.personnage = new Personnage();
    }

    public void lancerPartie() {
        ihm.afficherMessage("Bienvenue dans le jeu !");
        int choixTheme = ihm.demanderTheme();

        Controleur controleurSpecifique = null;
        Partie partie;
        switch (choixTheme) {
            case 1:
                partie = new SansDangerPartieForet(personnage);
                controleurSpecifique = new ControleurForet(this,partie);
                break;

            case 2:
                partie = new SansDangerPartieJungle(personnage);
                controleurSpecifique = new ControleurJungle( this,partie);
                break;

            default:
                ihm.afficherMessage("Le jeu est termine.");
                return;
        }

        if (controleurSpecifique != null) {
            if (controleurSpecifique.jouerPartie(partie)) {
                controleurSpecifique.jouerTour(partie);
            }else{
                ihm.afficherMessage("le jeu se termine");
            }
        }
    }

    public boolean jouerPartie(Partie partie) {
        ihm.afficherMessage("Configuration de la partie...");
        int choixCreation = ihm.demanderCreationCarte();

        switch (choixCreation) {
            case 1:
                 partie.initialiserCarte(
                        ihm.demanderCoordonnes("ordonnee"),
                        ihm.demanderCoordonnes("abscisse")
                );
                return true;

            case 2:
                partie.chargerCarte(ihm.demanderFichier());
                return true;

            default:
                return false;

        }

    }
    public  void jouerTour(Partie partie){

    }
}
