package modele;

import java.util.ArrayList;

public class EtatRassasie implements Etat{

    private static EtatRassasie instance;

    public EtatRassasie(){}
    public static synchronized EtatRassasie getInstance(){
        if(instance == null){
            instance = new EtatRassasie();
        }
        return instance;
    }
    @Override
    public void seDeplacer(Animal animal, ArrayList<ArrayList<String>> carte) {
        int abscisse = animal.getAbscisse();
        int ordonnee = animal.getOrdonnee();

        if(carte.get(ordonnee).get(abscisse+1).equals(" ")){
            animal.nouvellePosition(abscisse+1,ordonnee);
        } else if (carte.get(ordonnee+1).get(abscisse).equals(" ")) {
            animal.nouvellePosition(abscisse,ordonnee+1);
        } else if (carte.get(ordonnee-1).get(abscisse).equals(" ")) {
            animal.nouvellePosition(abscisse,ordonnee-1);
        } else{
            animal.nouvellePosition(abscisse-1,ordonnee);
        }

        animal.augmenterCptSansManger();
        if(animal.getNbTourSansManger() == animal.getCptTourSansManger()){
            animal.changerEtat(EtatAffame.getInstance());
        }
    }
}
