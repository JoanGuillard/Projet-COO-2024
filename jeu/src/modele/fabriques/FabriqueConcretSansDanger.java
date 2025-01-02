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
    public Partie creerPartieForet() {
        return  new SansDangerPartieForet(this.personnage,"A");
    }

    @Override
    public Partie creerPartieJungle() {
        return new SansDangerPartieJungle(this.personnage,"T");
    }
}
