package modele.predateurs;

import modele.Carte;
import modele.animaux.Singe;
import modele.predateurs.Predateur;

public class Scorpion extends Predateur {

    private int toursCacheSousRocher = 0;

    public Scorpion(String apparence, int abscisse, int ordonnee) {
        super(apparence, abscisse, ordonnee);
    }

    @Override
    public void seDeplacer(Carte carte) {
        int abscisseScorpion = this.getAbscisse();
        int ordonneeScorpion = this.getOrdonnee();

        if (toursCacheSousRocher > 0) {
            toursCacheSousRocher--;
            return;
        }

        if (carte.verifierCase(abscisseScorpion - 1, ordonneeScorpion, "S")) {
            tuerSinge(carte, abscisseScorpion - 1, ordonneeScorpion);
        } else if (carte.verifierCase(abscisseScorpion + 1, ordonneeScorpion, "S")) {
            tuerSinge(carte, abscisseScorpion + 1, ordonneeScorpion);
        } else if (carte.verifierCase(abscisseScorpion, ordonneeScorpion - 1, "S")) {
            tuerSinge(carte, abscisseScorpion, ordonneeScorpion - 1);
        } else if (carte.verifierCase(abscisseScorpion, ordonneeScorpion + 1, "S")) {
            tuerSinge(carte, abscisseScorpion, ordonneeScorpion + 1);
        } else {
            deplacementAleatoire(carte);
        }
    }

    private void tuerSinge(Carte carte, int x, int y) {
        Singe singe = (Singe) carte.getCase(x, y);
        singe.setEstMort(true);
        this.nouvellePosition(x, y);

        if (carte.verifierCase(x, y, "P")) {
            toursCacheSousRocher = 5;
        }
    }
}
