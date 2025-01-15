package projetJavaDeux;
import java.util.Date;

//----------------------

public class Emprunt
{
    private int idEmprunt;
    public int getIdEmprunt() { return idEmprunt; }
    //---------------------------------------------
    private int membreId;
    public int getMembreId() { return membreId; }
    //-------------------------------------------
    private int livreId;
    public int getLivreId() { return livreId; }
    //-----------------------------------------
    private Date dateEmprunt;
    public Date getDateEmprunt() { return dateEmprunt; }
    //--------------------------------------------------
    private Date dateRetourPrevue;
    public Date getDateRetourPrevue() { return dateRetourPrevue; }
    //------------------------------------------------------------
    private Date dateRetourEffective;
    public Date getDateRetourEffective() { return dateRetourEffective; }
    public void setDateRetourEffective(Date dateRetourEffective) {this.dateRetourEffective = dateRetourEffective;}
    //------------------------------------------------------------------------------------------------------------
    
    public Emprunt(int idEmprunt, int membreId, int livreId, Date dateEmprunt, Date dateRetourPrevue)
    {
        this.idEmprunt = idEmprunt;
        this.membreId = membreId;
        this.livreId = livreId;
        this.dateEmprunt = dateEmprunt;
        this.dateRetourPrevue = dateRetourPrevue;
    }

    public long calculerPénalité()
    {
        if (dateRetourEffective != null && dateRetourEffective.after(dateRetourPrevue))
        {
            long diffInMillis = dateRetourEffective.getTime() - dateRetourPrevue.getTime();
            long diffInDays = diffInMillis / (100 * 60 * 60 * 24);
            return diffInDays * 100; // Pénalité de 100 F CFA par jour de retard
        }
        return 0;
    }

    @Override
    public String toString()
    {
        return "Emprunt{idEmprunt=" + idEmprunt + ", membreId=" + membreId + ", livreId=" + livreId +
                ", dateEmprunt=" + dateEmprunt + ", dateRetourPrevue=" + dateRetourPrevue + 
                ", dateRetourEffective=" + dateRetourEffective + '}';
    }
}
