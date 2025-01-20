package projetJavaDeux;
import java.util.Scanner;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;


public class Main
{
	// Gestion des interactions avec la base de données
	
    private static Scanner scanner = new Scanner(System.in);
    private static LivreDAO livreDAO = new LivreDAO();
    private static MembreDAO membreDAO = new MembreDAO();
    private static EmpruntDAO empruntDAO = new EmpruntDAO();
    private static RetourDAO retourDAO = new RetourDAO();
    private static HistoriqueEmpruntDAO historiqueEmpruntDAO = new HistoriqueEmpruntDAO();
    //-----------------------------------------------------------------------------------
    
    public static void main(String[] args)
    {
        afficherMenuPrincipal();
    }

    // Afficher Menu principal
    
    public static void afficherMenuPrincipal()
    {
        while (true)
        {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Gestion de livres");
            System.out.println("2. Gestion des membres");
            System.out.println("3. Gestion des transactions");
            System.out.println("4. Quitter");
            System.out.print("Indiquez votre choix par un numéro de [1;5]: ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix)
            {
                case 1:
                    afficherMenuLivres();
                    break;
                case 2:
                    afficherMenuMembres();
                    break;
                case 3:
                    afficherMenuTransaction();
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }

	// 1.	Afficher le Menu de gestion des livres

	public static void afficherMenuLivres()
    {
        while (true) {
            System.out.println("\n=== Gestion des Livres ===");
            System.out.println("1. Ajouter");
            System.out.println("2. Rechercher");
            System.out.println("3. Voir tous");
            System.out.println("4. Modifier");
            System.out.println("5. Supprimer");
            System.out.println("0. Retour");
            System.out.print("Indiquez votre choix parmi les numéros (1-6 ou 0): ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix)
            {
                case 1:
                	afficherMenuAjouterLivre();
//                    ajouterLivre();
                    break;
                case 2:
                    afficherMenuRechercheLivre();
                    break;
                case 3:
                    afficherTousLesLivres();
                    break;
                case 4:
                    modifierLivre();
                    break;
                case 5:
                    supprimerLivre();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }
	
		// 1.1 Afficher menu d'ajout d'un livre
	
    public static void afficherMenuAjouterLivre()
    {
        while (true)
        {
            System.out.println("\n=== Ajouter un livre ===");
            System.out.println("1. Livre existant");
            System.out.println("2. Créer nouveau");
            System.out.println("0. Retour");
            System.out.print("Indiquez votre choix parmi les numéros (1-2 ou 0): ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix)
            {
                case 1:
                    ajouterQuantite();
                    return;
                case 2:
                	ajouterLivre();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }

			// 1.1.1	Ajouter un livre existant

    public static void ajouterQuantite()
    {
        System.out.print("Entrez l'ID ou le titre du livre: ");
        String identifiant = scanner.nextLine();
        System.out.print("Nombre d'exemplaires à ajouter: ");
        int quantite = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne

        boolean result;
        try
        {
            int id = Integer.parseInt(identifiant);
            result = livreDAO.ajouterQuantiteParId(id, quantite);
        } catch (NumberFormatException e) {
            result = livreDAO.ajouterQuantiteParTitre(identifiant, quantite);
        }

        if (result) {
            System.out.println("Quantité ajoutée avec succès !");
        } else {
            System.out.println("Livre non trouvé.");
        }
    }
    
    		// 1.1.2	Ajouter un livre nouveau
    
    public static void ajouterLivre()
    {
        System.out.println("\n=== Ajouter un Livre ===");
        System.out.print("Titre: ");
        String titre = scanner.nextLine();
        System.out.print("Auteur: ");
        String auteur = scanner.nextLine();
        System.out.print("Catégorie: ");
        String categorie = scanner.nextLine();
        System.out.print("Nombre d'exemplaires: ");
        int nombreExemplaires = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne

        Livre livre = new Livre(titre, auteur, categorie, nombreExemplaires);
        livreDAO.ajouterLivre(livre);
        System.out.println("Livre ajouté avec succès !");
    }
    
    	// 1.2	Afficher Menu de Recherche d'un livre
    
    public static void afficherMenuRechercheLivre()
    {
        while (true)
        {
            System.out.println("\n=== Recherche d'un livre ===");
            System.out.println("1. Par titre");
            System.out.println("2. Par catégorie");
            System.out.println("0. Retour");
            System.out.print("Indiquez votre choix parmi les numéros (1-2 ou 0): ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix)
            {
                case 1:
                    rechercherLivreParTitre();
                    return;
                case 2:
                    rechercherLivreParCategorie();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }

    		// 1.2.1	Rechercher un livre par titre

    public static void rechercherLivreParTitre()
    {
        System.out.print("Entrez le titre du livre: ");
        String titre = scanner.nextLine();
        List<Livre> livres = livreDAO.rechercherLivreParTitre(titre);

        if (livres.isEmpty()) {
            System.out.println("Livre non trouvé.");
        } else {
            for (Livre livre : livres) {
                livre.afficherDetails();
            }
        }
    }

    		// 1.2.2	Rechercher un livre par catégorie

    public static void rechercherLivreParCategorie()
    {
        System.out.print("Entrez la catégorie désirée: ");
        String categorie = scanner.nextLine();
        List<Livre> livres = livreDAO.rechercherLivreParCategorie(categorie);

        if (livres.isEmpty())
        {
            System.out.println("Aucun livre trouvé dans cette catégorie.");
        } else
        {
            for (Livre livre : livres)
            {
                livre.afficherDetails();
            }
        }
    }

    	// 1.3	Afficher tous les livres

    public static void afficherTousLesLivres()
    {
        List<Livre> livres = livreDAO.afficherTousLesLivres();
 
        for (Livre livre : livres)
        {
            livre.afficherDetails();
        }
    }

    	// 1.4	Modifier un livre

    public static void modifierLivre()
    {
        System.out.print("Entrez l'ID du livre à modifier: ");
        int idLivre = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        
        System.out.print("Nouveau titre: ");
        String titre = scanner.nextLine();
        System.out.print("Nouvel auteur: ");
        String auteur = scanner.nextLine();
        System.out.print("Nouvelle catégorie: ");
        String categorie = scanner.nextLine();
        System.out.print("Nouveau nombre d'exemplaires: ");
        int nombreExemplaires = scanner.nextInt();
        scanner.nextLine();

        Livre livre = new Livre(idLivre, titre, auteur, categorie, nombreExemplaires);
        livreDAO.modifierLivre(livre);
        System.out.println("Livre modifié avec succès !");
    }
    
    	// 1.5	Supprimer un livre
    
    public static void supprimerLivre()
    {
        System.out.print("Entrez l'ID du livre à supprimer: ");
        int idLivre = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        livreDAO.supprimerLivre(idLivre);
    }

    // 2.	Afficher le Menu de Gestion des membres
   
    public static void afficherMenuMembres() {
        while (true) {
            System.out.println("\n=== Gestion des Membres ===");
            System.out.println("1. Ajouter");
            System.out.println("2. Supprimer");
            System.out.println("3. Rechercher");
//            System.out.println("4. Profil");
            System.out.println("0. Retourner au menu principal");
            System.out.print("Choisissez une option (1-4, ou 0): ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix) {
                case 1:
                    ajouterMembre();
                    break;
                case 2:
                    supprimerMembre();
                    break;
                case 3:
                	rechercherMembreParID();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }

    
    	// 2.1	Ajouter un membre
    
    public static void ajouterMembre()	// méthode crée dans le membreDAO
    {
        System.out.println("Ajouter un membre...");
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prénom: ");
        String prenom = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        Membre membre = new Membre(0, nom, prenom, email, null); // Date non utilisée        
        membreDAO.ajouterMembre(membre);	// Appeler la méthode pour ajouter le membre
        System.out.println("Membre ajouté avec succès.");
    }

    	// 2.2	Supprimer un membre

    public static void supprimerMembre()
    {
        System.out.print("Entrez l'ID du membre à supprimer: ");
        int idLivre = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        membreDAO.supprimerMembre(idLivre);
    }

    
    	// 2.3	Rechercher un membre

    public static void rechercherMembreParID()
    {
        System.out.print("Entrez l'ID du membre: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        Membre membre = membreDAO.rechercherMembreParID(id);

        if (membre != null) {
            membre.afficherDetails();
        } else {
            System.out.println("Membre non trouvé.");
        }
    }
    
    // 3.	Afficher les transactions
    
    public static void afficherMenuTransaction()
    {
        while (true)
        {
            System.out.println("\n=== Gestion des Transactions ===");
            System.out.println("1. Emprunts");	// rechercher d'un livre	1.2 
            System.out.println("2. Retours");
            System.out.println("0. Retourner au menu principal");
            System.out.print("Choisissez une option (1-2, ou 0): ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix)
            {
                case 1:
                    menuEmprunts();
                    break;
                case 2:
                	menuRetours();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }
    
    	// 3.1	Les emprunts
    
    public static void menuEmprunts()
    {
        while (true)
        {
            System.out.println("\n=== Gestion des Emprunts ===");
            System.out.println("1. Vérifier Disponibilité");	// rechercher d'un livre	1.2 
            System.out.println("2. Emprunt");
            System.out.println("4. Tous les emprunts");
            System.out.println("5. Mes emprunts");
            System.out.println("0. Retourner au menu principal");
            System.out.print("Choisissez une option (1-2, ou 0): ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix)
            {
            case 1:
                verifierDisponibiliteLivre();
                break;
            case 2:
            	enregistrerEmprunt();
                break;
            case 3:
            	afficherTousLesEmprunts();
                break;
            case 4:
            	afficherEmpruntsParMembre();
                break;
                case 0:
                    return;
                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }

    		// 3.1.1	Vérifier la disponibilité d'un livre
    
    public static void verifierDisponibiliteLivre()
    {
        System.out.print("Entrez le titre du livre: ");
        String titre = scanner.nextLine();
        boolean disponible = livreDAO.verifierDisponibiliteLivre(titre);

        if (disponible) {
            System.out.println("Livre disponible.");
        } else {
            System.out.println("Livre indisponible.");
        }
    }

			// 3.1.2	Enregistre un emprunt
    
    public static void enregistrerEmprunt()
    {
        System.out.println("Effectuer un emprunt...");
        System.out.print("NI de l'emprunteur : ");
        int idMembre = Integer.parseInt(scanner.nextLine()); // Conversion String -> int
        System.out.print("NI du livre : ");
        int idLivre = Integer.parseInt(scanner.nextLine()); // Conversion String -> int
        System.out.print("Date de retour prévue (format YYYY-MM-DD) : ");
        LocalDate dateRetourPrevue = LocalDate.parse(scanner.nextLine()); // Conversion String -> LocalDate
        Emprunt emprunt = new Emprunt(0, idMembre, idLivre, null, dateRetourPrevue);        
        empruntDAO.enregistrerEmprunt(emprunt);	// Appeler la méthode pour ajouter l'emprunt
        System.out.println("Eprunt ajouté avec succès.");
    }
    
			// 3.1.3	Afficher la liste des emprunts
    
    public static void afficherTousLesEmprunts()
    {
        List<Emprunt> emprunts = empruntDAO.afficherTousLesEmprunts();
 
        for (Emprunt emprunt : emprunts)
        {
            emprunt.afficherDetails();
        }
    }

				// 3.1.4	Afficher les emprunts d'un membre
    
	public static void afficherEmpruntsParMembre()
	{
        System.out.println("Entrez l'ID du membre: ");
        int idMembre = scanner.nextInt();
        List<Emprunt> emprunts = empruntDAO.afficherEmpruntsParMembre(idMembre);
        if (emprunts.isEmpty()) {
            System.out.println("Aucun emprunt trouvé pour ce membre.");
        } else {
            System.out.println("Liste des emprunts pour le membre " + idMembre + " :");
            for (Emprunt emprunt : emprunts) {
                emprunt.afficherDetails();
            }
        }
    }	
	
		// 3.2	Les Retours

	public static void menuRetours()
	{
		while (true)
		{
			System.out.println("\n=== Gestion des Emprunts ===");
			System.out.println("1. Enregistrer Retour");
            System.out.println("2. Consulter les pénalités");
            System.out.println("3. Endosser une pénalité");
			System.out.println("0. Retourner au menu principal");
			System.out.print("Choisissez une option (1-2, ou 0): ");

			int choix = scanner.nextInt();
			scanner.nextLine();  // Consommer la nouvelle ligne après un entier

			switch (choix)
			{
				case 1:
					retourEmprunt();
					break;
                case 2:
                	menuConsultationPenalite();
                    break;
            	case 3:
            		endosserPenalite();
                    break;
				case 0:
					return;
				default:
                System.out.println("Option invalide. Essayez encore.");
			}
		}
	}
	
			// 3.2.1	Enregistrer Retour

	public static void retourEmprunt()
	{
		System.out.print("Entrez l'ID de l'emprunt à retourner : ");
		int idEmprunt = Integer.parseInt(scanner.nextLine());

		Emprunt emprunt = empruntDAO.getEmpruntById(idEmprunt);
		if (emprunt == null)
		{
			System.out.println("Emprunt non trouvé.");
			return;
		}

		// Afficher les détails de l'emprunt
    
		emprunt.afficherDetails();

		// Demander la date de retour
    
		System.out.print("Date de retour (format YYYY-MM-DD) : ");
		LocalDate dateRetour = LocalDate.parse(scanner.nextLine());

		// Calculer la pénalité
    
		long joursRetard = java.time.temporal.ChronoUnit.DAYS.between(emprunt.getDateRetourPrevue(), dateRetour);
		int penalite = (int) joursRetard * 100;

		// Créer et enregistrer le retour

		Retour retour = new Retour(0, idEmprunt, dateRetour, penalite);
		retourDAO.enregistrerRetour(retour);

		// Afficher les détails du retour

		retour.afficherDetails();
	}
	


			// 3.2.2	Consulter les pénalités

	public static void menuConsultationPenalite()
	{
		while (true)
		{
			System.out.println("\n=== Consulter par : ===");
			System.out.println("1. id_penalite");
			System.out.println("2. id_membre");
			System.out.println("3. nom_membre");
			System.out.println("0. Retour");
			System.out.print("Indiquez votre choix parmi (1-3 ou 0): ");

			int choix = scanner.nextInt();
			scanner.nextLine();  // Consommer la nouvelle ligne après un entier

			switch (choix)
			{
			case 1:
				consultationParIdPenalite();
				break;
			case 2:
				consultationParIdMembre();
				break;
			case 3:
            	consultationParNomMembre();
                break;
			case 0:
				return;
			default:
				System.out.println("Choix invalide. Essayez encore.");
			}
		}
	}

					// 3.2.2.1	Rechercher une pénalité par ID PENALITE
	public static void consultationParIdPenalite()
	{
		System.out.println("EN COURS DE DEVELOPPEMENT !");
	}

					// 3.2.2.2	Rechercher une pénalité par ID MEMBRE

	public static void consultationParIdMembre()
	{
		System.out.println("EN COURS DE DEVELOPPEMENT !");
	}

					// 3.2.2.3	Rechercher une pénalité par NOM DU MEMBRE

	public static void consultationParNomMembre()
	{
		System.out.println("EN COURS DE DEVELOPPEMENT !");
	}

			// 3.2.3	Endosser Pénalité
    
	public static void endosserPenalite()
	{
		System.out.println("EN COURS DE DEVELOPPEMENT !");
	}
}