package fr.insy2s.utils.wrapper;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class WrapperArchivedStatement {
    // Releve
    private final Long id;
    private final String dateDebut;
    private final String dateFin;
    private final String solde;
    private final String banque;
    private final String nomSociete;
    private final List<WrapperPDFSingleOperation> operationList;
    private final JRBeanCollectionDataSource operationsDataSource;
//    private final List<WrapperPDFSingleFacture> factureList;

    public WrapperArchivedStatement(Long id, String dateDebut, String dateFin, String solde,
                                    String banque, String nomSociete, List<WrapperPDFSingleOperation> operationList)
    {
        this.id                     = id;
        this.dateDebut              = dateDebut +"";
        this.dateFin                = dateFin   +"";
        this.solde                  = solde;
        this.banque                 = banque;
        this.nomSociete             = nomSociete;
        this.operationList          = operationList;
        this.operationsDataSource   = new JRBeanCollectionDataSource(operationList, false);
    }

    public Long getId() {
        return id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public String getSolde() {
        return solde;
    }

    public String getBanque() {
        return banque;
    }

    public String getNomSociete() {
        return nomSociete;
    }

    public List<WrapperPDFSingleOperation> getOperationList() {
        return operationList;
    }

    public JRBeanCollectionDataSource getOperationsDataSource() {
        return operationsDataSource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WrapperArchivedStatement that = (WrapperArchivedStatement) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(dateDebut, that.dateDebut) &&
            Objects.equals(dateFin, that.dateFin) &&
            Objects.equals(solde, that.solde) &&
            Objects.equals(banque, that.banque) &&
            Objects.equals(nomSociete, that.nomSociete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateDebut, dateFin, solde, banque, nomSociete);
    }

    @Override
    public String toString() {
        return "WrapperArchivedStatement{" +
            "id=" + id +
            ", dateDebut='" + dateDebut + '\'' +
            ", dateFin='" + dateFin + '\'' +
            ", solde='" + solde + '\'' +
            ", banque='" + banque + '\'' +
            ", nomSociete='" + nomSociete + '\'' +
            '}';
    }
}

