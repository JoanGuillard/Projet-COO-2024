package modele;

import java.util.ArrayList;
import static modele.CouleursAffichage.*;

public class EtatRassasie extends Etat{

    private static EtatRassasie instance;

    public EtatRassasie(){}
    public static synchronized EtatRassasie getInstance(){
        if(instance == null){
            instance = new EtatRassasie();
        }
        return instance;
    }
    @Override
    public void seDeplacer(Animal animal, ArrayList<ArrayList<ElementCarte>> carte) {
        int abscisse = animal.getAbscisse();
        int ordonnee = animal.getOrdonnee();
        deplacementAleatoire(carte, abscisse, ordonnee, animal);
        animal.augmenterCptSansManger();
        if(animal.getNbTourSansManger() == animal.getCptTourSansManger()){
            animal.changerEtat(EtatAffame.getInstance());
        }
    }

    @Override
    public String toString(Animal animal) {
        if(animal.isAmi()){
            return ANSI_BLACK_BACKGROUND +ANSI_PURPLE + animal.getApparence() + ANSI_RESET;
        }else{
            return ANSI_BLACK_BACKGROUND + ANSI_BLUE + animal.getApparence() + ANSI_RESET;
        }
    }
}
