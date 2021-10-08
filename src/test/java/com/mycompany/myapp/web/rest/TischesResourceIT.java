package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.Tisches;
import com.mycompany.myapp.repository.TischesRepository;
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
 * Integration tests for the {@Link TischesResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class TischesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TISCH_PLATTE = "AAAAAAAAAA";
    private static final String UPDATED_TISCH_PLATTE = "BBBBBBBBBB";

    private static final String DEFAULT_TISCH_BEIN = "AAAAAAAAAA";
    private static final String UPDATED_TISCH_BEIN = "BBBBBBBBBB";

    @Autowired
    private TischesRepository tischesRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restTischesMockMvc;

    private Tisches tisches;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TischesResource tischesResource = new TischesResource(tischesRepository);
        this.restTischesMockMvc = MockMvcBuilders.standaloneSetup(tischesResource)
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
    public static Tisches createEntity() {
        Tisches tisches = new Tisches()
            .name(DEFAULT_NAME)
            .tischPlatte(DEFAULT_TISCH_PLATTE)
            .tischBein(DEFAULT_TISCH_BEIN);
        return tisches;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tisches createUpdatedEntity() {
        Tisches tisches = new Tisches()
            .name(UPDATED_NAME)
            .tischPlatte(UPDATED_TISCH_PLATTE)
            .tischBein(UPDATED_TISCH_BEIN);
        return tisches;
    }

    @BeforeEach
    public void initTest() {
        tischesRepository.deleteAll();
        tisches = createEntity();
    }

    @Test
    public void createTisches() throws Exception {
        int databaseSizeBeforeCreate = tischesRepository.findAll().size();

        // Create the Tisches
        restTischesMockMvc.perform(post("/api/tisches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tisches)))
            .andExpect(status().isCreated());

        // Validate the Tisches in the database
        List<Tisches> tischesList = tischesRepository.findAll();
        assertThat(tischesList).hasSize(databaseSizeBeforeCreate + 1);
        Tisches testTisches = tischesList.get(tischesList.size() - 1);
        assertThat(testTisches.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testTisches.getTischPlatte()).isEqualTo(DEFAULT_TISCH_PLATTE);
        assertThat(testTisches.getTischBein()).isEqualTo(DEFAULT_TISCH_BEIN);
    }

    @Test
    public void createTischesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tischesRepository.findAll().size();

        // Create the Tisches with an existing ID
        tisches.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restTischesMockMvc.perform(post("/api/tisches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tisches)))
            .andExpect(status().isBadRequest());

        // Validate the Tisches in the database
        List<Tisches> tischesList = tischesRepository.findAll();
        assertThat(tischesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllTisches() throws Exception {
        // Initialize the database
        tischesRepository.save(tisches);

        // Get all the tischesList
        restTischesMockMvc.perform(get("/api/tisches?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tisches.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].tischPlatte").value(hasItem(DEFAULT_TISCH_PLATTE.toString())))
            .andExpect(jsonPath("$.[*].tischBein").value(hasItem(DEFAULT_TISCH_BEIN.toString())));
    }
    
    @Test
    public void getTisches() throws Exception {
        // Initialize the database
        tischesRepository.save(tisches);

        // Get the tisches
        restTischesMockMvc.perform(get("/api/tisches/{id}", tisches.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tisches.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.tischPlatte").value(DEFAULT_TISCH_PLATTE.toString()))
            .andExpect(jsonPath("$.tischBein").value(DEFAULT_TISCH_BEIN.toString()));
    }

    @Test
    public void getNonExistingTisches() throws Exception {
        // Get the tisches
        restTischesMockMvc.perform(get("/api/tisches/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateTisches() throws Exception {
        // Initialize the database
        tischesRepository.save(tisches);

        int databaseSizeBeforeUpdate = tischesRepository.findAll().size();

        // Update the tisches
        Tisches updatedTisches = tischesRepository.findById(tisches.getId()).get();
        updatedTisches
            .name(UPDATED_NAME)
            .tischPlatte(UPDATED_TISCH_PLATTE)
            .tischBein(UPDATED_TISCH_BEIN);

        restTischesMockMvc.perform(put("/api/tisches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTisches)))
            .andExpect(status().isOk());

        // Validate the Tisches in the database
        List<Tisches> tischesList = tischesRepository.findAll();
        assertThat(tischesList).hasSize(databaseSizeBeforeUpdate);
        Tisches testTisches = tischesList.get(tischesList.size() - 1);
        assertThat(testTisches.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testTisches.getTischPlatte()).isEqualTo(UPDATED_TISCH_PLATTE);
        assertThat(testTisches.getTischBein()).isEqualTo(UPDATED_TISCH_BEIN);
    }

    @Test
    public void updateNonExistingTisches() throws Exception {
        int databaseSizeBeforeUpdate = tischesRepository.findAll().size();

        // Create the Tisches

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTischesMockMvc.perform(put("/api/tisches")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tisches)))
            .andExpect(status().isBadRequest());

        // Validate the Tisches in the database
        List<Tisches> tischesList = tischesRepository.findAll();
        assertThat(tischesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteTisches() throws Exception {
        // Initialize the database
        tischesRepository.save(tisches);

        int databaseSizeBeforeDelete = tischesRepository.findAll().size();

        // Delete the tisches
        restTischesMockMvc.perform(delete("/api/tisches/{id}", tisches.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tisches> tischesList = tischesRepository.findAll();
        assertThat(tischesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Tisches.class);
        Tisches tisches1 = new Tisches();
        tisches1.setId("id1");
        Tisches tisches2 = new Tisches();
        tisches2.setId(tisches1.getId());
        assertThat(tisches1).isEqualTo(tisches2);
        tisches2.setId("id2");
        assertThat(tisches1).isNotEqualTo(tisches2);
        tisches1.setId(null);
        assertThat(tisches1).isNotEqualTo(tisches2);
    }
}
