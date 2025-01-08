package modele;

import exceptions.CommandeInconnueException;
import exceptions.DeplacementImpossibleException;
import modele.predateurs.Predateur;

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

    public void setDimensions(){
        this.hauteur = carte.size();
        this.largeur = carte.get(0).size();
    }

    /**
     * Ajoute une nouvelle ligne d'éléments à la carte.
     *
     * @param ligne Une liste d'éléments représentant une ligne de la carte.
     */
    public void ajouterLigne(ArrayList<ElementCarte> ligne){
        carte.add(ligne);
    }


    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    /**
     * Calcule les coordonnées d'une case adjacente selon une direction donnée.
     *
     * @param position Direction du déplacement : "H" (haut), "D" (droite), "G" (gauche) ou "B" (bas).
     * @param abscisse Coordonnée horizontale de la case de départ.
     * @param ordonnee Coordonnée verticale de la case de départ.
     * @return Un tableau contenant les coordonnées de la case cible.
     * @throws Exception Si le déplacement est impossible ou si la commande est inconnue.
     */
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


    /**
     * Met à jour l'apparence d'une case après déplacement d'un élément.
     *
     * @param element L'élément à déplacer.
     * @param position Direction du déplacement.
     * @param abscisse Coordonnée horizontale de la case de départ.
     * @param ordonnee Coordonnée verticale de la case de départ.
     * @return L'apparence précédente de la case cible.
     * @throws Exception Si le déplacement est impossible ou si la commande est inconnue.
     */
    public String setCaseString(ElementCarte element, String position,int abscisse, int ordonnee) throws Exception {
        int[] coordonnees = getCoordonnees(position,abscisse,ordonnee);
        String res = getCase(coordonnees[0],coordonnees[1]).getApparence();
        getCase(coordonnees[0],coordonnees[1]).setApparence(" ");
        return res;
    }

    public ArrayList<ArrayList<ElementCarte>> getCarte() {
        return this.carte;
    }

    public ElementCarte getCase(int abscisse, int ordonnee){
        return carte.get(ordonnee).get(abscisse);
    }

    /**
     * Vérifie si une case est vide (sans élément).
     *
     * @param abscisse Coordonnée horizontale.
     * @param ordonnee Coordonnée verticale.
     * @return  true si la case est vide, false sinon.
     */
    public boolean estCaseVide(int abscisse, int ordonnee){
        return carte.get(ordonnee).get(abscisse).getApparence().equals(" ");
    }

    public void setCase(int abscisse, int ordonnee, ElementCarte element){
        carte.get(ordonnee).set(abscisse,element);
    }

    /**
     * Vérifie si une case spécifique contient un élément donné.
     *
     * @param abscisse Coordonnée horizontale.
     * @param ordonnee Coordonnée verticale.
     * @param element Apparence de l'élément recherché.
     * @return true si la case contient l'élément, false sinon.
     */
    public boolean verifierCase(int abscisse, int ordonnee , String element){
        return ordonnee < hauteur && ordonnee >= 0 && abscisse >= 0 && abscisse < largeur && getCase(abscisse,ordonnee).getApparence().equals(element);
    }

    /**
     * Vérifie si une case contient un prédateur.
     *
     * @param abscisse Coordonnée horizontale.
     * @param ordonnee Coordonnée verticale.
     * @return true si la case contient un prédateur, false sinon.
     */
    public boolean verifierPredateur(int abscisse, int ordonnee){
        return ordonnee < hauteur && ordonnee >= 0 && abscisse >= 0 && abscisse < largeur && getCase(abscisse,ordonnee) instanceof Predateur;
    }

    /**
     * Vérifie si une case adjacente à une position donnée contient un élément spécifique.
     *
     * @param abscisse Coordonnée horizontale de la case de départ.
     * @param ordonnee Coordonnée verticale de la case de départ.
     * @param element Apparence de l'élément recherché.
     * @return true si une case adjacente contient l'élément, false sinon.
     */
    public boolean estCaseAdjacente(int abscisse, int ordonnee, String element){
        return verifierCase(abscisse+1, ordonnee, element) ||
                verifierCase(abscisse-1, ordonnee, element) ||
                verifierCase(abscisse, ordonnee+1, element) ||
                verifierCase(abscisse, ordonnee-1, element);
    }

}
