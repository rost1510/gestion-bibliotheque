package projetJavaDeux;
import java.time.LocalDate;
import java.util.Date;

public class Retour
{
    private int idRetour;
    public int getIdRetour() {return idRetour;}
    public void setIdRetour(int idRetour) {this.idRetour = idRetour;}
    //-------------------------------------------------------------------
    private int idEmprunt;
    public int getIdEmprunt() {return idEmprunt;}
    public void setIdEmprunt(int iddEmprunt) {this.idEmprunt = idEmprunt;}
    //---------------------------------------------------------------
//    private LocalDate dateRetourPrevue;
//    public LocalDate getDateRetourPrevue() {return dateRetourPrevue;}
//    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {this.dateRetourPrevue = dateRetourPrevue;}
    //------------------------------------------------------------------------------------------------
    private LocalDate dateRetour;
    public LocalDate getDateRetour() {return dateRetour;}
    public void setDateRetour(LocalDate dateRetour) {this.dateRetour = dateRetour;}
    //----------------------------------------------------------------------------
    private int penalite;
    public int getPenalite() {return penalite;}
    public void setPenalite(int penalite) {this.penalite = penalite;}
    //-----------------------------------------------------------
    
    // Constructeur

    public Retour(int idRetour, int idEmprunt, LocalDate dateRetour, int penalite)
    {
        this.idRetour = idRetour;
        this.idEmprunt = idEmprunt;
//        this.dateRetourPrevue = dateRetourPrevue;
        this.dateRetour = dateRetour;
        this.penalite = penalite;
    }
    
    public void afficherDetails()
    {
        System.out.println("ID du retour: " + idRetour);
        System.out.println("ID de l'emprunt: " + idEmprunt);
        System.out.println("Date du retour: " + dateRetour);
        System.out.println("Pénalité: " + penalite);
    }
}
