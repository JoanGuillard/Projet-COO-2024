package modele.strategies;

import modele.Carte;
import modele.Personnage;
import modele.animaux.Animal;
import modele.etats.EtatAffame;

public class StrategieAffameEcureuil implements IStrategieDeplacementAnimal {

    private static StrategieAffameEcureuil instance;

    public static StrategieAffameEcureuil getInstance() {
        if(instance == null){
            instance = new StrategieAffameEcureuil();
        }
        return instance;
    }
    
    @Override
    public void seDeplacer(EtatAffame etat, Animal animal, Carte carte, Personnage personnage) {
        int abscisseAnimal = animal.getAbscisse();
        int ordonneeAnimal = animal.getOrdonnee();
        if(!etat.chercherNourriture(animal,carte,personnage,abscisseAnimal,ordonneeAnimal)){
            if(!etat.verifierDanger(carte,abscisseAnimal,ordonneeAnimal,animal,personnage,1)){
                etat.deplacementAleatoire(carte,abscisseAnimal,ordonneeAnimal,animal,1);
                carte.setCase(animal.getAbscisse(),animal.getOrdonnee(),animal);
            }
        }

    }
}
