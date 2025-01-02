package modele.predateurs;

import modele.Carte;
import modele.animaux.Singe;

public class Serpent extends Predateur {

    private int toursDigestion = 0;

    public Serpent(String apparence, int abscisse, int ordonnee) {
        super(apparence, abscisse, ordonnee);
    }

    @Override
    public void seDeplacer(Carte carte) {
        if (toursDigestion > 0) {
            toursDigestion--;
            return;
        }

        int abscisseSerpent = this.getAbscisse();
        int ordonneeSerpent = this.getOrdonnee();

        if (carte.verifierCase(abscisseSerpent - 1, ordonneeSerpent, "S")) {
            attaquerSinge(carte, abscisseSerpent - 1, ordonneeSerpent);
        } else if (carte.verifierCase(abscisseSerpent + 1, ordonneeSerpent, "S")) {
            attaquerSinge(carte, abscisseSerpent + 1, ordonneeSerpent);
        } else if (carte.verifierCase(abscisseSerpent, ordonneeSerpent - 1, "S")) {
            attaquerSinge(carte, abscisseSerpent, ordonneeSerpent - 1);
        } else if (carte.verifierCase(abscisseSerpent, ordonneeSerpent + 1, "S")) {
            attaquerSinge(carte, abscisseSerpent, ordonneeSerpent + 1);
        } else {
            deplacementAleatoireDeuxCases(carte,"E");
        }
    }

    private void attaquerSinge(Carte carte, int x, int y) {
        Singe singe = (Singe) carte.getCase(x, y);
        singe.setEstMort(true);
        this.nouvellePosition(x, y);

        toursDigestion = 3;
    }

    private void deplacementAleatoireDeuxCases(Carte carte,String element) {
        int abscisse = this.getAbscisse();
        int ordonnee = this.getOrdonnee();

        int deltaX = (int) (Math.random() * 5) - 2;
        int deltaY = (int) (Math.random() * 5) - 2;

        int nouvelleAbscisse = abscisse + deltaX;
        int nouvelleOrdonnee = ordonnee + deltaY;

        if (carte.verifierCase(nouvelleAbscisse, nouvelleOrdonnee,element) &&
                carte.estCaseVide(nouvelleAbscisse, nouvelleOrdonnee)) {
            this.nouvellePosition(nouvelleAbscisse, nouvelleOrdonnee);

        }

    }
}
