package modele;

import java.util.ArrayList;

public interface Etat {

    public void seDeplacer(Animal animal, ArrayList<ArrayList<ElementCarte>> carte);
    public String toString(Animal animal);


}
