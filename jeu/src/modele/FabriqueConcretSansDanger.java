package modele;

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
