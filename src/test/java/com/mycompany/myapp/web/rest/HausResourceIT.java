package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.Haus;
import com.mycompany.myapp.repository.HausRepository;
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
 * Integration tests for the {@Link HausResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class HausResourceIT {

    private static final String DEFAULT_NR = "AAAAAAAAAA";
    private static final String UPDATED_NR = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private HausRepository hausRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restHausMockMvc;

    private Haus haus;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HausResource hausResource = new HausResource(hausRepository);
        this.restHausMockMvc = MockMvcBuilders.standaloneSetup(hausResource)
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
    public static Haus createEntity() {
        Haus haus = new Haus()
            .nr(DEFAULT_NR)
            .name(DEFAULT_NAME);
        return haus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Haus createUpdatedEntity() {
        Haus haus = new Haus()
            .nr(UPDATED_NR)
            .name(UPDATED_NAME);
        return haus;
    }

    @BeforeEach
    public void initTest() {
        hausRepository.deleteAll();
        haus = createEntity();
    }

    @Test
    public void createHaus() throws Exception {
        int databaseSizeBeforeCreate = hausRepository.findAll().size();

        // Create the Haus
        restHausMockMvc.perform(post("/api/haus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(haus)))
            .andExpect(status().isCreated());

        // Validate the Haus in the database
        List<Haus> hausList = hausRepository.findAll();
        assertThat(hausList).hasSize(databaseSizeBeforeCreate + 1);
        Haus testHaus = hausList.get(hausList.size() - 1);
        assertThat(testHaus.getNr()).isEqualTo(DEFAULT_NR);
        assertThat(testHaus.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    public void createHausWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hausRepository.findAll().size();

        // Create the Haus with an existing ID
        haus.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restHausMockMvc.perform(post("/api/haus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(haus)))
            .andExpect(status().isBadRequest());

        // Validate the Haus in the database
        List<Haus> hausList = hausRepository.findAll();
        assertThat(hausList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllHaus() throws Exception {
        // Initialize the database
        hausRepository.save(haus);

        // Get all the hausList
        restHausMockMvc.perform(get("/api/haus?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(haus.getId())))
            .andExpect(jsonPath("$.[*].nr").value(hasItem(DEFAULT_NR.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }
    
    @Test
    public void getHaus() throws Exception {
        // Initialize the database
        hausRepository.save(haus);

        // Get the haus
        restHausMockMvc.perform(get("/api/haus/{id}", haus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(haus.getId()))
            .andExpect(jsonPath("$.nr").value(DEFAULT_NR.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    public void getNonExistingHaus() throws Exception {
        // Get the haus
        restHausMockMvc.perform(get("/api/haus/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateHaus() throws Exception {
        // Initialize the database
        hausRepository.save(haus);

        int databaseSizeBeforeUpdate = hausRepository.findAll().size();

        // Update the haus
        Haus updatedHaus = hausRepository.findById(haus.getId()).get();
        updatedHaus
            .nr(UPDATED_NR)
            .name(UPDATED_NAME);

        restHausMockMvc.perform(put("/api/haus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHaus)))
            .andExpect(status().isOk());

        // Validate the Haus in the database
        List<Haus> hausList = hausRepository.findAll();
        assertThat(hausList).hasSize(databaseSizeBeforeUpdate);
        Haus testHaus = hausList.get(hausList.size() - 1);
        assertThat(testHaus.getNr()).isEqualTo(UPDATED_NR);
        assertThat(testHaus.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    public void updateNonExistingHaus() throws Exception {
        int databaseSizeBeforeUpdate = hausRepository.findAll().size();

        // Create the Haus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHausMockMvc.perform(put("/api/haus")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(haus)))
            .andExpect(status().isBadRequest());

        // Validate the Haus in the database
        List<Haus> hausList = hausRepository.findAll();
        assertThat(hausList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteHaus() throws Exception {
        // Initialize the database
        hausRepository.save(haus);

        int databaseSizeBeforeDelete = hausRepository.findAll().size();

        // Delete the haus
        restHausMockMvc.perform(delete("/api/haus/{id}", haus.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Haus> hausList = hausRepository.findAll();
        assertThat(hausList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Haus.class);
        Haus haus1 = new Haus();
        haus1.setId("id1");
        Haus haus2 = new Haus();
        haus2.setId(haus1.getId());
        assertThat(haus1).isEqualTo(haus2);
        haus2.setId("id2");
        assertThat(haus1).isNotEqualTo(haus2);
        haus1.setId(null);
        assertThat(haus1).isNotEqualTo(haus2);
    }
}
