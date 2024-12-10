/*package controleur;

import modele.Carte;
import modele.Partie;
import modele.SansDangerPartieJungle;

public class ControleurJungle extends Controleur {
    private Partie partie;
    public ControleurJungle(Controleur controleurPrincipal,Partie partie) {
        super(controleurPrincipal.ihm);
        this.partie=partie;
        this.personnage = controleurPrincipal.personnage;
    }

    public void jouerTour(Partie partie) {
        boolean continuerJeu = true;

        while (continuerJeu) {
            ihm.afficherMessage(partie.toString());
            ihm.afficherMessage("Voici une partie Jungle");
            int action = ihm.demanderActionJoueur();

            try {
                switch (action) {
                    case 1:
                        String direction = ihm.demanderDirection();
                        partie.deplacerPersonnage(direction);
                        ihm.afficherMessage(partie.toString());
                        ihm.afficherMessage(partie.toString());
                        ihm.afficherMessage("Deplacement effectue.");
                        break;

                    case 2:
                        String positionObjet = ihm.demanderDirection();
                        partie.ramasserObjetPersonnage(positionObjet);
                        ihm.afficherMessage("Objet ramasse !");
                        break;

                    case 3:
                        String objet = ihm.demanderObjetADeposer(personnage);
                        String positionDeposer = ihm.demanderDirection();
                        partie.deposerObjetPersonnage(positionDeposer, objet);
                        ihm.afficherMessage(partie.toString());
                        ihm.afficherMessage("Objet depose.");
                        break;

                    case 4:
                        String positionAnimal = ihm.demanderDirection();
                        partie.frapperAnimalPersonnage(positionAnimal);
                        ihm.afficherMessage(partie.toString());
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
*/