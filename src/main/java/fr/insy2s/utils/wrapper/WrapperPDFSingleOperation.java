package fr.insy2s.utils.wrapper;

public class WrapperPDFSingleOperation {
    private final Long id;
    private final String date;
    private final String description;
    private final String type;
    private final String solde;

    public WrapperPDFSingleOperation(Long id, String date, String description, String type, String solde) {
        this.id             = id;
        this.date           = date;
        this.description    = description;
        this.type           = type;
        this.solde          = solde;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getSolde() {
        return solde;
    }
}
