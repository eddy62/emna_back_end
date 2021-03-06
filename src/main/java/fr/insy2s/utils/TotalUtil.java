package fr.insy2s.utils;

import fr.insy2s.domain.Devis;
import fr.insy2s.domain.Facture;
import fr.insy2s.domain.LigneProduit;

import java.math.BigDecimal;

public final class TotalUtil {

    public static BigDecimal getTTCFacture(Facture facture) {
        BigDecimal totalFactureTTC = BigDecimal.valueOf(0);
        for (LigneProduit ligneProduits : facture.getListeLigneProduits()) {
            totalFactureTTC = totalFactureTTC.add(ligneProduits.getProduit().getPrix()
                .multiply(ligneProduits.getRemise() != null ? BigDecimal.valueOf(1).subtract(ligneProduits.getRemise())
                    : BigDecimal.valueOf(1))
                .multiply(BigDecimal.valueOf(ligneProduits.getQuantite()))
                .multiply(ligneProduits.getProduit().getTva()
                    .divide(BigDecimal.valueOf(100))
                    .add(BigDecimal.valueOf(1))));
        }
        return totalFactureTTC.setScale(2);
    }

    public static BigDecimal getTTCDevis(Devis devis) {
        BigDecimal totalDevisTTC = BigDecimal.valueOf(0);
        for (LigneProduit ligneProduits : devis.getListeLigneProduits()) {
            totalDevisTTC = totalDevisTTC.add(ligneProduits.getProduit().getPrix()
                .multiply(ligneProduits.getRemise() != null ? BigDecimal.valueOf(1).subtract(ligneProduits.getRemise())
                    : BigDecimal.valueOf(1))
                .multiply(BigDecimal.valueOf(ligneProduits.getQuantite()))
                .multiply(ligneProduits.getProduit().getTva()
                    .divide(BigDecimal.valueOf(100))
                    .add(BigDecimal.valueOf(1))));
        }
        return totalDevisTTC.setScale(2);
    }
}
