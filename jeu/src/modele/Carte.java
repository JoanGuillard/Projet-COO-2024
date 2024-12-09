package modele;

import exceptions.CommandeInconnueException;
import exceptions.DeplacementImpossibleException;

import javax.swing.text.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Carte {
    private static Carte instance;
    private  ArrayList<ArrayList<ElementCarte>> carte ;
    private int largeur;
    private int hauteur;


    public Carte() {
        this.carte = new ArrayList<ArrayList<ElementCarte>>();
    }

    public static Carte getInstance() {
        if (instance == null) {
            instance = new Carte();
        }
        return instance;
    }

    // Charger une carte depuis un fichier

    public ElementCarte getElement(int x, int y) {
        if (x >= 0 && x < hauteur && y >= 0 && y < largeur) {
            return carte.get(x).get(y);
        }
        return null;
    }

    public void setElement(int x, int y, ElementCarte element) {
        if (x >= 0 && x < hauteur && y >= 0 && y < largeur) {
            carte.get(x).set(y, element);
        }
    }

    public void ajouterLigne(ArrayList<ElementCarte> ligne){
        carte.add(ligne);
    }


    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int[] getCoordonnees(String position, int abscisse, int ordonnee) throws Exception{
        int[] coordonnees = new int[2];
        switch (position) {
            case "H":
                if (ordonnee - 1 >= 0) {
                    coordonnees[0]=abscisse;
                    coordonnees[1]=ordonnee-1;
                    return coordonnees;
                } else {
                    throw new DeplacementImpossibleException("La case du dessus n'est pas accessible !");
                }

            case "D":
                if (abscisse + 1 < carte.get(0).size()) {
                    coordonnees[0]=abscisse+1;
                    coordonnees[1]=ordonnee;
                    return coordonnees;
                } else {
                    throw new DeplacementImpossibleException("La case de droite n'est pas accessible !");
                }

            case "G":
                if (abscisse - 1 >= 0) {
                    coordonnees[0]=abscisse-1;
                    coordonnees[1]=ordonnee;
                    return coordonnees;
                } else {
                    throw new DeplacementImpossibleException("La case de gauche n'est pas accessible !");
                }

            case "B":
                if (ordonnee + 1 < carte.size()) {
                    coordonnees[0]=abscisse;
                    coordonnees[1]=ordonnee+1;
                    return coordonnees;
                } else {
                    throw new DeplacementImpossibleException("La case du dessous n'est pas accessible !");
                }
            default:
                throw new CommandeInconnueException("Commande inconnue");
        }
    }



    public String setCaseString(ElementCarte element, String position,int abscisse, int ordonnee) throws Exception {
        int[] coordonnees = getCoordonnees(position,abscisse,ordonnee);
        String res = getCase(coordonnees[0],coordonnees[1]).getApparence();
        getCase(coordonnees[0],coordonnees[1]).setApparence(" ");
        return res;
    }

    public ArrayList<ArrayList<ElementCarte>> getCarte() {
        return carte;
    }

    public ElementCarte getCase(int abscisse, int ordonnee){
        return carte.get(ordonnee).get(abscisse);
    }

    public boolean estCaseVide(int abscisse, int ordonnee){
        return carte.get(ordonnee).get(abscisse).getApparence().equals(" ");
    }

    public void setCase(int abscisse, int ordonnee, ElementCarte element){
        carte.get(ordonnee).set(abscisse,element);
    }
}
