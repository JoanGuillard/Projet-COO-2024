package modele.parties;
import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Ecureuil;

import java.util.Random;

import static modele.CouleursAffichage.*;
public class SansDangerPartieForet extends Partie{
    public SansDangerPartieForet(Personnage personnage) {
        super(personnage);
        getPersonnage().setInventaire("C");
        getPersonnage().setInventaire("G");
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
            default:
                return ANSI_GREEN_BACKGROUND + e.getApparence() + ANSI_RESET;
        }
    }

    @Override
    public ElementCarte ajouterElementCarte(String element,int abscisse, int ordonnee) {
        switch(element){
            case "@":
                getPersonnage().nouvellePosition(abscisse,ordonnee);
                return getPersonnage();
            case "E":
                Ecureuil e = new Ecureuil(abscisse,ordonnee,1,5);
                getLesAnimaux().add(e);
                return e;
            default:
                return new ElementCarte(element);
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
    @Override
    protected String genererElementAleatoire(Random random) {
        int chance = random.nextInt(100);
        if (chance < 90) return " ";
        else if (chance < 92) return "C";
        else if (chance < 94) return "B";
        else if (chance < 96) return "E";
        else if (chance < 97) return "A";
        else   return "G";

    }
    @Override
    public void initialiserCarte(int hauteur, int largeur) {
        Carte carte = creerNouvelleCarte("A",hauteur, largeur);
        remplirCarte(carte, hauteur, largeur);
        this.setCarte(carte);
    }



}