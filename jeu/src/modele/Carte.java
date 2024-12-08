package modele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Carte {
    private static Carte instance;
    private  ArrayList<ArrayList<ElementCarte>> carte ;
    private int largeur;
    private int hauteur;


    private Carte() {
        this.carte = new ArrayList<>();
    }

    public static Carte getInstance() {
        if (instance == null) {
            instance = new Carte();
        }
        return instance;
    }

    // Charger une carte depuis un fichier
    public void chargerCarte(String fichier,Partie partie){
        this.carte = new ArrayList<ArrayList<ElementCarte>>();
        try {
            Scanner scanner = new Scanner(new File(fichier));
            int ordonnee =0;
            while (scanner.hasNextLine()) {
                ArrayList<ElementCarte> ligneCarte = new ArrayList<ElementCarte>();
                String line = scanner.nextLine();

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    ligneCarte.add(partie.ajouterElementCarte(String.valueOf(c),i,ordonnee));
                    //Utilisation du Patron de méthode pour ajouter les éléments dans la carte.
                    // Raison : identifier les éléments selon le type de partie (buisson pour la forêt et rocher pour la jungle par ex)
                    // et leur attribuer la couleur qui leur correspond directement dans la carte.

                }
                this.carte.add(ligneCarte);
                ordonnee++;
            }


            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable : " + e.getMessage());
        }
    }

    public void creerNouvelleCarte(int abscisse, int ordonne, Partie partie) {
        this.carte.clear();
        this.hauteur = ordonne;
        this.largeur = abscisse;

        for (int i = 0; i < hauteur; i++) {
            ArrayList<ElementCarte> ligneCarte = new ArrayList<>();
            for (int j = 0; j < largeur; j++) {
                if (i == 0 || i == hauteur - 1 || j == 0 || j == largeur - 1) {
                    ligneCarte.add(partie.ajouterElementCarte("A", i, j));
                } else {
                    ligneCarte.add(partie.ajouterElementCarte(" ", i, j));
                }
            }
            carte.add(ligneCarte);
        }
    }

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


    public int getLargeur() {
        return largeur;
    }

    public int getHauteur() {
        return hauteur;
    }
}
