package modele.animaux;

import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.etats.EtatAffame;
import modele.etats.EtatEffraye;
import modele.etats.EtatJunkie;
import modele.etats.EtatRassasie;

import static modele.CouleursAffichage.*;
public class Singe extends Animal{

    private boolean estInconscient;

    private int nbTourSurAmi = 0;
    public Singe(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger) {
        super(abscisse, ordonnee, nbNourritureAmi, nbTourSansManger);
        setApparence("S");
        this.getRegimeAlimentaire().add("B");
        this.getRegimeAlimentaire().add("C");
        this.getRegimeAlimentaire().add("H");
        this.setNbTourJunkie(3);
        this.setNbTourCache(0);
    }



    @Override
    public void intoxication() {
        estInconscient=true;
        this.changerEtat(EtatRassasie.getInstance());
    }

    @Override
    public void devenirAmi(Personnage personnage) {
        this.setAmi(true);
        personnage.ajouterAmiCache(this);
        this.nouvellePosition(personnage.getAbscisse(), personnage.getOrdonnee());
        this.setEstCache(true);
        nbTourSurAmi=3;
    }

    @Override
    public void seDeplacer(Carte carte, Personnage personnage) {
        if(estInconscient) {
            getEtat().deplacementAleatoire(carte, getAbscisse(), getOrdonnee(), this, 1);
            augmenterCptTourJunkie();
            if(getNbTourJunkie() == getCptTourJunkie()){
                estInconscient = false;
                this.changerEtat(EtatAffame.getInstance());
                setCptTourJunkie(0);
            }
        }
        else {
            if (getNbTourCache() > 0) {
                nbTourSurAmi--;
                if (getEtat().verifierDanger(carte, getAbscisse(), getOrdonnee(), this, personnage)) {
                    System.out.println("OUHOUH AHAH !");
                }
                this.nouvellePosition(personnage.getAbscisse(), personnage.getOrdonnee());
                if (nbTourSurAmi == 0) {
                    setEstCache(false);
                    personnage.supprimerAmiCache(this);
                }
            } else {
                // si le singe est rassasié
                if(getCptTourSansManger() < getNbTourSansManger()) {
                    getEtat().seDeplacer(this, carte, personnage);
                } else if(!getEtat().verifierDanger(carte, getAbscisse(), getOrdonnee(), this, personnage)) {
                    getEtat().seDeplacer(this, carte, personnage);
                    if(getCptTourSansManger() >= getNbTourSansManger()){
                        getEtat().deplacementAleatoire(carte,getAbscisse(),getOrdonnee(),this,1);
                    }
                }
                if (nbTourSurAmi == 0) {
                    carte.setCase(getAbscisse(), getOrdonnee(), this);
                }
            }
        }


    }

    @Override
    public void fuir(Carte carte, Personnage personnage, int nvAbscisse, int nvOrdonnee) {
        if(!isEstCache()) {
            if (seCacher(carte, "T")) {
                return;
            } else if (seCacher(carte, "R")) {
                return;
            } else {
                carte.setCase(getAbscisse(), getOrdonnee(), new ElementCarte(getCachette()));
                this.nouvellePosition(nvAbscisse, nvOrdonnee);
            }
        }
    }

    public String toString(){
        if(estInconscient){
            return ANSI_YELLOW_BACKGROUND + ANSI_PURPLE + getApparence() + ANSI_RESET;
        }else{
            return super.toString();
        }
    }



    @Override
    public boolean estComestible(String aliment) {
        return !aliment.equals("H");
    }
}
