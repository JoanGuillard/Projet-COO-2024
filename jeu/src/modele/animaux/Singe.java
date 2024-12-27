package modele.animaux;

public class Singe extends Animal{
    public Singe(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence("S");
        this.getRegimeAlimentaire().add("C");
        this.getRegimeAlimentaire().add("B");
    }
}
