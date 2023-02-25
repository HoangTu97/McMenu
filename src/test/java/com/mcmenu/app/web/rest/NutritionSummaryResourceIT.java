package com.mcmenu.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mcmenu.app.IntegrationTest;
import com.mcmenu.app.domain.NutritionSummary;
import com.mcmenu.app.domain.enumeration.NutritionKey;
import com.mcmenu.app.repository.NutritionSummaryRepository;
import com.mcmenu.app.service.dto.NutritionSummaryDTO;
import com.mcmenu.app.service.mapper.NutritionSummaryMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NutritionSummaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NutritionSummaryResourceIT {

    private static final NutritionKey DEFAULT_KEY = NutritionKey.CALORIES;
    private static final NutritionKey UPDATED_KEY = NutritionKey.TOTAL_FAT;

    private static final Integer DEFAULT_QUANTITY_MG = 0;
    private static final Integer UPDATED_QUANTITY_MG = 1;

    private static final Integer DEFAULT_PERCENT_DAILY_VALUES = 0;
    private static final Integer UPDATED_PERCENT_DAILY_VALUES = 1;

    private static final String ENTITY_API_URL = "/api/nutrition-summaries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NutritionSummaryRepository nutritionSummaryRepository;

    @Autowired
    private NutritionSummaryMapper nutritionSummaryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNutritionSummaryMockMvc;

    private NutritionSummary nutritionSummary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NutritionSummary createEntity(EntityManager em) {
        NutritionSummary nutritionSummary = new NutritionSummary()
            .key(DEFAULT_KEY)
            .quantityMg(DEFAULT_QUANTITY_MG)
            .percentDailyValues(DEFAULT_PERCENT_DAILY_VALUES);
        return nutritionSummary;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NutritionSummary createUpdatedEntity(EntityManager em) {
        NutritionSummary nutritionSummary = new NutritionSummary()
            .key(UPDATED_KEY)
            .quantityMg(UPDATED_QUANTITY_MG)
            .percentDailyValues(UPDATED_PERCENT_DAILY_VALUES);
        return nutritionSummary;
    }

    @BeforeEach
    public void initTest() {
        nutritionSummary = createEntity(em);
    }

    @Test
    @Transactional
    void createNutritionSummary() throws Exception {
        int databaseSizeBeforeCreate = nutritionSummaryRepository.findAll().size();
        // Create the NutritionSummary
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);
        restNutritionSummaryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeCreate + 1);
        NutritionSummary testNutritionSummary = nutritionSummaryList.get(nutritionSummaryList.size() - 1);
        assertThat(testNutritionSummary.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testNutritionSummary.getQuantityMg()).isEqualTo(DEFAULT_QUANTITY_MG);
        assertThat(testNutritionSummary.getPercentDailyValues()).isEqualTo(DEFAULT_PERCENT_DAILY_VALUES);
    }

    @Test
    @Transactional
    void createNutritionSummaryWithExistingId() throws Exception {
        // Create the NutritionSummary with an existing ID
        nutritionSummary.setId(1L);
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);

        int databaseSizeBeforeCreate = nutritionSummaryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNutritionSummaryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkKeyIsRequired() throws Exception {
        int databaseSizeBeforeTest = nutritionSummaryRepository.findAll().size();
        // set the field null
        nutritionSummary.setKey(null);

        // Create the NutritionSummary, which fails.
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);

        restNutritionSummaryMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isBadRequest());

        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllNutritionSummaries() throws Exception {
        // Initialize the database
        nutritionSummaryRepository.saveAndFlush(nutritionSummary);

        // Get all the nutritionSummaryList
        restNutritionSummaryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(nutritionSummary.getId().intValue())))
            .andExpect(jsonPath("$.[*].key").value(hasItem(DEFAULT_KEY.toString())))
            .andExpect(jsonPath("$.[*].quantityMg").value(hasItem(DEFAULT_QUANTITY_MG)))
            .andExpect(jsonPath("$.[*].percentDailyValues").value(hasItem(DEFAULT_PERCENT_DAILY_VALUES)));
    }

    @Test
    @Transactional
    void getNutritionSummary() throws Exception {
        // Initialize the database
        nutritionSummaryRepository.saveAndFlush(nutritionSummary);

        // Get the nutritionSummary
        restNutritionSummaryMockMvc
            .perform(get(ENTITY_API_URL_ID, nutritionSummary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(nutritionSummary.getId().intValue()))
            .andExpect(jsonPath("$.key").value(DEFAULT_KEY.toString()))
            .andExpect(jsonPath("$.quantityMg").value(DEFAULT_QUANTITY_MG))
            .andExpect(jsonPath("$.percentDailyValues").value(DEFAULT_PERCENT_DAILY_VALUES));
    }

    @Test
    @Transactional
    void getNonExistingNutritionSummary() throws Exception {
        // Get the nutritionSummary
        restNutritionSummaryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNutritionSummary() throws Exception {
        // Initialize the database
        nutritionSummaryRepository.saveAndFlush(nutritionSummary);

        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();

        // Update the nutritionSummary
        NutritionSummary updatedNutritionSummary = nutritionSummaryRepository.findById(nutritionSummary.getId()).get();
        // Disconnect from session so that the updates on updatedNutritionSummary are not directly saved in db
        em.detach(updatedNutritionSummary);
        updatedNutritionSummary.key(UPDATED_KEY).quantityMg(UPDATED_QUANTITY_MG).percentDailyValues(UPDATED_PERCENT_DAILY_VALUES);
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(updatedNutritionSummary);

        restNutritionSummaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nutritionSummaryDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isOk());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
        NutritionSummary testNutritionSummary = nutritionSummaryList.get(nutritionSummaryList.size() - 1);
        assertThat(testNutritionSummary.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testNutritionSummary.getQuantityMg()).isEqualTo(UPDATED_QUANTITY_MG);
        assertThat(testNutritionSummary.getPercentDailyValues()).isEqualTo(UPDATED_PERCENT_DAILY_VALUES);
    }

    @Test
    @Transactional
    void putNonExistingNutritionSummary() throws Exception {
        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();
        nutritionSummary.setId(count.incrementAndGet());

        // Create the NutritionSummary
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNutritionSummaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, nutritionSummaryDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNutritionSummary() throws Exception {
        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();
        nutritionSummary.setId(count.incrementAndGet());

        // Create the NutritionSummary
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNutritionSummaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNutritionSummary() throws Exception {
        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();
        nutritionSummary.setId(count.incrementAndGet());

        // Create the NutritionSummary
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNutritionSummaryMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNutritionSummaryWithPatch() throws Exception {
        // Initialize the database
        nutritionSummaryRepository.saveAndFlush(nutritionSummary);

        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();

        // Update the nutritionSummary using partial update
        NutritionSummary partialUpdatedNutritionSummary = new NutritionSummary();
        partialUpdatedNutritionSummary.setId(nutritionSummary.getId());

        partialUpdatedNutritionSummary.quantityMg(UPDATED_QUANTITY_MG).percentDailyValues(UPDATED_PERCENT_DAILY_VALUES);

        restNutritionSummaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNutritionSummary.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNutritionSummary))
            )
            .andExpect(status().isOk());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
        NutritionSummary testNutritionSummary = nutritionSummaryList.get(nutritionSummaryList.size() - 1);
        assertThat(testNutritionSummary.getKey()).isEqualTo(DEFAULT_KEY);
        assertThat(testNutritionSummary.getQuantityMg()).isEqualTo(UPDATED_QUANTITY_MG);
        assertThat(testNutritionSummary.getPercentDailyValues()).isEqualTo(UPDATED_PERCENT_DAILY_VALUES);
    }

    @Test
    @Transactional
    void fullUpdateNutritionSummaryWithPatch() throws Exception {
        // Initialize the database
        nutritionSummaryRepository.saveAndFlush(nutritionSummary);

        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();

        // Update the nutritionSummary using partial update
        NutritionSummary partialUpdatedNutritionSummary = new NutritionSummary();
        partialUpdatedNutritionSummary.setId(nutritionSummary.getId());

        partialUpdatedNutritionSummary.key(UPDATED_KEY).quantityMg(UPDATED_QUANTITY_MG).percentDailyValues(UPDATED_PERCENT_DAILY_VALUES);

        restNutritionSummaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNutritionSummary.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNutritionSummary))
            )
            .andExpect(status().isOk());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
        NutritionSummary testNutritionSummary = nutritionSummaryList.get(nutritionSummaryList.size() - 1);
        assertThat(testNutritionSummary.getKey()).isEqualTo(UPDATED_KEY);
        assertThat(testNutritionSummary.getQuantityMg()).isEqualTo(UPDATED_QUANTITY_MG);
        assertThat(testNutritionSummary.getPercentDailyValues()).isEqualTo(UPDATED_PERCENT_DAILY_VALUES);
    }

    @Test
    @Transactional
    void patchNonExistingNutritionSummary() throws Exception {
        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();
        nutritionSummary.setId(count.incrementAndGet());

        // Create the NutritionSummary
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNutritionSummaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, nutritionSummaryDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNutritionSummary() throws Exception {
        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();
        nutritionSummary.setId(count.incrementAndGet());

        // Create the NutritionSummary
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNutritionSummaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNutritionSummary() throws Exception {
        int databaseSizeBeforeUpdate = nutritionSummaryRepository.findAll().size();
        nutritionSummary.setId(count.incrementAndGet());

        // Create the NutritionSummary
        NutritionSummaryDTO nutritionSummaryDTO = nutritionSummaryMapper.toDto(nutritionSummary);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNutritionSummaryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(nutritionSummaryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NutritionSummary in the database
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNutritionSummary() throws Exception {
        // Initialize the database
        nutritionSummaryRepository.saveAndFlush(nutritionSummary);

        int databaseSizeBeforeDelete = nutritionSummaryRepository.findAll().size();

        // Delete the nutritionSummary
        restNutritionSummaryMockMvc
            .perform(delete(ENTITY_API_URL_ID, nutritionSummary.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NutritionSummary> nutritionSummaryList = nutritionSummaryRepository.findAll();
        assertThat(nutritionSummaryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
