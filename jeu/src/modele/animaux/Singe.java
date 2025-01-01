package modele.animaux;

import modele.Carte;
import modele.Personnage;

public class Singe extends Animal{
    public Singe(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence("S");
        this.getRegimeAlimentaire().add("C");
        this.getRegimeAlimentaire().add("H");
        this.getRegimeAlimentaire().add("B");
    }

    @Override
    public void intoxication() {

    }

    @Override
    public void fuir(Carte carte, Personnage personnage, int nvAbscisse, int nvOrdonnee) {

    }


    @Override
    public boolean estComestible(String aliment) {
        return !aliment.equals("H");
    }
}
