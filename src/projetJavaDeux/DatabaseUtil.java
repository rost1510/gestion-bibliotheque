package projetJavaDeux;
import java.sql.*;
import java.util.List;

//---------------------

public class DatabaseUtil
{

    private static final String URL = "jdbc:postgresql://localhost:5432/gbibliotheque";
    private static final String USER = "postgres"; // Remplacer par ton utilisateur PostgreSQL
    private static final String PASSWORD = "postgres"; // Remplacer par ton mot de passe

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Méthode pour fermer la connexion et les ressources
    public static void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode d'insertion générique pour toute entité
    public static void executeUpdate(String sql, Object... params) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode de récupération d'une entité par son ID (ex: Livre, Membre, Emprunt)
    public static <T> T executeQuery(String sql, ResultSetHandler<T> handler, Object... params) {
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return handler.handle(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Interface pour manipuler le ResultSet de manière générique
    public interface ResultSetHandler<T> {
        T handle(ResultSet rs) throws SQLException;
    }
}
