package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.service.dto.TypeContratDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * WrapperContrat
 *
 * @author Created by Cédric Belot, Modified by Jérémy Schrotzenberger
 */
public class WrapperContrat {

    //contrat
    private Long id;
    private String titre;
    private LocalDate dateCreation;
    private Boolean signe;
    private Boolean archive;

    //employe
    private Long idEmploye;

    //typeContrat
    private Long idTypeContrat;
    private String codeRef;
    private String intitule;

    //Liste de wrappers de saisies d'article
    List<WrapperSaisieArticle> wrapperSaisieArticles;

    /**
     * Empty constructor
     */
    public WrapperContrat() {
        //empty method
    }

    /**
     * Parameterized constructor
     *
     * @param contratDTO           the DTO containing contrat attributes
     * @param typeContratDTO       the DTO containing typeContrat attributes
     * @param wrapperSaisieArticles      the list of wrapperArticles
     */
    public WrapperContrat(ContratDTO contratDTO, TypeContratDTO typeContratDTO, List<WrapperSaisieArticle> wrapperSaisieArticles) {
        super();
        //contrat
        this.id = contratDTO.getId();
        this.titre = contratDTO.getTitre();
        this.dateCreation = contratDTO.getDateCreation();
        this.signe = contratDTO.isSigne();
        this.archive = contratDTO.isArchive();
        this.idEmploye = contratDTO.getEmployeId();

        //typeContrat
        this.idTypeContrat = typeContratDTO.getId();
        this.codeRef = typeContratDTO.getCodeRef();
        this.intitule = typeContratDTO.getIntitule();

        //Liste de wrappers de saisies d'articles
        this.wrapperSaisieArticles = wrapperSaisieArticles;
    }

    // getters setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Boolean getSigne() {
        return signe;
    }

    public void setSigne(Boolean signe) {
        this.signe = signe;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }

    public Long getIdTypeContrat() {
        return idTypeContrat;
    }

    public void setIdTypeContrat(Long idTypeContrat) {
        this.idTypeContrat = idTypeContrat;
    }

    public String getCodeRef() {
        return codeRef;
    }

    public void setCodeRef(String codeRef) {
        this.codeRef = codeRef;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Long getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(Long idEmploye) {
        this.idEmploye = idEmploye;
    }

    public List<WrapperSaisieArticle> getWrapperSaisieArticles() {
        return wrapperSaisieArticles;
    }

    public void setWrapperSaisieArticles(List<WrapperSaisieArticle> wrapperSaisieArticles) {
        this.wrapperSaisieArticles = wrapperSaisieArticles;
    }
}
