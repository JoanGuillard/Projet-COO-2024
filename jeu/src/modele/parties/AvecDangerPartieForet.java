package modele.parties;

import modele.ElementCarte;
import modele.Personnage;
import modele.PierrePrecieuse;
import modele.animaux.Ecureuil;
import modele.predateurs.Hiboux;
import modele.predateurs.Predateur;
import modele.predateurs.Renard;

import java.util.ArrayList;
import java.util.Random;

import static modele.CouleursAffichage.*;

public class AvecDangerPartieForet extends Partie{


    public AvecDangerPartieForet(Personnage personnage,String bordure) {
        super(personnage,bordure);
        getPersonnage().setInventaire("C");
        getPersonnage().setInventaire("G");
        getPersonnage().setInventaire("M");
        this.setBordure("A");
    }

    @Override
    public String afficherElement(ElementCarte e) {
        switch(e.getApparence()){
            case "C":
                return ANSI_WHITE_BACKGROUND + ANSI_BLACK + e.getApparence() + ANSI_RESET;
            case "G":
                return ANSI_RED_BACKGROUND + ANSI_BLACK + e.getApparence() + ANSI_RESET;
            case "A", "B":
                return ANSI_BLACK_BACKGROUND + ANSI_GREEN + e.getApparence() + ANSI_RESET;
            case "@":
                return e.getApparence();
            case "E":
                return e.toString();
            case "M":
                return ANSI_BLACK_BACKGROUND + ANSI_PURPLE + e.getApparence() + ANSI_RESET;
            case "R":
                return ANSI_BLACK_BACKGROUND + ANSI_RED + e.getApparence() + ANSI_RESET;
            case "H":
                return e.toString();
            default:
                return ANSI_GREEN_BACKGROUND + e.getApparence() + ANSI_RESET;
        }
    }



    @Override
    protected String genererElementAleatoire(Random random) {
        int chance = random.nextInt(100);

        if (chance < 88) return " ";
        else if (chance < 90) return "C";
        else if (chance < 91) return "H";
        else if (chance < 92) return "B";
        else if (chance < 93) return "R";
        else if (chance < 94) return "E";
        else if (chance < 95) return "A";
        else if (chance < 96) return "M";
        else if (chance < 97) return "2";
        else if (chance < 98) return "3";

        else   return "G";
    }



    @Override
    public ElementCarte ajouterElementCarte(String element, int abscisse, int ordonnee) {
        switch(element){
            case "@":
                getPersonnage().nouvellePosition(abscisse,ordonnee);
                //enregistrerPosition(getPersonnage());
                return getPersonnage();
            case "E":
                Ecureuil e = new Ecureuil(abscisse,ordonnee,1,5);
                getLesAnimaux().add(e);
                //enregistrerPosition(e);
                return e;
            case "R":
                Renard r = new Renard("R",abscisse,ordonnee);
                getLesPredateurs().add(r);
                //enregistrerPosition(r);
                return r;
            case "H":
                Hiboux h = new Hiboux("H",abscisse,ordonnee);
                getLesPredateurs().add(h);
                //enregistrerPosition(h);
                return h;
            case "2":
                PierrePrecieuse p2 = new PierrePrecieuse(2);
                return p2;
            case "3":
                PierrePrecieuse p3 = new PierrePrecieuse(3);
                return p3;
            default:
                return new ElementCarte(element);
        }
    }

    @Override
    public boolean estNourriture(String element) {
        switch (element){
            case "C", "M", "G":
                return true;
            default :
                return false;
        }
    }
}
