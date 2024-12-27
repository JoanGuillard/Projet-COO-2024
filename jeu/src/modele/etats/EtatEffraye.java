package modele.etats;
import modele.Carte;
import modele.Personnage;
import modele.animaux.Animal;

import static modele.CouleursAffichage.*;

public class EtatEffraye extends Etat{
    private int nbTourEffraye;
    private static EtatEffraye instance;

    public EtatEffraye() {
        this.nbTourEffraye = 0;
    }

    public static synchronized EtatEffraye getInstance(){
        if(instance == null){
            instance = new EtatEffraye();
        }
        return instance;
    }
    @Override
    public void seDeplacer(Animal animal, Carte carte, Personnage personnage) {
        if(++nbTourEffraye == animal.getNbTourCache()){
            animal.changerEtat(EtatAffame.getInstance());
        }
    }

    @Override
    public String toString(Animal animal) {
        return ANSI_CYAN_BACKGROUND + ANSI_BLACK + animal.getApparence()+ ANSI_RESET;
    }
}