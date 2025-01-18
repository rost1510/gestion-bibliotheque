package projetJavaDeux;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // Afficher les emprunts d'un membre
    public List<Emprunt> afficherEmpruntsMembre(int membreId) {
        // Code pour récupérer les emprunts d'un membre dans la base de données
        List<Emprunt> emprunts = new ArrayList<>();
        // Ajoutez des exemples d'emprunts fictifs
        return emprunts;
    }

    // Afficher l'historique des emprunts d'un membre
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