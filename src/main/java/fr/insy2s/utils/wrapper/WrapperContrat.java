package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.service.dto.TypeContratDTO;

import java.time.LocalDate;

/**
 * WrapperContrat
 *
 * @author De Cédric Belot
 */
public class WrapperContrat {

    //contrat
    private Long id;
    private String titre;
    private LocalDate dateCreation;
    private Boolean signe;
    private Boolean archive;

    //typeContrat
    private Long idTypeContrat;
    private String codeRef;
    private String intitule;

    /**
     * Empty constructor
     */
    public WrapperContrat() {
        //empty method
    }

    /**
     * Parameterized constructor
     *
     * @param contratDTO     the DTO containing contrat attributes
     * @param typeContratDTO the DTO containing typeContrat attributes
     */
    public WrapperContrat(ContratDTO contratDTO, TypeContratDTO typeContratDTO) {
        super();
        //contrat
        this.id = contratDTO.getId();
        this.titre = contratDTO.getTitre();
        this.dateCreation = contratDTO.getDateCreation();
        this.signe = contratDTO.isSigne();
        this.archive = contratDTO.isArchive();
        //typeContrat
        this.idTypeContrat = typeContratDTO.getId();
        this.codeRef = typeContratDTO.getCodeRef();
        this.intitule = typeContratDTO.getIntitule();
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

}
