package projetJavaDeux;
import java.sql.*;

//--------------------

public class MembreDAO
{
    // Ajouter un membre
    public static void ajouterMembre(Membre membre)
    {
        String sql = "INSERT INTO membres (id, nom, prenom, email, adhesionDate) VALUES (?, ?, ?, ?, ?)";
        DatabaseUtil.executeUpdate(sql, membre.getId(), membre.getNom(), membre.getPrenom(), membre.getEmail(), membre.getAdhesionDate());
    }

    // Récupérer un membre par ID
    
    public static Membre getMembreById(int id)
    {
        String sql = "SELECT * FROM membres WHERE id = ?";
        return DatabaseUtil.executeQuery(sql, rs -> new Membre(
            rs.getInt("id"),
            rs.getString("nom"),
            rs.getString("prenom"),
            rs.getString("email"),
            rs.getDate("adhesionDate")
        ), id);
    }
}
