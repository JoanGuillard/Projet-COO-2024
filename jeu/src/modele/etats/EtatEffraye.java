package modele.etats;
import modele.Carte;
import modele.Personnage;
import modele.animaux.Animal;

import static modele.CouleursAffichage.*;

public class EtatEffraye extends Etat{

    private static EtatEffraye instance;


    public EtatEffraye() {

    }

    public static synchronized EtatEffraye getInstance(){
        if(instance == null){
            instance = new EtatEffraye();
        }
        return instance;
    }
    @Override
    public void seDeplacer(Animal animal, Carte carte, Personnage personnage) {
        if(animal.getNbTourCache() == 3){
            animal.changerEtat(EtatAffame.getInstance());
            animal.setEstCache(false);
            if(animal.estCacheAvecAmi(personnage)){
                personnage.supprimerAmiCache(animal);
            }
            animal.seDeplacer(carte,personnage);

        }else{
            if(animal.estCacheAvecAmi(personnage)){
                animal.nouvellePosition(personnage.getAbscisse(), personnage.getOrdonnee());
            }
            animal.augmenterNbTourCache();
        }
    }

    @Override
    public String toString(Animal animal) {
        return ANSI_CYAN_BACKGROUND + ANSI_BLACK + animal.getApparence()+ ANSI_RESET;
    }
}
