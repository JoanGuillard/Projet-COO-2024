package modele.etats;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;
import modele.parties.Partie;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public abstract class Etat {


    /**
     * Effectue le déplacement d'un animal selon son état.
     *
     * @param animal L'animal à déplacer.
     * @param carte La carte sur laquelle l'animal évolue.
     * @param personnage Le personnage de la partie, utilisé pour certaines interactions.
     */
    public abstract void seDeplacer(Animal animal, Carte carte, Personnage personnage);

    /**
     * Fournit une représentation sous forme de chaîne de l'état de l'animal.
     *
     * @param animal L'animal dont l'état doit être représenté.
     * @return Une chaîne représentant l'état de l'animal.
     */
    public abstract String toString(Animal animal);


    /**
     * Vérifie s'il existe un danger pour l'animal (présence de prédateurs).
     *
     * @param carte La carte de jeu.
     * @param abscisseAnimal La position actuelle de l'animal sur l'axe X.
     * @param ordonneeAnimal La position actuelle de l'animal sur l'axe Y.
     * @param animal L'animal à vérifier.
     * @param personnage Le personnage du jeu, utilisé dans la fuite.
     * @param nbCase Le nombre de cases à vérifier autour de l'animal.
     * @return true si un prédateur est détecté et l'animal fuit, false sinon.
     */
    public boolean verifierDanger(Carte carte, int abscisseAnimal, int ordonneeAnimal,Animal animal,Personnage personnage, int nbCase){
        for(int i =1; i< 5; i++){
            if(carte.verifierPredateur(abscisseAnimal+i,ordonneeAnimal)){
                animal.fuir(carte,personnage,abscisseAnimal-nbCase,ordonneeAnimal);
                return true;
            }else if(carte.verifierPredateur(abscisseAnimal-i,ordonneeAnimal)){
                animal.fuir(carte,personnage,abscisseAnimal+nbCase,ordonneeAnimal);
                return true;
            }else if(carte.verifierPredateur(abscisseAnimal,ordonneeAnimal+i)){
                animal.fuir(carte,personnage,abscisseAnimal,ordonneeAnimal-nbCase);
                return true;
            }else if(carte.verifierPredateur(abscisseAnimal,ordonneeAnimal-i)){
                animal.fuir(carte,personnage,abscisseAnimal,ordonneeAnimal+nbCase);
                return true;
            }
        }
        return false;
    }

    /**
     * Effectue un déplacement aléatoire de l'animal dans une direction vide.
     *
     * @param carte La carte de jeu sur laquelle l'animal se déplace.
     * @param abscisseAnimal La position actuelle de l'animal sur l'axe X.
     * @param ordonneeAnimal La position actuelle de l'animal sur l'axe Y.
     * @param animal L'animal qui se déplace.
     * @param nbCase Le nombre de cases autour de l'animal à vérifier pour un déplacement.
     */
    public void deplacementAleatoire(Carte carte, int abscisseAnimal, int ordonneeAnimal,Animal animal, int nbCase){
        Map<Integer, Integer> abscissesVides = new HashMap<Integer,Integer>();
        Map<Integer, Integer> ordonneesVides = new HashMap<Integer,Integer>();
        int nbCaseVide = 0;
        carte.setCase(abscisseAnimal,ordonneeAnimal,new ElementCarte(animal.getCachette()));
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
            carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), animal);
            animal.setEstCache(false);
            animal.setCachette(" ");
        }

    }



}
