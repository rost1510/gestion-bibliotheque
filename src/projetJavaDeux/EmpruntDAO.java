package projetJavaDeux;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmpruntDAO
{
    private static final String URL = "jdbc:postgresql://localhost:5432/testBranch";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    
    // Enregistrer un emprunt (implémentation de base)
	
    public void enregistrerEmprunt(Emprunt emprunt)
    {
        String sqlEmprunt = "INSERT INTO tabemprunt (id_membre, id_livre, date_emprunt, date_retour_prevue_emprunt) VALUES (?, ?, NOW(), ?)";
        String sqlUpdateLivre = "UPDATE tablivre SET nombre_exemplaires_livre = nombre_exemplaires_livre - 1 WHERE id_livre = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            // Commencer une transaction
            conn.setAutoCommit(false);

            try (PreparedStatement stmtEmprunt = conn.prepareStatement(sqlEmprunt);
                 PreparedStatement stmtUpdateLivre = conn.prepareStatement(sqlUpdateLivre))
            {
                // Enregistrer l'emprunt
            	
                stmtEmprunt.setInt(1, emprunt.getIdMembre()); // utiliser setInt pour un entier
                stmtEmprunt.setInt(2, emprunt.getIdLivre());
                stmtEmprunt.setObject(3, emprunt.getDateRetourPrevue()); // utiliser setObject pour LocalDate
                stmtEmprunt.executeUpdate();

                // Décrémenter la quantité du livre emprunté
                
                stmtUpdateLivre.setInt(1, emprunt.getIdLivre());
                stmtUpdateLivre.executeUpdate();

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

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM tabemprunt"))
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
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
             
            preparedStatement.setInt(1, idMembre);
            ResultSet resultSet = preparedStatement.executeQuery();
            
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
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, idEmprunt);
            ResultSet resultSet = preparedStatement.executeQuery();
            
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

/*    // Afficher l'historique des emprunts d'un membre
    public List<Emprunt> afficherHistoriqueMembre(int membreId) {
        // Code pour récupérer l'historique des emprunts d'un membre
        List<Emprunt> emprunts = new ArrayList<>();
        // Ajoutez des exemples d'emprunts fictifs
        return emprunts;
    }

    // Vérifier la disponibilité d'un livre
    public boolean verifierDisponibiliteLivre(int livreId) {
        // Code pour vérifier la disponibilité d'un livre
        return true;
    }

    // Afficher l'historique des livres
    public List<Emprunt> afficherHistoriqueLivres() {
        // Code pour récupérer l'historique des livres empruntés
        return new ArrayList<>();
    }*/
}
