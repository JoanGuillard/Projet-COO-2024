package modele;

public class PierrePrecieuse extends ElementCarte{
    private int nbTours;
    public PierrePrecieuse(int nbTours) {
        this.nbTours = nbTours;
        this.setApparence(String.valueOf(nbTours));
    }
    public int getNbTours() {
        return nbTours;
    }


}
