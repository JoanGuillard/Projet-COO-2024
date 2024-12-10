/*package controleur;

import modele.Carte;
import modele.Partie;
import modele.SansDangerPartieForet;

public class ControleurForet extends Controleur {
    private Partie partie;

    public ControleurForet(Controleur controleurPrincipal,Partie partie) {
        super(controleurPrincipal.ihm);
        this.personnage = controleurPrincipal.personnage;
        this.partie=partie;
    }

    public void jouerTour(Partie partie) {
        boolean continuerJeu = true;
        ihm.afficherMessage(partie.toString());
        while (continuerJeu) {
            int action = ihm.demanderActionJoueur();

            try {
                switch (action) {
                    case 1:
                        String direction = ihm.demanderDirection();
                        partie.deplacerPersonnage(direction);
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