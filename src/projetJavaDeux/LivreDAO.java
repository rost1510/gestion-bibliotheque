package projetJavaDeux;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//-------------------------

public class LivreDAO
{
    // Ajouter un livre
	
    public static void ajouterLivre(Livre livre)
    {
        String sql = "INSERT INTO livres (id, titre, auteur, categorie, nombreExemplaires) VALUES (?, ?, ?, ?, ?)";
        DatabaseUtil.executeUpdate(sql, livre.getId(), livre.getTitre(), livre.getAuteur(), livre.getCategorie(), livre.getNombreExemplaires());
    }

    // Récupérer un livre par ID
    
    public static Livre getLivreById(int id)
    {
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
    
    public static List<Livre> getAllLivres()
    {
        List<Livre> livres = new ArrayList<>();
        String sql = "SELECT * FROM livres";
 
        try (Connection conn = DatabaseUtil.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                livres.add(new Livre(
                    rs.getInt("id"),
                    rs.getString("titre"),
                    rs.getString("auteur"),
                    rs.getString("categorie"),
                    rs.getInt("nombreExemplaires")
                ));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return livres;
    }
    
    //------------------------------------------------------------------------------------------------
    
    // Méthode pour rechercher un livre par titre ou catégorie
    
    public static Livre rechercherLivre(String critere, String valeur)
    {
        //Nettoyer la valeur entrée par l'utilisateur (enlever les espaces avant et après)
    	
    	valeur = valeur.trim();
    	//---------------------
    	String query = "";
        
        // Déterminer la requête en fonction du critère choisi
        
        if (critere.equals("titre"))
        {
            query = "SELECT * FROM livres WHERE LOWER(titre) = LOWER(?)";	//Comparaison insensible à la casse
        } else if
        (critere.equals("categorie"))
        {
        	//Nettoyer le champ categorie de la base de données en utilisant LOWER et TRIM
        	
            query = "SELECT * FROM livres WHERE LOWER(TRIM(categorie)) = LOWER(TRIM(?))";	//Comparaison insensible à la casse
        }

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query))
        {
            // Passer la valeur du critère entrée par l'utilisateur en la transformant également en minuscule
        	
            ps.setString(1, valeur.toLowerCase());	//Utilisation de la valeur nettoyée (en minuscule)

            try (ResultSet rs = ps.executeQuery())
            {
                if (rs.next())
                {
                    // Si un livre est trouvé, créer un objet Livre
                	
                    int id = rs.getInt("id");
                    String titre = rs.getString("titre");
                    String auteur = rs.getString("auteur");
                    String categorie = rs.getString("categorie");
                    int nombreExemplaires = rs.getInt("nombreExemplaires");

                    return new Livre(id, titre, auteur, categorie, nombreExemplaires);
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null; // Si aucun livre n'a été trouvé
    }
}
