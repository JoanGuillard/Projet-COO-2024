package modele.animaux;

import modele.*;
import modele.etats.Etat;
import modele.etats.EtatEffraye;
import modele.etats.EtatJunkie;
import modele.etats.EtatRassasie;
import modele.strategies.IStrategieDeplacementAffame;

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

    private int cptTourJunkie;
    private boolean estMort;

    private IStrategieDeplacementAffame strategieAffame;


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

    public void seNourrir(boolean estNourriParAmi,String aliment,Personnage personnage,Carte carte){
        this.cptTourSansManger =0;
        this.setCachette(" ");
        this.setEstCache(false);
        if(estComestible(aliment)){
            this.changerEtat(EtatRassasie.getInstance());
            if (estNourriParAmi){
                cptNourritureAmi++;
                if(cptNourritureAmi == nbNourritureAmi){
                    devenirAmi(personnage,carte);
                }
            }

        }else{
            intoxication();
        }

    }

    public void setCptTourJunkie(int cptTourJunkie) {
        this.cptTourJunkie = cptTourJunkie;
    }

    public abstract void intoxication();

    public void setEstMort(boolean estMort) {
        this.estMort = estMort;
    }

    public boolean isEstMort() {
        return estMort;
    }

    public abstract void devenirAmi(Personnage personnage, Carte carte);

    public void setAmi(boolean ami) {
        this.ami = ami;
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
    }

    public String toString(){
        if(estCache){
            return ANSI_BLACK_BACKGROUND + ANSI_GREEN + getApparence() + ANSI_RESET;
        }
        return etat.toString(this);
    }

    public Etat getEtat() {
        return etat;
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

    public boolean seCacher(Carte carte,String cachette){
        int abscisseAnimal = getAbscisse();
        int ordonneeAnimal = getOrdonnee();
        if(carte.verifierCase(abscisseAnimal-1,ordonneeAnimal,cachette)){
            this.setEstCache(true);
            carte.setCase(abscisseAnimal,ordonneeAnimal,new ElementCarte(getCachette()));
            this.setCachette(cachette);
            this.nouvellePosition(abscisseAnimal-1,ordonneeAnimal);
            carte.setCase(getAbscisse(),getOrdonnee(),this);
            return true;
        } else if(carte.verifierCase(abscisseAnimal+1,ordonneeAnimal,cachette)){
            this.setEstCache(true);
            carte.setCase(abscisseAnimal,ordonneeAnimal,new ElementCarte(getCachette()));
            this.setCachette(cachette);
            this.nouvellePosition(abscisseAnimal+1,ordonneeAnimal);
            carte.setCase(getAbscisse(),getOrdonnee(),this);
            return true;
        } else if(carte.verifierCase(abscisseAnimal,ordonneeAnimal-1,cachette) ){
            this.setEstCache(true);
            carte.setCase(abscisseAnimal,ordonneeAnimal,new ElementCarte(getCachette()));
            this.setCachette(cachette);
            this.nouvellePosition(abscisseAnimal,ordonneeAnimal-1);
            carte.setCase(getAbscisse(),getOrdonnee(),this);
            return true;
        } else if(carte.verifierCase(abscisseAnimal,ordonneeAnimal+1,cachette)){
            this.setEstCache(true);
            carte.setCase(abscisseAnimal,ordonneeAnimal,new ElementCarte(getCachette()));
            this.setCachette(cachette);
            this.nouvellePosition(abscisseAnimal,ordonneeAnimal+1);
            carte.setCase(getAbscisse(),getOrdonnee(),this);
            return true;
    }else{
        return false;
    }
}

    public abstract void fuir(Carte carte, Personnage personnage, int nvAbscisse, int nvOrdonnee);

    public abstract boolean estComestible(String aliment);

    public boolean estCacheAvecAmi(Personnage p){
        return p.getLesAmisCaches().contains(this);
    }

    public int getCptTourJunkie() {
        return cptTourJunkie;
    }

    public void augmenterCptTourJunkie(){
        cptTourJunkie++;
    }

    public void augmenterNbTourCache(){
        nbTourCache++;
    }

    public IStrategieDeplacementAffame getStrategieAffame() {
        return strategieAffame;
    }

    public void setStrategieAffame(IStrategieDeplacementAffame strategieAffame) {
        this.strategieAffame = strategieAffame;
    }
}
