package fr.insy2s.web.rest.vm;

public class ContratEmployerVM {
    Long employerId;

    Boolean contratSigner;

    Boolean contratArchiver;

    String contratTitre;

    String employerNom;

    String employerPrenom;

    public ContratEmployerVM(Long employerId, String contratTitre, String employerNom, String employerPrenom, Boolean contratSigner, Boolean contratArchiver) {
        this.employerId = employerId;
        this.contratTitre = contratTitre;
        this.employerNom = employerNom;
        this.employerPrenom = employerPrenom;
        this.contratArchiver = contratArchiver;
        this.contratSigner = contratSigner;
    }

    public ContratEmployerVM() {
    }

    public Boolean getContratSigner(){return contratSigner;}

    public void setContratSigner(Boolean contratSigner){this.contratSigner =contratSigner;}

    public Boolean getContratArchiver(){return contratArchiver;}

    public void setContratArchiver(Boolean contratArchiver){this.contratArchiver = contratArchiver;}

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
