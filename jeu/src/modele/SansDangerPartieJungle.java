package modele;
import java.util.Random;

import static modele.CouleursAffichage.*;

public class SansDangerPartieJungle extends Partie {

    public SansDangerPartieJungle(Personnage personnage) {
        super(personnage);
        getPersonnage().setInventaire("C");
        getPersonnage().setInventaire("B");
    }

    @Override
    public String afficherElement(ElementCarte e) {
        switch (e.getApparence()) {
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
            default:
                return ANSI_GREEN_BACKGROUND + e.getApparence() + ANSI_RESET;
        }
    }

    @Override
    public ElementCarte ajouterElementCarte(String element, int abscisse, int ordonnee) {
        switch (element) {
            case "S":
                Singe s = new Singe(abscisse, ordonnee, 2, 3);
                getLesAnimaux().add(s);
                return s;
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
            case "B" , "C":
                return true;
            default:
                return false;
        }
    }
    @Override
    public void remplirCarte(Carte carte,int hauteur,int largeur) {
        Random random = new Random();


        int totalCases = (hauteur - 2) * (largeur - 2); // pour etre loin de la bordure
        int casesVidesCibles = totalCases / 2; // 50% des cases vides
        int casesRemplies = 0;

        for (int i = 1; i < hauteur -1; i++) {
            for (int j = 1; j < largeur -1; j++) {
                if (casesRemplies >= totalCases - casesVidesCibles) break;

                String element = genererElementAleatoire(random);
                if (!element.equals(" ")) casesRemplies++;

                carte.setCase(j, i ,ajouterElementCarte(element, j, i));
            }
        }

        // Ajouter le personnage dans une zone protégée
        ajouterPersonnageDansZoneProtegee(carte, random);
    }
    @Override
    protected String genererElementAleatoire(Random random) {
        int chance = random.nextInt(100);
        if (chance < 90) return " ";
        else if (chance < 92) return "C";
        else if (chance < 94) return "R";
        else if (chance <96 ) return "S";
        else if (chance < 97) return "T";
        else return "B"; // 10% cocotiers
    }
    @Override
    public void initialiserCarte(int hauteur, int largeur) {
        Carte carte = creerNouvelleCarte("T",hauteur, largeur);
        remplirCarte(carte,hauteur,largeur);
        this.setCarte(carte);
    }
}
