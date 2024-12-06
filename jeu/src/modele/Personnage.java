package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Personnage {
    private String nom;
    private String apparence;
    private int abscisse;
    private int ordonnee;
    private Map<String,Integer> inventaire;

    public Personnage(String nom){
        this.nom = nom;
        this.apparence = "@";
        this.inventaire = new HashMap<String,Integer>();
    }

    public void nouvellePosition(int abscisse, int ordonnee){
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
    }
    public String getNom() {
        return nom;
    }

    public int getAbscisse() {
        return abscisse;
    }

    public String getApparence() {
        return apparence;
    }

    public int getOrdonnee() {
        return ordonnee;
    }

    public void setInventaire(){

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
}
