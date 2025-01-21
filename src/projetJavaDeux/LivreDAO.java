package projetJavaDeux;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class LivreDAO
{  
    // Méthode pour Ajouter un livre nouveau (implémentation de base)
	
    public void ajouterLivre(Livre livre)	
    {    	
        // Code pour insérer le livre dans la table tlivre de notre base de données

    	String sql = "INSERT INTO tablivre (titre_livre, auteur_livre, categorie_livre, nombre_exemplaires_livre) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql))
        {
        	stmt.setString(1, livre.getTitre());
            stmt.setString(2, livre.getAuteur());
            stmt.setString(3, livre.getCategorie());
            stmt.setInt(4, livre.getNombreExemplaires());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
        	e.printStackTrace();
        }
    }
    
 // Méthode pour ajouter la quantité d'un livre existant par ID
    
    public boolean ajouterQuantiteParId(int id, int quantite)
    {
        String query = "UPDATE tablivre SET nombre_exemplaires_livre = nombre_exemplaires_livre + ? WHERE id_livre = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, quantite);
            stmt.setInt(2, id);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Méthode pour ajouter la quantité d'un livre existant par titre
    
    public boolean ajouterQuantiteParTitre(String titre, int quantite)
    {
        String query = "UPDATE tablivre SET nombre_exemplaires_livre = nombre_exemplaires_livre + ? WHERE titre_livre = ?";

        try (Connection conn = DatabaseUtil.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, quantite);
            stmt.setString(2, titre);
            
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    // Rechercher un livre par titre

    public List<Livre> rechercherLivreParTitre(String titre_livre)
    {
        List<Livre> livres = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM tablivre WHERE titre_livre = ?"))
        {
            preparedStatement.setString(1, titre_livre);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next())
            {
                int id_livre = resultSet.getInt("id_livre");
                String titre = resultSet.getString("titre_livre");
                String auteur_livre = resultSet.getString("auteur_livre");
                String categorie_livre = resultSet.getString("categorie_livre");
                int nombre_exemplaires_livre = resultSet.getInt("nombre_exemplaires_livre");
                Livre livre = new Livre(id_livre, titre, auteur_livre, categorie_livre, nombre_exemplaires_livre);
                livres.add(livre);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return livres;
    }


    // Rechercher un livre par catégorie
    
    public List<Livre> rechercherLivreParCategorie(String categorie)
    {
    	List<Livre> livres = new ArrayList<>();
    	
    	try (Connection conn = DatabaseUtil.getConnection();
    	     PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM tablivre WHERE categorie_livre = ?"))
    	{
    		preparedStatement.setString(1, categorie);
    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		while (resultSet.next())
    		{
    			int id = resultSet.getInt("id_livre");
    	        String titre = resultSet.getString("titre_livre");
    	        String auteur = resultSet.getString("auteur_livre");
    	        int nombreExemplaires = resultSet.getInt("nombre_exemplaires_livre");
    	        Livre livre = new Livre(id, titre, auteur, categorie, nombreExemplaires);
    	        livres.add(livre);
    	    }
    	} catch (SQLException e)
    	{
    		e.printStackTrace();
    	}
    	return livres;
    }

    // Afficher tous les livres
    
    public List<Livre> afficherTousLesLivres()
    {
        List<Livre> livres = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery("SELECT * FROM tablivre"))
        {
            while (resultSet.next())
            {
                int id = resultSet.getInt("id_livre");
                String titre = resultSet.getString("titre_livre");
                String auteur = resultSet.getString("auteur_livre");
                String categorie = resultSet.getString("categorie_livre");
                int nombreExemplaires = Integer.parseInt(resultSet.getString("nombre_exemplaires_livre"));               
                Livre livre = new Livre(id, titre, auteur, categorie, nombreExemplaires);
                livres.add(livre);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }

    // Modifier un livre

    public void modifierLivre(Livre livre)
    {
        String query = "UPDATE tablivre SET titre_livre = ?, auteur_livre = ?, categorie_livre = ?, nombre_exemplaires_livre = ? WHERE id_livre = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement prepaStmt = conn.prepareStatement(query))
        {             
        	prepaStmt.setString(1, livre.getTitre());
        	prepaStmt.setString(2, livre.getAuteur());
        	prepaStmt.setString(3, livre.getCategorie());
        	prepaStmt.setInt(4, livre.getNombreExemplaires());
        	prepaStmt.setInt(5, livre.getId());
            
            int rowsUpdated = prepaStmt.executeUpdate();
            if (rowsUpdated > 0)
            {
                System.out.println("Le livre a été modifié avec succès.");
            } else
            {
                System.out.println("Aucun livre trouvé avec cet ID.");
            }
        } catch (SQLException e)
        {
            System.err.println("Erreur lors de la mise à jour du livre : " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Supprimer un livre

    public void supprimerLivre(int id)
    {
        String query = "DELETE FROM tablivre WHERE id_livre = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement prepaStmt = conn.prepareStatement(query))
        {     
        	prepaStmt.setInt(1, id);
            
            int rowsDeleted = prepaStmt.executeUpdate();
            if (rowsDeleted > 0)
            {
                System.out.println("Livre supprimé avec succès.");
            } else
            {
                System.out.println("Aucun livre trouvé avec cet ID.");
            }
        } catch (SQLException e)
        {
            System.err.println("Erreur lors de la suppression du livre : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Vérifier la disponibilité d'un livre
    
    public boolean verifierDisponibiliteLivre(String titre)
    {
        String query = "SELECT nombre_exemplaires_livre FROM tablivre WHERE titre_livre = ?";
        boolean disponible = false;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement prepaStmt = conn.prepareStatement(query))
        {
        	prepaStmt.setString(1, titre);
            ResultSet resultSet = prepaStmt.executeQuery();

            if (resultSet.next())
            {
                int nombreExemplaires = resultSet.getInt("nombre_exemplaires_livre");
                if (nombreExemplaires >= 1)
                {
                    disponible = true;
                }
            }
        } catch (SQLException e)
        {
            System.err.println("Erreur lors de la vérification de la disponibilité du livre : " + e.getMessage());
            e.printStackTrace();
        }
        return disponible;
    }
}