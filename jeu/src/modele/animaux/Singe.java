package modele.animaux;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.etats.*;
import modele.strategies.StrategieAffameSinge;

public class Singe extends Animal{




    public Singe(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence("S");
        this.getRegimeAlimentaire().add("B");
        this.getRegimeAlimentaire().add("C");
        this.getRegimeAlimentaire().add("H");
        this.setNbTourJunkie(3);
        this.setNbTourCache(0);
        this.setStrategieAffame(StrategieAffameSinge.getInstance());

    }




    @Override
    public void intoxication() {
        this.changerEtat(EtatInconscient.getInstance());
    }

    @Override
    public void devenirAmi(Personnage personnage, Carte carte) {
        this.setAmi(true);
        personnage.ajouterAmiCache(this);
        changerEtat(EtatSurAmi.getInstance());
        carte.setCase(getAbscisse(),getOrdonnee(),new ElementCarte(getCachette()));
        this.nouvellePosition(personnage.getAbscisse(), personnage.getOrdonnee());
    }


    @Override
    public void fuir(Carte carte, Personnage personnage, int nvAbscisse, int nvOrdonnee) {
        if(!isEstCache()) {
            if (seCacher(carte, "T")) {
                return;
            } else if (seCacher(carte, "R")) {
                return;
            } else if(carte.verifierCase(nvAbscisse,nvOrdonnee," ")){
                carte.setCase(getAbscisse(), getOrdonnee(), new ElementCarte(getCachette()));
                this.nouvellePosition(nvAbscisse, nvOrdonnee);
                carte.setCase(getAbscisse(), getOrdonnee(), this);
            }
        }
    }




    @Override
    public boolean estComestible(String aliment) {
        return !aliment.equals("H");
    }
}
