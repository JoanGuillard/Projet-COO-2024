package modele.parties;

import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Ecureuil;
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
                return ANSI_PURPLE_BACKGROUND + ANSI_BLACK + e.getApparence() + ANSI_RESET;

            default:
                return ANSI_GREEN_BACKGROUND + e.getApparence() + ANSI_RESET;
        }
    }



    @Override
    protected String genererElementAleatoire(Random random) {
        return null;
    }



    @Override
    public ElementCarte ajouterElementCarte(String element, int abscisse, int ordonnee) {
        switch(element){
            case "@":
                getPersonnage().nouvellePosition(abscisse,ordonnee);
                return getPersonnage();
            case "E":
                Ecureuil e = new Ecureuil(abscisse,ordonnee,1,5);
                getLesAnimaux().add(e);
                return e;
            case "R":
                Renard r = new Renard("R",abscisse,ordonnee);
            default:
                return new ElementCarte(element);
        }
    }

    @Override
    public boolean estNourriture(String element) {
        return false;
    }
}
