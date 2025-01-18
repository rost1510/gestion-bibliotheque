package projetJavaDeux;
import java.time.LocalDate;
import java.util.Date;

public class Emprunt
{
    private int idEmprunt;
    public int getIdEmprunt() {return idEmprunt;}
    public void setIdEmprunt(int idEmprunt) {this.idEmprunt = idEmprunt;}
    //-------------------------------------------------------------------
    private int idMembre;
    public int getIdMembre() {return idMembre;}
    public void setIdMembre(int membreId) {this.idMembre = idMembre;}
    //---------------------------------------------------------------
    private int idLivre;
    public int getIdLivre() {return idLivre;}
    public void setIdLivre(int livreId) {this.idLivre = livreId;}
    //-----------------------------------------------------------
    private LocalDate dateEmprunt;
    public LocalDate getDateEmprunt() {return dateEmprunt;}
    public void setDateEmprunt(LocalDate dateEmprunt) {this.dateEmprunt = dateEmprunt;}
    //----------------------------------------------------------------------------
    private LocalDate dateRetourPrevue;
    public LocalDate getDateRetourPrevue() {return dateRetourPrevue;}
    public void setDateRetourPrevue(LocalDate dateRetourPrevue) {this.dateRetourPrevue = dateRetourPrevue;}
    //------------------------------------------------------------------------------------------------
    
    // Constructeur

    public Emprunt(int idEmprunt, int idMembre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetourPrevue)
    {
        this.idEmprunt = idEmprunt;
        this.idMembre = idMembre;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
    }
    
    public void afficherDetails()
    {
        System.out.println("ID de l'emprunt: " + idEmprunt);
        System.out.println("NI de l'emprunteur: " + idMembre);
        System.out.println("NI du livre: " + idLivre);
        System.out.println("Date de sortie: " + dateEmprunt);
        System.out.println("Date de retour prévue: " + dateRetourPrevue);
    }

}