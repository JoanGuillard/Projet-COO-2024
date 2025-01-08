package modele.fabriques;

import modele.Personnage;
import modele.parties.Partie;

public abstract class FabriqueAbstraitePartie {
    protected Personnage personnage;

    public FabriqueAbstraitePartie(Personnage personnage) {
        this.personnage = personnage;
    }

    /**
     * Méthode abstraite pour créer une partie de type Jungle.
     * Cette méthode sera implémentée dans les sous-classes pour créer une partie de jungle spécifique.
     *
     * @return Un objet Partie représentant la partie de type Jungle.
     */
    public abstract Partie creerPartieJungle();

    /**
     * Méthode abstraite pour créer une partie de type Forêt.
     * Cette méthode sera implémentée dans les sous-classes pour créer une partie de forêt spécifique.
     *
     * @return Un objet Partie représentant la partie de type Forêt.
     */
    public abstract Partie creerPartieForet();


}
