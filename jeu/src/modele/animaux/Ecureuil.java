package modele.animaux;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.etats.EtatEffraye;
import modele.etats.EtatJunkie;

public class Ecureuil extends Animal{


    public Ecureuil(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence("E");
        this.getRegimeAlimentaire().add("C");
        this.getRegimeAlimentaire().add("M");
        this.getRegimeAlimentaire().add("G");
        this.setNbTourJunkie(5);
        this.setNbTourCache(3);
    }

    @Override
    public void intoxication() {
        this.changerEtat(EtatJunkie.getInstance());
    }

    @Override
    public void fuir(Carte carte, Personnage personnage, int nvAbscisse, int nvOrdonnee) {
        if(isAmi() && carte.estCaseAdjacente(getAbscisse(),getOrdonnee(), personnage.getApparence())){
            personnage.ajouterAmiCache(this);
            this.nouvellePosition(personnage.getAbscisse(), personnage.getOrdonnee());
            this.changerEtat(EtatEffraye.getInstance());
            this.setEstCache(true);
        } else if (seCacher(carte,"A")) {
            return;
        }else if (seCacher(carte, "B")){
            return;
        }else{
            carte.setCase(getAbscisse(),getOrdonnee(),new ElementCarte(getCachette()));
            this.nouvellePosition(nvAbscisse,nvOrdonnee);
            this.setEstCache(false);
        }
    }


    @Override
    public boolean estComestible(String aliment) {
        return !aliment.equals("M");
    }


}
