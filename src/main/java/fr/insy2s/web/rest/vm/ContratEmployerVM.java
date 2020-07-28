package fr.insy2s.web.rest.vm;

public class ContratEmployerVM {
    Long employerId;

    String contratTitre;

    String employerNom;

    String employerPrenom;

    public ContratEmployerVM(Long employerId, String contratTitre, String employerNom, String employerPrenom) {
        this.employerId = employerId;
        this.contratTitre = contratTitre;
        this.employerNom = employerNom;
        this.employerPrenom = employerPrenom;
    }

    public ContratEmployerVM() {
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
    }

    public String getContratTitre() {
        return contratTitre;
    }

    public void setContratTitre(String contratTitre) {
        this.contratTitre = contratTitre;
    }

    public String getEmployerNom() {
        return employerNom;
    }

    public void setEmployerNom(String employerNom) {
        this.employerNom = employerNom;
    }

    public String getEmployerPrenom() {
        return employerPrenom;
    }

    public void setEmployerPrenom(String employerPrenom) {
        this.employerPrenom = employerPrenom;
    }
}
