package com.mcmenu.app.web.rest;

import com.mcmenu.app.repository.MealRepository;
import com.mcmenu.app.service.MealService;
import com.mcmenu.app.service.dto.MealDTO;
import com.mcmenu.app.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.mcmenu.app.domain.Meal}.
 */
@RestController
@RequestMapping("/api")
public class MealResource {

    private final Logger log = LoggerFactory.getLogger(MealResource.class);

    private static final String ENTITY_NAME = "meal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MealService mealService;

    private final MealRepository mealRepository;

    public MealResource(MealService mealService, MealRepository mealRepository) {
        this.mealService = mealService;
        this.mealRepository = mealRepository;
    }

    /**
     * {@code POST  /meals} : Create a new meal.
     *
     * @param mealDTO the mealDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mealDTO, or with status {@code 400 (Bad Request)} if the meal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meals")
    public ResponseEntity<MealDTO> createMeal(@Valid @RequestBody MealDTO mealDTO) throws URISyntaxException {
        log.debug("REST request to save Meal : {}", mealDTO);
        if (mealDTO.getId() != null) {
            throw new BadRequestAlertException("A new meal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MealDTO result = mealService.save(mealDTO);
        return ResponseEntity
            .created(new URI("/api/meals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meals/:id} : Updates an existing meal.
     *
     * @param id the id of the mealDTO to save.
     * @param mealDTO the mealDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mealDTO,
     * or with status {@code 400 (Bad Request)} if the mealDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mealDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meals/{id}")
    public ResponseEntity<MealDTO> updateMeal(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody MealDTO mealDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Meal : {}, {}", id, mealDTO);
        if (mealDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mealDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mealRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        MealDTO result = mealService.update(mealDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mealDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /meals/:id} : Partial updates given fields of an existing meal, field will ignore if it is null
     *
     * @param id the id of the mealDTO to save.
     * @param mealDTO the mealDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mealDTO,
     * or with status {@code 400 (Bad Request)} if the mealDTO is not valid,
     * or with status {@code 404 (Not Found)} if the mealDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the mealDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/meals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<MealDTO> partialUpdateMeal(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody MealDTO mealDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Meal partially : {}, {}", id, mealDTO);
        if (mealDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, mealDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!mealRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<MealDTO> result = mealService.partialUpdate(mealDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, mealDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /meals} : get all the meals.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meals in body.
     */
    @GetMapping("/meals")
    public List<MealDTO> getAllMeals(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Meals");
        return mealService.findAll();
    }

    /**
     * {@code GET  /meals/:id} : get the "id" meal.
     *
     * @param id the id of the mealDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mealDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meals/{id}")
    public ResponseEntity<MealDTO> getMeal(@PathVariable Long id) {
        log.debug("REST request to get Meal : {}", id);
        Optional<MealDTO> mealDTO = mealService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mealDTO);
    }

    /**
     * {@code DELETE  /meals/:id} : delete the "id" meal.
     *
     * @param id the id of the mealDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meals/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        log.debug("REST request to delete Meal : {}", id);
        mealService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
