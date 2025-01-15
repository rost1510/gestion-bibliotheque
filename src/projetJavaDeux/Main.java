package projetJavaDeux;
import java.util.Scanner;
import java.sql.Date;

//-----------------------

public class Main
{
    private static Bibliotheque bibliotheque = new Bibliotheque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        while (true)
        {
            System.out.println("\nBien venu(e) sur notre plate-forme\n\n-------------- Menu --------------\n");
            System.out.println("1. Ajouter un livre");
            System.out.println("2. Rechercher un livre");
            System.out.println("3. Inscrire un membre");
            System.out.println("4. Enregistrer un emprunt");
            System.out.println("5. Afficher les emprunts en retard");
            System.out.println("6. Quitter");

            System.out.print("Choisissez une option: ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Pour consommer le retour à la ligne après nextInt

            switch (choix)
            {
                case 1:
                    ajouterLivre();	//Appel la méthode pour ajouter un livre
                    break;
                case 2:
                    rechercherLivre();	//Appel la méthode pour rechercher un livre
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

    private static void ajouterLivre()
    {
        // Demander les informations à l'utilisateur

    	System.out.print("Entrez l'ID du livre : ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne restante après nextInt()
        //--------------------------------------------------------------------------
        System.out.print("Entrez le titre du livre : ");
        String titre = scanner.nextLine();
        //----------------------------------------------
        System.out.print("Entrez l'auteur du livre : ");
        String auteur = scanner.nextLine();
        //----------------------------------------------
        System.out.print("Entrez la catégorie du livre : ");
        String categorie = scanner.nextLine();
        //--------------------------------------------------
        System.out.print("Entrez le nombre d'exemplaires du livre : ");
        int nombreExemplaires = scanner.nextInt();

        // Créer un nouvel objet Livre avec les informations saisies

        Livre livre = new Livre(id, titre, auteur, categorie, nombreExemplaires);

        // Ajouter le livre à la base de données via le DAO

        LivreDAO.ajouterLivre(livre);

        // Confirmer l'ajout

        System.out.println("Le livre a été ajouté avec succès !");
    }
    
    //-----------------------------------------------------------------------------------------------

    private static void rechercherLivre()
    {
        // Demander à l'utilisateur quel critère il souhaite utiliser pour la recherche

    	System.out.println("Par quel critère souhaitez-vous rechercher ? (titre/categorie)");
        String critere = scanner.nextLine();

        // Vérifier que le critère est valide

        if (!critere.equals("titre") && !critere.equals("categorie"))
        {
            System.out.println("Critère invalide. Veuillez entrer 'titre' ou 'categorie'.");
            return;
        }

        // Demander la valeur pour le critère de recherche

        System.out.print("Entrez la valeur pour " + critere + ": ");
        String valeur = scanner.nextLine();

        // Rechercher le livre dans la base de données via le DAO
        
        Livre livre = LivreDAO.rechercherLivre(critere, valeur);

        // Afficher les résultats

        if (livre != null)
        {
            System.out.println("Livre trouvé : " + livre);
        } else
        {
            System.out.println("Aucun livre trouvé avec ce critère.");
        }
    }

    //-----------------------------------------------------------------------------------------------
    
    private static void inscrireMembre()
    {
    	try
    	{
    		// Demander les informations pour inscrire un membre
    	
    		System.out.print("Entrez l'ID du membre: ");
    		int id = scanner.nextInt();
    		scanner.nextLine();  // Consommer le retour à la ligne

    		System.out.print("Entrez le nom du membre: ");
    		String nom = scanner.nextLine();

    		System.out.print("Entrez le prénom du membre: ");
    		String prenom = scanner.nextLine();

    		System.out.print("Entrez l'email du membre: ");
    		String email = scanner.nextLine();

    		System.out.print("Entrez la date d'adhésion (YYYY-MM-DD): ");
    		String adhesionDateStr = scanner.nextLine();
    		Date adhesionDate = Date.valueOf(adhesionDateStr);  // Convertir en Date

    		// Créer un objet Membre
        
    		Membre membre = new Membre(id, nom, prenom, email, adhesionDate);

    		// Ajouter le membre via le DAO
        
    		MembreDAO.ajouterMembre(membre);

    		// Confirmer l'ajout
        
    		System.out.println("Membre inscrit avec succès: " + membre);
    	} catch (Exception e)
    	{
    		System.out.println("Erreur lors de l'inscription du membre: " + e.getMessage());
    		e.printStackTrace();
    	}
    }

    //-----------------------------------------------------------------------------------------------
    
    private static void enregistrerEmprunt()
    {
    	try
    	{
    		// Demander les informations pour enregistrer un emprunt
    		
    		System.out.print("Entrez l'ID du membre: ");
    		int membreId = scanner.nextInt();
    		scanner.nextLine(); // Consommer le retour à la ligne

    		System.out.print("Entrez l'ID du livre: ");
    		int livreId = scanner.nextInt();
    		scanner.nextLine(); // Consommer le retour à la ligne

    		System.out.print("Entrez la date d'emprunt (YYYY-MM-DD): ");
    		String dateEmpruntStr = scanner.nextLine();
    		Date dateEmprunt = Date.valueOf(dateEmpruntStr); // Convertir en Date

    		System.out.print("Entrez la date de retour prévue (YYYY-MM-DD): ");
    		String dateRetourPrevueStr = scanner.nextLine();
    		Date dateRetourPrevue = Date.valueOf(dateRetourPrevueStr); // Convertir en Date

    		// Vérifier si le membre et le livre existent
    		
    		Membre membre = Bibliotheque.getMembreById(membreId); // Utilisation de la méthode de la bibliothèque
    		Livre livre = Bibliotheque.getLivreById(livreId);

    		if (membre == null)
    		{
    			System.out.println("Aucun membre trouvé avec cet ID.");
    			return;
    		}
    		
    		if (livre == null)
    		{
    			System.out.println("Aucun livre trouvé avec cet ID.");
    			return;
    		}

    		// Créer un objet Emprunt
    		Emprunt emprunt = new Emprunt(
            EmpruntDAO.getNextId(),  // On suppose qu'il existe une méthode pour obtenir le prochain ID
            membreId,
            livreId,
            dateEmprunt,
            dateRetourPrevue
    		);

    		// Ajouter l'emprunt via la méthode de la bibliothèque
    		
    		Bibliotheque.enregistrerEmprunt(emprunt);

    		// Confirmer l'ajout
    		
    		System.out.println("Emprunt enregistré avec succès: " + emprunt);
    	} catch (Exception e)
    	{
    		System.out.println("Erreur lors de l'enregistrement de l'emprunt: " + e.getMessage());
    		e.printStackTrace();
    	}
    }
    
    //----------------------------------------------------------------------------------------------
    
    private static void afficherEmpruntsEnRetard()
    {
        // Implémentation pour afficher les emprunts en retard
    }
}
