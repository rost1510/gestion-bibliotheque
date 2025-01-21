package projetJavaDeux;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RetourDAO
{    
    // Enregistrer un retour
	
    public void enregistrerRetour(Retour retour)
    {
        String sqlRetour = "INSERT INTO tabretour (id_emprunt, date_effective_retour, penalite_retour) VALUES (?, ?, ?)";
        String sqlUpdateLivre = "UPDATE tablivre SET nombre_exemplaires_livre = nombre_exemplaires_livre + 1 WHERE id_livre = (SELECT id_livre FROM tabemprunt WHERE id_emprunt = ?)";

        try (Connection conn = DatabaseUtil.getConnection())
        {
            // Commencer une transaction
        	
            conn.setAutoCommit(false);

            try (PreparedStatement prepaStmtRetour = conn.prepareStatement(sqlRetour);
                 PreparedStatement prepaStmtUpdateLivre = conn.prepareStatement(sqlUpdateLivre))
            {
                // Enregistrer le retour
            	
            	prepaStmtRetour.setInt(1, retour.getIdEmprunt());
            	prepaStmtRetour.setObject(2, retour.getDateRetour());
            	prepaStmtRetour.setInt(3, retour.getPenalite());
            	prepaStmtRetour.executeUpdate();
                
                // Récupérer l'ID généré
                
                try (ResultSet generatedKeys = prepaStmtRetour.getGeneratedKeys())
                {
                    if (generatedKeys.next())
                    {
                        retour.setIdRetour(generatedKeys.getInt(1));
                    }
                }

                // Incrémenter la quantité du livre retourné
                
                prepaStmtUpdateLivre.setInt(1, retour.getIdEmprunt());
                prepaStmtUpdateLivre.executeUpdate();

                // Confirmer la transaction
                
                conn.commit();
            } catch (SQLException e)
            {
                // En cas d'erreur, annuler la transaction
       
            	conn.rollback();
                throw e;
            }
        } catch (SQLException e)
        {
            System.err.println("Erreur lors de l'enregistrement du retour : " + e.getMessage());
            e.printStackTrace();
        }
    }
}