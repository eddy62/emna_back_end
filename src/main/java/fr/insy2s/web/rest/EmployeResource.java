package fr.insy2s.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insy2s.service.EmployeService;
import fr.insy2s.service.dto.EmployeDTO;
import fr.insy2s.utils.wrapper.WrapperEmploye;
import fr.insy2s.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link fr.insy2s.domain.Employe}.
 */
@RestController
@RequestMapping("/api")
public class EmployeResource {

    private final Logger         log         = LoggerFactory.getLogger(EmployeResource.class);

    private static final String  ENTITY_NAME = "employe";

    @Value("${jhipster.clientApp.name}")
    private String               applicationName;

    private final EmployeService employeService;

    public EmployeResource(EmployeService employeService) {
        this.employeService = employeService;
    }

    /**
     * {@code POST  /employes} : Create a new employe.
     *
     * @param employeDTO the employeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employeDTO, or with status {@code 400 (Bad Request)} if the employe has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employes")
    public ResponseEntity<EmployeDTO> createEmploye(@Valid @RequestBody EmployeDTO employeDTO) throws URISyntaxException {
        log.debug("REST request to save Employe : {}", employeDTO);
        if (employeDTO.getId() != null) {
            throw new BadRequestAlertException("A new employe cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeDTO result = employeService.save(employeDTO);
        return ResponseEntity.created(new URI("/api/employes/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
    }

    /**
     * {@code PUT  /employes} : Updates an existing employe.
     *
     * @param employeDTO the employeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employeDTO, or with status {@code 400 (Bad Request)} if the employeDTO is not valid, or with status
     *         {@code 500 (Internal Server Error)} if the employeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employes")
    public ResponseEntity<EmployeDTO> updateEmploye(@Valid @RequestBody EmployeDTO employeDTO) throws URISyntaxException {
        log.debug("REST request to update Employe : {}", employeDTO);
        if (employeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeDTO result = employeService.save(employeDTO);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employeDTO.getId().toString())).body(result);
    }

    /**
     * {@code GET  /employes} : get all the employes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employes in body.
     */
    @GetMapping("/employes")
    public List<EmployeDTO> getAllEmployes() {
        log.debug("REST request to get all Employes");
        return employeService.findAll();
    }

    /**
     * {@code GET  /employes/:id} : get the "id" employe.
     *
     * @param id the id of the employeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employes/{id}")
    public ResponseEntity<EmployeDTO> getEmploye(@PathVariable Long id) {
        log.debug("REST request to get Employe : {}", id);
        Optional<EmployeDTO> employeDTO = employeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employeDTO);
    }

    /**
     * {@code DELETE  /employes/:id} : delete the "id" employe.
     *
     * @param id the id of the employeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employes/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        log.debug("REST request to delete Employe : {}", id);
        employeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code GET  /wrapperemployes} : get all the wrapperEmployes.
     * 
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of wrapperEmployes in body.
     */
    @GetMapping("/wrapperemployes")
    public List<WrapperEmploye> getAllWrapperEmployes() {
        log.debug("REST request to get all WrapperEmployes");
        List<WrapperEmploye> list = employeService.findAllWrapperEmploye();
        return list;
    }

    /**
     * {@code GET /wrapperemployes/society/:id} : get all the wrapperEmployes by society.
     * 
     * @param id of the society
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of society's wrapperEmployes in body.
     */
    @GetMapping("/wrapperemployes/society/{id}")
    public List<WrapperEmploye> getAllWrapperEmployesBySociety(@PathVariable Long id) {
        log.debug("REST request to get all WrapperEmployes by society : {}", id);
        List<WrapperEmploye> list = employeService.findAllWrapperEmployeBySociete(id);
        return list;
    }

    /**
     * {@code GET  /wrapperemployes/:id} : get the "id" wrapperemploye.
     * 
     * @param id the id of the wrapperemploye to retrieve
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the wrapperemploye, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wrapperemployes/{id}")
    public ResponseEntity<WrapperEmploye> getWrapperEmploye(@PathVariable Long id) {
        log.debug("REST request to get WrapperEmploye : {}", id);
        Optional<WrapperEmploye> wrapperEmploye = employeService.findById(id);
        return ResponseUtil.wrapOrNotFound(wrapperEmploye);
    }
}
