package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static modele.CouleursAffichage.*;

public class EtatAffame extends Etat{
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
            if( verifierCase(abscisseAnimal, ordonneeAnimal-1, carte,nourriture) ){
                animal.nouvellePosition(abscisseAnimal,ordonneeAnimal-1);
                animal.seNourrir(false);
                return;
            } else if ( verifierCase(abscisseAnimal, ordonneeAnimal+1, carte,nourriture) ) {
                animal.nouvellePosition(abscisseAnimal,ordonneeAnimal+1);
                animal.seNourrir(false);
                return;
            } else if ( verifierCase(abscisseAnimal-1, ordonneeAnimal, carte,nourriture) ) {
                animal.nouvellePosition(abscisseAnimal-1,ordonneeAnimal);
                animal.seNourrir(false);
                return;
            } else if ( verifierCase(abscisseAnimal+1, ordonneeAnimal, carte,nourriture) ) {
                animal.nouvellePosition(abscisseAnimal+1,ordonneeAnimal);
                animal.seNourrir(false);
                return;
            }
        }
        deplacementAleatoire(carte,abscisseAnimal,ordonneeAnimal,animal);
    }



    @Override
    public String toString(Animal animal) {
        return ANSI_BLACK_BACKGROUND + ANSI_RED + animal.getApparence() + ANSI_RESET;
    }
}
