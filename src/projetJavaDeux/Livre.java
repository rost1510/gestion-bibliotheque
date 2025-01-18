package projetJavaDeux;

public class Livre
{
    private int id_livre;
    public int getId() {return id_livre;}
    public void setId(int id) {this.id_livre = id;}
    //---------------------------------------
    private String titre_livre;
    public String getTitre() {return titre_livre;}
    public void setTitre(String titre) {this.titre_livre = titre;}
    //------------------------------------------------------
    private String auteur_livre;
    public String getAuteur() {return auteur_livre;}
    public void setAuteur(String auteur) {this.auteur_livre = auteur;}
    //----------------------------------------------------------
    private String categorie_livre;
    public String getCategorie() {return categorie_livre;}
    public void setCategorie(String categorie) {this.categorie_livre = categorie;}
    //----------------------------------------------------------------------
    private int nombre_exemplaires_livre;
    public int getNombreExemplaires() {return nombre_exemplaires_livre;}
    public void setNombreExemplaires(int nombreExemplaires) {this.nombre_exemplaires_livre = nombreExemplaires;}
    //---------------------------------------------------------------------------------------------------
    
    //============== Constructeurs pour l'ajout de livre dans la bd ===============//

    public Livre(String titre_livre, String auteur_livre, String categorie_livre, int nombre_exemplaires_livre)
    {
        this.titre_livre = titre_livre;
        this.auteur_livre = auteur_livre;
        this.categorie_livre = categorie_livre;
        this.nombre_exemplaires_livre = nombre_exemplaires_livre;
    }
    //---------------------------------------------------------------------------------------
    public Livre(int id_livre, String titre_livre, String auteur_livre, String categorie_livre, int nombre_exemplaires_livre)
    {
        this.id_livre = id_livre;
        this.titre_livre = titre_livre;
        this.auteur_livre = auteur_livre;
        this.categorie_livre = categorie_livre;
        this.nombre_exemplaires_livre = nombre_exemplaires_livre;
    }
    //---------------------------------------------
    
    //========== Méthode pour afficher les détails du livre ==========//

    public void afficherDetails()
    {
        System.out.println("ID: " + id_livre);
        System.out.println("Titre: " + titre_livre);
        System.out.println("Auteur: " + auteur_livre);
        System.out.println("Catégorie: " + categorie_livre);
        System.out.println("Nombre d'exemplaires: " + nombre_exemplaires_livre);
    }	
}
