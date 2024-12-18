package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Etat {

    public abstract void seDeplacer(Animal animal, Carte carte, Personnage personnage);
    public abstract String toString(Animal animal);



    public void deplacementAleatoire(Carte carte, int abscisseAnimal, int ordonneeAnimal,Animal animal){
        Map<Integer, Integer> abscissesVides = new HashMap<Integer,Integer>();
        Map<Integer, Integer> ordonneesVides = new HashMap<Integer,Integer>();
        int nbCaseVide = 0;
        if(carte.verifierCase(abscisseAnimal,ordonneeAnimal-1," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal-1);
        }if (carte.verifierCase(abscisseAnimal,ordonneeAnimal+1, " ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal+1);
        }if (carte.verifierCase(abscisseAnimal-1,ordonneeAnimal," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal-1);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal);
        }if(carte.verifierCase(abscisseAnimal+1,ordonneeAnimal," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal+1);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal);
        }
        Random random = new Random();
        int numCase =  random.nextInt((nbCaseVide - 1) + 1) + 1;
        animal.nouvellePosition(abscissesVides.get(numCase),ordonneesVides.get(numCase));
    }

}
