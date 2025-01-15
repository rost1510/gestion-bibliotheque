package projetJavaDeux;
import java.util.ArrayList;
import java.util.List;

//-------------------------

public class Bibliotheque
{
    private List<Livre> livres = new ArrayList<>();
    private List<Membre> membres = new ArrayList<>();
    private List<Emprunt> emprunts = new ArrayList<>();

    // Ajouter un livre
    
    public void ajouterLivre(Livre livre)
    {
        LivreDAO.ajouterLivre(livre);
    }

    // Rechercher un livre
    
    public Livre rechercherLivreById(int id)
    {
        return LivreDAO.getLivreById(id);
    }

    //------------------------------------------------------------------------------------------------
    
    // Ajouter un emprunt
    
    public static void enregistrerEmprunt(Emprunt emprunt)
    {
        EmpruntDAO.enregistrerEmprunt(emprunt);
    }

    // Récupérer un membre par ID
    
    public static Membre getMembreById(int id)
    {
        return MembreDAO.getMembreById(id); // Utilisation du DAO pour récupérer un membre par ID
    }

    // Récupérer un livre par ID
    
    public static Livre getLivreById(int id)
    {
        return LivreDAO.getLivreById(id); // Utilisation du DAO pour récupérer un livre par ID
    }

    //------------------------------------------------------------------------------------------------
    
    // Rechercher un livre par titre ou catégorie
    public Livre rechercherLivre(String critere, String valeur)
    {
        for (Livre livre : livres)
        {
            if ((critere.equals("titre") && livre.getTitre().equals(valeur)) ||
                (critere.equals("categorie") && livre.getCategorie().equals(valeur)))
            {
                return livre;
            }
        }
        return null;
    }

    // Afficher tous les livres
    public void afficherLivres()
    {
        for (Livre livre : livres)
        {
            System.out.println(livre);
        }
    }

    // Rechercher un membre par nom
    public Membre rechercherMembre(String nom)
    {
        for (Membre membre : membres)
        {
            if (membre.getNom().equals(nom))
            {
                return membre;
            }
        }
        return null;
    }

    // Afficher les emprunts en retard
    public void afficherEmpruntsEnRetard()
    {
        for (Emprunt emprunt : emprunts)
        {
            if (emprunt.calculerPénalité() > 0)
            {
                System.out.println(emprunt);
            }
        }
    }
}
