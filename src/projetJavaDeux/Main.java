package projetJavaDeux;

import java.util.Scanner;
import java.sql.Date;
import projetJavaDeux.EmpruntDAO;

public class Main {
    private static Bibliotheque bibliotheque = new Bibliotheque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nBienvenue sur notre plate-forme\n\n-------------- Menu --------------\n");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Inscrire un membre");
            System.out.println("4. Enregistrer un emprunt");
            System.out.println("5. Afficher les emprunts en retard");
            System.out.println("6. Quitter");

            System.out.print("Choisissez une option: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le retour à la ligne après nextInt

            switch (choix) {
                case 1:
                    ajouterLivre();
                    break;
                case 2:
                    rechercherLivre();
                    break;
                case 3:
                    inscrireMembre();
                    break;
                case 4:
                    enregistrerEmprunt();
                    break;
                case 5:
                    afficherEmpruntsEnRetard();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void ajouterLivre() {
        System.out.print("Entrez le titre du livre : ");
        String titre = scanner.nextLine();

        System.out.print("Entrez l'auteur du livre : ");
        String auteur = scanner.nextLine();

        System.out.print("Entrez la catégorie du livre : ");
        String categorie = scanner.nextLine();

        System.out.print("Entrez le nombre d'exemplaires du livre : ");
        int nombreExemplaires = scanner.nextInt();

        Livre livre = new Livre(0, titre, auteur, categorie, nombreExemplaires);

        LivreDAO.ajouterLivre(livre);

        System.out.println("Le livre a été ajouté avec succès !");
    }

    private static void rechercherLivre() {
        System.out.println("Par quel critère souhaitez-vous rechercher ? (titre/categorie)");
        String critere = scanner.nextLine();

        if (!critere.equals("titre") && !critere.equals("categorie")) {
            System.out.println("Critère invalide. Veuillez entrer 'titre' ou 'categorie'.");
            return;
        }

        System.out.print("Entrez la valeur pour " + critere + ": ");
        String valeur = scanner.nextLine();

        Livre livre = LivreDAO.rechercherLivre(critere, valeur);

        if (livre != null) {
            System.out.println("Livre trouvé : " + livre);
        } else {
            System.out.println("Aucun livre trouvé avec ce critère.");
        }
    }

    private static void inscrireMembre() {
        try {
            System.out.print("Entrez le nom du membre: ");
            String nom = scanner.nextLine();

            System.out.print("Entrez le prénom du membre: ");
            String prenom = scanner.nextLine();

            System.out.print("Entrez l'email du membre: ");
            String email = scanner.nextLine();

            System.out.print("Entrez la date d'adhésion (YYYY-MM-DD): ");
            String adhesionDateStr = scanner.nextLine();
            Date adhesionDate = Date.valueOf(adhesionDateStr);

            Membre membre = new Membre(0, nom, prenom, email, adhesionDate);

            MembreDAO.ajouterMembre(membre);

            System.out.println("Membre inscrit avec succès: " + membre);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'inscription du membre: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void enregistrerEmprunt() {
        try {
            System.out.print("Entrez l'ID du membre: ");
            int membreId = scanner.nextInt();

            System.out.print("Entrez l'ID du livre: ");
            int livreId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Entrez la date d'emprunt (YYYY-MM-DD): ");
            String dateEmpruntStr = scanner.nextLine();
            Date dateEmprunt = Date.valueOf(dateEmpruntStr);

            System.out.print("Entrez la date de retour prévue (YYYY-MM-DD): ");
            String dateRetourPrevueStr = scanner.nextLine();
            Date dateRetourPrevue = Date.valueOf(dateRetourPrevueStr);

            Emprunt emprunt = new Emprunt(0, membreId, livreId, dateEmprunt, dateRetourPrevue, null);

            EmpruntDAO.enregistrerEmprunt(emprunt);

            System.out.println("Emprunt enregistré avec succès: " + emprunt);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement de l'emprunt: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void afficherEmpruntsEnRetard() {
        System.out.println("Liste des emprunts en retard :");
        for (Emprunt emprunt : EmpruntDAO.getEmpruntsEnRetard()) {
            System.out.println(emprunt);
        }
    }
}
