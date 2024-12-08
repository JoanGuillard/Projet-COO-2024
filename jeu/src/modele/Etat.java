package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Etat {

    public abstract void seDeplacer(Animal animal, ArrayList<ArrayList<ElementCarte>> carte);
    public abstract String toString(Animal animal);

    public boolean verifierCase(int abscisse, int ordonnee , ArrayList<ArrayList<ElementCarte>> carte,String element){
        return ordonnee < carte.size() && ordonnee >= 0 && abscisse >= 0 && abscisse < carte.get(ordonnee).size() && carte.get(ordonnee).get(abscisse).getApparence().equals(element);
    }

    public void deplacementAleatoire(ArrayList<ArrayList<ElementCarte>> carte, int abscisseAnimal, int ordonneeAnimal,Animal animal){
        Map<Integer, Integer> abscissesVides = new HashMap<Integer,Integer>();
        Map<Integer, Integer> ordonneesVides = new HashMap<Integer,Integer>();
        int nbCaseVide = 0;
        if(verifierCase(abscisseAnimal,ordonneeAnimal-1,carte," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal-1);
        }if (verifierCase(abscisseAnimal,ordonneeAnimal+1,carte, " ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal+1);
        }if (verifierCase(abscisseAnimal-1,ordonneeAnimal,carte," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal-1);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal);
        }if(verifierCase(abscisseAnimal+1,ordonneeAnimal,carte," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal+1);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal);
        }
        Random random = new Random();
        int numCase =  random.nextInt((nbCaseVide - 1) + 1) + 1;
        animal.nouvellePosition(abscissesVides.get(numCase),ordonneesVides.get(numCase));
    }

}
