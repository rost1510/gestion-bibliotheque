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

    // Supprimer un membre
    public void supprimerMembre(int id) 
    {
        String query = "DELETE FROM tabmembre WHERE id_membre = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {     
            preparedStatement.setInt(1, id);
            
            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0)
            {
                System.out.println("Membre supprimé avec succès.");
            } else
            {
                System.out.println("Aucun membre trouvé avec cet ID.");
            }
        } catch (SQLException e)
        {
            System.err.println("Erreur lors de la suppression du livre : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Rechercher un membre par ID
    
    public Membre rechercherMembreParID(int id)
    {
        String query = "SELECT * FROM tabmembre WHERE id_membre = ?";
        Membre membre = null;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom_membre");
                String prenom = resultSet.getString("prenom_membre");
                String email = resultSet.getString("email_membre");
                Date adhesionDate = resultSet.getDate("date_adhesion_membre");

                membre = new Membre(id, nom, prenom, email, adhesionDate);
            }
        } catch (SQLException e)
        {
            System.err.println("Erreur lors de la recherche du membre : " + e.getMessage());
            e.printStackTrace();
        }

        return membre;
    }

/*    // Afficher les détails d'un membre
    public Membre afficherDetailsMembre(int id) {
        // Code pour afficher les détails d'un membre dans la base de données
        return new Membre("Nom Exemple", "Prénom Exemple", "exemple@email.com", "2023-01-01");
    }*/
}