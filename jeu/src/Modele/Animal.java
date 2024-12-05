package Modele;

import java.util.ArrayList;

public abstract class Animal {
    private boolean ami;
    private int abscisse;
    private int ordonnee;
    private int nbTourSansManger;
    private int cptTourSansManger;
    private int nbNourritureAmi;
    private int cptNourritureAmi;
    private ArrayList<String> regimeAlimentaire;
    private Etat etat;

    public Animal(int abscisse, int ordonnee, int nbNourritureAmi, int nbTourSansManger){
        this.abscisse = abscisse;
        this.ordonnee = ordonnee;
        this.nbNourritureAmi = nbNourritureAmi;
        this.nbTourSansManger = nbTourSansManger;
        this.cptTourSansManger =0;
        this.cptNourritureAmi = 0;
        this.ami = false;
    }

    public void nouvellePosition(int abscisse, int ordonnee){
        this.ordonnee = ordonnee;
        this.abscisse = abscisse;
    }

    public void seNourrir(boolean estNourriParAmi){
        this.cptTourSansManger =0;
        if (estNourriParAmi){
            cptNourritureAmi++;
            if(cptNourritureAmi == nbNourritureAmi){
                devenirAmi();
            }
        }
    }

    public void devenirAmi(){
        this.ami = true;
    }

    public int getAbscisse() {
        return abscisse;
    }

    public int getOrdonnee() {
        return ordonnee;
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

    public void seDeplacer(ArrayList<ArrayList<String>> carte){
        etat.seDeplacer(this,carte);
    }

    public abstract String toString();

    public void augmenterCptSansManger(){
        this.cptTourSansManger++;
    }

    public void augmenterCptNourritureAmi(){this.cptNourritureAmi++;}

    public void changerEtat(Etat nouvelEtat){
        this.etat = nouvelEtat;
    }
}
