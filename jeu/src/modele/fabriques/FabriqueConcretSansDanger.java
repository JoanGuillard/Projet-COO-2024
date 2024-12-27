package modele.fabriques;

import modele.Personnage;
import modele.parties.Partie;
import modele.parties.SansDangerPartieForet;
import modele.parties.SansDangerPartieJungle;

public class FabriqueConcretSansDanger extends FabriqueAbstraitePartie{
    public FabriqueConcretSansDanger(Personnage personnage){
        super(personnage);
    }
    @Override
    public Partie creerPartieForet(Personnage personnage) {
        return  new SansDangerPartieForet(personnage);
    }

    @Override
    public Partie creerPartieJungle(Personnage personnage) {
        return new SansDangerPartieJungle(personnage);
    }
}
