package modele.predateurs;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;
import modele.parties.Partie;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Predateur extends ElementCarte {

    public Predateur(String apparence,int abscisse, int ordonnee){
        super(apparence);
        this.nouvellePosition(abscisse,ordonnee);
    }
    /**
     * Effectue un déplacement aléatoire du prédateur sur la carte.
     * Le prédateur se déplace vers une case vide choisie au hasard parmi les cases adjacentes disponibles.
     *
     * @param carte  La carte sur laquelle le prédateur se déplace.
     * @param nbCase Le nombre de cases à vérifier pour trouver une position vide.
     */
    public void deplacementAleatoire(Carte carte, int nbCase){
        Map<Integer, Integer> abscissesVides = new HashMap<Integer,Integer>();
        Map<Integer, Integer> ordonneesVides = new HashMap<Integer,Integer>();
        int abscisse = this.getAbscisse();
        int ordonnee = this.getOrdonnee();
        int nbCaseVide = 0;
        if(carte.verifierCase(abscisse,ordonnee-nbCase," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisse);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonnee-nbCase);
        }if (carte.verifierCase(abscisse,ordonnee+nbCase, " ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisse);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonnee+nbCase);
        }if (carte.verifierCase(abscisse-nbCase,ordonnee," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisse-nbCase);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonnee);
        }if(carte.verifierCase(abscisse+nbCase,ordonnee," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisse+nbCase);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonnee);
        }
        Random random = new Random();
        if(nbCaseVide >0){
            int numCase =  random.nextInt(nbCaseVide)+1;
            this.nouvellePosition(abscissesVides.get(numCase),ordonneesVides.get(numCase));
        }
    }
    /**
     * Méthode abstraite définissant le comportement de déplacement spécifique d'un prédateur.
     * Les sous-classes doivent implémenter cette méthode.
     *
     * @param carte La carte sur laquelle le prédateur se déplace.
     */
    public abstract void seDeplacer( Carte carte);


}
