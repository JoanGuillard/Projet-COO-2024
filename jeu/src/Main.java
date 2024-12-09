import controleur.ControleurJungle;
import modele.*;
import controleur.Controleur;
import vue.Ihm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


import static modele.CouleursAffichage.ANSI_BLACK;


public class Main {
    public static void main(String[] args) {
        /*Controleur controleur = new Controleur();

        // Lancer le jeu
        System.out.println("Bienvenue dans le jeu de gestion de carte !");
        System.out.println("Vous pouvez choisir de créer une carte ou d'en charger une existante.");
        System.out.println("Saisissez les dimensions ou un fichier pour commencer. Tapez 0 pour quitter à tout moment.");

        controleur.jouerPartie();

        System.out.println("Merci d'avoir joué ! À bientôt.");*/
        Ihm ihm = new Ihm();
        /*Controleur controleur = new Controleur() {
            @Override
            public Partie creerPartie() {
                return null;  // Vous ne créez pas de partie ici, car c'est géré dans les sous-contrôleurs
            }

            @Override
            public void jouerTour(Partie partie) {
                // Implémentation spécifique à chaque sous-contrôleur
            }
        };*/
        Controleur controleur = new ControleurJungle();

        // Lancez le jeu
        controleur.lancerPartie();/*p.chargerCarte("jeu/src/cartesForet/carte.txt");
        System.out.println(p.toString());
        for(int i =0; i<50; i++){
            try {
                int choixAction = ihm.demanderActionJoueur();
                switch (choixAction) {
                    case 1:
                        // Déplacement du personnage
                        String direction = ihm.demanderDirection();
                        if (direction.equals("Q")) {
                            ihm.afficherMessage("Vous avez choisi de quitter le jeu. Merci d'avoir joué !");
                            break;
                        } else {
                            p.deplacerPersonnage(direction);
                            System.out.println(p.toString());

                            ihm.afficherMessage("Vous vous êtes déplacé vers : " + direction);
                        }
                        break;

                    case 2:
                        String directionRamassage = ihm.demanderDirection();
                        // Ramasser un objet
                        p.ramasserObjetPersonnage(directionRamassage);
                        System.out.println(p.toString());
                        ihm.afficherMessage("Vous avez ramassé un objet.");
                        break;

                    case 3:
                        String objet = ihm.demanderObjetADeposer(p.getPersonnage());
                        String directionDepot = ihm.demanderDirection();
                        // Poser un objet
                        p.deposerObjetPersonnage(directionDepot, objet);
                        System.out.println(p.toString());
                        ihm.afficherMessage("Vous avez posé un objet.");
                        break;
                    case 4:
                        String directionAnimal = ihm.demanderDirection();
                        // Interagir avec un animal
                        p.frapperAnimalPersonnage(directionAnimal);
                        ihm.afficherMessage("Vous avez interagi avec un animal.");
                        break;
                }
                    p.passerTourAnimaux();
                    System.out.println(p.toString());

            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }*/
    }
}