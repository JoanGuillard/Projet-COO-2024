package modele.animaux;

import modele.*;
import modele.etats.Etat;
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
    public Animal(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger){
        this.nbNourritureAmi = nbNourritureAmi;
        this.nbTourSansManger = nbTourSansManger;
        this.cptTourSansManger =0;
        this.cptNourritureAmi = 0;
        this.ami = false;
        this.etat = EtatRassasie.getInstance();
        this.nouvellePosition(abscisse,ordonnee);
        this.regimeAlimentaire = new ArrayList<String>();
        this.estCache = false;
    }

    public void seNourrir(boolean estNourriParAmi){
        this.cptTourSansManger =0;
        if (estNourriParAmi){
            cptNourritureAmi++;
            if(cptNourritureAmi == nbNourritureAmi){
                devenirAmi();
            }
        }
        this.changerEtat(EtatRassasie.getInstance());
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

    public void setEstCache(boolean estCache) {
        this.estCache = estCache;
    }
}
