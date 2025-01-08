package modele.etats;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.animaux.Animal;

import static modele.CouleursAffichage.*;


public class EtatAffame extends Etat{
    private static EtatAffame instance;

    public EtatAffame(){}
    public static synchronized EtatAffame getInstance(){
        if(instance == null){
            instance = new EtatAffame();
        }
        return instance;
    }

    @Override
    public void seDeplacer(Animal animal, Carte carte, Personnage personnage){
        animal.getStrategieAffame().seDeplacer(this,animal,carte,personnage);
    }


    /**
     * Cherche de la nourriture autour de l'animal dans sa position actuelle.
     *
     * @param animal L'animal qui cherche de la nourriture.
     * @param carte La carte de jeu.
     * @param personnage Le personnage de la partie.
     * @param abscisseAnimal La position actuelle de l'animal sur l'axe X.
     * @param ordonneeAnimal La position actuelle de l'animal sur l'axe Y.
     * @return true si la nourriture a été trouvée et consommée, false sinon.
     */
    public boolean chercherNourriture(Animal animal, Carte carte, Personnage personnage,int abscisseAnimal,int ordonneeAnimal){
        for (String nourriture : animal.getRegimeAlimentaire()) {
            if (carte.verifierCase(abscisseAnimal, ordonneeAnimal - 1, nourriture)) {
                carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), new ElementCarte(animal.getCachette()));
                animal.nouvellePosition(abscisseAnimal, ordonneeAnimal - 1);
                carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), animal);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal, ordonneeAnimal - 1, personnage.getApparence()), nourriture, personnage,carte);
                return true;

            } else if (carte.verifierCase(abscisseAnimal, ordonneeAnimal + 1, nourriture)) {
                carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), new ElementCarte(animal.getCachette()));
                animal.nouvellePosition(abscisseAnimal, ordonneeAnimal + 1);
                carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), animal);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal, ordonneeAnimal + 1, personnage.getApparence()), nourriture,personnage,carte);
                return true;

            } else if (carte.verifierCase(abscisseAnimal - 1, ordonneeAnimal, nourriture)) {
                carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), new ElementCarte(animal.getCachette()));
                animal.nouvellePosition(abscisseAnimal - 1, ordonneeAnimal);
                carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), animal);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal - 1, ordonneeAnimal, personnage.getApparence()), nourriture,personnage,carte);
                return true;
            } else if (carte.verifierCase(abscisseAnimal + 1, ordonneeAnimal, nourriture)) {
                carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), new ElementCarte(animal.getCachette()));
                animal.nouvellePosition(abscisseAnimal + 1, ordonneeAnimal);
                carte.setCase(animal.getAbscisse(), animal.getOrdonnee(), animal);
                animal.seNourrir(carte.estCaseAdjacente(abscisseAnimal + 1, ordonneeAnimal, personnage.getApparence()), nourriture,personnage,carte);
                return true;
            }
        }
        return false;

    }

    @Override
    public String toString(Animal animal) {
        return ANSI_BLACK_BACKGROUND + ANSI_RED + animal.getApparence() + ANSI_RESET;
    }
}
