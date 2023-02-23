package com.mcmenu.app.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.mcmenu.app.repository.CategoryRepository;
import com.mcmenu.app.service.CategoryService;
import com.mcmenu.app.service.MealService;
import com.mcmenu.app.service.ProductService;
import com.mcmenu.app.service.dto.CategoryDTO;
import com.mcmenu.app.service.dto.CategoryFullMenuDTO;
import com.mcmenu.app.service.dto.MealDTO;
import com.mcmenu.app.service.dto.ProductDTO;
import com.mcmenu.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mcmenu.app.domain.Category}.
 */
@RestController
@RequestMapping("/api")
public class CategoryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    private static final String ENTITY_NAME = "category";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryService categoryService;

    private final CategoryRepository categoryRepository;

    private final ProductService productService;
    private final MealService mealService;

    public CategoryResource(
        CategoryService categoryService,
        CategoryRepository categoryRepository,
        ProductService productService,
        MealService mealService
    ) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.mealService = mealService;
    }

    /**
     * {@code POST  /categories} : Create a new category.
     *
     * @param categoryDTO the categoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryDTO, or with status {@code 400 (Bad Request)} if the category has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws URISyntaxException {
        log.debug("REST request to save Category : {}", categoryDTO);
        if (categoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CategoryDTO result = categoryService.save(categoryDTO);
        return ResponseEntity
            .created(new URI("/api/categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /categories/:id} : Updates an existing category.
     *
     * @param id the id of the categoryDTO to save.
     * @param categoryDTO the categoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryDTO,
     * or with status {@code 400 (Bad Request)} if the categoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CategoryDTO categoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Category : {}, {}", id, categoryDTO);
        if (categoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CategoryDTO result = categoryService.update(categoryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /categories/:id} : Partial updates given fields of an existing category, field will ignore if it is null
     *
     * @param id the id of the categoryDTO to save.
     * @param categoryDTO the categoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryDTO,
     * or with status {@code 400 (Bad Request)} if the categoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the categoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the categoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/categories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CategoryDTO> partialUpdateCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CategoryDTO categoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Category partially : {}, {}", id, categoryDTO);
        if (categoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategoryDTO> result = categoryService.partialUpdate(categoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /categories} : get all the categories.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categories in body.
     */
    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Categories");
        return categoryService.findAll();
    }

    /**
     * {@code GET  /categories/:id} : get the "id" category.
     *
     * @param id the id of the categoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<CategoryDTO> categoryDTO = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryDTO);
    }

    /**
     * {@code GET  /categories/:id/full-menu} : get the "id" category.
     *
     * @param id the id of the categoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the
     *         categoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categories/{id}/full-menu")
    public ResponseEntity<List<CategoryFullMenuDTO>> getCategoryFullMenu(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<CategoryDTO> categoryDTO = categoryService.findOne(id);
        if (categoryDTO.isEmpty()) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        List<ProductDTO> productDTOS = productService.findAllByCate(id);
        List<CategoryFullMenuDTO> list = productDTOS
            .stream()
            .map(e -> {
                CategoryFullMenuDTO dto = new CategoryFullMenuDTO();
                dto.setId(e.getId());
                dto.setItemType(CategoryFullMenuDTO.ItemType.PRODUCT);
                dto.setName(e.getName());
                dto.setImageUrl(e.getImageUrl());
                return dto;
            })
            .collect(Collectors.toList());

        List<MealDTO> mealDTOS = mealService.findAllByCate(id);
        list.addAll(
            mealDTOS
                .stream()
                .map(e -> {
                    CategoryFullMenuDTO dto = new CategoryFullMenuDTO();
                    dto.setId(e.getId());
                    dto.setItemType(CategoryFullMenuDTO.ItemType.MEAL);
                    dto.setName(e.getName());
                    dto.setImageUrl(e.getImageUrl());
                    return dto;
                })
                .collect(Collectors.toList())
        );

        return ResponseEntity.ok(list);
    }

    /**
     * {@code DELETE  /categories/:id} : delete the "id" category.
     *
     * @param id the id of the categoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete Category : {}", id);
        categoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /_search/categories?query=:query} : search for the category corresponding
     * to the query.
     *
     * @param query the query of the category search.
     * @return the result of the search.
     */
    @GetMapping("/_search/categories")
    public List<CategoryDTO> searchCategories(@RequestParam String query) {
        log.debug("REST request to search Categories for query {}", query);
        return categoryService.search(query);
    }
}
