package modele.etats;

import modele.Carte;
import modele.Personnage;
import modele.animaux.Animal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Etat {

    public abstract void seDeplacer(Animal animal, Carte carte, Personnage personnage);
    public abstract String toString(Animal animal);


    public boolean verifierDanger(Carte carte, int abscisseAnimal, int ordonneeAnimal,Animal animal,Personnage personnage){
        for(int i =1; i< 5; i++){
            if(carte.verifierPredateur(abscisseAnimal+i,ordonneeAnimal)){
                animal.fuir(carte,personnage,abscisseAnimal-1,ordonneeAnimal);
                return true;
            }else if(carte.verifierPredateur(abscisseAnimal-i,ordonneeAnimal)){
                animal.fuir(carte,personnage,abscisseAnimal+1,ordonneeAnimal);
                return true;
            }else if(carte.verifierPredateur(abscisseAnimal,ordonneeAnimal+i)){
                animal.fuir(carte,personnage,abscisseAnimal,ordonneeAnimal-1);
                return true;
            }else if(carte.verifierPredateur(abscisseAnimal,ordonneeAnimal-i)){
                animal.fuir(carte,personnage,abscisseAnimal,ordonneeAnimal+1);
                return true;
            }
        }
        return false;
    }


    public void deplacementAleatoire(Carte carte, int abscisseAnimal, int ordonneeAnimal,Animal animal, int nbCase){
        Map<Integer, Integer> abscissesVides = new HashMap<Integer,Integer>();
        Map<Integer, Integer> ordonneesVides = new HashMap<Integer,Integer>();
        int nbCaseVide = 0;
        if(carte.verifierCase(abscisseAnimal,ordonneeAnimal-nbCase," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal-nbCase);
        }if (carte.verifierCase(abscisseAnimal,ordonneeAnimal+nbCase, " ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal+nbCase);
        }if (carte.verifierCase(abscisseAnimal-nbCase,ordonneeAnimal," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal-nbCase);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal);
        }if(carte.verifierCase(abscisseAnimal+nbCase,ordonneeAnimal," ")){
            nbCaseVide++;
            abscissesVides.putIfAbsent(nbCaseVide,abscisseAnimal+nbCase);
            ordonneesVides.putIfAbsent(nbCaseVide,ordonneeAnimal);
        }
        Random random = new Random();
        if(nbCaseVide >0){
            int numCase =  random.nextInt(nbCaseVide)+1;
            animal.nouvellePosition(abscissesVides.get(numCase),ordonneesVides.get(numCase));
            animal.setCachette(" ");
        }

    }



}
