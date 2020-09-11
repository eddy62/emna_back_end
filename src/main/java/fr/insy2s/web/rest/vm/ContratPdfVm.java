package fr.insy2s.web.rest.vm;

public class ContratPdfVm {
    String titre;

    public ContratPdfVm() {
    }

    public ContratPdfVm(String titre) {
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
}
