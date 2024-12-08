package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static modele.CouleursAffichage.*;

public class Personnage extends ElementCarte{


    private String nom;
    private String apparence;
    private Map<String,Integer> inventaire;

    public Personnage(){
        super(ANSI_BLACK_BACKGROUND + ANSI_PURPLE + "@" + ANSI_RESET);
        this.inventaire = new HashMap<String,Integer>();
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
}
