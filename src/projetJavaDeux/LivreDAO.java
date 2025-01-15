package projetJavaDeux;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDAO {

    // Ajouter un livre
    public static void ajouterLivre(Livre livre) {
        String sql = "INSERT INTO livres (titre, auteur, categorie, nombreExemplaires) VALUES (?, ?, ?, ?)";
        DatabaseUtil.executeUpdate(sql, livre.getTitre(), livre.getAuteur(), livre.getCategorie(), livre.getNombreExemplaires());
    }

    // Récupérer un livre par ID
    public static Livre getLivreById(int id) {
        String sql = "SELECT * FROM livres WHERE id = ?";
        return DatabaseUtil.executeQuery(sql, rs -> new Livre(
            rs.getInt("id"),
            rs.getString("titre"),
            rs.getString("auteur"),
            rs.getString("categorie"),
            rs.getInt("nombreExemplaires")
        ), id);
    }

    // Récupérer tous les livres
    public static List<Livre> getAllLivres() {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livres";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                livres.add(new Livre(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getString("categorie"),
                    rs.getInt("nombreExemplaires")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Rechercher un livre par titre ou catégorie
    public static Livre rechercherLivre(String critere, String valeur) {
        valeur = valeur.trim();
        String query;

        if (critere.equals("titre")) {
            query = "SELECT * FROM livres WHERE LOWER(titre) = LOWER(?)";
        } else if (critere.equals("categorie")) {
            query = "SELECT * FROM livres WHERE LOWER(TRIM(categorie)) = LOWER(TRIM(?))";
        } else {
            throw new IllegalArgumentException("Critère de recherche invalide. Utilisez 'titre' ou 'categorie'.");
        }

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, valeur.toLowerCase());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Livre(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        rs.getString("categorie"),
                        rs.getInt("nombreExemplaires")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
