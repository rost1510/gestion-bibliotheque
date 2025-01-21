package projetJavaDeux;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class MembreDAO
{    
    // Ajouter un membre (implémentation de base)
    
    public void ajouterMembre(Membre membre)
    {
        // Code pour ajouter un membre dans la base de données
    	
    	String sql = "INSERT INTO tabmembre (nom_membre, prenom_membre, email_membre, date_adhesion_membre) VALUES (?, ?, ?, NOW())";
    	
        try (Connection conn = DatabaseUtil.getConnection();
        	PreparedStatement prepaStmt = conn.prepareStatement(sql)
        	)
        {
        	prepaStmt.setString(1, membre.getNom());
        	prepaStmt.setString(2, membre.getPrenom());
        	prepaStmt.setString(3, membre.getEmail());
        	prepaStmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    // Supprimer un membre
    public void supprimerMembre(int id) 
    {
        String query = "DELETE FROM tabmembre WHERE id_membre = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement prepaStmt = conn.prepareStatement(query))
        {     
        	prepaStmt.setInt(1, id);
            
            int rowsDeleted = prepaStmt.executeUpdate();
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

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement prepaStmt = conn.prepareStatement(query)) {
             
        	prepaStmt.setInt(1, id);
            ResultSet resultSet = prepaStmt.executeQuery();

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
}