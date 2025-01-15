package projetJavaDeux;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpruntDAO {

    // Ajouter un emprunt
    public static void ajouterEmprunt(Emprunt emprunt) {
        String sql = "INSERT INTO emprunts (membreId, livreId, dateEmprunt, dateRetourPrevue, dateRetourEffective) VALUES (?, ?, ?, ?, ?)";
        DatabaseUtil.executeUpdate(sql, 
            emprunt.getMembreId(), 
            emprunt.getLivreId(), 
            emprunt.getDateEmprunt(), 
            emprunt.getDateRetourPrevue(), 
            emprunt.getDateRetourEffective());
    }

    // Enregistrer un emprunt
    public static void enregistrerEmprunt(Emprunt emprunt) {
        ajouterEmprunt(emprunt); // Réutilisation
    }

    // Autres méthodes (getEmpruntById, getAllEmprunts, etc.)...

    // Récupérer un emprunt par ID
    public static Emprunt getEmpruntById(int idEmprunt) {
        String sql = "SELECT * FROM emprunts WHERE idEmprunt = ?";
        return DatabaseUtil.executeQuery(sql, rs -> new Emprunt(
            rs.getInt("idEmprunt"),
            rs.getInt("membreId"),
            rs.getInt("livreId"),
            rs.getDate("dateEmprunt"),
            rs.getDate("dateRetourPrevue"),
            rs.getDate("dateRetourEffective")
        ), idEmprunt);
    }

    // Récupérer tous les emprunts
    public static List<Emprunt> getAllEmprunts() {
        List<Emprunt> emprunts = new ArrayList<>();
        String sql = "SELECT * FROM emprunts";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                emprunts.add(new Emprunt(
                    rs.getInt("idEmprunt"),
                    rs.getInt("membreId"),
                    rs.getInt("livreId"),
                    rs.getDate("dateEmprunt"),
                    rs.getDate("dateRetourPrevue"),
                    rs.getDate("dateRetourEffective")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emprunts;
    }

    // Mettre à jour la date de retour effective
    public static void mettreAJourDateRetour(int idEmprunt, Date dateRetourEffective) {
        String sql = "UPDATE emprunts SET dateRetourEffective = ? WHERE idEmprunt = ?";
        DatabaseUtil.executeUpdate(sql, dateRetourEffective, idEmprunt);
    }

    // Récupérer les emprunts en retard
    public static List<Emprunt> getEmpruntsEnRetard() {
        List<Emprunt> empruntsEnRetard = new ArrayList<>();
        String sql = "SELECT * FROM emprunts WHERE dateRetourPrevue < CURRENT_DATE AND dateRetourEffective IS NULL";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                empruntsEnRetard.add(new Emprunt(
                    rs.getInt("idEmprunt"),
                    rs.getInt("membreId"),
                    rs.getInt("livreId"),
                    rs.getDate("dateEmprunt"),
                    rs.getDate("dateRetourPrevue"),
                    rs.getDate("dateRetourEffective")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empruntsEnRetard;
    }
}
