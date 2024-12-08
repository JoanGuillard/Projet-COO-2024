import modele.Partie;
import modele.Personnage;
import modele.SansDangerPartieForet;
import controleur.Controleur;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static modele.CouleursAffichage.ANSI_BLACK;

public class Main {
    public static void main(String[] args) {
        Controleur controleur = new Controleur();

        // Lancer le jeu
        System.out.println("Bienvenue dans le jeu de gestion de carte !");
        System.out.println("Vous pouvez choisir de créer une carte ou d'en charger une existante.");
        System.out.println("Saisissez les dimensions ou un fichier pour commencer. Tapez 0 pour quitter à tout moment.");

        controleur.jouerPartie();

        System.out.println("Merci d'avoir joué ! À bientôt.");
        SansDangerPartieForet p = new SansDangerPartieForet(new Personnage());
        p.chargerCarte("jeu/src/cartes/carte.txt");
        System.out.println(p.toString());


    }
}