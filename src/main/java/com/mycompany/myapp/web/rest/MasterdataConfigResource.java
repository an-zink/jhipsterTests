package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.MasterdataConfig;
import com.mycompany.myapp.repository.MasterdataConfigRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.MasterdataConfig}.
 */
@RestController
@RequestMapping("/api")
public class MasterdataConfigResource {

    private final Logger log = LoggerFactory.getLogger(MasterdataConfigResource.class);

    private static final String ENTITY_NAME = "masterdataConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MasterdataConfigRepository masterdataConfigRepository;

    public MasterdataConfigResource(MasterdataConfigRepository masterdataConfigRepository) {
        this.masterdataConfigRepository = masterdataConfigRepository;
    }

    /**
     * {@code POST  /masterdata-configs} : Create a new masterdataConfig.
     *
     * @param masterdataConfig the masterdataConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new masterdataConfig, or with status {@code 400 (Bad Request)} if the masterdataConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/masterdata-configs")
    public ResponseEntity<MasterdataConfig> createMasterdataConfig(@Valid @RequestBody MasterdataConfig masterdataConfig) throws URISyntaxException {
        log.debug("REST request to save MasterdataConfig : {}", masterdataConfig);
        if (masterdataConfig.getId() != null) {
            throw new BadRequestAlertException("A new masterdataConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MasterdataConfig result = masterdataConfigRepository.save(masterdataConfig);
        return ResponseEntity.created(new URI("/api/masterdata-configs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /masterdata-configs} : Updates an existing masterdataConfig.
     *
     * @param masterdataConfig the masterdataConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated masterdataConfig,
     * or with status {@code 400 (Bad Request)} if the masterdataConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the masterdataConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/masterdata-configs")
    public ResponseEntity<MasterdataConfig> updateMasterdataConfig(@Valid @RequestBody MasterdataConfig masterdataConfig) throws URISyntaxException {
        log.debug("REST request to update MasterdataConfig : {}", masterdataConfig);
        if (masterdataConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MasterdataConfig result = masterdataConfigRepository.save(masterdataConfig);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, masterdataConfig.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /masterdata-configs} : get all the masterdataConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of masterdataConfigs in body.
     */
    @GetMapping("/masterdata-configs")
    public List<MasterdataConfig> getAllMasterdataConfigs() {
        log.debug("REST request to get all MasterdataConfigs");
        return masterdataConfigRepository.findAll();
    }

    /**
     * {@code GET  /masterdata-configs/:id} : get the "id" masterdataConfig.
     *
     * @param id the id of the masterdataConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the masterdataConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/masterdata-configs/{id}")
    public ResponseEntity<MasterdataConfig> getMasterdataConfig(@PathVariable String id) {
        log.debug("REST request to get MasterdataConfig : {}", id);
        Optional<MasterdataConfig> masterdataConfig = masterdataConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(masterdataConfig);
    }

    /**
     * {@code DELETE  /masterdata-configs/:id} : delete the "id" masterdataConfig.
     *
     * @param id the id of the masterdataConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/masterdata-configs/{id}")
    public ResponseEntity<Void> deleteMasterdataConfig(@PathVariable String id) {
        log.debug("REST request to delete MasterdataConfig : {}", id);
        masterdataConfigRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
