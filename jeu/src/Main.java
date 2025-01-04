
import modele.*;
import controleur.Controleur;

import modele.parties.AvecDangerPartieForet;

import modele.parties.AvecDangerPartieJungle;

import modele.parties.Partie;
import vue.Ihm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


import static modele.CouleursAffichage.ANSI_BLACK;


public class Main {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        Controleur controleur = new Controleur(ihm);


        controleur.lancerPartie();

    }
}
