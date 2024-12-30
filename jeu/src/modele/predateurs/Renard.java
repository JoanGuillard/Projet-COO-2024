package modele.predateurs;

import modele.Carte;
import modele.animaux.Ecureuil;
import modele.predateurs.Predateur;

public class Renard extends Predateur {


    public Renard(String apparence, int abscisse, int ordonnee) {
        super(apparence, abscisse, ordonnee);
    }

    @Override
    public void seDeplacer( Carte carte) {
        int abscisseRenard = this.getAbscisse();
        int ordonneeRenard = this.getOrdonnee();

        if(carte.verifierCase(abscisseRenard-1,ordonneeRenard,"E")){
            Ecureuil e = (Ecureuil) carte.getCase(abscisseRenard-1,ordonneeRenard);
            if(!e.seCacher(carte,"A","B")){
                this.nouvellePosition(abscisseRenard-1,ordonneeRenard);
                e.setEstMort(true);
            }
        }else if(carte.verifierCase(abscisseRenard-+1,ordonneeRenard,"E")){
            Ecureuil e = (Ecureuil) carte.getCase(abscisseRenard+1,ordonneeRenard);
            if(!e.seCacher(carte,"A","B")){
                this.nouvellePosition(abscisseRenard+1,ordonneeRenard);
                e.setEstMort(true);
            }
        }else if(carte.verifierCase(abscisseRenard,ordonneeRenard-1,"E")){
            Ecureuil e = (Ecureuil) carte.getCase(abscisseRenard,ordonneeRenard-1);
            if(!e.seCacher(carte,"A","B")){
                this.nouvellePosition(abscisseRenard,ordonneeRenard-1);
                e.setEstMort(true);
            }
        } else if(carte.verifierCase(abscisseRenard+1,ordonneeRenard,"E")){
            Ecureuil e = (Ecureuil) carte.getCase(abscisseRenard+1,ordonneeRenard);
            if(!e.seCacher(carte,"A","B")){
                this.nouvellePosition(abscisseRenard+1,ordonneeRenard);
                e.setEstMort(true);
            }
        }else{
            deplacementAleatoire(carte);
        }
    }
}
