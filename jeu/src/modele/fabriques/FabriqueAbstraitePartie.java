package modele.fabriques;

import modele.Personnage;
import modele.parties.Partie;

public abstract class FabriqueAbstraitePartie {
    protected Personnage personnage;

    public FabriqueAbstraitePartie(Personnage personnage) {
        this.personnage = personnage;
    }

    public abstract Partie creerPartieJungle();
    public abstract Partie creerPartieForet();


}
