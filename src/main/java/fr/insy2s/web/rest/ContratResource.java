package fr.insy2s.web.rest;

import fr.insy2s.repository.projection.IContratAllInfoProjection;
import fr.insy2s.repository.projection.IContratEmployerProjection;
import fr.insy2s.service.ContratService;
import fr.insy2s.service.dto.ContratDTO;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import fr.insy2s.web.rest.vm.ClauseEtArticleVM;
import fr.insy2s.web.rest.vm.ContratAllInfoVM;
import fr.insy2s.web.rest.vm.ContratEmployerVM;
import io.github.jhipster.web.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.insy2s.domain.Contrat}.
 */
@RestController
@RequestMapping("/api")
public class ContratResource {

    private final Logger log = LoggerFactory.getLogger(ContratResource.class);

    private static final String ENTITY_NAME = "contrat";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContratService contratService;

    public ContratResource(ContratService contratService) {
        this.contratService = contratService;
    }

    /**
     * {@code POST  /contrats} : Create a new contrat.
     *
     * @param contratDTO the contratDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contratDTO, or with status {@code 400 (Bad Request)} if the contrat has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/contrats")
    public ResponseEntity<ContratDTO> createContrat(@Valid @RequestBody ContratDTO contratDTO) throws URISyntaxException {
        log.debug("REST request to save Contrat : {}", contratDTO);
        if (contratDTO.getId() != null) {
            throw new BadRequestAlertException("A new contrat cannot already have an ID", ENTITY_NAME, "idexists");
        }
        contratDTO.setDateCreation(LocalDate.now());
        contratDTO.setSigne(false);
        contratDTO.setArchive(false);
        ContratDTO result = contratService.save(contratDTO);
        return ResponseEntity.created(new URI("/api/contrats/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /contrats} : Updates an existing contrat.
     *
     * @param contratDTO the contratDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contratDTO,
     * or with status {@code 400 (Bad Request)} if the contratDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contratDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/contrats")
    public ResponseEntity<ContratDTO> updateContrat(@Valid @RequestBody ContratDTO contratDTO) throws URISyntaxException {
        log.debug("REST request to update Contrat : {}", contratDTO);
        if (contratDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContratDTO result = contratService.save(contratDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contratDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /contrats} : get all the contrats.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contrats in body.
     */
    @GetMapping("/contrats")
    public List<ContratDTO> getAllContrats() {
        log.debug("REST request to get all Contrats");
        return contratService.findAll();
    }

    /**
     * {@code GET  /contrats/:id} : get the "id" contrat.
     *
     * @param id the id of the contratDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contratDTO, or with status {@code 404 (Not Found)}.
     */
   /* @GetMapping("/contrats/{id}")
    public ResponseEntity<ContratDTO> getContrat(@PathVariable Long id) {
        log.debug("REST request to get Contrat : {}", id);
        Optional<ContratDTO> contratDTO = contratService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contratDTO);
    }*/

