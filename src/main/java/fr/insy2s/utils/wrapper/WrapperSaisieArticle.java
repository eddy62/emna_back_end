package fr.insy2s.utils.wrapper;

import fr.insy2s.service.dto.ArticleDTO;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.service.dto.SaisieArticleDTO;

import java.time.LocalDate;

/**
 * WrapperArticle
 *
 * @author Created by Jérémy Schrotzenberger
 */

public class WrapperSaisieArticle {

    //SaisieArticle
    private Long id;
    private String libelle;

    //Article
    private Long idArticle;

    //Contrat
    private Long idContrat;

    /**
     * Empty constructor
     */
    public WrapperSaisieArticle() {
        //empty method
    }

    /**
     * Parameterized constructor
     *
     * @param saisieArticleDTO  the DTO containing saisieArticle attributes
     * @param articleDTO        the DTO containing article attributes
     */
    public WrapperSaisieArticle(SaisieArticleDTO saisieArticleDTO, ArticleDTO articleDTO) {
        super();
        //SaisieArticle
        this.id = saisieArticleDTO.getId();
        this.libelle = saisieArticleDTO.getLibelle();
        //Article
        this.idArticle = articleDTO.getId();
        //Contrat
        this.idContrat = saisieArticleDTO.getContratId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Long idArticle) {
        this.idArticle = idArticle;
    }

    public Long getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(Long idContrat) {
        this.idContrat = idContrat;
    }

}
