package modele;

import static modele.CouleursAffichage.*;

public class PierrePrecieuse extends ElementCarte {
    private int nbTourArriere;

    public PierrePrecieuse(String apparence, int abscisse, int ordonnee, int nbTourArriere) {
        super(apparence);
        this.nouvellePosition(abscisse, ordonnee);
        this.nbTourArriere = nbTourArriere;
    }

    public int getNbTourArriere() {
        return nbTourArriere;
    }

    public void setNbTourArriere(int nbTourArriere) {
        this.nbTourArriere = nbTourArriere;
    }

    @Override
    public String toString() {
        return ANSI_YELLOW + getApparence() + ANSI_RESET;
    }
}
