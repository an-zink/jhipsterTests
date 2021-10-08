package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Auto;
import com.mycompany.myapp.repository.AutoRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Auto}.
 */
@RestController
@RequestMapping("/api")
public class AutoResource {

    private final Logger log = LoggerFactory.getLogger(AutoResource.class);

    private static final String ENTITY_NAME = "auto";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AutoRepository autoRepository;

    public AutoResource(AutoRepository autoRepository) {
        this.autoRepository = autoRepository;
    }

    /**
     * {@code POST  /autos} : Create a new auto.
     *
     * @param auto the auto to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auto, or with status {@code 400 (Bad Request)} if the auto has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/autos")
    public ResponseEntity<Auto> createAuto(@RequestBody Auto auto) throws URISyntaxException {
        log.debug("REST request to save Auto : {}", auto);
        if (auto.getId() != null) {
            throw new BadRequestAlertException("A new auto cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Auto result = autoRepository.save(auto);
        return ResponseEntity.created(new URI("/api/autos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /autos} : Updates an existing auto.
     *
     * @param auto the auto to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auto,
     * or with status {@code 400 (Bad Request)} if the auto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/autos")
    public ResponseEntity<Auto> updateAuto(@RequestBody Auto auto) throws URISyntaxException {
        log.debug("REST request to update Auto : {}", auto);
        if (auto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Auto result = autoRepository.save(auto);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auto.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /autos} : get all the autos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of autos in body.
     */
    @GetMapping("/autos")
    public List<Auto> getAllAutos() {
        log.debug("REST request to get all Autos");
        return autoRepository.findAll();
    }

    /**
     * {@code GET  /autos/:id} : get the "id" auto.
     *
     * @param id the id of the auto to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auto, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/autos/{id}")
    public ResponseEntity<Auto> getAuto(@PathVariable String id) {
        log.debug("REST request to get Auto : {}", id);
        Optional<Auto> auto = autoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auto);
    }

    /**
     * {@code DELETE  /autos/:id} : delete the "id" auto.
     *
     * @param id the id of the auto to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/autos/{id}")
    public ResponseEntity<Void> deleteAuto(@PathVariable String id) {
        log.debug("REST request to delete Auto : {}", id);
        autoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
