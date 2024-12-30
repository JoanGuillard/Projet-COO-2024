package modele.etats;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;

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
        carte.setCase(animal.getAbscisse(), animal.getOrdonnee(),new ElementCarte(animal.getCachette()));
        for(String nourriture : animal.getRegimeAlimentaire()){
            if( carte.verifierCase(abscisseAnimal, ordonneeAnimal-1, nourriture) ){
                animal.nouvellePosition(abscisseAnimal,ordonneeAnimal-1);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal,ordonneeAnimal-1, personnage.getApparence()),nourriture);
                return;
            } else if ( carte.verifierCase(abscisseAnimal, ordonneeAnimal+1, nourriture) ) {
                animal.nouvellePosition(abscisseAnimal,ordonneeAnimal+1);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal,ordonneeAnimal+1, personnage.getApparence()),nourriture);
                return;
            } else if ( carte.verifierCase(abscisseAnimal-1, ordonneeAnimal, nourriture) ) {
                animal.nouvellePosition(abscisseAnimal-1,ordonneeAnimal);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal-1,ordonneeAnimal, personnage.getApparence()),nourriture);
                return;
            } else if ( carte.verifierCase(abscisseAnimal+1, ordonneeAnimal,nourriture) ) {
                animal.nouvellePosition(abscisseAnimal+1,ordonneeAnimal);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal+1,ordonneeAnimal, personnage.getApparence()),nourriture);
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
