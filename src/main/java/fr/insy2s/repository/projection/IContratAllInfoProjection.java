package fr.insy2s.repository.projection;

import java.time.LocalDate;

public interface IContratAllInfoProjection {
    Long getContratId();

    String getContratTitre();

    LocalDate getContratDateCreation();

    String getContratSigner();

    String getContratArchiver();

    String getEmployerMatricule();

    String getEmployerCivilite();

    String getEmployerNomNaissance();

    String getEmployerNomUsage();

    String getEmployerPrenom();

    LocalDate getEmployerDateNaissance();

    String getEmployerVilleNaissance();

    String getEmployerDepartementNaissance();

    String getEmployerPaysNaissance();

    String getEmployerNumeroSecuriteSociale();

    String getEmployerEmail();

    String getEmployerTelephonFixe();

    String getEmployerTelephonePortable();

    String getEmployerFax();

    String getEmployerAdresseBoitePostale();

    String getEmployerAdresseCodePostal();

    String getEmployerAdresseNomRue();

    String getEmployerAdresseNumeroRue();

    String getEmployerAdresseVille();

    String getClauseDescription();

    String getArticleTitre();

    String getInfoSocieteRaisonSociale();

    String getInfoSocieteTelephone();

    String getInfoSocieterFax();

    String getInfoSocieterFormeJuridique();

    String getInfoSocieterSiren();

    String getInfoSocieterSiret();

    String getInfoSocieterEmail();

    String getInfoSocieterAdresseBoitePostale();

    String getInfoSocieterAdresseCodePostale();

    String getInfoSocieterAdresseNomRue();

    String getInfoSocieterAdresseNumeroRue();

    String getInfoSocieterAdresseVille();


}
