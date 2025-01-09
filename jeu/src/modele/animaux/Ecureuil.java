package modele.animaux;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.etats.EtatEffraye;
import modele.etats.EtatJunkie;
import modele.etats.EtatSurAmi;
import modele.strategies.StrategieAffameEcureuil;


public class Ecureuil extends Animal{



    public Ecureuil(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence("E");
        this.getRegimeAlimentaire().add("G");
        this.getRegimeAlimentaire().add("C");
        this.getRegimeAlimentaire().add("M");
        this.setNbTourJunkie(5);
        this.setNbTourCache(0);
        this.setStrategieAffame(StrategieAffameEcureuil.getInstance());
    }

    @Override
    public void intoxication() {
        this.changerEtat(EtatJunkie.getInstance());
    }

    @Override
    public void devenirAmi(Personnage personnage,Carte carte) {
        this.setAmi(true);
    }


    @Override
    public void fuir(Carte carte, Personnage personnage, int nvAbscisse, int nvOrdonnee) {
        if(!isEstCache()) {
            if (isAmi() && carte.estCaseAdjacente(getAbscisse(), getOrdonnee(), personnage.getApparence())) {
                seCacherSurAmi(personnage,carte);
            } else if (seCacher(carte, "A")){
                return;
            } else if (seCacher(carte, "B")){
                return;
            } else if(carte.verifierCase(nvAbscisse,nvOrdonnee," ")){
                fuirPredateur(carte,personnage,nvAbscisse,nvOrdonnee);
            }
        }
    }


    @Override
    public boolean estComestible(String aliment) {
        return !aliment.equals("M");
    }


}
