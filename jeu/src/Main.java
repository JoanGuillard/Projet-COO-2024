import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String filePath = "jeu\\src\\carte.txt";
        ArrayList<String> test = new ArrayList<String>();
        try {
            // Créer une instance de Scanner pour lire le fichier
            Scanner scanner = new Scanner(new File(filePath));

            // Lire le fichier ligne par ligne
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine(); // Lire la ligne actuelle
                //System.out.println("Nouvelle ligne : " + line);

                // Parcourir chaque caractère de la ligne
                for (int i = 0; i < line.length(); i++) {

                    char c = line.charAt(i); // Récupérer le caractère à la position i
                    test.add(String.valueOf(c));
                    System.out.print(test.get(test.size()-1) + "\u001B[42m");
                    //System.out.print(c);

                }
                System.out.println();
            }

            // Fermer le scanner
            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("Fichier introuvable : " + e.getMessage());
        }
    }
}