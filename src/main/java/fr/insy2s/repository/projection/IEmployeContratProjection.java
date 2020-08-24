package fr.insy2s.repository.projection;

public interface IEmployeContratProjection {
    Long getEmployerId();

    String getEmployerNom();

    String getEmployerPrenom();

    Long getSocieteId();

    String getArticleTitre();

    String getArticleDescription();

    Long getArticleId();

    Long getClauseId();

    String getClauseReference();

    String getClauseDescription();


}
