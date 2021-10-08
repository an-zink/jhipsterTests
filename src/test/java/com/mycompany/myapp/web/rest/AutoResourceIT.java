package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.Auto;
import com.mycompany.myapp.repository.AutoRepository;
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
 * Integration tests for the {@Link AutoResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class AutoResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_MODELL = "AAAAAAAAAA";
    private static final String UPDATED_MODELL = "BBBBBBBBBB";

    @Autowired
    private AutoRepository autoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restAutoMockMvc;

    private Auto auto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AutoResource autoResource = new AutoResource(autoRepository);
        this.restAutoMockMvc = MockMvcBuilders.standaloneSetup(autoResource)
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
    public static Auto createEntity() {
        Auto auto = new Auto()
            .name(DEFAULT_NAME)
            .number(DEFAULT_NUMBER)
            .modell(DEFAULT_MODELL);
        return auto;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Auto createUpdatedEntity() {
        Auto auto = new Auto()
            .name(UPDATED_NAME)
            .number(UPDATED_NUMBER)
            .modell(UPDATED_MODELL);
        return auto;
    }

    @BeforeEach
    public void initTest() {
        autoRepository.deleteAll();
        auto = createEntity();
    }

    @Test
    public void createAuto() throws Exception {
        int databaseSizeBeforeCreate = autoRepository.findAll().size();

        // Create the Auto
        restAutoMockMvc.perform(post("/api/autos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auto)))
            .andExpect(status().isCreated());

        // Validate the Auto in the database
        List<Auto> autoList = autoRepository.findAll();
        assertThat(autoList).hasSize(databaseSizeBeforeCreate + 1);
        Auto testAuto = autoList.get(autoList.size() - 1);
        assertThat(testAuto.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAuto.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testAuto.getModell()).isEqualTo(DEFAULT_MODELL);
    }

    @Test
    public void createAutoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = autoRepository.findAll().size();

        // Create the Auto with an existing ID
        auto.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restAutoMockMvc.perform(post("/api/autos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auto)))
            .andExpect(status().isBadRequest());

        // Validate the Auto in the database
        List<Auto> autoList = autoRepository.findAll();
        assertThat(autoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllAutos() throws Exception {
        // Initialize the database
        autoRepository.save(auto);

        // Get all the autoList
        restAutoMockMvc.perform(get("/api/autos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auto.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].modell").value(hasItem(DEFAULT_MODELL.toString())));
    }
    
    @Test
    public void getAuto() throws Exception {
        // Initialize the database
        autoRepository.save(auto);

        // Get the auto
        restAutoMockMvc.perform(get("/api/autos/{id}", auto.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(auto.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.modell").value(DEFAULT_MODELL.toString()));
    }

    @Test
    public void getNonExistingAuto() throws Exception {
        // Get the auto
        restAutoMockMvc.perform(get("/api/autos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAuto() throws Exception {
        // Initialize the database
        autoRepository.save(auto);

        int databaseSizeBeforeUpdate = autoRepository.findAll().size();

        // Update the auto
        Auto updatedAuto = autoRepository.findById(auto.getId()).get();
        updatedAuto
            .name(UPDATED_NAME)
            .number(UPDATED_NUMBER)
            .modell(UPDATED_MODELL);

        restAutoMockMvc.perform(put("/api/autos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAuto)))
            .andExpect(status().isOk());

        // Validate the Auto in the database
        List<Auto> autoList = autoRepository.findAll();
        assertThat(autoList).hasSize(databaseSizeBeforeUpdate);
        Auto testAuto = autoList.get(autoList.size() - 1);
        assertThat(testAuto.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAuto.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testAuto.getModell()).isEqualTo(UPDATED_MODELL);
    }

    @Test
    public void updateNonExistingAuto() throws Exception {
        int databaseSizeBeforeUpdate = autoRepository.findAll().size();

        // Create the Auto

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAutoMockMvc.perform(put("/api/autos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(auto)))
            .andExpect(status().isBadRequest());

        // Validate the Auto in the database
        List<Auto> autoList = autoRepository.findAll();
        assertThat(autoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteAuto() throws Exception {
        // Initialize the database
        autoRepository.save(auto);

        int databaseSizeBeforeDelete = autoRepository.findAll().size();

        // Delete the auto
        restAutoMockMvc.perform(delete("/api/autos/{id}", auto.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Auto> autoList = autoRepository.findAll();
        assertThat(autoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Auto.class);
        Auto auto1 = new Auto();
        auto1.setId("id1");
        Auto auto2 = new Auto();
        auto2.setId(auto1.getId());
        assertThat(auto1).isEqualTo(auto2);
        auto2.setId("id2");
        assertThat(auto1).isNotEqualTo(auto2);
        auto1.setId(null);
        assertThat(auto1).isNotEqualTo(auto2);
    }
}
