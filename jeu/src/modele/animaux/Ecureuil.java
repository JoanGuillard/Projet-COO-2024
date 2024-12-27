package modele.animaux;

public class Ecureuil extends Animal{


    public Ecureuil(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence("E");
        this.getRegimeAlimentaire().add("C");
        this.getRegimeAlimentaire().add("M");
        this.getRegimeAlimentaire().add("G");
        this.setNbTourJunkie(5);
        this.setNbTourCache(3);
    }


}
