package modele;

import exceptions.ActionImpossibleException;
import exceptions.CommandeInconnueException;
import exceptions.DeplacementImpossibleException;
import exceptions.ObjetNonRamassableException;
import static modele.CouleursAffichage.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Partie {
    private Personnage personnage;
    private ArrayList<Animal> lesAnimaux;
    private ArrayList<ArrayList<ElementCarte>> carte;

    public Partie(Personnage personnage){
        this.personnage = personnage;
        this.lesAnimaux = new ArrayList<Animal>();
    }


    /**
     * Permet de charger une carte à partir d'un fichier .txt
     * @param fichier le fichier à charger
     */
    public void chargerCarte(String fichier){
        this.carte = new ArrayList<ArrayList<ElementCarte>>();
        try {
            Scanner scanner = new Scanner(new File(fichier));
            int ordonnee =0;
            while (scanner.hasNextLine()) {
                ArrayList<ElementCarte> ligneCarte = new ArrayList<ElementCarte>();
                String line = scanner.nextLine();

                for (int i = 0; i < line.length(); i++){
                    char c = line.charAt(i);
                    ligneCarte.add(ajouterElementCarte(String.valueOf(c),i,ordonnee));
                    //Utilisation du Patron de méthode pour ajouter les éléments dans la carte.
                    // Raison : identifier les éléments selon le type de partie (buisson pour la forêt et rocher pour la jungle par ex)
                    // et leur attribuer la couleur qui leur correspond directement dans la carte.

                }
                this.carte.add(ligneCarte);
                ordonnee++;
            }


            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable : " + e.getMessage());
        }
    }


    public abstract String afficherElement(ElementCarte e);

    /**
     *
     * @return La carte en chaîne de caratères
     */

    public String toString(){
        String res = "";
        for (ArrayList<ElementCarte> elementCartes : carte) {
            for (ElementCarte elementCarte : elementCartes) {
                res += afficherElement(elementCarte);
            }
            res += '\n';
        }
        return res ;
    }

    /**
     * Crée un objet de type Element et lui définit une apparence selon le caractère rencontré
     * @param element l'élément rencontré dans le fichier .txt
     * @param abscisse l'abscisse de l'élément rencontré (utile seulement pour le personnage et les animaux)
     * @param ordonnee l'ordonnee de l'élément rencontré (utile seulement pour le personnage et les animaux)
     * @return un objet de type ElementCarte
     */
    public abstract ElementCarte ajouterElementCarte(String element,int abscisse,int ordonnee);

    /**
     * Permet d'afficher l'apparence de l'animal sur la carte en fonction du type de la partie et de l'animal
     * @param animal L'animal à afficher
     */


    /**
     * Vérifie si l'élément en paramètre est de la nourriture en fonction du type de partie
     * @param element L'élément à vérifier
     * @return true si l'élément est de la nourriture et false sinon
     */
    public abstract boolean estNourriture(String element);

    /**
     * Vérifie si l'élément en paramètre est un animal en fonction du type de la partie
     * @param element L'élément à vérifier
     * @return true si l'élément est un animal, false sinon
     */
    public boolean estAnimal(ElementCarte element) {
        return element instanceof Animal;
    }

    /**
     * Permet de déplacer les animaux présents sur la carte
     */
    public void passerTourAnimaux() {
        for (Animal animal : lesAnimaux) {
            this.setCase(animal.getAbscisse(), animal.getOrdonnee(), new ElementCarte(" "));
            animal.seDeplacer(this.getCarte());
            this.setCase(animal.getAbscisse(), animal.getOrdonnee(), animal);
        }
    }

    /**
     * Permet de déplacer le personnage selon la direction qu'il a choisi
     * @param direction la direction choisie par le personnage
     * @throws Exception se lève si le personnage tente d'effectuer un déplacement impossible (limite de carte, obstacle)
     */
    public void deplacerPersonnage(String direction) throws Exception {
        int[] coordonnees = getCoordonnees(direction, personnage.getAbscisse(), personnage.getOrdonnee());
        int nvAbscisse = coordonnees[0];
        int nvOrdonnee = coordonnees[1];
        if(estCaseVide(nvAbscisse,nvOrdonnee)){
            setCase(personnage.getAbscisse(), personnage.getOrdonnee(), carte.get(nvOrdonnee).get(nvAbscisse));
            setCase(nvAbscisse,nvOrdonnee, personnage);
            carte.get(nvOrdonnee).get(nvAbscisse).nouvellePosition(personnage.getAbscisse(), personnage.getOrdonnee());
            personnage.nouvellePosition(nvAbscisse,nvOrdonnee);
        }else{
            throw new DeplacementImpossibleException("Deplacement impossible !");
        }

    }

    /**
     * Ajoute un objet à l'inventaire du personnage
     * @param positionObjet la position de l'objet à ajouter
     * @throws Exception se lève si la case où doit se trouver l'objet est vide
     */
    public void ramasserObjetPersonnage(String positionObjet) throws Exception{
        int[] coordonneesObjet = getCoordonnees(positionObjet, personnage.getAbscisse(), personnage.getOrdonnee());
        int nvAbscisse = coordonneesObjet[0];
        int nvOrdonnee = coordonneesObjet[1];
        if(estNourriture(getCase(nvAbscisse,nvOrdonnee).getApparence())){
            personnage.ajouterDansInventaire(setCaseString(new ElementCarte(" "),positionObjet,personnage.getAbscisse(),personnage.getOrdonnee()));
        }else{
            throw new ObjetNonRamassableException("Cette case est vide ou l'objet ne peut pas être ramassé !");
        }
    }

    public void frapperAnimalPersonnage(String positionAnimal) throws Exception{

        int[] coordonneesAnimal = getCoordonnees(positionAnimal, personnage.getAbscisse(), personnage.getOrdonnee());
        ElementCarte animal = getCase(coordonneesAnimal[0],coordonneesAnimal[1]);
        if(estAnimal(animal)){
            ((Animal) animal).devenirEnnemi();
        }else{
            throw new ActionImpossibleException("Action impossible");
        }

    }

    public void deposerObjetPersonnage(String position, String objet) throws Exception{
        int[] coordonnees = getCoordonnees(position, personnage.getAbscisse(), personnage.getOrdonnee());
        if(personnage.getNbObjet(objet) >0 && estCaseVide(coordonnees[0], coordonnees[1])){
            personnage.deposerObjet(objet);
            getCase(coordonnees[0],coordonnees[1]).setApparence(objet);
        }
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public ArrayList<Animal> getLesAnimaux() {
        return lesAnimaux;
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

    public String setCaseString(ElementCarte element, String position,int abscisse, int ordonnee) throws Exception {
        int[] coordonnees = getCoordonnees(position,abscisse,ordonnee);
        String res = getCase(coordonnees[0],coordonnees[1]).getApparence();
        getCase(coordonnees[0],coordonnees[1]).setApparence(" ");
        return res;
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


}
