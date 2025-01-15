package projetJavaDeux;
import java.sql.*;

//---------------------

public class EmpruntDAO
{
    // Enregistrer un emprunt
	
    public static void enregistrerEmprunt(Emprunt emprunt)
    {
        String sql = "INSERT INTO emprunts (membreId, livreId, dateEmprunt, dateRetourPrevue) VALUES (?, ?, ?, ?)";
        DatabaseUtil.executeUpdate(sql,
            emprunt.getIdEmprunt(),
            emprunt.getMembreId(),
            emprunt.getLivreId(),
            emprunt.getDateEmprunt(),
            emprunt.getDateRetourPrevue()
        );
    }

    // Méthode pour obtenir le prochain ID d'emprunt
    
    public static int getNextId()
    {
        String sql = "SELECT nextval('emprunts_id_seq')";  // Assumes PostgreSQL sequence
        
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
  
        	if (rs.next())
        	{
                return rs.getInt(1);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return 0; // Si l'ID ne peut pas être récupéré, retourner 0 ou générer un ID manuellement
    }
}
