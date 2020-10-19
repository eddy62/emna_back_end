package fr.insy2s.utils.wrapper;

import fr.insy2s.domain.SaisieArticle;

public class WrapperSingleAmendement {
    private final String titleArticle;
    private final String entitleArticle;
    private final String description;
    private final String inputArticle;

    public WrapperSingleAmendement(SaisieArticle saisierticle) {
        this.titleArticle = saisierticle.getArticle().getTitre();
        this.entitleArticle = saisierticle.getArticle().getIntitule();
        this.description = saisierticle.getArticle().getDescription();
        this.inputArticle = saisierticle.getLibelle();
    }

    public String getTitleArticle() {
        return titleArticle;
    }

    public String getEntitleArticle() {
        return entitleArticle;
    }

    public String getDescription() {
        return description;
    }

    public String getInputArticle() {
        return inputArticle;
    }

    @Override
    public String toString() {
        return "WrapperSingleAmendement{" +
            "titleArticle='" + titleArticle + '\'' +
            ", entitleArticle='" + entitleArticle + '\'' +
            ", description='" + description + '\'' +
            ", inputArticle='" + inputArticle + '\'' +
            '}';
    }
}
