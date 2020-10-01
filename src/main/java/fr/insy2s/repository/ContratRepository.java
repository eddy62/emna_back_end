package fr.insy2s.repository;

import fr.insy2s.domain.Contrat;
import fr.insy2s.repository.projection.IContratAllInfoProjection;
import fr.insy2s.repository.projection.IContratEmployerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Contrat entity.
 */
@Repository
public interface ContratRepository extends JpaRepository<Contrat, Long> {
    @Query(nativeQuery = true,
        value = "SELECT DISTINCT c.ID AS contratId, c.TITRE AS contratTitre, c.DATE_CREATION AS contratDateCreation, c.SIGNE AS contratSigner, c.ARCHIVE AS contratArchiver, e.MATRICULE AS employerMatricule, e.CIVILITE AS employerCivilite, e.NOM_NAISSANCE AS employerNomNaissance, e.NOM_USAGE AS employerNomUsage, e.PRENOM AS employerPrenom, e.DATE_NAISSANCE AS employerDateNaissance, e.VILLE_NAISSANCE AS employerVilleNaissance, e.DEPARTEMENT_NAISSANCE AS employerDepartementNaissance, e.PAYS_NAISANCE AS employerPaysNaissance, e.NUMERO_SECURITE_SOCIALE AS employerNumeroSecuriteSociale, e.EMAIL AS employerEmail, e.TELEPHONE_FIX AS employerTelephonFixe, e.TELEPHONE_PORTABLE AS employerTelephonePortable, e.FAX AS employerFax, ad.BOITE_POSTALE AS employerAdresseBoitePostale, ad.CODE_POSTAL AS employerAdresseCodePostal, ad.NOM_RUE AS employerAdresseNomRue, ad.NUMERO_RUE AS employerAdresseNumeroRue, ad.VILLE  AS employerAdresseVille, cl.DESCRIPTION  AS clauseDescription, a.TITRE  AS articleTitre, ie.RAISON_SOCIALE AS infoSocieteRaisonSociale, ie.TELEPHONE AS infoSocieteTelephone, ie.FAX AS infoSocieterFax, ie.FORME_JURIDIQUE AS infoSocieterFormeJuridique, ie.SIREN AS infoSocieterSiren, ie.SIRET AS infoSocieterSiret, ie.EMAIL AS infoSocieterEmail, ad2.BOITE_POSTALE AS infoSocieterAdresseBoitePostale, ad2.CODE_POSTAL AS infoSocieterAdresseCodePostale, ad2.NOM_RUE AS infoSocieterAdresseNomRue, ad2.NUMERO_RUE AS infoSocieterAdresseNumeroRue, ad2.VILLE AS infoSocieterAdresseVille FROM CONTRAT c INNER JOIN EMPLOYE e ON c.EMPLOYE_ID = e.ID INNER JOIN ADRESSE ad ON e.ADRESSE_ID = ad.ID INNER JOIN CLAUSE_LISTE_CONTRATS clc ON c.ID = clc.LISTE_CONTRATS_ID inner JOIN CLAUSE cl ON cl.ID = clc.CLAUSE_ID INNER JOIN article a ON  a.id = cl.ARTICLE_ID INNER JOIN SOCIETE s ON  s.ID = a.SOCIETE_ID INNER JOIN ADRESSE ad2 ON  s.ADRESSE_ID = ad2.ID INNER JOIN INFO_ENTREPRISE ie ON  ie.ID = s.INFO_ENTREPRISE_ID WHERE c.EMPLOYE_ID =:id")
    List<IContratAllInfoProjection> getContratAllInfo(@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT e.ID AS employerId, e.NOM_USAGE AS employerNom, e.PRENOM AS employerPrenom, c.TITRE AS contratTitre, c.SIGNE AS contratSigner , c.ARCHIVE AS contratArchiver FROM EMPLOYE e INNER JOIN CONTRAT c ON c.EMPLOYE_ID = e.ID WHERE e.SOCIETE_ID =:id")
    List<IContratEmployerProjection> getAllContratEmployerByEmployeId(@Param("id") Long id);

    @Query("from Contrat c where c.employe.id=:idEmployee and c.archive=false")
    Contrat getActiveContratEmployee(@Param("idEmployee")Long id);
}
