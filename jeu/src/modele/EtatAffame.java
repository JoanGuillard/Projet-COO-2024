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
    public void seDeplacer(Animal animal, Carte carte, Personnage personnage) {
        int abscisseAnimal = animal.getAbscisse();
        int ordonneeAnimal = animal.getOrdonnee();
        for(String nourriture : animal.getRegimeAlimentaire()){
            if( carte.verifierCase(abscisseAnimal, ordonneeAnimal-1, nourriture) ){
                animal.nouvellePosition(abscisseAnimal,ordonneeAnimal-1);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal,ordonneeAnimal-1, personnage.getApparence()));
                return;
            } else if ( carte.verifierCase(abscisseAnimal, ordonneeAnimal+1, nourriture) ) {
                animal.nouvellePosition(abscisseAnimal,ordonneeAnimal+1);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal,ordonneeAnimal+1, personnage.getApparence()));
                return;
            } else if ( carte.verifierCase(abscisseAnimal-1, ordonneeAnimal, nourriture) ) {
                animal.nouvellePosition(abscisseAnimal-1,ordonneeAnimal);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal-1,ordonneeAnimal, personnage.getApparence()));
                return;
            } else if ( carte.verifierCase(abscisseAnimal+1, ordonneeAnimal,nourriture) ) {
                animal.nouvellePosition(abscisseAnimal+1,ordonneeAnimal);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal+1,ordonneeAnimal, personnage.getApparence()));
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
