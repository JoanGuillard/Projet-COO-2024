package modele.predateurs;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Predateur extends ElementCarte {

    public Predateur(String apparence,int abscisse, int ordonnee){
        super(apparence);
        this.nouvellePosition(abscisse,ordonnee);
    }

    public void deplacementAleatoire(Carte carte){
        Map<Integer, Integer> abscissesVides = new HashMap<Integer,Integer>();
        Map<Integer, Integer> ordonneesVides = new HashMap<Integer,Integer>();
        int abscisse = this.getAbscisse();
        int ordonnee = this.getOrdonnee();
        int nbCaseVide = 0;
        if(carte.verifierCase(abscisse,ordonnee-1," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisse);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonnee-1);
        }if (carte.verifierCase(abscisse,ordonnee+1, " ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisse);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonnee+1);
        }if (carte.verifierCase(abscisse-1,ordonnee," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisse-1);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonnee);
        }if(carte.verifierCase(abscisse+1,ordonnee," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisse+1);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonnee);
        }
        Random random = new Random();
        int numCase =  random.nextInt((nbCaseVide - 1) + 1) + 1;
        this.nouvellePosition(abscissesVides.get(numCase),ordonneesVides.get(numCase));
    }

    public abstract void seDeplacer( Carte carte);


}
