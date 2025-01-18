package projetJavaDeux;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO
{
	// Établir la connexion avec la base de données
	
    private static final String URL = "jdbc:postgresql://localhost:5432/testBranch";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    
    // Ajouter un membre (implémentation de base)
    
    public void ajouterMembre(Membre membre)
    {
        // Code pour ajouter un membre dans la base de données
    	
    	String sql = "INSERT INTO tabmembre (nom_membre, prenom_membre, email_membre, date_adhesion_membre) VALUES (?, ?, ?, NOW())";
    	
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        	PreparedStatement stmt = conn.prepareStatement(sql)
        	)
        {
            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getPrenom());
            stmt.setString(3, membre.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

/*    // Supprimer un membre
    public void supprimerMembre(int id) {
        // Code pour supprimer un membre de la base de données
        System.out.println("Membre supprimé de la base de données.");
    }

    // Rechercher un membre par nom
    public Membre rechercherMembreParNom(String nom) {
        // Code pour rechercher un membre par nom dans la base de données
        return new Membre(nom, "Prénom Exemple", "exemple@email.com", "2023-01-01");
    }

    // Afficher les détails d'un membre
    public Membre afficherDetailsMembre(int id) {
        // Code pour afficher les détails d'un membre dans la base de données
        return new Membre("Nom Exemple", "Prénom Exemple", "exemple@email.com", "2023-01-01");
    }*/
}