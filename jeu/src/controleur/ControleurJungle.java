package controleur;

import modele.Partie;
import modele.Personnage;
import modele.SansDangerPartieJungle;
import vue.Ihm;

public class ControleurJungle extends Controleur {

    private Partie partie;

    public ControleurJungle() {
        super();
        this.partie = new SansDangerPartieJungle(personnage);
    }

    @Override
    public Partie creerPartie() {
        return new SansDangerPartieJungle(personnage);
    }

    @Override
    public void jouerTour(Partie partie) {
        boolean continuerJeu = true;

        while (continuerJeu) {
            ihm.afficherMessage(partie.toString(carte.getCarte()));
            ihm.afficherMessage("Bonjour");
            int action = ihm.demanderActionJoueur();

            try {
                switch (action) {
                    case 1:
                        String direction = ihm.demanderDirection();
                        partie.deplacerPersonnage(direction);
                        partie.toString(carte.getCarte());
                        ihm.afficherMessage(partie.toString(carte.getCarte()));
                        ihm.afficherMessage("Deplacement effectue.");
                        break;

                    case 2:
                        String positionObjet = ihm.demanderDirection();
                        partie.ramasserObjetPersonnage(positionObjet);
                        ihm.afficherMessage("Objet ramasse.");
                        break;

                    case 3:
                        String positionDeposer = ihm.demanderDirection();
                        String objet = ihm.demanderObjetADeposer(personnage);
                        partie.deposerObjetPersonnage(positionDeposer, objet);
                        ihm.afficherMessage(partie.toString(carte.getCarte()));
                        ihm.afficherMessage("Objet depose.");
                        break;

                    case 4:
                        String positionAnimal = ihm.demanderDirection();
                        partie.frapperAnimalPersonnage(positionAnimal);
                        ihm.afficherMessage(partie.toString(carte.getCarte()));
                        ihm.afficherMessage("Animal frappe.");
                        break;

                    default:
                        ihm.afficherMessage("Vous quittez la partie.");
                        continuerJeu = false;
                        break;

                }
            } catch (Exception e) {
                ihm.afficherMessage("Erreur : " + e.getMessage());
            }
        }
    }
}
