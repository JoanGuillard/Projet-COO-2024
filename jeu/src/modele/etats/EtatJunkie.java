package modele.etats;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;
import modele.parties.Partie;

import static modele.CouleursAffichage.*;
public class EtatJunkie extends Etat{

    private static EtatJunkie instance;


    public EtatJunkie() {
    }

    public static synchronized EtatJunkie getInstance(){
        if(instance == null){
            instance = new EtatJunkie();
        }
        return instance;
    }
    @Override
    public void seDeplacer(Animal animal, Carte carte, Personnage personnage) {
        if (animal.getCptTourJunkie() == animal.getNbTourJunkie()) {
            animal.changerEtat(EtatAffame.getInstance());
            animal.setCptTourJunkie(0);
            return;
        }
        int abscisseAnimal = animal.getAbscisse();
        int ordonneeAnimal = animal.getOrdonnee();

        if(!verifierDanger(carte,abscisseAnimal,ordonneeAnimal,animal,personnage,2)){
            animal.setEstCache(false);
            carte.setCase(animal.getAbscisse(), animal.getOrdonnee(),new ElementCarte(animal.getCachette()));
            deplacementAleatoire(carte,abscisseAnimal,ordonneeAnimal,animal,2);
        }

        animal.augmenterCptTourJunkie();
        carte.setCase(animal.getAbscisse(),animal.getOrdonnee(),animal);
    }


    @Override
    public String toString(Animal animal) {
        return ANSI_BLACK_BACKGROUND + ANSI_YELLOW + animal.getApparence() + ANSI_RESET;
    }
}
