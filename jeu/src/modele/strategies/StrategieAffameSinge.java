package modele.strategies;

import modele.Carte;
import modele.Personnage;
import modele.animaux.Animal;
import modele.etats.EtatAffame;

public class StrategieAffameSinge implements IStrategieDeplacementAffame{

    private static StrategieAffameSinge instance;

    public static StrategieAffameSinge getInstance() {
        if(instance == null){
            instance = new StrategieAffameSinge();
        }
        return instance;
    }

    @Override
    public void seDeplacer(EtatAffame etat, Animal animal, Carte carte, Personnage personnage) {
        int abscisseAnimal = animal.getAbscisse();
        int ordonneeAnimal = animal.getOrdonnee();
        if(!etat.verifierDanger(carte,abscisseAnimal,ordonneeAnimal,animal,personnage,1)){
            if(!etat.chercherNourriture(animal,carte,personnage,abscisseAnimal,ordonneeAnimal)){
                etat.deplacementAleatoire(carte,abscisseAnimal,ordonneeAnimal,animal,1);
                carte.setCase(animal.getAbscisse(),animal.getOrdonnee(),animal);
            }
        }

    }
}
