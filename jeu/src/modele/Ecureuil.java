package modele;
import static modele.CouleursAffichage.*;
public class Ecureuil extends Animal{


    public Ecureuil(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence("E" );
        this.getRegimeAlimentaire().add("C");
        this.getRegimeAlimentaire().add("G");

    }
}
