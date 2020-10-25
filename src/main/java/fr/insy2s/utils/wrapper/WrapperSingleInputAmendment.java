package fr.insy2s.utils.wrapper;

import fr.insy2s.domain.SaisieArticle;

public class WrapperSingleInputAmendment {
    private final String titleArticle;
    private final String entitledArticle;
    private final String description;
    private final String inputArticle;

    public WrapperSingleInputAmendment(SaisieArticle saisierticle) {
        this.titleArticle = saisierticle.getArticle().getTitre();
        this.entitledArticle = saisierticle.getArticle().getIntitule();
        this.description = saisierticle.getArticle().getDescription();
        this.inputArticle = saisierticle.getLibelle();
    }

    public String getTitleArticle() {
        return titleArticle;
    }

    public String getEntitledArticle() {
        return entitledArticle;
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
            ", entitleArticle='" + entitledArticle + '\'' +
            ", description='" + description + '\'' +
            ", inputArticle='" + inputArticle + '\'' +
            '}';
    }
}
