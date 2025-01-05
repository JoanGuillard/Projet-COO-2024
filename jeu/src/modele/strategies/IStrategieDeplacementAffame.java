package modele.strategies;

import modele.Carte;
import modele.Personnage;
import modele.animaux.Animal;
import modele.etats.EtatAffame;

public interface IStrategieDeplacementAffame {

    public void seDeplacer(EtatAffame etat, Animal animal, Carte carte, Personnage personnage);
}
