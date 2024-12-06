package modele;
import static modele.CouleursAffichage.*;
public class Ecureuil extends Animal{


    public Ecureuil(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence(ANSI_BLACK_BACKGROUND + ANSI_BLUE + "E" + ANSI_RESET);
    }
}
