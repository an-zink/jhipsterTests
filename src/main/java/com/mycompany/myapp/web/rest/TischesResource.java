package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Tisches;
import com.mycompany.myapp.repository.TischesRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Tisches}.
 */
@RestController
@RequestMapping("/api")
public class TischesResource {

    private final Logger log = LoggerFactory.getLogger(TischesResource.class);

    private static final String ENTITY_NAME = "tisches";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TischesRepository tischesRepository;

    public TischesResource(TischesRepository tischesRepository) {
        this.tischesRepository = tischesRepository;
    }

    /**
     * {@code POST  /tisches} : Create a new tisches.
     *
     * @param tisches the tisches to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tisches, or with status {@code 400 (Bad Request)} if the tisches has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tisches")
    public ResponseEntity<Tisches> createTisches(@RequestBody Tisches tisches) throws URISyntaxException {
        log.debug("REST request to save Tisches : {}", tisches);
        if (tisches.getId() != null) {
            throw new BadRequestAlertException("A new tisches cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Tisches result = tischesRepository.save(tisches);
        return ResponseEntity.created(new URI("/api/tisches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tisches} : Updates an existing tisches.
     *
     * @param tisches the tisches to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tisches,
     * or with status {@code 400 (Bad Request)} if the tisches is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tisches couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tisches")
    public ResponseEntity<Tisches> updateTisches(@RequestBody Tisches tisches) throws URISyntaxException {
        log.debug("REST request to update Tisches : {}", tisches);
        if (tisches.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Tisches result = tischesRepository.save(tisches);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tisches.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tisches} : get all the tisches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tisches in body.
     */
    @GetMapping("/tisches")
    public List<Tisches> getAllTisches() {
        log.debug("REST request to get all Tisches");
        return tischesRepository.findAll();
    }

    /**
     * {@code GET  /tisches/:id} : get the "id" tisches.
     *
     * @param id the id of the tisches to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tisches, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tisches/{id}")
    public ResponseEntity<Tisches> getTisches(@PathVariable String id) {
        log.debug("REST request to get Tisches : {}", id);
        Optional<Tisches> tisches = tischesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tisches);
    }

    /**
     * {@code DELETE  /tisches/:id} : delete the "id" tisches.
     *
     * @param id the id of the tisches to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tisches/{id}")
    public ResponseEntity<Void> deleteTisches(@PathVariable String id) {
        log.debug("REST request to delete Tisches : {}", id);
        tischesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
