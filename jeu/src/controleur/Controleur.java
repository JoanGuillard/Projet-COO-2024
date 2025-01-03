package controleur;

import modele.fabriques.FabriqueAbstraitePartie;
import modele.fabriques.FabriqueConcretAvecDanger;
import modele.fabriques.FabriqueConcretSansDanger;
import modele.parties.Partie;
import modele.Personnage;
import modele.parties.SansDangerPartieForet;
import modele.parties.SansDangerPartieJungle;
import vue.Ihm;

public class Controleur {
    private Ihm ihm;
    private FabriqueAbstraitePartie fabriquePartie;

    public Controleur(Ihm ihm) {
        this.ihm = ihm;

    }

    public void lancerPartie() {
        Partie partie;
        ihm.afficherMessage("Bienvenue dans le jeu !");
        int choixTheme = ihm.demanderTheme();
        switch (choixTheme) {
            case 1:
                fabriquePartie = new FabriqueConcretSansDanger(new Personnage());
                partie = fabriquePartie.creerPartieForet();
                break;

            case 2:
                fabriquePartie = new FabriqueConcretSansDanger(new Personnage());
                  partie= fabriquePartie.creerPartieJungle();
                break;
            case 3:
                fabriquePartie = new FabriqueConcretAvecDanger(new Personnage());
                partie = fabriquePartie.creerPartieForet();
                break;

            case 4:
                fabriquePartie = new FabriqueConcretAvecDanger(new Personnage());
                partie= fabriquePartie.creerPartieJungle();
                break;

            default:
                ihm.afficherMessage("Le jeu est termine.");
                return;
        }


        if (jouerPartie(partie)) {
            jouerTour(partie);
        } else {
            ihm.afficherMessage("le jeu se termine");
        }
    }


    public boolean jouerPartie(Partie partie) {
        ihm.afficherMessage("Configuration de la partie...");
        int choixCreation = ihm.demanderCreationCarte();

        switch (choixCreation) {
            case 1:
                partie.initialiserCarte(
                        ihm.demanderCoordonnes("ordonnee"),
                        ihm.demanderCoordonnes("abscisse"),
                        partie.getBordure()
                );
                return true;

            case 2:
                String chemin = ihm.demanderFichier();
                if (chemin.equals("q")) {
                    return false;
                }
                partie.chargerCarte(chemin);
                return true;

            default:
                return false;

        }

    }

    public void jouerTour(Partie partie) {
        boolean continuerJeu = true;
        ihm.afficherMessage(partie.toString());

        while (continuerJeu) {
            int action = ihm.demanderActionJoueur();


            switch (action) {
                case 1:
                    try {
                        String direction = ihm.demanderDirection();
                        partie.deplacerPersonnage(direction);
                        ihm.afficherMessage(partie.toString());
                        ihm.afficherMessage("Deplacement effectue.");
                    } catch (Exception e) {
                        ihm.afficherAvecSleep(e,partie);
                    }
                    break;

                case 2:
                    try {
                        String positionObjet = ihm.demanderDirection();
                        partie.ramasserObjetPersonnage(positionObjet);
                        ihm.afficherMessage("Objet ramasse !");
                    } catch (Exception e) {
                        ihm.afficherAvecSleep(e,partie);
                    }
                    break;
                case 3:
                    try {
                        String objet = ihm.demanderObjetADeposer(partie.getPersonnage());
                        String positionDeposer = ihm.demanderDirection();
                        partie.deposerObjetPersonnage(positionDeposer, objet);
                        ihm.afficherMessage(partie.toString());
                        ihm.afficherMessage("Objet depose.");
                    } catch (Exception e) {
                        ihm.afficherAvecSleep(e,partie);
                    }
                    break;

                case 4:
                    try {
                        String positionAnimal = ihm.demanderDirection();
                        partie.frapperAnimalPersonnage(positionAnimal);
                        ihm.afficherMessage(partie.toString());
                        ihm.afficherMessage("Animal frappe.");
                    } catch (Exception e) {
                        ihm.afficherAvecSleep(e,partie);
                    }
                    break;

                default:
                    ihm.afficherMessage("Vous quittez la partie.");
                    continuerJeu = false;
                    break;
            }
            partie.passerTourAnimaux();
            ihm.afficherMessage(partie.toString());

        }
    }
}

