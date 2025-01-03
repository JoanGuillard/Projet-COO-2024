package modele.parties;

import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Singe;
import modele.predateurs.Predateur;
import modele.predateurs.Scorpion;
import modele.predateurs.Serpent;
import java.util.Random;
import static modele.CouleursAffichage.*;

public class AvecDangerPartieJungle extends Partie{

    public AvecDangerPartieJungle(Personnage personnage,String bordure) {
        super(personnage,bordure);
        getPersonnage().setInventaire("C");
        getPersonnage().setInventaire("B");
        getPersonnage().setInventaire("H");


    }

    @Override
    public String afficherElement(ElementCarte e) {
        switch(e.getApparence()){
            case "H":
                return ANSI_BLACK_BACKGROUND + ANSI_PURPLE + e.getApparence() + ANSI_RESET;
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
            case "R":
                return ANSI_CYAN_BACKGROUND + ANSI_BLACK + e.getApparence() + ANSI_RESET;
            //cocotier
            case "T":
                return ANSI_BLACK_BACKGROUND + ANSI_GREEN + e.getApparence() + ANSI_RESET;
                //scorpion
            case "O":
                return e.toString();
                //serpent
            case "E":
                return e.toString();

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
        else if (chance < 95) return "H"; //champignon hallu
        else if (chance < 96) return "O"; //scorpion
        else if (chance < 97) return "R"; //petit rocher
        else return "B"; //banane
    }



    @Override
    public ElementCarte ajouterElementCarte(String element, int abscisse, int ordonnee) {
        switch (element) {
            case "S":
                Singe s = new Singe(abscisse, ordonnee, 2, 3);
                getLesAnimaux().add(s);
                return s;
            case "O":
                Predateur o = new Scorpion("O", abscisse, ordonnee);
                getLesPredateurs().add(o);
                return o;
            case "E":
                Predateur e = new Serpent("E", abscisse, ordonnee);
                getLesPredateurs().add(e);
                return e;
            case "@":
                getPersonnage().nouvellePosition(abscisse, ordonnee);
                return getPersonnage();

        }
        return new ElementCarte(element);
    }

        @Override
        public boolean estNourriture(String element) {
            switch (element) {
                case "B" , "C","H":
                    return true;
                default:
                    return false;
            }

        }
}

