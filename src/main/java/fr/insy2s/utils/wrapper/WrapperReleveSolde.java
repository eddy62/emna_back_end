package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.ReleveDTO;

import java.math.BigDecimal;

public class WrapperReleveSolde {
    private ReleveDTO releve;

    private BigDecimal solde;

    public WrapperReleveSolde(ReleveDTO releve, BigDecimal solde) {
        this.releve = releve;
        this.solde = solde;
    }

    public ReleveDTO getReleve() {
        return releve;
    }

    public void setReleve(ReleveDTO releve) {
        this.releve = releve;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }
}
