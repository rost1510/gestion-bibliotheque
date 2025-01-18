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
    	
        // Code pour insérer un emprunt dans la base de données
    	
    	String sql = "INSERT INTO tabemprunt (id_membre, id_livre, date_emprunt, date_retour_prevue_emprunt) VALUES (?, ?, NOW(), ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql)
            )
        {
            stmt.setInt(1, emprunt.getIdMembre());	// utiliser setInt pour un entier
            stmt.setInt(2, emprunt.getIdLivre());
            stmt.setObject(3, emprunt.getDateRetourPrevue());	// utiliser setObject pour LocalDate
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

/*    // Retourner un emprunt
    public void retournerEmprunt(int idEmprunt) {
        // Code pour marquer l'emprunt comme retourné dans la base de données
        System.out.println("Emprunt retourné dans la base de données.");
    }

    // Calculer les pénalités (si applicable)
    public double calculerPenalite(Emprunt emprunt) {
        // Code pour calculer le montant de la pénalité (par exemple, 100 F CFA par jour de retard)
        return 100.0;
    }
*/
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
    
    public List<Emprunt> afficherEmpruntsParMembre(int idMembre)
    {
        List<Livre> livres = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tablivre WHERE titre_livre = ?")) {
            preparedStatement.setString(1, titre_livre);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id_livre = resultSet.getInt("id_livre");
                String titre = resultSet.getString("titre_livre");
                String auteur_livre = resultSet.getString("auteur_livre");
                String categorie_livre = resultSet.getString("categorie_livre");
                int nombre_exemplaires_livre = resultSet.getInt("nombre_exemplaires_livre");
                Livre livre = new Livre(id_livre, titre, auteur_livre, categorie_livre, nombre_exemplaires_livre);
                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
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