package modele.fabriques;

import modele.Personnage;
import modele.parties.*;

public class FabriqueConcretAvecDanger extends FabriqueAbstraitePartie{
    public FabriqueConcretAvecDanger(Personnage personnage){
        super(personnage);
    }

    @Override
    public Partie creerPartieForet() {

        return  new AvecDangerPartieForet(this.personnage,"A");

    }

    @Override
    public Partie creerPartieJungle() {

        return new AvecDangerPartieJungle(this.personnage,"T");

    }
}