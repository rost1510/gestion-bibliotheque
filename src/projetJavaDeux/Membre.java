package projetJavaDeux;
import java.sql.Date;

//---------------------

public class Membre
{
    private int id;
    public int getId() { return id; }
    //-------------------------------
    private String nom;
    public String getNom() { return nom; }
    //------------------------------------
    private String prenom;
    public String getPrenom() { return prenom; }
    //------------------------------------------
    private String email;
    public String getEmail() { return email; }
    //----------------------------------------
    private Date adhesionDate;
    public Date getAdhesionDate() { return adhesionDate; }
    //----------------------------------------------------
    
    public Membre(int id, String nom, String prenom, String email, Date adhesionDate)
    {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.adhesionDate = adhesionDate;
    }

    @Override
    public String toString() {
        return "Membre{id=" + id + ", nom='" + nom + "', prenom='" + prenom + "', email='" + email + "', adhesionDate=" + adhesionDate + '}';
    }
}
