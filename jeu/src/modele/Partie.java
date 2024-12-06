package modele;

import exceptions.ActionImpossibleException;
import exceptions.CommandeInconnueException;
import exceptions.DeplacementImpossibleException;
import exceptions.ObjetNonRamassableException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Partie {
    private Personnage personnage;
    private ArrayList<Animal> lesAnimaux;
    private ArrayList<ArrayList<String>> carte;

    public Partie(Personnage personnage){
        this.personnage = personnage;
    }



    public void chargerCarte(String fichier){
        this.carte = new ArrayList<ArrayList<String>>();
        try {
            Scanner scanner = new Scanner(new File(fichier));

            while (scanner.hasNextLine()) {
                ArrayList<String> ligneCarte = new ArrayList<String>();
                String line = scanner.nextLine();

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    ajouterElementCarte(String.valueOf(c)); //Utilisation du Patron de méthode pour ajouter les éléments dans la carte.
                    // Raison : identifier les éléments selon le type de partie (buisson pour la forêt et rocher pour la jungle par ex)
                    // et leur attribuer les couleurs qui leur correspond directement dans la carte.
                }
                this.carte.add(ligneCarte);
            }


            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable : " + e.getMessage());
        }
    }

    public String toString(){
        String res = "";
        for(int i =0; i< carte.size(); i++){
            for(int j =0; j < carte.get(i).size(); j++){
                res+=  carte.get(i).get(j)  ;
            }
            res+='\n';
        }
        return res ;
    }

    public abstract void ajouterElementCarte(String element);

    public abstract void nvPositionAnimal(Animal animal);

    public abstract boolean estNourriture(String element);

    public abstract boolean estAnimal(String element);

    public void passerTourAnimaux() throws Exception {
        for(int i =0; i < lesAnimaux.size(); i++){
            lesAnimaux.get(i).seDeplacer(this.getCarte());
            nvPositionAnimal(lesAnimaux.get(i));
        }
    }

    public void deplacerPersonnage(String position) throws Exception {
        int[] coordonnees = getCoordonnees(position, personnage.getAbscisse(), personnage.getOrdonnee());
        int nvAbscisse = coordonnees[0];
        int nvOrdonnee = coordonnees[1];
        if(estCaseVide(nvAbscisse,nvOrdonnee)){
            setCase(personnage.getAbscisse(), personnage.getOrdonnee(), " ");
            setCase(nvAbscisse,nvOrdonnee, personnage.getApparence());
            personnage.nouvellePosition(nvAbscisse,nvOrdonnee);
        }else{
            throw new DeplacementImpossibleException("Deplacement impossible !");
        }

    }

    public void ramasserObjetPersonnage(String positionObjet) throws Exception{
        int[] coordonneesObjet = getCoordonnees(positionObjet, personnage.getAbscisse(), personnage.getOrdonnee());
        int nvAbscisse = coordonneesObjet[0];
        int nvOrdonnee = coordonneesObjet[1];
        if(estNourriture(getCase(nvAbscisse,nvOrdonnee))){
            personnage.ajouterDansInventaire(setCaseString(" ",positionObjet,personnage.getAbscisse(),personnage.getOrdonnee()));
        }else{
            throw new ObjetNonRamassableException("Cette case est vide ou l'objet ne peut pas être ramassé !");
        }
    }

    public void frapperAnimalPersonnage(String positionAnimal) throws Exception{
        if(estAnimal(positionAnimal)){
            int[] coordonneesAnimal = getCoordonnees(positionAnimal, personnage.getAbscisse(), personnage.getOrdonnee());
            Animal animal = getAnimal(coordonneesAnimal[0],coordonneesAnimal[1]);
            animal.devenirEnnemi();
        }else{
            throw new ActionImpossibleException("Action impossible");
        }

    }

    public void deposerObjetPersonnage(String position, String objet) throws Exception{
        int[] coordonnees = getCoordonnees(position, personnage.getAbscisse(), personnage.getOrdonnee());
        if(personnage.getNbObjet(objet) >0 && estCaseVide(coordonnees[0], coordonnees[1])){
            personnage.deposerObjet(objet);
            setCase(coordonnees[0],coordonnees[1],objet);
        }
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public ArrayList<Animal> getLesAnimaux() {
        return lesAnimaux;
    }

    public ArrayList<ArrayList<String>> getCarte() {
        return carte;
    }

    public String getCase(int abscisse, int ordonnee){
        return carte.get(ordonnee).get(abscisse);
    }

    public boolean estCaseVide(int abscisse, int ordonnee){
        if(carte.get(ordonnee).get(abscisse).equals(" ")){
            return true;
        }
        return false;
    }

    public void setCase(int abscisse, int ordonnee, String element){
        carte.get(ordonnee).set(abscisse,element);

    }

    public String setCaseString(String element, String position,int abscisse, int ordonnee) throws Exception {
        int[] coordonnees = getCoordonnees(position,abscisse,ordonnee);
        String res = getCase(coordonnees[0],coordonnees[1]);
        setCase(coordonnees[0],coordonnees[1]," ");
        return res;
    }

    public int[] getCoordonnees(String position, int abscisse, int ordonnee) throws Exception{
        int[] coordonnees = new int[2];
        switch (position) {
            case "z":
                if (ordonnee - 1 >= 0) {
                    coordonnees[0]=abscisse;
                    coordonnees[1]=ordonnee-1;
                    return coordonnees;
                } else {
                    throw new DeplacementImpossibleException("La case du dessus n'est pas accessible !");
                }

            case "d":
                if (abscisse + 1 < carte.get(0).size()) {
                    coordonnees[0]=abscisse+1;
                    coordonnees[1]=ordonnee;
                    return coordonnees;
                } else {
                    throw new DeplacementImpossibleException("La case de droite n'est pas accessible !");
                }

            case "q":
                if (abscisse - 1 >= 0) {
                    coordonnees[0]=abscisse-1;
                    coordonnees[1]=ordonnee;
                    return coordonnees;
                } else {
                    throw new DeplacementImpossibleException("La case de gauche n'est pas accessible !");
                }

            case "s":
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

    public Animal getAnimal(int abscisse, int ordonnee) throws ActionImpossibleException {
        for(Animal animal : lesAnimaux){
            if(animal.getAbscisse() == abscisse && animal.getOrdonnee() == ordonnee){
                return animal;
            }
        }
        throw new ActionImpossibleException("Action impossible");
    }
}