    /**
     * {@code DELETE  /contrats/:id} : delete the "id" contrat.
     *
     * @param id the id of the contratDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/contrats/{id}")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        log.debug("REST request to delete Contrat : {}", id);
        contratService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/contrats/{id}")
    public ResponseEntity<ContratAllInfoVM> getTous(@PathVariable Long id) {
        log.debug("REST request to get details Contrat : {}", id);
        List<IContratAllInfoProjection> iContratAllInfoProjections = this.contratService.getContratAllInfos(id);
        Optional<ContratAllInfoVM> contratAllInfoVM = Optional.of(new ContratAllInfoVM());
        contratAllInfoVM.get().setContratId(iContratAllInfoProjections.get(0).getContratId());
        contratAllInfoVM.get().setContratTitre(iContratAllInfoProjections.get(0).getContratTitre());
        contratAllInfoVM.get().setContratDateCreation(iContratAllInfoProjections.get(0).getContratDateCreation());
        contratAllInfoVM.get().setContratSigner(iContratAllInfoProjections.get(0).getContratSigner());
        contratAllInfoVM.get().setContratArchiver(iContratAllInfoProjections.get(0).getContratArchiver());
        contratAllInfoVM.get().setEmployerMatricule(iContratAllInfoProjections.get(0).getEmployerMatricule());
        contratAllInfoVM.get().setEmployerCivilite(iContratAllInfoProjections.get(0).getEmployerCivilite());
        contratAllInfoVM.get().setEmployerNomNaissance(iContratAllInfoProjections.get(0).getEmployerNomNaissance());
        contratAllInfoVM.get().setEmployerNomUsage(iContratAllInfoProjections.get(0).getEmployerNomUsage());
        contratAllInfoVM.get().setEmployerPrenom(iContratAllInfoProjections.get(0).getEmployerPrenom());
        contratAllInfoVM.get().setEmployerDateNaissance(iContratAllInfoProjections.get(0).getEmployerDateNaissance());
        contratAllInfoVM.get().setEmployerVilleNaissance(iContratAllInfoProjections.get(0).getEmployerVilleNaissance());
        contratAllInfoVM.get().setEmployerDepartementNaissance(iContratAllInfoProjections.get(0).getEmployerDepartementNaissance());
        contratAllInfoVM.get().setEmployerPaysNaissance(iContratAllInfoProjections.get(0).getEmployerPaysNaissance());
        contratAllInfoVM.get().setEmployerNumeroSecuriteSociale(iContratAllInfoProjections.get(0).getEmployerNumeroSecuriteSociale());
        contratAllInfoVM.get().setEmployerEmail(iContratAllInfoProjections.get(0).getEmployerEmail());
        contratAllInfoVM.get().setEmployerTelephonFixe(iContratAllInfoProjections.get(0).getEmployerTelephonFixe());
        contratAllInfoVM.get().setEmployerTelephonePortable(iContratAllInfoProjections.get(0).getEmployerTelephonePortable());
        contratAllInfoVM.get().setEmployerFax(iContratAllInfoProjections.get(0).getEmployerFax());
        contratAllInfoVM.get().setEmployerAdresseBoitePostale(iContratAllInfoProjections.get(0).getEmployerAdresseBoitePostale());
        contratAllInfoVM.get().setEmployerAdresseCodePostal(iContratAllInfoProjections.get(0).getEmployerAdresseCodePostal());
        contratAllInfoVM.get().setEmployerAdresseNomRue(iContratAllInfoProjections.get(0).getEmployerAdresseNomRue());
        contratAllInfoVM.get().setEmployerAdresseNumeroRue(iContratAllInfoProjections.get(0).getEmployerAdresseNumeroRue());
        contratAllInfoVM.get().setEmployerAdresseVille(iContratAllInfoProjections.get(0).getEmployerAdresseVille());
        contratAllInfoVM.get().setInfoSocieteRaisonSociale(iContratAllInfoProjections.get(0).getInfoSocieteRaisonSociale());
        contratAllInfoVM.get().setInfoSocieteTelephone(iContratAllInfoProjections.get(0).getInfoSocieteTelephone());
        contratAllInfoVM.get().setInfoSocieterFax(iContratAllInfoProjections.get(0).getInfoSocieterFax());
        contratAllInfoVM.get().setInfoSocieterFormeJuridique(iContratAllInfoProjections.get(0).getInfoSocieterFormeJuridique());
        contratAllInfoVM.get().setInfoSocieterSiren(iContratAllInfoProjections.get(0).getInfoSocieterSiren());
        contratAllInfoVM.get().setInfoSocieterSiret(iContratAllInfoProjections.get(0).getInfoSocieterSiret());
        contratAllInfoVM.get().setInfoSocieterEmail(iContratAllInfoProjections.get(0).getInfoSocieterEmail());
        contratAllInfoVM.get().setInfoSocieterAdresseBoitePostale(iContratAllInfoProjections.get(0).getInfoSocieterAdresseBoitePostale());
        contratAllInfoVM.get().setInfoSocieterAdresseCodePostale(iContratAllInfoProjections.get(0).getInfoSocieterAdresseCodePostale());
        contratAllInfoVM.get().setInfoSocieterAdresseNomRue(iContratAllInfoProjections.get(0).getInfoSocieterAdresseNomRue());
        contratAllInfoVM.get().setInfoSocieterAdresseNumeroRue(iContratAllInfoProjections.get(0).getInfoSocieterAdresseNumeroRue());
        contratAllInfoVM.get().setInfoSocieterAdresseVille(iContratAllInfoProjections.get(0).getInfoSocieterAdresseVille());
        List<ClauseEtArticleVM> clauseEtArticleVMS = new ArrayList<>();
        for (IContratAllInfoProjection clause : iContratAllInfoProjections) {
            clauseEtArticleVMS.add(new ClauseEtArticleVM(clause.getArticleTitre(), clause.getClauseDescription()));
        }
        contratAllInfoVM.get().setClauseEtArticleList(clauseEtArticleVMS);
        return ResponseEntity.ok(contratAllInfoVM.get());
    }

    @GetMapping("/contrats/employer/{id}")
    public List<ContratEmployerVM> getAllContratEmployerByIdSociete(@PathVariable Long id) {
        List<ContratEmployerVM> contratEmployerVMS = new ArrayList<>();
        List<IContratEmployerProjection> iContratEmployerProjections = this.contratService.getAllContratEmployerById(id);
        for (IContratEmployerProjection iContratEmployerProjection: iContratEmployerProjections) {
            contratEmployerVMS.add(new ContratEmployerVM(iContratEmployerProjection.getEmployerId(), iContratEmployerProjection.getContratTitre(), iContratEmployerProjection.getEmployerNom(), iContratEmployerProjection.getEmployerPrenom()));
        }
        return contratEmployerVMS;
    }
}
