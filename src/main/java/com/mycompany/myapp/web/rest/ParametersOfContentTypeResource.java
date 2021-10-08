package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.ParametersOfContentType;
import com.mycompany.myapp.repository.ParametersOfContentTypeRepository;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.ParametersOfContentType}.
 */
@RestController
@RequestMapping("/api")
public class ParametersOfContentTypeResource {

    private final Logger log = LoggerFactory.getLogger(ParametersOfContentTypeResource.class);

    private static final String ENTITY_NAME = "parametersOfContentType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParametersOfContentTypeRepository parametersOfContentTypeRepository;

    public ParametersOfContentTypeResource(ParametersOfContentTypeRepository parametersOfContentTypeRepository) {
        this.parametersOfContentTypeRepository = parametersOfContentTypeRepository;
    }

    /**
     * {@code POST  /parameters-of-content-types} : Create a new parametersOfContentType.
     *
     * @param parametersOfContentType the parametersOfContentType to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new parametersOfContentType, or with status {@code 400 (Bad Request)} if the parametersOfContentType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/parameters-of-content-types")
    public ResponseEntity<ParametersOfContentType> createParametersOfContentType(@RequestBody ParametersOfContentType parametersOfContentType) throws URISyntaxException {
        log.debug("REST request to save ParametersOfContentType : {}", parametersOfContentType);
        if (parametersOfContentType.getId() != null) {
            throw new BadRequestAlertException("A new parametersOfContentType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParametersOfContentType result = parametersOfContentTypeRepository.save(parametersOfContentType);
        return ResponseEntity.created(new URI("/api/parameters-of-content-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /parameters-of-content-types} : Updates an existing parametersOfContentType.
     *
     * @param parametersOfContentType the parametersOfContentType to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated parametersOfContentType,
     * or with status {@code 400 (Bad Request)} if the parametersOfContentType is not valid,
     * or with status {@code 500 (Internal Server Error)} if the parametersOfContentType couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/parameters-of-content-types")
    public ResponseEntity<ParametersOfContentType> updateParametersOfContentType(@RequestBody ParametersOfContentType parametersOfContentType) throws URISyntaxException {
        log.debug("REST request to update ParametersOfContentType : {}", parametersOfContentType);
        if (parametersOfContentType.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParametersOfContentType result = parametersOfContentTypeRepository.save(parametersOfContentType);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, parametersOfContentType.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /parameters-of-content-types} : get all the parametersOfContentTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of parametersOfContentTypes in body.
     */
    @GetMapping("/parameters-of-content-types")
    public List<ParametersOfContentType> getAllParametersOfContentTypes() {
        log.debug("REST request to get all ParametersOfContentTypes");
        return parametersOfContentTypeRepository.findAll();
    }

    /**
     * {@code GET  /parameters-of-content-types/:id} : get the "id" parametersOfContentType.
     *
     * @param id the id of the parametersOfContentType to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the parametersOfContentType, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/parameters-of-content-types/{id}")
    public ResponseEntity<ParametersOfContentType> getParametersOfContentType(@PathVariable String id) {
        log.debug("REST request to get ParametersOfContentType : {}", id);
        Optional<ParametersOfContentType> parametersOfContentType = parametersOfContentTypeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(parametersOfContentType);
    }

    /**
     * {@code DELETE  /parameters-of-content-types/:id} : delete the "id" parametersOfContentType.
     *
     * @param id the id of the parametersOfContentType to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/parameters-of-content-types/{id}")
    public ResponseEntity<Void> deleteParametersOfContentType(@PathVariable String id) {
        log.debug("REST request to delete ParametersOfContentType : {}", id);
        parametersOfContentTypeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
