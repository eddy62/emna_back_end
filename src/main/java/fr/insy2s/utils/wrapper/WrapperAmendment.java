package fr.insy2s.utils.wrapper;

import fr.insy2s.domain.Contrat;
import fr.insy2s.domain.Societe;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

public class WrapperAmendment {
    private String titleContract;
    private String nameSociety;
    private String adressSociety;
    private String siret;
    private String nameEmployee;
    private String dateBirth;
    private String placeBirth;
    private JRBeanCollectionDataSource inputArticleDataSource;

    public WrapperAmendment() {
    }

    public WrapperAmendment(Contrat contrat, Societe societe, List<WrapperSingleInputAmendment> amendements) {
        this.titleContract = contrat.getTitre();
        this.nameSociety = societe.getCivilite();
        this.adressSociety = societe.getAdresse().getNumeroRue() +" "+societe.getAdresse().getNomRue()+ " "+
        societe.getAdresse().getCodePostal()+" "+societe.getAdresse().getVille();
        this.siret = societe.getInfoEntreprise().getSiret();
        this.nameEmployee = contrat.getEmploye().getNomNaissance()+" "+contrat.getEmploye().getPrenom();
        this.dateBirth = contrat.getEmploye().getDateNaissance().toString();
        this.placeBirth = contrat.getEmploye().getVilleNaissance();
        this.inputArticleDataSource = new JRBeanCollectionDataSource(amendements,false);
    }

    public String getTitleContract() {
        return titleContract;
    }

    public String getNameSociety() {
        return nameSociety;
    }

    public String getAdressSociety() {
        return adressSociety;
    }

    public String getSiret() {
        return siret;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public JRBeanCollectionDataSource getInputArticleDataSource() {
        return inputArticleDataSource;
    }

    @Override
    public String toString() {
        return "WrapperAmendment{" +
            ", titleContract='" + titleContract + '\'' +
            ", nameSociety='" + nameSociety + '\'' +
            ", adressSociety='" + adressSociety + '\'' +
            ", siret='" + siret + '\'' +
            ", nameEmployee='" + nameEmployee + '\'' +
            ", dateBirth='" + dateBirth + '\'' +
            ", placeBirth='" + placeBirth + '\'' +
            ", inputArticleDataSource=" + inputArticleDataSource +
            '}';
    }
}
