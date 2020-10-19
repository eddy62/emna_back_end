package fr.insy2s.utils.wrapper;

import fr.insy2s.domain.SaisieArticle;

public class WrapperSingleAmendment {
    private final String titleArticle;
    private final String entitleArticle;
    private final String description;
    private final String inputArticle;

    public WrapperSingleAmendment(SaisieArticle saisierticle) {
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
        return "WrapperSingleAmendment{" +
            "titleArticle='" + titleArticle + '\'' +
            ", entitleArticle='" + entitleArticle + '\'' +
            ", description='" + description + '\'' +
            ", inputArticle='" + inputArticle + '\'' +
            '}';
    }
}
