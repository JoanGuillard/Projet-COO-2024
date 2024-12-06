package modele;
import static modele.CouleursAffichage.*;
public class SansDangerPartieForet extends Partie{
    public SansDangerPartieForet(Personnage personnage) {
        super(personnage);
        getPersonnage().setInventaire("C");
        getPersonnage().setInventaire("G");
    }

    @Override
    public ElementCarte ajouterElementCarte(String element,int abscisse, int ordonnee) {
        switch(element){
            case "C":
                return new ElementCarte(ANSI_WHITE_BACKGROUND + ANSI_BLACK + element + ANSI_RESET);
            case "G":
                return new ElementCarte(ANSI_RED_BACKGROUND + ANSI_BLACK + element + ANSI_RESET);
            case "A", "B":
                return new ElementCarte(ANSI_BLACK_BACKGROUND + ANSI_GREEN + element + ANSI_RESET);
            case "@":
                getPersonnage().nouvellePosition(abscisse,ordonnee);
                return  getPersonnage();
            case "E":
                return new Ecureuil(abscisse,ordonnee,1,5);
            default:
                return new ElementCarte(ANSI_GREEN_BACKGROUND + element + ANSI_RESET);
        }
    }

    @Override
    public boolean estNourriture(String element) {
        switch (element){
            case "C","G":
                return true;
            default:
                return false;
        }
    }



}
