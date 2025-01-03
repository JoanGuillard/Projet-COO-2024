package modele;

import modele.animaux.Animal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static modele.CouleursAffichage.*;

public class Personnage extends ElementCarte{



    private Map<String,Integer> inventaire;
    private ArrayList<Animal> lesAmisCaches;

    public Personnage(){
        super(ANSI_BLUE_BACKGROUND + ANSI_RED + "@" + ANSI_RESET);
        this.inventaire = new HashMap<String,Integer>();
        this.lesAmisCaches = new ArrayList<Animal>();
    }

    public void setInventaire(String element){
        this.inventaire.putIfAbsent(element,0);
    }

    public void deposerObjet(String objet){
        this.inventaire.put(objet,inventaire.get(objet)-1);
    }

    public int getNbObjet(String objet){
        return inventaire.get(objet);
    }

    public void ajouterDansInventaire(String element){
        this.inventaire.put(element,inventaire.get(element)+1);
    }

    public Map<String,Integer> getInventaire(){
        return inventaire;
    }

    public boolean isInInventaire(String element){
        return inventaire.containsKey(element);
    }

    public ArrayList<Animal> getLesAmisCaches() {
        return lesAmisCaches;
    }


    public void supprimerAmiCache(Animal animal){
        lesAmisCaches.remove(animal);
    }

    public void ajouterAmiCache(Animal animal) {
        this.lesAmisCaches.add(animal);
    }
}
