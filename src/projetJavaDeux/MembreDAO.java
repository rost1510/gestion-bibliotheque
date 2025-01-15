package projetJavaDeux;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembreDAO {

    // Ajouter un membre
    public static void ajouterMembre(Membre membre) {
        String sql = "INSERT INTO membres (nom, prenom, email, adhesionDate) VALUES (?, ?, ?, ?)";
        DatabaseUtil.executeUpdate(sql, membre.getNom(), membre.getPrenom(), membre.getEmail(), membre.getAdhesionDate());
    }

    // Récupérer un membre par ID
    public static Membre getMembreById(int id) {
        String sql = "SELECT * FROM membres WHERE id = ?";
        return DatabaseUtil.executeQuery(sql, rs -> new Membre(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("email"),
                rs.getDate("adhesionDate")
        ), id);
    }

    // Récupérer tous les membres
    public static List<Membre> getAllMembres() {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM membres";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                membres.add(new Membre(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getDate("adhesionDate")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return membres;
    }

    // Supprimer un membre par ID
    public static void supprimerMembre(int id) {
        String sql = "DELETE FROM membres WHERE id = ?";
        DatabaseUtil.executeUpdate(sql, id);
    }

    // Mettre à jour les informations d'un membre
    public static void mettreAJourMembre(Membre membre) {
        String sql = "UPDATE membres SET nom = ?, prenom = ?, email = ?, adhesionDate = ? WHERE id = ?";
        DatabaseUtil.executeUpdate(sql, membre.getNom(), membre.getPrenom(), membre.getEmail(), membre.getAdhesionDate(), membre.getId());
    }
}
