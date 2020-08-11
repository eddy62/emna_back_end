package fr.insy2s.repository.projection;

public interface IEmployeContratProjection {
    Long getEmployerId();

    String getEmployerNom();

    String getEmployerPrenom();

    Long getSocieteId();

    String getArticleTitre();

    Long getClauseId();

    String getClauseReference();

    String getClauseDescription();


}
