package modele.predateurs;

import modele.Carte;
import modele.ElementCarte;
import modele.animaux.Singe;


import static modele.CouleursAffichage.*;
import static modele.CouleursAffichage.ANSI_RESET;

public class Scorpion extends Predateur {

    private int toursCacheSousRocher = 0;

    private int tourApresMeurtre = 0;
    private boolean estCache = false;


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
        if (estCache) {
            carte.setCase(getAbscisse(), getOrdonnee(), new ElementCarte("R"));
            estCache = false;
        } else {
            carte.setCase(getAbscisse(), getOrdonnee(), new ElementCarte(" "));
        }
        if(tourApresMeurtre ==0) {
            if (carte.verifierCase(abscisseScorpion - 1, ordonneeScorpion, "S")) {
                tuerSinge(carte, abscisseScorpion - 1, ordonneeScorpion);
            } else if (carte.verifierCase(abscisseScorpion + 1, ordonneeScorpion, "S")) {
                tuerSinge(carte, abscisseScorpion + 1, ordonneeScorpion);
            } else if (carte.verifierCase(abscisseScorpion, ordonneeScorpion, "S")) {
                tuerSinge(carte, abscisseScorpion, ordonneeScorpion);
            } else if (carte.verifierCase(abscisseScorpion, ordonneeScorpion - 1, "S")) {
                tuerSinge(carte, abscisseScorpion, ordonneeScorpion - 1);
            } else if (carte.verifierCase(abscisseScorpion, ordonneeScorpion + 1, "S")) {
                tuerSinge(carte, abscisseScorpion, ordonneeScorpion + 1);
            }else{
                deplacementAleatoire(carte, 1);
            }
        }else {
            deplacementAleatoire(carte, 1);
            tourApresMeurtre--;
        }

        carte.setCase(getAbscisse(), getOrdonnee(), this);

    }

/**
 * Tue un singe sur la position spécifiée et met à jour l'état du scorpion.
 *
 * @param carte La carte sur laquelle le meurtre se produit.
 * @param x     Coordonnée x de la position du singe.
 * @param y     Coordonnée y de la position du singe.
 */
    private void tuerSinge(Carte carte, int x, int y) {
        Singe singe = (Singe) carte.getCase(x, y);
        if(!singe.isEstCache() || singe.getCachette().equals("R")){
            singe.setEstMort(true);
            carte.setCase(x,y,new ElementCarte(singe.getCachette()));
            this.nouvellePosition(x, y);
            tourApresMeurtre=2;
            if (carte.verifierCase(x, y, "R")) {
                toursCacheSousRocher = 5;
                estCache =true;
            }
        }else{
            deplacementAleatoire(carte, 1);
        }


    }

    public String toString(){
        if(toursCacheSousRocher > 0){
            return ANSI_BLACK_BACKGROUND + ANSI_BLUE + getApparence() + ANSI_RESET;
        }
        else if(tourApresMeurtre > 0){
            return ANSI_BLACK_BACKGROUND + ANSI_GREEN + getApparence() + ANSI_RESET;
        }else{
            return ANSI_BLACK_BACKGROUND + ANSI_RED + getApparence() + ANSI_RESET;
        }
    }
}
