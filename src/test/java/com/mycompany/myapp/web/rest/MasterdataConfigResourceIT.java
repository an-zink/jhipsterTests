package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.MasterdataConfig;
import com.mycompany.myapp.repository.MasterdataConfigRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

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


import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link MasterdataConfigResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class MasterdataConfigResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_CLAZZ = "AAAAAAAAAA";
    private static final String UPDATED_CLAZZ = "BBBBBBBBBB";

    private static final String DEFAULT_COLLECTION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COLLECTION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENVIRONMENT = "AAAAAAAAAA";
    private static final String UPDATED_ENVIRONMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PORT = "AAAAAAAAAA";
    private static final String UPDATED_PORT = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private MasterdataConfigRepository masterdataConfigRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restMasterdataConfigMockMvc;

    private MasterdataConfig masterdataConfig;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MasterdataConfigResource masterdataConfigResource = new MasterdataConfigResource(masterdataConfigRepository);
        this.restMasterdataConfigMockMvc = MockMvcBuilders.standaloneSetup(masterdataConfigResource)
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
    public static MasterdataConfig createEntity() {
        MasterdataConfig masterdataConfig = new MasterdataConfig()
            .name(DEFAULT_NAME)
            .path(DEFAULT_PATH)
            .clazz(DEFAULT_CLAZZ)
            .collectionName(DEFAULT_COLLECTION_NAME)
            .environment(DEFAULT_ENVIRONMENT)
            .contentType(DEFAULT_CONTENT_TYPE)
            .port(DEFAULT_PORT)
            .url(DEFAULT_URL);
        return masterdataConfig;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MasterdataConfig createUpdatedEntity() {
        MasterdataConfig masterdataConfig = new MasterdataConfig()
            .name(UPDATED_NAME)
            .path(UPDATED_PATH)
            .clazz(UPDATED_CLAZZ)
            .collectionName(UPDATED_COLLECTION_NAME)
            .environment(UPDATED_ENVIRONMENT)
            .contentType(UPDATED_CONTENT_TYPE)
            .port(UPDATED_PORT)
            .url(UPDATED_URL);
        return masterdataConfig;
    }

    @BeforeEach
    public void initTest() {
        masterdataConfigRepository.deleteAll();
        masterdataConfig = createEntity();
    }

    @Test
    public void createMasterdataConfig() throws Exception {
        int databaseSizeBeforeCreate = masterdataConfigRepository.findAll().size();

        // Create the MasterdataConfig
        restMasterdataConfigMockMvc.perform(post("/api/masterdata-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masterdataConfig)))
            .andExpect(status().isCreated());

        // Validate the MasterdataConfig in the database
        List<MasterdataConfig> masterdataConfigList = masterdataConfigRepository.findAll();
        assertThat(masterdataConfigList).hasSize(databaseSizeBeforeCreate + 1);
        MasterdataConfig testMasterdataConfig = masterdataConfigList.get(masterdataConfigList.size() - 1);
        assertThat(testMasterdataConfig.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMasterdataConfig.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testMasterdataConfig.getClazz()).isEqualTo(DEFAULT_CLAZZ);
        assertThat(testMasterdataConfig.getCollectionName()).isEqualTo(DEFAULT_COLLECTION_NAME);
        assertThat(testMasterdataConfig.getEnvironment()).isEqualTo(DEFAULT_ENVIRONMENT);
        assertThat(testMasterdataConfig.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testMasterdataConfig.getPort()).isEqualTo(DEFAULT_PORT);
        assertThat(testMasterdataConfig.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    public void createMasterdataConfigWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = masterdataConfigRepository.findAll().size();

        // Create the MasterdataConfig with an existing ID
        masterdataConfig.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restMasterdataConfigMockMvc.perform(post("/api/masterdata-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masterdataConfig)))
            .andExpect(status().isBadRequest());

        // Validate the MasterdataConfig in the database
        List<MasterdataConfig> masterdataConfigList = masterdataConfigRepository.findAll();
        assertThat(masterdataConfigList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllMasterdataConfigs() throws Exception {
        // Initialize the database
        masterdataConfigRepository.save(masterdataConfig);

        // Get all the masterdataConfigList
        restMasterdataConfigMockMvc.perform(get("/api/masterdata-configs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(masterdataConfig.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH.toString())))
            .andExpect(jsonPath("$.[*].clazz").value(hasItem(DEFAULT_CLAZZ.toString())))
            .andExpect(jsonPath("$.[*].collectionName").value(hasItem(DEFAULT_COLLECTION_NAME.toString())))
            .andExpect(jsonPath("$.[*].environment").value(hasItem(DEFAULT_ENVIRONMENT.toString())))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())));
    }
    
    @Test
    public void getMasterdataConfig() throws Exception {
        // Initialize the database
        masterdataConfigRepository.save(masterdataConfig);

        // Get the masterdataConfig
        restMasterdataConfigMockMvc.perform(get("/api/masterdata-configs/{id}", masterdataConfig.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(masterdataConfig.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH.toString()))
            .andExpect(jsonPath("$.clazz").value(DEFAULT_CLAZZ.toString()))
            .andExpect(jsonPath("$.collectionName").value(DEFAULT_COLLECTION_NAME.toString()))
            .andExpect(jsonPath("$.environment").value(DEFAULT_ENVIRONMENT.toString()))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE.toString()))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()));
    }

    @Test
    public void getNonExistingMasterdataConfig() throws Exception {
        // Get the masterdataConfig
        restMasterdataConfigMockMvc.perform(get("/api/masterdata-configs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateMasterdataConfig() throws Exception {
        // Initialize the database
        masterdataConfigRepository.save(masterdataConfig);

        int databaseSizeBeforeUpdate = masterdataConfigRepository.findAll().size();

        // Update the masterdataConfig
        MasterdataConfig updatedMasterdataConfig = masterdataConfigRepository.findById(masterdataConfig.getId()).get();
        updatedMasterdataConfig
            .name(UPDATED_NAME)
            .path(UPDATED_PATH)
            .clazz(UPDATED_CLAZZ)
            .collectionName(UPDATED_COLLECTION_NAME)
            .environment(UPDATED_ENVIRONMENT)
            .contentType(UPDATED_CONTENT_TYPE)
            .port(UPDATED_PORT)
            .url(UPDATED_URL);

        restMasterdataConfigMockMvc.perform(put("/api/masterdata-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMasterdataConfig)))
            .andExpect(status().isOk());

        // Validate the MasterdataConfig in the database
        List<MasterdataConfig> masterdataConfigList = masterdataConfigRepository.findAll();
        assertThat(masterdataConfigList).hasSize(databaseSizeBeforeUpdate);
        MasterdataConfig testMasterdataConfig = masterdataConfigList.get(masterdataConfigList.size() - 1);
        assertThat(testMasterdataConfig.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMasterdataConfig.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testMasterdataConfig.getClazz()).isEqualTo(UPDATED_CLAZZ);
        assertThat(testMasterdataConfig.getCollectionName()).isEqualTo(UPDATED_COLLECTION_NAME);
        assertThat(testMasterdataConfig.getEnvironment()).isEqualTo(UPDATED_ENVIRONMENT);
        assertThat(testMasterdataConfig.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testMasterdataConfig.getPort()).isEqualTo(UPDATED_PORT);
        assertThat(testMasterdataConfig.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    public void updateNonExistingMasterdataConfig() throws Exception {
        int databaseSizeBeforeUpdate = masterdataConfigRepository.findAll().size();

        // Create the MasterdataConfig

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMasterdataConfigMockMvc.perform(put("/api/masterdata-configs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(masterdataConfig)))
            .andExpect(status().isBadRequest());

        // Validate the MasterdataConfig in the database
        List<MasterdataConfig> masterdataConfigList = masterdataConfigRepository.findAll();
        assertThat(masterdataConfigList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteMasterdataConfig() throws Exception {
        // Initialize the database
        masterdataConfigRepository.save(masterdataConfig);

        int databaseSizeBeforeDelete = masterdataConfigRepository.findAll().size();

        // Delete the masterdataConfig
        restMasterdataConfigMockMvc.perform(delete("/api/masterdata-configs/{id}", masterdataConfig.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MasterdataConfig> masterdataConfigList = masterdataConfigRepository.findAll();
        assertThat(masterdataConfigList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MasterdataConfig.class);
        MasterdataConfig masterdataConfig1 = new MasterdataConfig();
        masterdataConfig1.setId("id1");
        MasterdataConfig masterdataConfig2 = new MasterdataConfig();
        masterdataConfig2.setId(masterdataConfig1.getId());
        assertThat(masterdataConfig1).isEqualTo(masterdataConfig2);
        masterdataConfig2.setId("id2");
        assertThat(masterdataConfig1).isNotEqualTo(masterdataConfig2);
        masterdataConfig1.setId(null);
        assertThat(masterdataConfig1).isNotEqualTo(masterdataConfig2);
    }
}
