package modele.animaux;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.etats.*;
import modele.strategies.StrategieAffameEcureuil;
import modele.strategies.StrategieAffameSinge;

import static modele.CouleursAffichage.*;
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
        setAmi(true);
        seCacherSurAmi(personnage,carte);
    }


    @Override
    public void fuir(Carte carte, Personnage personnage, int nvAbscisse, int nvOrdonnee) {
        if(!isEstCache()) {
            if (seCacher(carte, "T")) {
                return;
            } else if (seCacher(carte, "R")) {
                return;
            } else if(carte.verifierCase(nvAbscisse,nvOrdonnee," ")){
                fuirPredateur(carte,personnage,nvAbscisse,nvOrdonnee);
            }
        }else{
            System.out.println("OUH OUH AH AH !!! (le singe vous prévient d'un danger)");
        }
    }




    @Override
    public boolean estComestible(String aliment) {
        return !aliment.equals("H");
    }
}
