package projetJavaDeux;

import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres;
    private List<Membre> membres;
    private List<Emprunt> emprunts;

    public Bibliotheque() {
        this.livres = new ArrayList<>();
        this.membres = new ArrayList<>();
        this.emprunts = new ArrayList<>();
    }

    // Méthodes pour gérer les livres

    public void ajouterLivre(Livre livre) {
        livres.add(livre);
        LivreDAO.ajouterLivre(livre);
    }

    public Livre getLivreById(int id) {
        return livres.stream().filter(l -> l.getId() == id).findFirst().orElse(LivreDAO.getLivreById(id));
    }

    public List<Livre> getAllLivres() {
        if (livres.isEmpty()) {
            livres = LivreDAO.getAllLivres();
        }
        return livres;
    }

    // Méthodes pour gérer les membres

    public void ajouterMembre(Membre membre) {
        membres.add(membre);
        MembreDAO.ajouterMembre(membre);
    }

    public Membre getMembreById(int id) {
        return membres.stream().filter(m -> m.getId() == id).findFirst().orElse(MembreDAO.getMembreById(id));
    }

    public List<Membre> getAllMembres() {
        if (membres.isEmpty()) {
            membres = MembreDAO.getAllMembres();
        }
        return membres;
    }

    // Méthodes pour gérer les emprunts

    public void enregistrerEmprunt(Emprunt emprunt) {
        emprunts.add(emprunt);
        EmpruntDAO.ajouterEmprunt(emprunt);
    }

    public Emprunt getEmpruntById(int idEmprunt) {
        return emprunts.stream().filter(e -> e.getIdEmprunt() == idEmprunt).findFirst().orElse(EmpruntDAO.getEmpruntById(idEmprunt));
    }

    public List<Emprunt> getAllEmprunts() {
        if (emprunts.isEmpty()) {
            emprunts = EmpruntDAO.getAllEmprunts();
        }
        return emprunts;
    }

    public List<Emprunt> getEmpruntsEnRetard() {
        return EmpruntDAO.getEmpruntsEnRetard();
    }

    @Override
    public String toString() {
        return "Bibliotheque{" +
                "livres=" + livres +
                ", membres=" + membres +
                ", emprunts=" + emprunts +
                '}';
    }
}
