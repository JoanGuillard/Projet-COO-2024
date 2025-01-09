package modele;

import modele.animaux.Animal;

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

    /**
     * Dépose un objet de l'inventaire du personnage en diminuant sa quantité de 1.
     *
     * @param objet Le nom de l'objet à déposer.
     */
    public void deposerObjet(String objet){
        this.inventaire.put(objet,inventaire.get(objet)-1);
    }

    public int getNbObjet(String objet){
        return inventaire.get(objet);
    }

    /**
     * Ajoute un objet à l'inventaire du personnage en augmentant sa quantité de 1.
     *
     * @param element Le nom de l'élément à ajouter.
     */
    public void ajouterDansInventaire(String element){
        if(element.equals("2")||element.equals("3")){
            element = "K";
        }
        this.inventaire.put(element,inventaire.get(element)+1);
    }

    public Map<String,Integer> getInventaire(){
        return inventaire;
    }

    /**
     * Vérifie si un élément spécifique est présent dans l'inventaire.
     *
     * @param element Le nom de l'élément recherché.
     * @return  true si l'élément est présent dans l'inventaire,false sinon.
     */
    public boolean isInInventaire(String element){
        return inventaire.containsKey(element);
    }

    public ArrayList<Animal> getLesAmisCaches() {
        return lesAmisCaches;
    }

    /**
     * Supprime un animal de la liste des amis cachés.
     *
     * @param animal L'animal à supprimer de la liste.
     */
    public void supprimerAmiCache(Animal animal){
        lesAmisCaches.remove(animal);
    }

    /**
     * Ajoute un animal à la liste des amis cachés.
     *
     * @param animal L'animal à ajouter à la liste.
     */
    public void ajouterAmiCache(Animal animal) {
        this.lesAmisCaches.add(animal);
    }
}
