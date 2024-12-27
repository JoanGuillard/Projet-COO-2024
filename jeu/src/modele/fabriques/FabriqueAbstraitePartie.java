package modele.fabriques;

import modele.Personnage;
import modele.parties.Partie;

public abstract class FabriqueAbstraitePartie {
    private Personnage personnage;

    public FabriqueAbstraitePartie(Personnage personnage) {
        this.personnage = personnage;
    }

    public abstract Partie creerPartieJungle(Personnage personnage);
    public abstract Partie creerPartieForet(Personnage personnage);


}
