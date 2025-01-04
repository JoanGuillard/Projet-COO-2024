package modele.predateurs;

import modele.Carte;
import modele.ElementCarte;
import modele.animaux.Singe;
import modele.etats.EtatEffraye;

import static modele.CouleursAffichage.*;

public class Serpent extends Predateur {

    private int toursDigestion = 0;

    public Serpent(String apparence, int abscisse, int ordonnee) {
        super(apparence, abscisse, ordonnee);
    }

    @Override
    public void seDeplacer(Carte carte) {
        if (toursDigestion > 0) {
            toursDigestion--;
            return;
        }
        int abscisseSerpent = this.getAbscisse();
        int ordonneeSerpent = this.getOrdonnee();
        Singe s = null;
        carte.setCase(abscisseSerpent,ordonneeSerpent,new ElementCarte(" "));
        if (carte.verifierCase(abscisseSerpent - 1, ordonneeSerpent, "S")) {
            s = (Singe) carte.getCase(abscisseSerpent-1,ordonneeSerpent);
        } else if (carte.verifierCase(abscisseSerpent + 1, ordonneeSerpent, "S")) {
            s = (Singe) carte.getCase(abscisseSerpent+1,ordonneeSerpent);
        } else if (carte.verifierCase(abscisseSerpent, ordonneeSerpent - 1, "S")) {
            s = (Singe) carte.getCase(abscisseSerpent,ordonneeSerpent-1);
        } else if (carte.verifierCase(abscisseSerpent, ordonneeSerpent + 1, "S")) {
            s = (Singe) carte.getCase(abscisseSerpent,ordonneeSerpent+1);
        }
        if(s != null && !s.isEstCache()){
            if(!s.seCacher(carte,"T")){
                this.nouvellePosition(s.getAbscisse(),s.getOrdonnee());
                s.setEstMort(true);
                toursDigestion = 3;
            }else{
                s.changerEtat(EtatEffraye.getInstance());
            }
        }else{
            deplacementAleatoire(carte,2);
        }

        carte.setCase(getAbscisse(),getOrdonnee(),this);
    }

    public String toString(){
        if(toursDigestion > 0) {
            return ANSI_PURPLE_BACKGROUND + ANSI_BLACK + getApparence() + ANSI_RESET;
        }else{
            return ANSI_PURPLE_BACKGROUND + ANSI_CYAN + getApparence() + ANSI_RESET;
            }
    }




}
