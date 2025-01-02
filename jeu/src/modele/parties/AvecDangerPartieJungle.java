package modele.parties;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Singe;
import modele.predateurs.Predateur;
import modele.predateurs.Scorpion;
import modele.predateurs.Serpent;

import java.util.ArrayList;
import java.util.Random;

import static modele.CouleursAffichage.*;

public class AvecDangerPartieJungle extends Partie{
    public AvecDangerPartieJungle(Personnage personnage,String bordure) {
        super(personnage,bordure);
        getPersonnage().setInventaire("C");
        getPersonnage().setInventaire("B");
        getPersonnage().setInventaire("M");

    }

    @Override
    public String afficherElement(ElementCarte e) {
        switch(e.getApparence()){
            //banane
            case "B":
                return ANSI_YELLOW_BACKGROUND + ANSI_BLACK + e.getApparence() + ANSI_RESET;
            //champignon
            case "C":
                return ANSI_WHITE_BACKGROUND + ANSI_BLACK + e.getApparence() + ANSI_RESET;
            //singe
            case "S":
                return e.toString();
            //personnage
            case "@":
                return getPersonnage().getApparence();
            //rocher
            case "P":
                return ANSI_CYAN_BACKGROUND + ANSI_BLACK + e.getApparence() + ANSI_RESET;
            //cocotier
            case "T":
                return ANSI_BLACK_BACKGROUND + ANSI_GREEN + e.getApparence() + ANSI_RESET;
            case "O":
                return ANSI_BLACK_BACKGROUND + ANSI_RED + e.getApparence() + ANSI_RESET;
            case "E":
                return ANSI_PURPLE_BACKGROUND + ANSI_CYAN + e.getApparence() + ANSI_RESET;

            default:
                return ANSI_GREEN_BACKGROUND + e.getApparence() + ANSI_RESET;
        }
    }



    @Override
    protected String genererElementAleatoire(Random random) {
        int chance = random.nextInt(100);
        if (chance < 87) return " ";
        else if (chance <89) return  "S";//Singe
        else if (chance < 91) return "C"; //champignon normale
        else if (chance <92 ) return "E"; //serpent
        else if (chance < 94) return "T"; //cocotier
        else if (chance < 95) return "M"; //champignon hallu
        else if (chance < 96) return "O"; //scorpion
        else if (chance < 97) return "P"; //petit rocher



        else return "B"; //banane
    }



    @Override
    public ElementCarte ajouterElementCarte(String element, int abscisse, int ordonnee) {
        switch (element) {
            case "S":
                Singe s = new Singe(abscisse, ordonnee, 2, 3);
                getLesAnimaux().add(s);
                return s;
            case"O":
                Predateur o = new Scorpion("O",abscisse,ordonnee);
                getLesPredateurs().add(o);
                return  o;
            case "E":
                Predateur e = new Serpent("E",abscisse,ordonnee);
                getLesPredateurs().add(e);
                return e;
            case "@":
                getPersonnage().nouvellePosition(abscisse, ordonnee);
                return getPersonnage();
            default:
                return new ElementCarte(element);
        }
    }

    @Override
    public boolean estNourriture(String element) {
        switch (element) {
            case "B" , "C","M":
                return true;
            default:
                return false;
        }
    }
}
