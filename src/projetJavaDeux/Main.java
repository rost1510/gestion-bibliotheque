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
    private static PenaliteDAO penaliteDAO = new PenaliteDAO();
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
            System.out.println("3. Gestion d'emprunt");
            System.out.println("4. Gestion de pénalités");
            System.out.println("5. Quitter");
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
                    afficherMenuEmprunts();
                    break;
                case 4:
                    afficherMenuPenalites();
                    break;
                case 5:
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
            System.out.println("5. Historique");
            System.out.println("6. Supprimer");
            System.out.println("0. Retour");
            System.out.print("Indiquez votre choix parmi les numéros (1-6 ou 0): ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix)
            {
                case 1:
                    ajouterLivre();
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
    
    	// 1.1	Ajouter un livre
    
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
    
    	// 1.2	Afficher du Menu de Recherche d'un livre
    
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
        Livre livre = livreDAO.rechercherLivreParTitre(titre);

        if (livre != null)
        {
            livre.afficherDetails();
        } else
        {
            System.out.println("Livre non trouvé.");
        }
    }

    		// 1.2.2	Rechercher un livre par catégorie

    public static void rechercherLivreParCategorie()
    {
        System.out.print("Entrez la catégorie du livre: ");
        String categorie = scanner.nextLine();
        List<Livre> livres = livreDAO.rechercherLivreParCategorie(categorie);	// une seule catégorie peut avoir plusieurs livres

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
        scanner.nextLine();  // Consommer la nouvelle ligne

        Livre livre = new Livre(idLivre, titre, auteur, categorie, nombreExemplaires);
        livreDAO.modifierLivre(livre);
        System.out.println("Livre modifié avec succès !");
    }
    
		// 1.5	Afficher historique d'un livre (entrées et sorties)
    
    public static void afficherHistoriqueEmpruntsLivres()
    {
	
    }

    	// 1.6	Supprimer un livre
    
    public static void supprimerLivre()
    {
        System.out.print("Entrez l'ID du livre à supprimer: ");
        int idLivre = scanner.nextInt();
        scanner.nextLine();  // Consommer la nouvelle ligne
        livreDAO.supprimerLivre(idLivre);
        System.out.println("Livre supprimé avec succès !");
    }


    // 2.	Afficher le Menu de Gestion des membres
   
    public static void afficherMenuMembres() {
        while (true) {
            System.out.println("\n=== Gestion des Membres ===");
            System.out.println("1. Ajouter");
            System.out.println("2. Supprimer");
            System.out.println("3. Rechercher");
            System.out.println("4. Profil");
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
                    rechercherMembre();
                    break;
                case 4:
                    afficherProfilMembre();
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
        // Implémentez la méthode de suppression du membre ici
    }

    
    	// 2.3	Rechercher un membre

    public static void rechercherMembre()
 	{
        // Implémentez la méthode de recherche du membre ici
    }


    	// 2.4	Afficher le profil d'un membre
    
    public static void afficherProfilMembre()
    {
        // Implémentez la méthode d'affichage des détails du membre ici
    }

    
    // 3.	Afficher le Menu de Gestion des emprunts
    
    public static void afficherMenuEmprunts()
    {
        while (true)
        {
            System.out.println("\n=== Gestion des Emprunts ===");
            System.out.println("1. Disponible");	// rechercher d'un livre	1.2 
            System.out.println("2. Nouveau");
            System.out.println("3. Retour");
            System.out.println("4. Pénalité");
            System.out.println("5. Tous les emprunts");
            System.out.println("6. Rechercher");
            System.out.println("0. Retourner au menu principal");
            System.out.print("Choisissez une option (1-6, ou 0): ");

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
                	retournerEmprunt();
                    break;
                case 4:
                	calculerPenalite();
                    break;
                case 5:
                	afficherListeEmprunts();
                    break;
                case 6:
                	afficherDetailsEmprunt();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        }
    }
    
		// 3.1	Vérifier la disponibilité d'un livre
    
    public static void verifierDisponibiliteLivre()
    {

	}

		// 3.2	Enregistre un emprunt
    
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

		// 3.3	Retourner un emprunt
    
	public static void retournerEmprunt()
	{
		
	}

		// 3.4	Calculer la pénalité
	
	public static void calculerPenalite()
	{
		
	}

		// 3.5	Afficher la liste des emprunts
    
    public static void afficherListeEmprunts()
    {
	
	}

		// 3.6	Afficher les détails d'un emprunt
    
	public static void afficherDetailsEmprunt()
    {
		
	}

	// 4.	Afficher le Menu de Gestion des pénalités

    public static void afficherMenuPenalites()
    {
        while (true)
        {
            System.out.println("\n=== Gestion des Pénalités ===");
            System.out.println("1. Enregistre une pénalité");
            System.out.println("2. Endosser une pénalité");
            System.out.println("3. Consulter les pénalités");
            System.out.println("4. Rechercher une pénalités");
            System.out.println("0. Retour");
            System.out.print("Indiquez votre choix parmi (1-2 ou 0): ");

            int choix = scanner.nextInt();
            scanner.nextLine();  // Consommer la nouvelle ligne après un entier

            switch (choix)
            {
            	case 1:
            		ajouterPenalite();
            		break;
            		
            	case 2:
            		endosserPenalite();
                    break;
                case 3:
                	consulterPenalites();
                    break;
                case 4:
                    afficherMenuRecherchePenalite();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }
    
		// 4.1	Ajouter une pénalité
    
    public static void ajouterPenalite()
	{
	
	}

		// 4.2	Endosser une pénalités
    
	public static void endosserPenalite()
	{
		
	}    
    
		// 4.3	Consulter la liste des pénalités
	
	public static void consulterPenalites()
	{
	
	}	

    	// 4.4	Afficher les critères de la recherche d'une pénalité
	
    public static void afficherMenuRecherchePenalite()
    {
        while (true)
        {
            System.out.println("\n=== Rechercher par : ===");
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
            		recherchePenaliteParIdPenalite();
            		break;
            		
            	case 2:
            		recherchePenaliteParIdMembre();
                    break;
                case 3:
                	recherchePenaliteParNomMembre();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide. Essayez encore.");
            }
        }
    }
    
			// 4.4.1	Rechercher une pénalité par ID PENALITE
    
    public static void recherchePenaliteParIdPenalite()
    {
			
    }

    		// 4.4.2	Rechercher une pénalité par ID MEMBRE
    
    public static void recherchePenaliteParIdMembre()
    {
			
    }

    		// 4.4.3	Rechercher une pénalité par NOM DU MEMBRE
    
    public static void recherchePenaliteParNomMembre()
    {
		
    }

}