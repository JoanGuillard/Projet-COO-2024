package modele.etats;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;

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
    public void seDeplacer(Animal animal, Carte carte, Personnage personnage) {
        int abscisse = animal.getAbscisse();
        int ordonnee = animal.getOrdonnee();
        animal.augmenterCptSansManger();
        if (animal.getNbTourSansManger() == animal.getCptTourSansManger()) {
            animal.changerEtat(EtatAffame.getInstance());
        }
        if(!verifierDanger(carte,abscisse,ordonnee,animal,personnage)) {
            animal.setEstCache(false);
            carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), new ElementCarte(animal.getCachette()));
            deplacementAleatoire(carte, abscisse, ordonnee, animal,1);
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
