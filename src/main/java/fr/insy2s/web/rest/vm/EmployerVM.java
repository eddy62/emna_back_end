package fr.insy2s.web.rest.vm;

import java.util.List;

public class EmployerVM {
    Long employerId;

    String employerNom;

    String employerPrenom;

    Long societeId;

    public EmployerVM(Long employerId, String employerNom, String employerPrenom, Long societeId) {
        this.employerId = employerId;
        this.employerNom = employerNom;
        this.employerPrenom = employerPrenom;
        this.societeId = societeId;
    }

    public EmployerVM() {
    }

    public Long getEmployerId() {
        return employerId;
    }

    public void setEmployerId(Long employerId) {
        this.employerId = employerId;
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

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    @Override
    public String toString() {
        return "EmployerVM{" +
            "employerId=" + employerId +
            ", employerNom='" + employerNom + '\'' +
            ", employerPrenom='" + employerPrenom + '\'' +
            ", societeId=" + societeId +
            '}';
    }
}
