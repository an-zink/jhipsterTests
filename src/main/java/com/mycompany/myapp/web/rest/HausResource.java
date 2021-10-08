package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Haus;
import com.mycompany.myapp.repository.HausRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Haus}.
 */
@RestController
@RequestMapping("/api")
public class HausResource {

    private final Logger log = LoggerFactory.getLogger(HausResource.class);

    private static final String ENTITY_NAME = "haus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HausRepository hausRepository;

    public HausResource(HausRepository hausRepository) {
        this.hausRepository = hausRepository;
    }

    /**
     * {@code POST  /haus} : Create a new haus.
     *
     * @param haus the haus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new haus, or with status {@code 400 (Bad Request)} if the haus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/haus")
    public ResponseEntity<Haus> createHaus(@RequestBody Haus haus) throws URISyntaxException {
        log.debug("REST request to save Haus : {}", haus);
        if (haus.getId() != null) {
            throw new BadRequestAlertException("A new haus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Haus result = hausRepository.save(haus);
        return ResponseEntity.created(new URI("/api/haus/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /haus} : Updates an existing haus.
     *
     * @param haus the haus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated haus,
     * or with status {@code 400 (Bad Request)} if the haus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the haus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/haus")
    public ResponseEntity<Haus> updateHaus(@RequestBody Haus haus) throws URISyntaxException {
        log.debug("REST request to update Haus : {}", haus);
        if (haus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Haus result = hausRepository.save(haus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, haus.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /haus} : get all the haus.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of haus in body.
     */
    @GetMapping("/haus")
    public List<Haus> getAllHaus() {
        log.debug("REST request to get all Haus");
        return hausRepository.findAll();
    }

    /**
     * {@code GET  /haus/:id} : get the "id" haus.
     *
     * @param id the id of the haus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the haus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/haus/{id}")
    public ResponseEntity<Haus> getHaus(@PathVariable String id) {
        log.debug("REST request to get Haus : {}", id);
        Optional<Haus> haus = hausRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(haus);
    }

    /**
     * {@code DELETE  /haus/:id} : delete the "id" haus.
     *
     * @param id the id of the haus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/haus/{id}")
    public ResponseEntity<Void> deleteHaus(@PathVariable String id) {
        log.debug("REST request to delete Haus : {}", id);
        hausRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
