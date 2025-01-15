package projetJavaDeux;

public class Livre
{
    private int id;
    public int getId() { return id; }
    //----------------------------------
    private String titre;
    public String getTitre() { return titre; }
    //----------------------------------------
    private String auteur;
    public String getAuteur() { return auteur; }
    //------------------------------------------
    private String categorie;
    public String getCategorie() { return categorie; }
    //------------------------------------------------
    private int nombreExemplaires;
    public int getNombreExemplaires() { return nombreExemplaires; }
    //-------------------------------------------------------------
    
    public Livre(int id, String titre, String auteur, String categorie, int nombreExemplaires)
    {
        this.id = id;
        this.titre = titre;
        this.auteur = auteur;
        this.categorie = categorie;
        this.nombreExemplaires = nombreExemplaires;
    }

    @Override
    public String toString() {
        return "Livre{id=" + id + ", titre='" + titre + "', auteur='" + auteur + "', categorie='" + categorie + "', nombreExemplaires=" + nombreExemplaires + '}';
    }
}
