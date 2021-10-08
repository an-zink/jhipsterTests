package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterApp;
import com.mycompany.myapp.domain.ContentTypeInfo;
import com.mycompany.myapp.repository.ContentTypeInfoRepository;
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
 * Integration tests for the {@Link ContentTypeInfoResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)
public class ContentTypeInfoResourceIT {

    private static final String DEFAULT_CONTENT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ENVIROMENT = "AAA";
    private static final String UPDATED_ENVIROMENT = "BBB";

    private static final String DEFAULT_LAST_MODIFIED_BY_STRAPI_USER = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY_STRAPI_USER = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_DATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_DATE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER_OF_ENTRIES = 1;
    private static final Integer UPDATED_NUMBER_OF_ENTRIES = 2;

    private static final Integer DEFAULT_NUMBER_OF_PARAMETERS = 1;
    private static final Integer UPDATED_NUMBER_OF_PARAMETERS = 2;

    @Autowired
    private ContentTypeInfoRepository contentTypeInfoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restContentTypeInfoMockMvc;

    private ContentTypeInfo contentTypeInfo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContentTypeInfoResource contentTypeInfoResource = new ContentTypeInfoResource(contentTypeInfoRepository);
        this.restContentTypeInfoMockMvc = MockMvcBuilders.standaloneSetup(contentTypeInfoResource)
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
    public static ContentTypeInfo createEntity() {
        ContentTypeInfo contentTypeInfo = new ContentTypeInfo()
            .contentTypeName(DEFAULT_CONTENT_TYPE_NAME)
            .enviroment(DEFAULT_ENVIROMENT)
            .lastModifiedDate(DEFAULT_LAST_MODIFIED_DATE)
            .numberOfEntries(DEFAULT_NUMBER_OF_ENTRIES)
            .numberOfParameters(DEFAULT_NUMBER_OF_PARAMETERS);
        return contentTypeInfo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContentTypeInfo createUpdatedEntity() {
        ContentTypeInfo contentTypeInfo = new ContentTypeInfo()
            .contentTypeName(UPDATED_CONTENT_TYPE_NAME)
            .enviroment(UPDATED_ENVIROMENT)
//            .lastModifiedByStrapiUser(UPDATED_LAST_MODIFIED_BY_STRAPI_USER)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .numberOfEntries(UPDATED_NUMBER_OF_ENTRIES)
            .numberOfParameters(UPDATED_NUMBER_OF_PARAMETERS);
        return contentTypeInfo;
    }

    @BeforeEach
    public void initTest() {
        contentTypeInfoRepository.deleteAll();
        contentTypeInfo = createEntity();
    }

    @Test
    public void createContentTypeInfo() throws Exception {
        int databaseSizeBeforeCreate = contentTypeInfoRepository.findAll().size();

        // Create the ContentTypeInfo
        restContentTypeInfoMockMvc.perform(post("/api/content-type-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contentTypeInfo)))
            .andExpect(status().isCreated());

        // Validate the ContentTypeInfo in the database
        List<ContentTypeInfo> contentTypeInfoList = contentTypeInfoRepository.findAll();
        assertThat(contentTypeInfoList).hasSize(databaseSizeBeforeCreate + 1);
        ContentTypeInfo testContentTypeInfo = contentTypeInfoList.get(contentTypeInfoList.size() - 1);
        assertThat(testContentTypeInfo.getContentTypeName()).isEqualTo(DEFAULT_CONTENT_TYPE_NAME);
        assertThat(testContentTypeInfo.getEnviroment()).isEqualTo(DEFAULT_ENVIROMENT);
//        assertThat(testContentTypeInfo.getLastModifiedByStrapiUser()).isEqualTo(DEFAULT_LAST_MODIFIED_BY_STRAPI_USER);
        assertThat(testContentTypeInfo.getLastModifiedDate()).isEqualTo(DEFAULT_LAST_MODIFIED_DATE);
        assertThat(testContentTypeInfo.getNumberOfEntries()).isEqualTo(DEFAULT_NUMBER_OF_ENTRIES);
        assertThat(testContentTypeInfo.getNumberOfParameters()).isEqualTo(DEFAULT_NUMBER_OF_PARAMETERS);
    }

    @Test
    public void createContentTypeInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contentTypeInfoRepository.findAll().size();

        // Create the ContentTypeInfo with an existing ID
        contentTypeInfo.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restContentTypeInfoMockMvc.perform(post("/api/content-type-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contentTypeInfo)))
            .andExpect(status().isBadRequest());

        // Validate the ContentTypeInfo in the database
        List<ContentTypeInfo> contentTypeInfoList = contentTypeInfoRepository.findAll();
        assertThat(contentTypeInfoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllContentTypeInfos() throws Exception {
        // Initialize the database
        contentTypeInfoRepository.save(contentTypeInfo);

        // Get all the contentTypeInfoList
        restContentTypeInfoMockMvc.perform(get("/api/content-type-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contentTypeInfo.getId())))
            .andExpect(jsonPath("$.[*].contentTypeName").value(hasItem(DEFAULT_CONTENT_TYPE_NAME.toString())))
            .andExpect(jsonPath("$.[*].enviroment").value(hasItem(DEFAULT_ENVIROMENT.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedByStrapiUser").value(hasItem(DEFAULT_LAST_MODIFIED_BY_STRAPI_USER.toString())))
            .andExpect(jsonPath("$.[*].lastModifiedDate").value(hasItem(DEFAULT_LAST_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].numberOfEntries").value(hasItem(DEFAULT_NUMBER_OF_ENTRIES)))
            .andExpect(jsonPath("$.[*].numberOfParameters").value(hasItem(DEFAULT_NUMBER_OF_PARAMETERS)));
    }
    
    @Test
    public void getContentTypeInfo() throws Exception {
        // Initialize the database
        contentTypeInfoRepository.save(contentTypeInfo);

        // Get the contentTypeInfo
        restContentTypeInfoMockMvc.perform(get("/api/content-type-infos/{id}", contentTypeInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contentTypeInfo.getId()))
            .andExpect(jsonPath("$.contentTypeName").value(DEFAULT_CONTENT_TYPE_NAME.toString()))
            .andExpect(jsonPath("$.enviroment").value(DEFAULT_ENVIROMENT.toString()))
            .andExpect(jsonPath("$.lastModifiedByStrapiUser").value(DEFAULT_LAST_MODIFIED_BY_STRAPI_USER.toString()))
            .andExpect(jsonPath("$.lastModifiedDate").value(DEFAULT_LAST_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.numberOfEntries").value(DEFAULT_NUMBER_OF_ENTRIES))
            .andExpect(jsonPath("$.numberOfParameters").value(DEFAULT_NUMBER_OF_PARAMETERS));
    }

    @Test
    public void getNonExistingContentTypeInfo() throws Exception {
        // Get the contentTypeInfo
        restContentTypeInfoMockMvc.perform(get("/api/content-type-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateContentTypeInfo() throws Exception {
        // Initialize the database
        contentTypeInfoRepository.save(contentTypeInfo);

        int databaseSizeBeforeUpdate = contentTypeInfoRepository.findAll().size();

        // Update the contentTypeInfo
        ContentTypeInfo updatedContentTypeInfo = contentTypeInfoRepository.findById(contentTypeInfo.getId()).get();
        updatedContentTypeInfo
            .contentTypeName(UPDATED_CONTENT_TYPE_NAME)
            .enviroment(UPDATED_ENVIROMENT)
//            .lastModifiedByStrapiUser(UPDATED_LAST_MODIFIED_BY_STRAPI_USER)
            .lastModifiedDate(UPDATED_LAST_MODIFIED_DATE)
            .numberOfEntries(UPDATED_NUMBER_OF_ENTRIES)
            .numberOfParameters(UPDATED_NUMBER_OF_PARAMETERS);

        restContentTypeInfoMockMvc.perform(put("/api/content-type-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedContentTypeInfo)))
            .andExpect(status().isOk());

        // Validate the ContentTypeInfo in the database
        List<ContentTypeInfo> contentTypeInfoList = contentTypeInfoRepository.findAll();
        assertThat(contentTypeInfoList).hasSize(databaseSizeBeforeUpdate);
        ContentTypeInfo testContentTypeInfo = contentTypeInfoList.get(contentTypeInfoList.size() - 1);
        assertThat(testContentTypeInfo.getContentTypeName()).isEqualTo(UPDATED_CONTENT_TYPE_NAME);
        assertThat(testContentTypeInfo.getEnviroment()).isEqualTo(UPDATED_ENVIROMENT);
//        assertThat(testContentTypeInfo.getLastModifiedByStrapiUser()).isEqualTo(UPDATED_LAST_MODIFIED_BY_STRAPI_USER);
        assertThat(testContentTypeInfo.getLastModifiedDate()).isEqualTo(UPDATED_LAST_MODIFIED_DATE);
        assertThat(testContentTypeInfo.getNumberOfEntries()).isEqualTo(UPDATED_NUMBER_OF_ENTRIES);
        assertThat(testContentTypeInfo.getNumberOfParameters()).isEqualTo(UPDATED_NUMBER_OF_PARAMETERS);
    }

    @Test
    public void updateNonExistingContentTypeInfo() throws Exception {
        int databaseSizeBeforeUpdate = contentTypeInfoRepository.findAll().size();

        // Create the ContentTypeInfo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContentTypeInfoMockMvc.perform(put("/api/content-type-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contentTypeInfo)))
            .andExpect(status().isBadRequest());

        // Validate the ContentTypeInfo in the database
        List<ContentTypeInfo> contentTypeInfoList = contentTypeInfoRepository.findAll();
        assertThat(contentTypeInfoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteContentTypeInfo() throws Exception {
        // Initialize the database
        contentTypeInfoRepository.save(contentTypeInfo);

        int databaseSizeBeforeDelete = contentTypeInfoRepository.findAll().size();

        // Delete the contentTypeInfo
        restContentTypeInfoMockMvc.perform(delete("/api/content-type-infos/{id}", contentTypeInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContentTypeInfo> contentTypeInfoList = contentTypeInfoRepository.findAll();
        assertThat(contentTypeInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContentTypeInfo.class);
        ContentTypeInfo contentTypeInfo1 = new ContentTypeInfo();
        contentTypeInfo1.setId("id1");
        ContentTypeInfo contentTypeInfo2 = new ContentTypeInfo();
        contentTypeInfo2.setId(contentTypeInfo1.getId());
        assertThat(contentTypeInfo1).isEqualTo(contentTypeInfo2);
        contentTypeInfo2.setId("id2");
        assertThat(contentTypeInfo1).isNotEqualTo(contentTypeInfo2);
        contentTypeInfo1.setId(null);
        assertThat(contentTypeInfo1).isNotEqualTo(contentTypeInfo2);
    }
}
