package modele;

public class PierrePrecieuse extends ElementCarte{
    private int nbTours;
    public PierrePrecieuse(int nbTours) {
        this.nbTours = nbTours;
        if(nbTours==0){
            this.setApparence("k");
        }
        this.setApparence(String.valueOf(nbTours));
    }
    public int getNbTours() {
        return nbTours;
    }


}
