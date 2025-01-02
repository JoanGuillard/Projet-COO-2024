package modele.predateurs;

import modele.Carte;
import modele.ElementCarte;
import modele.animaux.Ecureuil;
import modele.etats.EtatEffraye;
import static modele.CouleursAffichage.*;
public class Hiboux extends Predateur{
    private int nbTourAuSol;
    private int cptTourAuSol;
    private boolean estRassasie;
    public Hiboux(String apparence, int abscisse, int ordonnee) {
        super(apparence, abscisse, ordonnee);
        this.estRassasie = false;
        this.nbTourAuSol = 1;
        this.cptTourAuSol = 0;
    }

    @Override
    public void seDeplacer(Carte carte) {
        if(nbTourAuSol == cptTourAuSol){
            estRassasie = false;
            cptTourAuSol = 0;
        }
        if(!estRassasie){
            int abscisseHiboux = this.getAbscisse();
            int ordonneeHiboux = this.getOrdonnee();
            Ecureuil e = null;
            carte.setCase(abscisseHiboux,ordonneeHiboux,new ElementCarte(" "));
            for(int i =1; i <4; i++) {
                if (carte.verifierCase(abscisseHiboux - i, ordonneeHiboux, "E")) {
                    e = (Ecureuil) carte.getCase(abscisseHiboux - i, ordonneeHiboux);
                } else if (carte.verifierCase(abscisseHiboux + i, ordonneeHiboux, "E")) {
                    e = (Ecureuil) carte.getCase(abscisseHiboux + i, ordonneeHiboux);

                } else if (carte.verifierCase(abscisseHiboux, ordonneeHiboux - i, "E")) {
                    e = (Ecureuil) carte.getCase(abscisseHiboux, ordonneeHiboux - i);

                } else if (carte.verifierCase(abscisseHiboux, ordonneeHiboux + i, "E")) {
                    e = (Ecureuil) carte.getCase(abscisseHiboux, ordonneeHiboux + i);

                }
            }

            if (e != null && !e.isEstCache()) {
                if (!e.seCacher(carte, "B")) {
                    this.nouvellePosition(e.getAbscisse(), e.getOrdonnee());
                    e.setEstMort(true);
                    estRassasie = true;
                } else {
                    e.changerEtat(EtatEffraye.getInstance());
                }
            } else {
                deplacementAleatoire(carte, 2);
            }

        }else{
            cptTourAuSol++;
        }
        carte.setCase(getAbscisse(),getOrdonnee(),this);
    }

    public String toString(){
        if(estRassasie){
            return ANSI_PURPLE_BACKGROUND + ANSI_BLACK + getApparence() + ANSI_RESET;
        }else{
            return ANSI_CYAN_BACKGROUND + ANSI_BLACK + getApparence() + ANSI_RESET;
        }
    }
}
