package fr.insy2s.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, fr.insy2s.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, fr.insy2s.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, fr.insy2s.domain.User.class.getName());
            createCache(cm, fr.insy2s.domain.Authority.class.getName());
            createCache(cm, fr.insy2s.domain.User.class.getName() + ".authorities");
            createCache(cm, fr.insy2s.domain.Facture.class.getName());
            createCache(cm, fr.insy2s.domain.Facture.class.getName() + ".listeProduits");
            createCache(cm, fr.insy2s.domain.ClientFournisseur.class.getName());
            createCache(cm, fr.insy2s.domain.ClientFournisseur.class.getName() + ".listeFactures");
            createCache(cm, fr.insy2s.domain.ClientFournisseur.class.getName() + ".listeDevis");
            createCache(cm, fr.insy2s.domain.Produit.class.getName());
            createCache(cm, fr.insy2s.domain.Produit.class.getName() + ".listeFactures");
            createCache(cm, fr.insy2s.domain.Produit.class.getName() + ".listeDevis");
            createCache(cm, fr.insy2s.domain.Operation.class.getName());
            createCache(cm, fr.insy2s.domain.Operation.class.getName() + ".listeFactures");
            createCache(cm, fr.insy2s.domain.Releve.class.getName());
            createCache(cm, fr.insy2s.domain.Releve.class.getName() + ".listeOperations");
            createCache(cm, fr.insy2s.domain.EtatReleve.class.getName());
            createCache(cm, fr.insy2s.domain.Devis.class.getName());
            createCache(cm, fr.insy2s.domain.Devis.class.getName() + ".listeProduits");
            createCache(cm, fr.insy2s.domain.Contrat.class.getName());
            createCache(cm, fr.insy2s.domain.Contrat.class.getName() + ".listeAvenants");
            createCache(cm, fr.insy2s.domain.Contrat.class.getName() + ".listeArticles");
            createCache(cm, fr.insy2s.domain.Contrat.class.getName() + ".listeClauses");
            createCache(cm, fr.insy2s.domain.Avenant.class.getName());
            createCache(cm, fr.insy2s.domain.Avenant.class.getName() + ".listeArticles");
            createCache(cm, fr.insy2s.domain.Avenant.class.getName() + ".listeClauses");
            createCache(cm, fr.insy2s.domain.Clause.class.getName());
            createCache(cm, fr.insy2s.domain.Clause.class.getName() + ".listeContrats");
            createCache(cm, fr.insy2s.domain.Clause.class.getName() + ".listeAvenants");
            createCache(cm, fr.insy2s.domain.Article.class.getName());
            createCache(cm, fr.insy2s.domain.Article.class.getName() + ".listeClauses");
            createCache(cm, fr.insy2s.domain.Article.class.getName() + ".listeAvenants");
            createCache(cm, fr.insy2s.domain.Article.class.getName() + ".listeContrats");
            createCache(cm, fr.insy2s.domain.Adresse.class.getName());
            createCache(cm, fr.insy2s.domain.Employe.class.getName());
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeContrats");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeAbsences");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listePrimes");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeFichePaies");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeHeureSupplementaires");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeNoteDeFrais");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeAutresVariables");
            createCache(cm, fr.insy2s.domain.TypeAbsence.class.getName());
            createCache(cm, fr.insy2s.domain.Absence.class.getName());
            createCache(cm, fr.insy2s.domain.TypePrime.class.getName());
            createCache(cm, fr.insy2s.domain.Prime.class.getName());
            createCache(cm, fr.insy2s.domain.FichePaie.class.getName());
            createCache(cm, fr.insy2s.domain.HeuresSupplementaires.class.getName());
            createCache(cm, fr.insy2s.domain.NoteDeFrais.class.getName());
            createCache(cm, fr.insy2s.domain.AutresVariable.class.getName());
            createCache(cm, fr.insy2s.domain.EtatFacture.class.getName());
            createCache(cm, fr.insy2s.domain.EtatDevis.class.getName());
            createCache(cm, fr.insy2s.domain.Comptable.class.getName());
            createCache(cm, fr.insy2s.domain.Comptable.class.getName() + ".listeSocietes");
            createCache(cm, fr.insy2s.domain.Societe.class.getName());
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeFactures");
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeDevis");
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeReleves");
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeProduits");
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeClientsFournisseurs");
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeClauses");
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeArticles");
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeEmployes");
            createCache(cm, fr.insy2s.domain.InfoEntreprise.class.getName());
            createCache(cm, fr.insy2s.domain.StatutEmploye.class.getName());
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeContrats");
            createCache(cm, fr.insy2s.domain.Facture.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.Document.class.getName());
            createCache(cm, fr.insy2s.domain.Releve.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.Contrat.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.Facture.class.getName() + ".listeLigneProduits");
            createCache(cm, fr.insy2s.domain.LigneProduit.class.getName());
            createCache(cm, fr.insy2s.domain.Devis.class.getName() + ".listeLigneProduits");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeAvanceRappelSalaires");
            createCache(cm, fr.insy2s.domain.Employe.class.getName() + ".listeDpaes");
            createCache(cm, fr.insy2s.domain.Societe.class.getName() + ".listeDpaes");
            createCache(cm, fr.insy2s.domain.TypeContrat.class.getName());
            createCache(cm, fr.insy2s.domain.EtatVariablePaie.class.getName());
            createCache(cm, fr.insy2s.domain.AvanceRappelSalaire.class.getName());
            createCache(cm, fr.insy2s.domain.Dpae.class.getName());
            createCache(cm, fr.insy2s.domain.Depense.class.getName());
            createCache(cm, fr.insy2s.domain.Depense.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.Depense.class.getName() + ".as");
            createCache(cm, fr.insy2s.domain.EtatDepense.class.getName());
            createCache(cm, fr.insy2s.domain.SaisieArticle.class.getName());
            createCache(cm, fr.insy2s.domain.Devis.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.Avenant.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.Absence.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.FichePaie.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.NoteDeFrais.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.AutresVariable.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.Dpae.class.getName() + ".listeDocuments");
            createCache(cm, fr.insy2s.domain.TypeDocument.class.getName());
            createCache(cm, fr.insy2s.domain.SaisieArticle.class.getName() + ".listeAvenants");
            createCache(cm, fr.insy2s.domain.Avenant.class.getName() + ".listeSaisieArticles");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

}
