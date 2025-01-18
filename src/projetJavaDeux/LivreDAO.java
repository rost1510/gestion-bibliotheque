package projetJavaDeux;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO
{
	// Établir la connexion avec la base de données
	
    private static final String URL = "jdbc:postgresql://localhost:5432/testBranch";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    
    
    // Méthode pour Ajouter un livre (implémentation de base)
	
    public void ajouterLivre(Livre livre)	
    {    	
        // Code pour insérer le livre dans la table tlivre de notre base de données

    	String sql = "INSERT INTO tablivre (titre_livre, auteur_livre, categorie_livre, nombre_exemplaires_livre) VALUES (?, ?, ?, ?)";

        try (
        		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        		PreparedStatement stmt = conn.prepareStatement(sql)
        	)
        {
        	stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getCategorie());
            stmt.setInt(4, livre.getNombreExemplaires());
            stmt.executeUpdate();

            System.out.println("Livre ajouté avec succès !");
        } catch (SQLException e)
        {
        	e.printStackTrace();
        }
    }

/*    // Rechercher un livre par titre
    public Livre rechercherLivreParTitre(String titre) {
        // Code pour rechercher un livre par titre dans la base de données
        // Exemple: "SELECT * FROM livres WHERE titre = ?"
        return new Livre(1, titre, "Auteur Exemple", "Catégorie Exemple", 5); // Retour fictif
    }

    // Rechercher un livre par catégorie
    public List<Livre> rechercherLivreParCategorie(String categorie) {
        // Code pour rechercher un livre par catégorie dans la base de données
        // Exemple: "SELECT * FROM livres WHERE categorie = ?"
        List<Livre> livres = new ArrayList<>();
        livres.add(new Livre(1, "Livre Exemple", "Auteur Exemple", categorie, 5)); // Retour fictif
        return livres;
    }

    // Afficher tous les livres disponibles
    public List<Livre> afficherTousLesLivres() {
        // Code pour récupérer tous les livres dans la base de données
        List<Livre> livres = new ArrayList<>();
        livres.add(new Livre(1, "Livre 1", "Auteur 1", "Catégorie 1", 5));
        livres.add(new Livre(2, "Livre 2", "Auteur 2", "Catégorie 2", 3));
        return livres;
    }

    // Modifier un livre
    public void modifierLivre(Livre livre) {
        // Code pour modifier un livre dans la base de données
        System.out.println("Livre modifié dans la base de données.");
    }

    // Supprimer un livre
    public void supprimerLivre(int id) {
        // Code pour supprimer un livre de la base de données
        System.out.println("Livre supprimé de la base de données.");
    }*/
}