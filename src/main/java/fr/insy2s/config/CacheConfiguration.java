package fr.insy2s.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

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
