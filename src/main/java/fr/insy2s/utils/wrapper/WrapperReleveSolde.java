package fr.insy2s.utils.wrapper;

import fr.insy2s.domain.Releve;
import fr.insy2s.service.dto.ReleveDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WrapperReleveSolde {
    private Long id;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private String banque;


    private Long etatReleveId;

    private Long societeId;

    private BigDecimal solde;

    public WrapperReleveSolde(ReleveDTO releveDTO, BigDecimal solde) {
        this.id = releveDTO.getId();

        this.dateDebut = releveDTO.getDateDebut();

        this.dateFin = releveDTO.getDateFin();

        this.banque = releveDTO.getBanque();

        this.etatReleveId = releveDTO.getEtatReleveId();

        this.societeId = releveDTO.getSocieteId();

        this.solde = solde;
    }

    public WrapperReleveSolde(Long id, LocalDate dateDebut, LocalDate dateFin, String banque, Long etatReleveId, Long societeId, BigDecimal solde) {
        this.id = id;

        this.dateDebut = dateDebut;

        this.dateFin = dateFin;

        this.banque = banque;

        this.etatReleveId = etatReleveId;

        this.societeId = societeId;

        this.solde = solde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public Long getEtatReleveId() {
        return etatReleveId;
    }

    public void setEtatReleveId(Long etatReleveId) {
        this.etatReleveId = etatReleveId;
    }

    public Long getSocieteId() {
        return societeId;
    }

    public void setSocieteId(Long societeId) {
        this.societeId = societeId;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "WrapperReleveSolde{" +
            "id=" + id +
            ", dateDebut=" + dateDebut +
            ", dateFin=" + dateFin +
            ", banque='" + banque + '\'' +
            ", etatReleveId=" + etatReleveId +
            ", societeId=" + societeId +
            ", solde=" + solde +
            '}';
    }
}
