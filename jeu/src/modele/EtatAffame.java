package modele;

import java.util.ArrayList;
import static modele.CouleursAffichage.*;

public class EtatAffame implements Etat{
    private static EtatAffame instance;

    public EtatAffame(){}
    public static synchronized EtatAffame getInstance(){
        if(instance == null){
            instance = new EtatAffame();
        }
        return instance;
    }
    @Override
    public void seDeplacer(Animal animal, ArrayList<ArrayList<ElementCarte>> carte) {
        int abscisseAnimal = animal.getAbscisse();
        int ordonneeAnimal = animal.getOrdonnee();
        for(String nourriture : animal.getRegimeAlimentaire()){
            if(carte.get(ordonneeAnimal-1).get(abscisseAnimal).getApparence().equals(nourriture)){
                animal.nouvellePosition(abscisseAnimal,ordonneeAnimal-1);

            }
        }
    }

    @Override
    public String toString(Animal animal) {
        return ANSI_BLACK + animal.getApparence() + ANSI_RESET;
    }
}
