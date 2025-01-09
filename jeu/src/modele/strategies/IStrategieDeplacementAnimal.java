package modele.strategies;

import modele.Carte;
import modele.Personnage;
import modele.animaux.Animal;
import modele.etats.EtatAffame;

public interface IStrategieDeplacementAnimal {

    /**
     * Déplace un animal affamé en fonction de son état actuel, de la carte et de la position du personnage.
     *
     * @param etat L'état  de l'animal.
     * @param animal L'animal qui doit se déplacer.
     * @param carte La carte sur laquelle se déroule le déplacement.
     * @param personnage .
     */
    public void seDeplacer(EtatAffame etat, Animal animal, Carte carte, Personnage personnage);
}
