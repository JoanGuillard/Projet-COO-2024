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
<<<<<<< HEAD
        return  new SansDangerPartieForet(this.personnage,"A");
=======
        return  new SansDangerPartieForet(this.personnage);
>>>>>>> 4d9dec978dd2f10d4b7da6a8429e25d7bc64a381
    }

    @Override
    public Partie creerPartieJungle() {
<<<<<<< HEAD
        return new SansDangerPartieJungle(this.personnage,"T");
=======
        return new SansDangerPartieJungle(this.personnage);
>>>>>>> 4d9dec978dd2f10d4b7da6a8429e25d7bc64a381
    }
}
