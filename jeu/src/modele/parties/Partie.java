package modele.parties;

import exceptions.ActionImpossibleException;
import exceptions.DeplacementImpossibleException;
import exceptions.ObjetNonRamassableException;
import modele.Carte;
import modele.ElementCarte;
import modele.Personnage;
import modele.PierrePrecieuse;
import modele.animaux.Animal;
import modele.predateurs.Predateur;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public abstract class Partie {
    private Personnage personnage;
    private ArrayList<Animal> lesAnimaux;
    private ArrayList<Predateur> lesPredateurs;
    private String bordure;
    private ArrayList<Carte> lesCartes;
    private Stack<ArrayList<ArrayList<ElementCarte>>> historique;


    private Carte carte;


    public Partie(Personnage personnage, String bordure) {

        this.personnage = personnage;
        this.lesAnimaux = new ArrayList<Animal>();
        this.lesPredateurs = new ArrayList<Predateur>();
        this.bordure = bordure;
        this.personnage.setInventaire("K");
        this.historique = new Stack<ArrayList<ArrayList<ElementCarte>>>();


    }

    public String getBordure() {
        return bordure;
    }

    public void setBordure(String bordure) {
        this.bordure = bordure;
    }

    /**
     * Permet de charger une carte à partir d'un fichier .txt
     *
     * @param fichier le fichier à charger
     */
    public void chargerCarte(String fichier) {
        this.carte = new Carte();
        try {
            Scanner scanner = new Scanner(new File(fichier));
            int ordonnee = 0;
            while (scanner.hasNextLine()) {
                ArrayList<ElementCarte> ligneCarte = new ArrayList<ElementCarte>();
                String line = scanner.nextLine();

                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    ligneCarte.add(ajouterElementCarte(String.valueOf(c), i, ordonnee));
                    //Utilisation du Patron de méthode pour ajouter les éléments dans la carte.
                    // Raison : identifier les éléments selon le type de partie (buisson pour la forêt et rocher pour la jungle par ex)
                    // et leur attribuer la couleur qui leur correspond directement dans la carte.

                }
                carte.ajouterLigne(ligneCarte);
                ordonnee++;
            }
            carte.setDimensions();


            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable : " + e.getMessage());
        }
    }

    public Carte creerNouvelleCarte(String bordure, int hauteur, int largeur) {
        this.carte = new Carte();
        for (int i = 0; i < hauteur; i++) {
            ArrayList<ElementCarte> ligneCarte = new ArrayList<>();
            for (int j = 0; j < largeur; j++) {
                if (i == 0 || i == hauteur - 1 || j == 0 || j == largeur - 1) {
                    ligneCarte.add(ajouterElementCarte(bordure, j, i));
                } else {
                    ligneCarte.add(ajouterElementCarte(" ", j, i));
                }
            }
            carte.ajouterLigne(ligneCarte);
        }
        carte.setDimensions();
        return carte;
    }


    public abstract String afficherElement(ElementCarte e);

    /**
     * @return La carte en chaîne de caratères
     */

    public String toString() {
        String res = "";
        for (ArrayList<ElementCarte> elementCartes : carte.getCarte()) {
            for (ElementCarte elementCarte : elementCartes) {
                res += afficherElement(elementCarte);
            }
            res += '\n';
        }
        return res;
    }

    /**
     * remplie la carte selon le theme choisi
     *
     * @param carte
     */
    public void remplirCarte(Carte carte, int hauteur, int largeur) {
        Random random = new Random();


        int totalCases = (hauteur - 2) * (largeur - 2); // pour etre loin de la bordure
        int casesVidesCibles = totalCases / 2; // 50% des cases vides
        int casesRemplies = 0;

        for (int i = 1; i < hauteur - 1; i++) {
            for (int j = 1; j < largeur - 1; j++) {
                if (casesRemplies >= totalCases - casesVidesCibles) break;

                String element = genererElementAleatoire(random);
                if (!element.equals(" ")) casesRemplies++;

                carte.setCase(j, i, ajouterElementCarte(element, j, i));

            }
        }
        ajouterPersonnageDansZoneProtegee(carte, random);
    }
    protected abstract String genererElementAleatoire(Random random);

    public void initialiserCarte(int hauteur, int largeur, String bordure) {
        Carte carte = creerNouvelleCarte(bordure, hauteur, largeur);
        remplirCarte(carte, hauteur, largeur);
        this.setCarte(carte);
        enregistrer();
    }

    /**
     * Crée un objet de type Element et lui définit une apparence selon le caractère rencontré
     *
     * @param element  l'élément rencontré dans le fichier .txt
     * @param abscisse l'abscisse de l'élément rencontré (utile seulement pour le personnage et les animaux)
     * @param ordonnee l'ordonnee de l'élément rencontré (utile seulement pour le personnage et les animaux)
     * @return un objet de type ElementCarte
     */
    public abstract ElementCarte ajouterElementCarte(String element, int abscisse, int ordonnee);


    /**
     * Permet d'afficher l'apparence de l'animal sur la carte en fonction du type de la partie et de l'animal
     * @param animal L'animal à afficher
     */


    /**
     * Vérifie si l'élément en paramètre est de la nourriture en fonction du type de partie
     *
     * @param element L'élément à vérifier
     * @return true si l'élément est de la nourriture et false sinon
     */
    public abstract boolean estNourriture(String element);

    /**
     * Vérifie si l'élément en paramètre est un animal en fonction du type de la partie
     *
     * @param element L'élément à vérifier
     * @return true si l'élément est un animal, false sinon
     */
    public boolean estAnimal(ElementCarte element) {
        return element instanceof Animal;
    }


    /**
     * Permet de déplacer les animaux présents sur la carte
     */
    public void passerTourAnimaux() {
        for (Animal animal : lesAnimaux) {
            if (!animal.isEstMort()) {
                animal.seDeplacer(carte, personnage);
                // enregistrerPosition(animal);
            }
        }
        for (Predateur predateur : lesPredateurs) {
            predateur.seDeplacer(carte);
           // enregistrerPosition(predateur);


        }

    enregistrer();

    }


    /**
     * Permet de déplacer le personnage selon la direction qu'il a choisi
     *
     * @param direction la direction choisie par le personnage
     * @throws Exception se lève si le personnage tente d'effectuer un déplacement impossible (limite de carte, obstacle)
     */
    public void deplacerPersonnage(String direction) throws Exception {
        int[] coordonnees = carte.getCoordonnees(direction, personnage.getAbscisse(), personnage.getOrdonnee());
        int nvAbscisse = coordonnees[0];//nouvelle abscisse du personnage
        int nvOrdonnee = coordonnees[1];//nouvelle ordonnee du personnage
        if (carte.estCaseVide(nvAbscisse, nvOrdonnee)) {
            //on échange la case où va se trouver le personnage avec la case actuelle du personnage
            carte.setCase(personnage.getAbscisse(), personnage.getOrdonnee(), carte.getCase(nvAbscisse, nvOrdonnee));
            carte.setCase(nvAbscisse, nvOrdonnee, personnage);
            carte.getCase(nvAbscisse, nvOrdonnee).nouvellePosition(personnage.getAbscisse(), personnage.getOrdonnee());
            personnage.nouvellePosition(nvAbscisse, nvOrdonnee);
        } else {
            throw new DeplacementImpossibleException("Deplacement impossible !");
        }
        //enregistrerPosition(personnage);

    }

    /**
     * Ajoute un objet à l'inventaire du personnage
     *
     * @param positionObjet la position de l'objet à ajouter
     * @throws Exception se lève si la case où doit se trouver l'objet est vide
     */
    public boolean ramasserObjetPersonnage(String positionObjet) throws Exception {
        int[] coordonneesObjet = carte.getCoordonnees(positionObjet, personnage.getAbscisse(), personnage.getOrdonnee());
        int nvAbscisse = coordonneesObjet[0];
        int nvOrdonnee = coordonneesObjet[1];

        if (estNourriture(carte.getCase(nvAbscisse, nvOrdonnee).getApparence())) {
            personnage.ajouterDansInventaire(carte.setCaseString(new ElementCarte(" "), positionObjet, personnage.getAbscisse(), personnage.getOrdonnee()));
        }else if ( carte.getCase(nvAbscisse,nvOrdonnee) instanceof PierrePrecieuse){
            PierrePrecieuse p = (PierrePrecieuse) carte.getCase(nvAbscisse,nvOrdonnee);
            personnage.ajouterDansInventaire(carte.setCaseString(new ElementCarte(" "), positionObjet, personnage.getAbscisse(), personnage.getOrdonnee()));
            reculer(p.getNbTours());
            return false;
        }else if( carte.getCase(nvAbscisse,nvOrdonnee).getApparence().equals("K")){
            personnage.ajouterDansInventaire(carte.setCaseString(new ElementCarte(" "), positionObjet, personnage.getAbscisse(), personnage.getOrdonnee()));
        }
        else {
            throw new ObjetNonRamassableException("Cette case est vide ou l'objet ne peut pas être ramassé !");
        }

    return true;
    }

    public void frapperAnimalPersonnage(String positionAnimal) throws Exception {

        int[] coordonneesAnimal = carte.getCoordonnees(positionAnimal, personnage.getAbscisse(), personnage.getOrdonnee());
        ElementCarte animal = carte.getCase(coordonneesAnimal[0], coordonneesAnimal[1]);
        if (estAnimal(animal)) {
            ((Animal) animal).devenirEnnemi();
        } else {
            throw new ActionImpossibleException("Action impossible");
        }

    }

    public void deposerObjetPersonnage(String position, String objet) throws Exception {
        int[] coordonnees = carte.getCoordonnees(position, personnage.getAbscisse(), personnage.getOrdonnee());
        if (personnage.getNbObjet(objet) > 0 && carte.estCaseVide(coordonnees[0], coordonnees[1])) {
            personnage.deposerObjet(objet);
            carte.getCase(coordonnees[0], coordonnees[1]).setApparence(objet);

        }else
            throw new ActionImpossibleException("Action impossible");
        }


    public Personnage getPersonnage() {
        return personnage;
    }

    public ArrayList<Animal> getLesAnimaux() {
        return lesAnimaux;
    }


    /**
     * place la personnage dans une case non bloquee pour le premier coup
     *
     * @param carte
     * @param random
     */
    public void ajouterPersonnageDansZoneProtegee(Carte carte, Random random) {
        int hauteur = carte.getHauteur();
        int largeur = carte.getLargeur();
        boolean positionTrouvee = false;

        while (!positionTrouvee) {
            int x = random.nextInt(largeur - 2) + 1;
            int y = random.nextInt(hauteur - 2) + 1;

            if (carte.getCase(x, y).getApparence().equals(" ") &&
                    (carte.getCase(x - 1, y).getApparence().matches("[ABTR]") ||
                            carte.getCase(x + 1, y).getApparence().matches("[ABTR]") ||
                            carte.getCase(x, y - 1).getApparence().matches("[ABTR]") ||
                            carte.getCase(x, y + 1).getApparence().matches("[ABTR]"))) {

                carte.setCase(x, y, getPersonnage());
                getPersonnage().nouvellePosition(x, y);
                positionTrouvee = true;
            }
        }
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }


    public ArrayList<Predateur> getLesPredateurs() {
        return lesPredateurs;
    }


    /*public void reculer(int nbTours) {
        for (Map.Entry<ElementCarte, Queue<int[]>> entry : historiquePositions.entrySet()) {
            ElementCarte elementCarte = entry.getKey();
            Queue<int[]> historique = entry.getValue();

            System.out.println("Tentative de recul de " + elementCarte + " de " + nbTours + " tours.");

            if (historique != null && historique.size() >= nbTours) {
                int[] positionCible = null;

                for (int i = 1; i < nbTours; i++) {
                    positionCible = historique.poll();
                }

                if (positionCible != null) {
                    System.out.println(elementCarte.getApparence() + " position actuelle: " + elementCarte.getAbscisse() + "," + elementCarte.getOrdonnee());

                    if (elementCarte == personnage) {
                        System.out.println("Personnage reculé à la position " + positionCible[0] + "," + positionCible[1]);
                        carte.setCase(elementCarte.getAbscisse(), elementCarte.getOrdonnee(), new ElementCarte(" "));
                        personnage.nouvellePosition(positionCible[0], positionCible[1]);
                        carte.setCase(positionCible[0], positionCible[1], personnage);
                    } else {
                        carte.setCase(elementCarte.getAbscisse(), elementCarte.getOrdonnee(), new ElementCarte(" "));
                        elementCarte.nouvellePosition(positionCible[0], positionCible[1]);
                        carte.setCase(positionCible[0], positionCible[1], elementCarte);
                    }
                    System.out.println(elementCarte.getApparence() + " reculé à la position " + positionCible[0] + "," + positionCible[1]);
                }
            } else {
                System.out.println("Impossible de reculer " + elementCarte.getApparence() + " : historique vide.");
            }
        }
    }*/


   /* public void enregistrerPosition(ElementCarte element) {
        historiquePositions.putIfAbsent(element, new LinkedList<>());
        Queue<int[]> historique = historiquePositions.get(element);

        int[] currentPosition = new int[]{element.getAbscisse(), element.getOrdonnee()};
        System.out.println(element.getApparence() + "  position de debut: " + element.getAbscisse() + "," + element.getOrdonnee());

        if (historique.isEmpty() || !Arrays.equals(historique.peek(), currentPosition)) {
            historique.offer(currentPosition);
        }

        while (historique.size() >4) {
            historique.poll();
        }*/
   public void reculer(int nbTours) {
       if (historique.size() < nbTours) {
           System.out.println("Il n'y a pas suffisamment d'états dans l'historique pour reculer " + nbTours + " tours.");
           return;
       }
       for (int i = 0; i < nbTours; i++) {
           historique.pop();
       }
       if (!historique.isEmpty()) {
           ArrayList<ArrayList<ElementCarte>> cartePrecedente = historique.peek();
           Carte nouvelleCarte = new Carte();
           for (ArrayList<ElementCarte> ligne : cartePrecedente) {
               ArrayList<ElementCarte> nouvelleLigne = new ArrayList<>(ligne);
               nouvelleCarte.ajouterLigne(nouvelleLigne);
           }
           this.carte = nouvelleCarte;
           this.carte.setDimensions();
           restaurerPositions();
           System.out.println("Carte restaurée après " + nbTours + " tours.");
       } else {
           System.out.println("Aucun état à restaurer.");
       }
   }


    public void enregistrer() {
        ArrayList<ArrayList<ElementCarte>> copieCarte = new ArrayList<>();
        for (ArrayList<ElementCarte> ligne : carte.getCarte()) {
            ArrayList<ElementCarte> copieLigne = new ArrayList<>(ligne);
            copieCarte.add(copieLigne);
        }
        historique.push(copieCarte);
    }

    private void restaurerPositions() {
        for (int x = 0; x < carte.getLargeur(); x++) {
            for (int y = 0; y < carte.getHauteur(); y++) {
                ElementCarte element = carte.getCase(x, y);
                if (element != null) {
                    element.nouvellePosition(x, y);
                }
            }
        }
    }


}




