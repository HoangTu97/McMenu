package com.mcmenu.app.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mcmenu.app.IntegrationTest;
import com.mcmenu.app.domain.Ingredients;
import com.mcmenu.app.repository.IngredientsRepository;
import com.mcmenu.app.repository.search.IngredientsSearchRepository;
import com.mcmenu.app.service.dto.IngredientsDTO;
import com.mcmenu.app.service.mapper.IngredientsMapper;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import org.apache.commons.collections4.IterableUtils;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link IngredientsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IngredientsResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_INSIDE_INGREDIENTS = "AAAAAAAAAA";
    private static final String UPDATED_INSIDE_INGREDIENTS = "BBBBBBBBBB";

    private static final String DEFAULT_INSIDE_CONTAINS = "AAAAAAAAAA";
    private static final String UPDATED_INSIDE_CONTAINS = "BBBBBBBBBB";

    private static final String DEFAULT_MAY_CONTAINS = "AAAAAAAAAA";
    private static final String UPDATED_MAY_CONTAINS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/ingredients";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/ingredients";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IngredientsRepository ingredientsRepository;

    @Autowired
    private IngredientsMapper ingredientsMapper;

    @Autowired
    private IngredientsSearchRepository ingredientsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIngredientsMockMvc;

    private Ingredients ingredients;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ingredients createEntity(EntityManager em) {
        Ingredients ingredients = new Ingredients()
            .name(DEFAULT_NAME)
            .imageUrl(DEFAULT_IMAGE_URL)
            .insideIngredients(DEFAULT_INSIDE_INGREDIENTS)
            .insideContains(DEFAULT_INSIDE_CONTAINS)
            .mayContains(DEFAULT_MAY_CONTAINS);
        return ingredients;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ingredients createUpdatedEntity(EntityManager em) {
        Ingredients ingredients = new Ingredients()
            .name(UPDATED_NAME)
            .imageUrl(UPDATED_IMAGE_URL)
            .insideIngredients(UPDATED_INSIDE_INGREDIENTS)
            .insideContains(UPDATED_INSIDE_CONTAINS)
            .mayContains(UPDATED_MAY_CONTAINS);
        return ingredients;
    }

    @AfterEach
    public void cleanupElasticSearchRepository() {
        ingredientsSearchRepository.deleteAll();
        assertThat(ingredientsSearchRepository.count()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        ingredients = createEntity(em);
    }

    @Test
    @Transactional
    void createIngredients() throws Exception {
        int databaseSizeBeforeCreate = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        // Create the Ingredients
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);
        restIngredientsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        Ingredients testIngredients = ingredientsList.get(ingredientsList.size() - 1);
        assertThat(testIngredients.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testIngredients.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testIngredients.getInsideIngredients()).isEqualTo(DEFAULT_INSIDE_INGREDIENTS);
        assertThat(testIngredients.getInsideContains()).isEqualTo(DEFAULT_INSIDE_CONTAINS);
        assertThat(testIngredients.getMayContains()).isEqualTo(DEFAULT_MAY_CONTAINS);
    }

    @Test
    @Transactional
    void createIngredientsWithExistingId() throws Exception {
        // Create the Ingredients with an existing ID
        ingredients.setId(1L);
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        int databaseSizeBeforeCreate = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());

        // An entity with an existing ID cannot be created, so this API call must fail
        restIngredientsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        // set the field null
        ingredients.setName(null);

        // Create the Ingredients, which fails.
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        restIngredientsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void checkImageUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        // set the field null
        ingredients.setImageUrl(null);

        // Create the Ingredients, which fails.
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        restIngredientsMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isBadRequest());

        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void getAllIngredients() throws Exception {
        // Initialize the database
        ingredientsRepository.saveAndFlush(ingredients);

        // Get all the ingredientsList
        restIngredientsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ingredients.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].insideIngredients").value(hasItem(DEFAULT_INSIDE_INGREDIENTS)))
            .andExpect(jsonPath("$.[*].insideContains").value(hasItem(DEFAULT_INSIDE_CONTAINS)))
            .andExpect(jsonPath("$.[*].mayContains").value(hasItem(DEFAULT_MAY_CONTAINS)));
    }

    @Test
    @Transactional
    void getIngredients() throws Exception {
        // Initialize the database
        ingredientsRepository.saveAndFlush(ingredients);

        // Get the ingredients
        restIngredientsMockMvc
            .perform(get(ENTITY_API_URL_ID, ingredients.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ingredients.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL))
            .andExpect(jsonPath("$.insideIngredients").value(DEFAULT_INSIDE_INGREDIENTS))
            .andExpect(jsonPath("$.insideContains").value(DEFAULT_INSIDE_CONTAINS))
            .andExpect(jsonPath("$.mayContains").value(DEFAULT_MAY_CONTAINS));
    }

    @Test
    @Transactional
    void getNonExistingIngredients() throws Exception {
        // Get the ingredients
        restIngredientsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingIngredients() throws Exception {
        // Initialize the database
        ingredientsRepository.saveAndFlush(ingredients);

        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();
        ingredientsSearchRepository.save(ingredients);
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());

        // Update the ingredients
        Ingredients updatedIngredients = ingredientsRepository.findById(ingredients.getId()).get();
        // Disconnect from session so that the updates on updatedIngredients are not directly saved in db
        em.detach(updatedIngredients);
        updatedIngredients
            .name(UPDATED_NAME)
            .imageUrl(UPDATED_IMAGE_URL)
            .insideIngredients(UPDATED_INSIDE_INGREDIENTS)
            .insideContains(UPDATED_INSIDE_CONTAINS)
            .mayContains(UPDATED_MAY_CONTAINS);
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(updatedIngredients);

        restIngredientsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ingredientsDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isOk());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        Ingredients testIngredients = ingredientsList.get(ingredientsList.size() - 1);
        assertThat(testIngredients.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testIngredients.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testIngredients.getInsideIngredients()).isEqualTo(UPDATED_INSIDE_INGREDIENTS);
        assertThat(testIngredients.getInsideContains()).isEqualTo(UPDATED_INSIDE_CONTAINS);
        assertThat(testIngredients.getMayContains()).isEqualTo(UPDATED_MAY_CONTAINS);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<Ingredients> ingredientsSearchList = IterableUtils.toList(ingredientsSearchRepository.findAll());
                Ingredients testIngredientsSearch = ingredientsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testIngredientsSearch.getName()).isEqualTo(UPDATED_NAME);
                assertThat(testIngredientsSearch.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
                assertThat(testIngredientsSearch.getInsideIngredients()).isEqualTo(UPDATED_INSIDE_INGREDIENTS);
                assertThat(testIngredientsSearch.getInsideContains()).isEqualTo(UPDATED_INSIDE_CONTAINS);
                assertThat(testIngredientsSearch.getMayContains()).isEqualTo(UPDATED_MAY_CONTAINS);
            });
    }

    @Test
    @Transactional
    void putNonExistingIngredients() throws Exception {
        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        ingredients.setId(count.incrementAndGet());

        // Create the Ingredients
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIngredientsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, ingredientsDTO.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithIdMismatchIngredients() throws Exception {
        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        ingredients.setId(count.incrementAndGet());

        // Create the Ingredients
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIngredientsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIngredients() throws Exception {
        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        ingredients.setId(count.incrementAndGet());

        // Create the Ingredients
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIngredientsMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void partialUpdateIngredientsWithPatch() throws Exception {
        // Initialize the database
        ingredientsRepository.saveAndFlush(ingredients);

        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();

        // Update the ingredients using partial update
        Ingredients partialUpdatedIngredients = new Ingredients();
        partialUpdatedIngredients.setId(ingredients.getId());

        partialUpdatedIngredients.imageUrl(UPDATED_IMAGE_URL);

        restIngredientsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIngredients.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIngredients))
            )
            .andExpect(status().isOk());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        Ingredients testIngredients = ingredientsList.get(ingredientsList.size() - 1);
        assertThat(testIngredients.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testIngredients.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testIngredients.getInsideIngredients()).isEqualTo(DEFAULT_INSIDE_INGREDIENTS);
        assertThat(testIngredients.getInsideContains()).isEqualTo(DEFAULT_INSIDE_CONTAINS);
        assertThat(testIngredients.getMayContains()).isEqualTo(DEFAULT_MAY_CONTAINS);
    }

    @Test
    @Transactional
    void fullUpdateIngredientsWithPatch() throws Exception {
        // Initialize the database
        ingredientsRepository.saveAndFlush(ingredients);

        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();

        // Update the ingredients using partial update
        Ingredients partialUpdatedIngredients = new Ingredients();
        partialUpdatedIngredients.setId(ingredients.getId());

        partialUpdatedIngredients
            .name(UPDATED_NAME)
            .imageUrl(UPDATED_IMAGE_URL)
            .insideIngredients(UPDATED_INSIDE_INGREDIENTS)
            .insideContains(UPDATED_INSIDE_CONTAINS)
            .mayContains(UPDATED_MAY_CONTAINS);

        restIngredientsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIngredients.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIngredients))
            )
            .andExpect(status().isOk());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        Ingredients testIngredients = ingredientsList.get(ingredientsList.size() - 1);
        assertThat(testIngredients.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testIngredients.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testIngredients.getInsideIngredients()).isEqualTo(UPDATED_INSIDE_INGREDIENTS);
        assertThat(testIngredients.getInsideContains()).isEqualTo(UPDATED_INSIDE_CONTAINS);
        assertThat(testIngredients.getMayContains()).isEqualTo(UPDATED_MAY_CONTAINS);
    }

    @Test
    @Transactional
    void patchNonExistingIngredients() throws Exception {
        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        ingredients.setId(count.incrementAndGet());

        // Create the Ingredients
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIngredientsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, ingredientsDTO.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIngredients() throws Exception {
        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        ingredients.setId(count.incrementAndGet());

        // Create the Ingredients
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIngredientsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIngredients() throws Exception {
        int databaseSizeBeforeUpdate = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        ingredients.setId(count.incrementAndGet());

        // Create the Ingredients
        IngredientsDTO ingredientsDTO = ingredientsMapper.toDto(ingredients);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIngredientsMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(ingredientsDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ingredients in the database
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    @Transactional
    void deleteIngredients() throws Exception {
        // Initialize the database
        ingredientsRepository.saveAndFlush(ingredients);
        ingredientsRepository.save(ingredients);
        ingredientsSearchRepository.save(ingredients);

        int databaseSizeBeforeDelete = ingredientsRepository.findAll().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the ingredients
        restIngredientsMockMvc
            .perform(delete(ENTITY_API_URL_ID, ingredients.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ingredients> ingredientsList = ingredientsRepository.findAll();
        assertThat(ingredientsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(ingredientsSearchRepository.findAll());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    @Transactional
    void searchIngredients() throws Exception {
        // Initialize the database
        ingredients = ingredientsRepository.saveAndFlush(ingredients);
        ingredientsSearchRepository.save(ingredients);

        // Search the ingredients
        restIngredientsMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + ingredients.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ingredients.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].insideIngredients").value(hasItem(DEFAULT_INSIDE_INGREDIENTS)))
            .andExpect(jsonPath("$.[*].insideContains").value(hasItem(DEFAULT_INSIDE_CONTAINS)))
            .andExpect(jsonPath("$.[*].mayContains").value(hasItem(DEFAULT_MAY_CONTAINS)));
    }
}
