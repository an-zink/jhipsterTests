package com.mycompany.myapp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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

import com.mycompany.myapp.domain.ContentTypeInfo;
import com.mycompany.myapp.repository.ContentTypeInfoRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;



/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ContentTypeInfo}.
 */
@RestController
@RequestMapping("/api")
public class ContentTypeInfoResource {	
	
    private final Logger log = LoggerFactory.getLogger(ContentTypeInfoResource.class);

    private static final String ENTITY_NAME = "contentTypeInfo";
    private static final String SORT_DATA = "Incorrect ContentType Entity, Entity must not be null!";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContentTypeInfoRepository contentTypeInfoRepository;

	

    public ContentTypeInfoResource(ContentTypeInfoRepository contentTypeInfoRepository) {
        this.contentTypeInfoRepository = contentTypeInfoRepository;
    }

    /**
     * {@code POST  /content-type-infos} : Create a new contentTypeInfo.
     *
     * @param contentTypeInfo the contentTypeInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contentTypeInfo, or with status {@code 400 (Bad Request)} if the contentTypeInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/content-type-infos")
    public ResponseEntity<ContentTypeInfo> createContentTypeInfo(@Valid @RequestBody ContentTypeInfo contentTypeInfo) throws URISyntaxException {
        log.debug("REST request to save ContentTypeInfo : {}", contentTypeInfo);
        if (contentTypeInfo.getId() != null) {
            throw new BadRequestAlertException("A new contentTypeInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContentTypeInfo result = contentTypeInfoRepository.save(contentTypeInfo);
        return ResponseEntity.created(new URI("/api/content-type-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /content-type-infos} : Updates an existing contentTypeInfo.
     *
     * @param contentTypeInfo the contentTypeInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contentTypeInfo,
     * or with status {@code 400 (Bad Request)} if the contentTypeInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contentTypeInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/content-type-infos")
    public ResponseEntity<ContentTypeInfo> updateContentTypeInfo(@Valid @RequestBody ContentTypeInfo contentTypeInfo) throws URISyntaxException {
        log.debug("REST request to update ContentTypeInfo : {}", contentTypeInfo);
        if (contentTypeInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContentTypeInfo result = contentTypeInfoRepository.save(contentTypeInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contentTypeInfo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /content-type-infos} : get all the contentTypeInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contentTypeInfos in body.
     */
    @GetMapping("/content-type-infos")
    public List<ContentTypeInfo> getAllContentTypeInfos() {
        log.debug("REST request to get all ContentTypeInfos");
        return contentTypeInfoRepository.findAll();
    }

    /**
     * {@code GET  /content-type-infos/:id} : get the "id" contentTypeInfo.
     *
     * @param id the id of the contentTypeInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contentTypeInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/content-type-infos/{id}")
    public ResponseEntity<ContentTypeInfo> getContentTypeInfo(@PathVariable String id) {
        log.debug("REST request to get ContentTypeInfo : {}", id);
        Optional<ContentTypeInfo> contentTypeInfo = contentTypeInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(contentTypeInfo);
    }

    /**
     * {@code DELETE  /content-type-infos/:id} : delete the "id" contentTypeInfo.
     *
     * @param id the id of the contentTypeInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/content-type-infos/{id}")
    public ResponseEntity<Void> deleteContentTypeInfo(@PathVariable String id) {
        log.debug("REST request to delete ContentTypeInfo : {}", id);
        contentTypeInfoRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
    
    @GetMapping("/content-type-infos/environment")
    public List<ContentTypeInfo> getEnvironments(){
    	List<ContentTypeInfo> Enviorments = new ArrayList<>();
    	Enviorments=contentTypeInfoRepository.findAll();
    	return findEnvironments(Enviorments);
    	
    }
    @GetMapping("/content-type-infos/contentTypeName")
    public List<ContentTypeInfo> getContentTypeName(){
    	List<ContentTypeInfo> ContentTypeName = new ArrayList<>();
    	ContentTypeName = contentTypeInfoRepository.findAll();
    	return findContentTypeNames(ContentTypeName); 
    } 
    @GetMapping("/content-type-infos/datasort")
    public List<List<ContentTypeInfo>> findSortData() {
		return findDataSortTabel();
    }
 
    //Sortierung der Daten Nach Umgebungen 
    private List<List<ContentTypeInfo>> findDataSortTabel() {
    	List<ContentTypeInfo> contentType = contentTypeInfoRepository.findAll();
    	List<ContentTypeInfo> contentTypeNames = findContentTypeNames(contentType);
    	List<ContentTypeInfo> contentTypeEnvironments = findEnvironments(contentType);
    	List<List<ContentTypeInfo>> dataSort = new ArrayList<>();
    	for (int i = 0; i < contentTypeNames.size(); i++) {
    		dataSort.add(new ArrayList<>());
			for (int j = 0; j < contentTypeEnvironments.size(); j++) {
				dataSort.get(i).add(new ContentTypeInfo());	
			}
		}
    	for (int i = 0; i <contentTypeNames.size(); i++) {
			for (int j = 0; j < contentTypeEnvironments.size(); j++) {
				for (int k = 0; k < contentType.size(); k++) {
					
					try {
						if(contentType.get(k).getContentTypeName().equals(contentTypeNames.get(i).getContentTypeName())&&
								contentType.get(k).getEnviroment().equals(contentTypeEnvironments.get(j).getEnviroment())) {
							dataSort.get(i).remove(j);
							dataSort.get(i).add(j,contentType.get(k));
						}
						
					} catch (java.lang.NullPointerException e) {
						// TODO: handle exception
						log.error(SORT_DATA );
					}
						
				}
			}
			dataSort.get(i).add(0, contentTypeNames.get(i));
		}

		return dataSort;
	}

	private List<ContentTypeInfo> findContentTypeNames(List<ContentTypeInfo> contentTypeName) {
		// TODO Auto-generated method stub
    	ArrayList<String> environment= new ArrayList<String>();
    	Optional<String> test = null;
    	List<ContentTypeInfo> enviroments= new ArrayList<>();
    	for(int i=0;i<contentTypeName.size();i++) {
    		if(i==0) {
    			environment.add(contentTypeName.get(i).getContentTypeName());
    			enviroments.add(contentTypeName.get(i));
    			continue;
    		}
    		int x=i;
    			test=environment.stream().filter(string -> string.equals(contentTypeName.get(x).getContentTypeName())).findFirst();
    		if(test.isPresent()==false) {
    			environment.add(contentTypeName.get(i).getContentTypeName());
    			enviroments.add(contentTypeName.get(i));
    		}
    	}
    	return enviroments;
	}

	private List<ContentTypeInfo> findEnvironments(List<ContentTypeInfo> AllEntetys){
		// größe der ListeAllEntetys.size()
    	ArrayList<String> environment= new ArrayList<String>();
    	Optional<String> test = null;
    	List<ContentTypeInfo> enviroments= new ArrayList<>();
    	for(int i=0;i<AllEntetys.size();i++) {
    		if(i==0) {
    			environment.add(AllEntetys.get(i).getEnviroment());
    			enviroments.add(AllEntetys.get(i));
    			continue;
    		}
    		int x=i;
    			test=environment.stream().filter(string -> string.equals(AllEntetys.get(x).getEnviroment())).findFirst();
    		if(test.isPresent()==false) {
    			environment.add(AllEntetys.get(i).getEnviroment());
    			enviroments.add(AllEntetys.get(i));
    		}
    	}
    	return enviroments;
    }
	
}



