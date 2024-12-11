package modele;

import java.util.ArrayList;

public abstract class Animal extends ElementCarte{
    private boolean ami;

    private int nbTourSansManger;
    private int cptTourSansManger;
    private int nbNourritureAmi;
    private int cptNourritureAmi;
    private ArrayList<String> regimeAlimentaire;
    private Etat etat;

    public Animal(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger){
        this.nbNourritureAmi = nbNourritureAmi;
        this.nbTourSansManger = nbTourSansManger;
        this.cptTourSansManger =0;
        this.cptNourritureAmi = 0;
        this.ami = false;
        this.etat = EtatRassasie.getInstance();
        this.nouvellePosition(abscisse,ordonnee);
        this.regimeAlimentaire = new ArrayList<String>();
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
    }

    public String toString(){
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
}
