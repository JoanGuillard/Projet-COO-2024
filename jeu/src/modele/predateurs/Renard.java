package modele.predateurs;

import modele.Carte;
import modele.ElementCarte;
import modele.animaux.Ecureuil;
import modele.etats.EtatEffraye;
import modele.predateurs.Predateur;

public class Renard extends Predateur {


    public Renard(String apparence, int abscisse, int ordonnee) {
        super(apparence, abscisse, ordonnee);
    }

    @Override
    public void seDeplacer( Carte carte) {
        int abscisseRenard = this.getAbscisse();
        int ordonneeRenard = this.getOrdonnee();
        Ecureuil e = null;
        carte.setCase(abscisseRenard,ordonneeRenard,new ElementCarte(" "));
        if(carte.verifierCase(abscisseRenard-1,ordonneeRenard,"E")){
             e = (Ecureuil) carte.getCase(abscisseRenard-1,ordonneeRenard);
        }else if(carte.verifierCase(abscisseRenard+1,ordonneeRenard,"E")){
             e = (Ecureuil) carte.getCase(abscisseRenard+1,ordonneeRenard);

        }else if(carte.verifierCase(abscisseRenard,ordonneeRenard-1,"E")){
             e = (Ecureuil) carte.getCase(abscisseRenard,ordonneeRenard-1);

        } else if(carte.verifierCase(abscisseRenard,ordonneeRenard+1,"E")){
             e = (Ecureuil) carte.getCase(abscisseRenard,ordonneeRenard+1);

        }else{
            if(e != null){
                if(!e.seCacher(carte,"A")){
                    this.nouvellePosition(e.getAbscisse(),e.getOrdonnee());
                    e.setEstMort(true);
                }else{
                    e.changerEtat(EtatEffraye.getInstance());
                }
            }else{
                deplacementAleatoire(carte);
            }
        }
        carte.setCase(getAbscisse(),getOrdonnee(),this);
    }
}
