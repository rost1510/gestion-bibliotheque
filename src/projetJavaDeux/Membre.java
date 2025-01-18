package projetJavaDeux;

import java.sql.Date;
import java.time.LocalDate;

public class Membre
{
    private int id;
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    //---------------------------------------
    private String nom;
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    //----------------------------------------------
    private String prenom;
    public String getPrenom() {return prenom;}
    public void setPrenom(String prenom) {this.prenom = prenom;}
    //----------------------------------------------------------
    private String email;
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    //------------------------------------------------------
    private LocalDate adhesionDate;
    public LocalDate getAdhesionDate() {return adhesionDate;}
    public void setAdhesionDate(LocalDate adhesionDate) {this.adhesionDate = adhesionDate;}
    //----------------------------------------------------------------------------------
    
    // Constructeur pour l'opération d'ajout

    public Membre(int id, String nom, String prenom, String email, Date dateAdhesion)
    {
    	this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionDate = adhesionDate;
    }



/*    // Méthode pour afficher les détails du membre

    public void afficherDetails() {
        System.out.println("ID: " + id);
        System.out.println("Nom: " + nom);
        System.out.println("Prénom: " + prenom);
        System.out.println("Email: " + email);
        System.out.println("Date d'adhésion: " + adhesionDate);
    }	*/
}