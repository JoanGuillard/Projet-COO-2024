package modele.animaux;

import modele.*;
import modele.etats.Etat;
import modele.etats.EtatEffraye;
import modele.etats.EtatJunkie;
import modele.etats.EtatRassasie;

import java.util.ArrayList;
import static modele.CouleursAffichage.*;


public abstract class Animal extends ElementCarte {
    private boolean ami;
    private int nbTourSansManger;
    private int cptTourSansManger;
    private int nbNourritureAmi;
    private int cptNourritureAmi;
    private ArrayList<String> regimeAlimentaire;
    private int nbTourCache;
    private Etat etat;
    private boolean estCache;
    private String cachette;
    private int nbTourJunkie;

    private boolean estMort;


    public Animal(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger){
        this.nbNourritureAmi = nbNourritureAmi;
        this.nbTourSansManger = nbTourSansManger;
        this.cptTourSansManger =0;
        this.cptNourritureAmi = 0;
        this.ami = false;
        this.etat = EtatRassasie.getInstance();
        this.nouvellePosition(abscisse,ordonnee);
        this.regimeAlimentaire = new ArrayList<String>();
        this.cachette = " ";
        this.estCache = false;
        this.estMort = false;
    }

    public void seNourrir(boolean estNourriParAmi,String aliment){
        this.cptTourSansManger =0;
        if(estComestible(aliment)){
            if (estNourriParAmi){
                cptNourritureAmi++;
                if(cptNourritureAmi == nbNourritureAmi){
                    devenirAmi();
                }
            }
            this.changerEtat(EtatRassasie.getInstance());
        }else{
            changerEtat(EtatJunkie.getInstance());
        }

    }

    public void setEstMort(boolean estMort) {
        this.estMort = estMort;
    }

    public boolean isEstMort() {
        return estMort;
    }

    public void devenirAmi(){
        this.ami = true;
    }


    public int getNbTourSansManger() {
        return nbTourSansManger;
    }

    public int getCptTourSansManger() {
        return cptTourSansManger;
    }

    public int getCptNourritureAmi() {
        return cptNourritureAmi;
    }

    public int getNbNourritureAmi() {
        return nbNourritureAmi;
    }

    public ArrayList<String> getRegimeAlimentaire() {
        return regimeAlimentaire;
    }

    public void devenirEnnemi(){
        this.ami = false;
    }

    public void seDeplacer(Carte carte, Personnage personnage){
        etat.seDeplacer(this,carte,personnage);
        carte.setCase(getAbscisse(), getOrdonnee(), this);
    }

    public String toString(){
        if(estCache){
            return ANSI_BLACK_BACKGROUND + ANSI_GREEN + getApparence() + ANSI_RESET;
        }
        return etat.toString(this);
    }


    public void augmenterCptSansManger(){
        this.cptTourSansManger++;
    }


    public void augmenterCptNourritureAmi(){this.cptNourritureAmi++;}

    public void changerEtat(Etat nouvelEtat){
        this.etat = nouvelEtat;
    }

    public boolean isAmi() {
        return ami;
    }

    public int getNbTourJunkie() {
        return nbTourJunkie;
    }

    public void setNbTourJunkie(int nbTourJunkie) {
        this.nbTourJunkie = nbTourJunkie;
    }

    public int getNbTourCache() {
        return nbTourCache;
    }

    public void setNbTourCache(int nbTourCache) {
        this.nbTourCache = nbTourCache;
    }

    public boolean isEstCache() {
        return estCache;
    }

    public String getCachette() {
        return cachette;
    }

    public void setCachette(String cachette) {
        this.cachette = cachette;
    }

    public void setEstCache(boolean estCache) {
        this.estCache = estCache;
    }

    public boolean seCacher(Carte carte,String cachette1, String cachette2){
        int abscisseAnimal = getAbscisse();
        int ordonneeAnimal = getOrdonnee();
        if(carte.verifierCase(abscisseAnimal-1,ordonneeAnimal,cachette1) || carte.verifierCase(abscisseAnimal-1,ordonneeAnimal,cachette2)){
        this.setCachette(carte.getCase(abscisseAnimal,ordonneeAnimal).getApparence());
        this.setEstCache(true);
        this.nouvellePosition(abscisseAnimal-1,ordonneeAnimal);
        this.changerEtat(EtatEffraye.getInstance());
        return true;
    }
        else if(carte.verifierCase(abscisseAnimal+1,ordonneeAnimal,cachette1) || carte.verifierCase(abscisseAnimal-1,ordonneeAnimal,cachette2)){
        this.setCachette(carte.getCase(abscisseAnimal,ordonneeAnimal).getApparence());
        this.setEstCache(true);
        this.nouvellePosition(abscisseAnimal+1,ordonneeAnimal);
        this.changerEtat(EtatEffraye.getInstance());

        return true;
    }
        else if(carte.verifierCase(abscisseAnimal,ordonneeAnimal-1,cachette1) || carte.verifierCase(abscisseAnimal-1,ordonneeAnimal,cachette2)){
        this.setCachette(carte.getCase(abscisseAnimal,ordonneeAnimal).getApparence());
        this.setEstCache(true);
        this.nouvellePosition(abscisseAnimal,ordonneeAnimal-1);
        this.changerEtat(EtatEffraye.getInstance());

        return true;
    }
        else if(carte.verifierCase(abscisseAnimal,ordonneeAnimal+1,cachette1) || carte.verifierCase(abscisseAnimal-1,ordonneeAnimal,cachette2)){
        this.setCachette(carte.getCase(abscisseAnimal,ordonneeAnimal).getApparence());
        this.setEstCache(true);
        this.nouvellePosition(abscisseAnimal,ordonneeAnimal+1);
        this.changerEtat(EtatEffraye.getInstance());

        return true;
    }else{
        return false;
    }
}

    public abstract boolean estComestible(String aliment);
}
