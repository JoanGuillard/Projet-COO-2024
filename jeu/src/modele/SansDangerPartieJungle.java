package modele;
import static modele.CouleursAffichage.*;

public class SansDangerPartieJungle extends Partie {

    public SansDangerPartieJungle(Personnage personnage) {
        super(personnage);
        getPersonnage().setInventaire("B");
        getPersonnage().setInventaire("C");
    }

    @Override
    public ElementCarte ajouterElementCarte(String element, int abscisse, int ordonnee) {
        switch (element) {
            case "B":
                return new ElementCarte(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + element + ANSI_RESET);
            case "C":
                return new ElementCarte(ANSI_GREEN_BACKGROUND + ANSI_WHITE + element + ANSI_RESET);
            case "S":
                return new Singe(abscisse, ordonnee, 2, 3);
            case "@":
                getPersonnage().nouvellePosition(abscisse, ordonnee);
                return getPersonnage();
            case " ": // Zone vide
                return new ElementCarte(ANSI_GREEN_BACKGROUND + " " + ANSI_RESET);
            default:
                return new ElementCarte(ANSI_GREEN_BACKGROUND + element + ANSI_RESET);
        }
    }

    @Override
    public boolean estNourriture(String element) {
        switch (element) {
            case "B":
            case "C":
                return true;
            default:
                return false;
        }
    }

}
