package modele.etats;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;

public class EtatSurAmi extends Etat{
    private static EtatSurAmi instance;


    public EtatSurAmi() {

    }

    public static synchronized EtatSurAmi getInstance(){
        if(instance == null){
            instance = new EtatSurAmi();
        }
        return instance;
    }
    
    
    @Override
    public void seDeplacer(Animal animal, Carte carte, Personnage personnage) {
        int abscisse = animal.getAbscisse();
        int ordonnee = animal.getOrdonnee();
        if(animal.getNbTourCache() <3){
            animal.augmenterNbTourCache();
            animal.nouvellePosition(personnage.getAbscisse(), personnage.getOrdonnee());
        }else{
            animal.changerEtat(EtatAffame.getInstance());
            animal.setEstCache(false);
            personnage.supprimerAmiCache(animal);
            animal.setNbTourCache(0);
        }
    }

    @Override
    public String toString(Animal animal) {
        return null;
    }
}
