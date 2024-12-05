package Modele;

import java.util.ArrayList;

public class EtatAffame implements Etat{
    private static EtatAffame instance;

    public EtatAffame(){}
    public static synchronized EtatAffame getInstance(){
        if(instance == null){
            instance = new EtatAffame();
        }
        return instance;
    }
    @Override
    public void seDeplacer(Animal animal, ArrayList<ArrayList<String>> carte) {

    }
}
