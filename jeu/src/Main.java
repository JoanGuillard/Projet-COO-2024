import modele.Partie;
import modele.Personnage;
import modele.SansDangerPartieForet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static modele.CouleursAffichage.ANSI_BLACK;

public class Main {
    public static void main(String[] args) {
        SansDangerPartieForet p = new SansDangerPartieForet(new Personnage());
        p.chargerCarte("jeu/src/cartes/carte.txt");
        System.out.println(p.toString());


    }
}