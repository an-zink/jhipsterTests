package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.ParametersOfContentType;
import com.mycompany.myapp.repository.ParametersOfContentTypeRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

/**
 * Integration tests for the {@Link ParametersOfContentTypeResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class ParametersOfContentTypeResourceIT {

    @Autowired
    private ParametersOfContentTypeRepository parametersOfContentTypeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restParametersOfContentTypeMockMvc;

    private ParametersOfContentType parametersOfContentType;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ParametersOfContentTypeResource parametersOfContentTypeResource = new ParametersOfContentTypeResource(parametersOfContentTypeRepository);
        this.restParametersOfContentTypeMockMvc = MockMvcBuilders.standaloneSetup(parametersOfContentTypeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParametersOfContentType createEntity() {
        ParametersOfContentType parametersOfContentType = new ParametersOfContentType();
        return parametersOfContentType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ParametersOfContentType createUpdatedEntity() {
        ParametersOfContentType parametersOfContentType = new ParametersOfContentType();
        return parametersOfContentType;
    }

    @BeforeEach
    public void initTest() {
        parametersOfContentTypeRepository.deleteAll();
        parametersOfContentType = createEntity();
    }

    @Test
    public void createParametersOfContentType() throws Exception {
        int databaseSizeBeforeCreate = parametersOfContentTypeRepository.findAll().size();

        // Create the ParametersOfContentType
        restParametersOfContentTypeMockMvc.perform(post("/api/parameters-of-content-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parametersOfContentType)))
            .andExpect(status().isCreated());

        // Validate the ParametersOfContentType in the database
        List<ParametersOfContentType> parametersOfContentTypeList = parametersOfContentTypeRepository.findAll();
        assertThat(parametersOfContentTypeList).hasSize(databaseSizeBeforeCreate + 1);
        @SuppressWarnings("unused")
		ParametersOfContentType testParametersOfContentType = parametersOfContentTypeList.get(parametersOfContentTypeList.size() - 1);
    }

    @Test
    public void createParametersOfContentTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = parametersOfContentTypeRepository.findAll().size();

        // Create the ParametersOfContentType with an existing ID
        parametersOfContentType.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restParametersOfContentTypeMockMvc.perform(post("/api/parameters-of-content-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parametersOfContentType)))
            .andExpect(status().isBadRequest());

        // Validate the ParametersOfContentType in the database
        List<ParametersOfContentType> parametersOfContentTypeList = parametersOfContentTypeRepository.findAll();
        assertThat(parametersOfContentTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllParametersOfContentTypes() throws Exception {
        // Initialize the database
        parametersOfContentTypeRepository.save(parametersOfContentType);

        // Get all the parametersOfContentTypeList
        restParametersOfContentTypeMockMvc.perform(get("/api/parameters-of-content-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(parametersOfContentType.getId())));
    }
    
    @Test
    public void getParametersOfContentType() throws Exception {
        // Initialize the database
        parametersOfContentTypeRepository.save(parametersOfContentType);

        // Get the parametersOfContentType
        restParametersOfContentTypeMockMvc.perform(get("/api/parameters-of-content-types/{id}", parametersOfContentType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(parametersOfContentType.getId()));
    }

    @Test
    public void getNonExistingParametersOfContentType() throws Exception {
        // Get the parametersOfContentType
        restParametersOfContentTypeMockMvc.perform(get("/api/parameters-of-content-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateParametersOfContentType() throws Exception {
        // Initialize the database
        parametersOfContentTypeRepository.save(parametersOfContentType);

        int databaseSizeBeforeUpdate = parametersOfContentTypeRepository.findAll().size();

        // Update the parametersOfContentType
        ParametersOfContentType updatedParametersOfContentType = parametersOfContentTypeRepository.findById(parametersOfContentType.getId()).get();

        restParametersOfContentTypeMockMvc.perform(put("/api/parameters-of-content-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedParametersOfContentType)))
            .andExpect(status().isOk());

        // Validate the ParametersOfContentType in the database
        List<ParametersOfContentType> parametersOfContentTypeList = parametersOfContentTypeRepository.findAll();
        assertThat(parametersOfContentTypeList).hasSize(databaseSizeBeforeUpdate);
        @SuppressWarnings("unused")
		ParametersOfContentType testParametersOfContentType = parametersOfContentTypeList.get(parametersOfContentTypeList.size() - 1);
    }

    @Test
    public void updateNonExistingParametersOfContentType() throws Exception {
        int databaseSizeBeforeUpdate = parametersOfContentTypeRepository.findAll().size();

        // Create the ParametersOfContentType

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restParametersOfContentTypeMockMvc.perform(put("/api/parameters-of-content-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(parametersOfContentType)))
            .andExpect(status().isBadRequest());

        // Validate the ParametersOfContentType in the database
        List<ParametersOfContentType> parametersOfContentTypeList = parametersOfContentTypeRepository.findAll();
        assertThat(parametersOfContentTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteParametersOfContentType() throws Exception {
        // Initialize the database
        parametersOfContentTypeRepository.save(parametersOfContentType);

        int databaseSizeBeforeDelete = parametersOfContentTypeRepository.findAll().size();

        // Delete the parametersOfContentType
        restParametersOfContentTypeMockMvc.perform(delete("/api/parameters-of-content-types/{id}", parametersOfContentType.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ParametersOfContentType> parametersOfContentTypeList = parametersOfContentTypeRepository.findAll();
        assertThat(parametersOfContentTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ParametersOfContentType.class);
        ParametersOfContentType parametersOfContentType1 = new ParametersOfContentType();
        parametersOfContentType1.setId("id1");
        ParametersOfContentType parametersOfContentType2 = new ParametersOfContentType();
        parametersOfContentType2.setId(parametersOfContentType1.getId());
        assertThat(parametersOfContentType1).isEqualTo(parametersOfContentType2);
        parametersOfContentType2.setId("id2");
        assertThat(parametersOfContentType1).isNotEqualTo(parametersOfContentType2);
        parametersOfContentType1.setId(null);
        assertThat(parametersOfContentType1).isNotEqualTo(parametersOfContentType2);
    }
}
