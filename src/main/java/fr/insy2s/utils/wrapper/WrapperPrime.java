package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.PrimeDTO;
import fr.insy2s.service.dto.TypePrimeDTO;

/**
 * Wrapper Prime avec TypePrime
 */
public class WrapperPrime {

    // Prime
    private Long id;
    private String type;
    private Double montant;
    private Integer annee;
    private Integer mois;
    private Long employeId;
    private Long etatVariablePaieId;

    // TypePrime
    private Long idTypePrime;
    private String codeRef;
    private String intitule;

    /**
     * Constructeur WrapperPrime par défaut
     *
     */
    public WrapperPrime(){
        // constructeur par défaut
    }

    /**
     *  Constructeur WrapperPrime avec paramètres
     *
     * @param primeDTO
     * @param typePrimeDTO
     */
    public  WrapperPrime(PrimeDTO primeDTO, TypePrimeDTO typePrimeDTO){

        super();
        // Prime
        this.id = primeDTO.getId();
        this.type = primeDTO.getType();
        this.montant = primeDTO.getMontant();
        this.annee = primeDTO.getAnnee();
        this.mois = primeDTO.getMois();
        this.employeId = primeDTO.getEmployeId();
        this.etatVariablePaieId = primeDTO.getEtatVariablePaieId();

        // TypePrime
        this.idTypePrime = typePrimeDTO.getId();
        this.codeRef = typePrimeDTO.getCodeRef();
        this.intitule = typePrimeDTO.getIntitule();

    }

    // Getters / Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Integer getMois() {
        return mois;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public Long getEtatVariablePaieId() {
        return etatVariablePaieId;
    }

    public void setEtatVariablePaieId(Long etatVariablePaieId) {
        this.etatVariablePaieId = etatVariablePaieId;
    }

    public Long getIdTypePrime() {
        return idTypePrime;
    }

    public void setIdTypePrime(Long idTypePrime) {
        this.idTypePrime = idTypePrime;
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
