package projetJavaDeux;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class EmpruntDAO
{
    // Enregistrer un emprunt (implémentation de base)
	
    public void enregistrerEmprunt(Emprunt emprunt)
    {
        String sqlEmprunt = "INSERT INTO tabemprunt (id_membre, id_livre, date_emprunt, date_retour_prevue_emprunt) VALUES (?, ?, NOW(), ?)";
        String sqlUpdateLivre = "UPDATE tablivre SET nombre_exemplaires_livre = nombre_exemplaires_livre - 1 WHERE id_livre = ?";

        try (Connection conn = DatabaseUtil.getConnection())
        {
            // Commencer une transaction
        	
            conn.setAutoCommit(false);

            try (PreparedStatement prepaStmtEmprunt = conn.prepareStatement(sqlEmprunt);
                 PreparedStatement prepaStmtUpdateLivre = conn.prepareStatement(sqlUpdateLivre))
            {
                // Enregistrer l'emprunt
            	
            	prepaStmtEmprunt.setInt(1, emprunt.getIdMembre()); // utiliser setInt pour un entier
            	prepaStmtEmprunt.setInt(2, emprunt.getIdLivre());
            	prepaStmtEmprunt.setObject(3, emprunt.getDateRetourPrevue()); // utiliser setObject pour LocalDate
            	prepaStmtEmprunt.executeUpdate();

                // Décrémenter la quantité du livre emprunté
                
            	prepaStmtUpdateLivre.setInt(1, emprunt.getIdLivre());
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
            System.err.println("Erreur lors de l'enregistrement de l'emprunt : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Afficher tous les emprunts
    
    public List<Emprunt> afficherTousLesEmprunts()
    {
        List<Emprunt> emprunts = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM tabemprunt"))
        {
            while (resultSet.next())
            {
                int idEmprunt = resultSet.getInt("id_emprunt");
                int idMembre = resultSet.getInt("id_membre");
                int idLivre = resultSet.getInt("id_livre");
                LocalDate dateEmprunt = LocalDate.parse(resultSet.getString("date_emprunt"));
                LocalDate dateRetourPrevue = LocalDate.parse(resultSet.getString("date_retour_prevue_emprunt"));
                Emprunt emprunt = new Emprunt(idEmprunt, idMembre, idLivre, dateEmprunt, dateRetourPrevue);
                emprunts.add(emprunt);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return emprunts;
    }

    // Afficher les emprunts pour un membre
    
    public List<Emprunt> afficherEmpruntsParMembre(int idMembre) {
        List<Emprunt> emprunts = new ArrayList<>();

        String query = "SELECT * FROM tabemprunt WHERE id_membre = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement prepaStmt = conn.prepareStatement(query)) {
             
        	prepaStmt.setInt(1, idMembre);
            ResultSet resultSet = prepaStmt.executeQuery();
            
            while (resultSet.next()) {
                int idEmprunt = resultSet.getInt("id_emprunt");
                int idLivre = resultSet.getInt("id_livre");
                LocalDate dateEmprunt = resultSet.getDate("date_emprunt").toLocalDate();
                LocalDate dateRetourPrevue = resultSet.getDate("date_retour_prevue_emprunt").toLocalDate();
                Emprunt emprunt = new Emprunt(idEmprunt, idMembre, idLivre, dateEmprunt, dateRetourPrevue);
                emprunts.add(emprunt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprunts;
    }
    
    // Méthode pour obtenir un emprunt par son ID
    
    public Emprunt getEmpruntById(int idEmprunt)
    {
        String query = "SELECT * FROM tabemprunt WHERE id_emprunt = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement prepaStmt = conn.prepareStatement(query))
        {
        	prepaStmt.setInt(1, idEmprunt);
            ResultSet resultSet = prepaStmt.executeQuery();
            
            if (resultSet.next())
            {
                int idMembre = resultSet.getInt("id_membre");
                int idLivre = resultSet.getInt("id_livre");
                LocalDate dateEmprunt = resultSet.getDate("date_emprunt").toLocalDate();
                LocalDate dateRetourPrevue = resultSet.getDate("date_retour_prevue_emprunt").toLocalDate();
                return new Emprunt(idEmprunt, idMembre, idLivre, dateEmprunt, dateRetourPrevue);
            }
        } catch (SQLException e)
        {
            System.err.println("Erreur lors de la récupération de l'emprunt : " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
