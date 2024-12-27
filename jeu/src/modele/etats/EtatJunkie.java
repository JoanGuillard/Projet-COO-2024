package modele.etats;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;

import static modele.CouleursAffichage.*;
public class EtatJunkie extends Etat{

    private static EtatJunkie instance;
    private int nbTourJunkie;

    public EtatJunkie() {
        this.nbTourJunkie = 0;
    }

    public static synchronized EtatJunkie getInstance(){
        if(instance == null){
            instance = new EtatJunkie();
        }
        return instance;
    }
    @Override
    public void seDeplacer(Animal animal, Carte carte, Personnage personnage) {
        int abscisseAnimal = animal.getAbscisse();
        int ordonneeAnimal = animal.getOrdonnee();
        carte.setCase(animal.getAbscisse(), animal.getOrdonnee(),new ElementCarte(animal.getCachette()));
        deplacementAleatoire(carte,abscisseAnimal,ordonneeAnimal,animal);
        deplacementAleatoire(carte,abscisseAnimal,ordonneeAnimal,animal);
        if(++nbTourJunkie == animal.getNbTourJunkie()){
            animal.changerEtat(EtatAffame.getInstance());
        }
    }

    @Override
    public String toString(Animal animal) {
        return ANSI_BLACK_BACKGROUND + ANSI_YELLOW + animal.getApparence() + ANSI_RESET;
    }
}
