package com.mcmenu.app.web.rest;

import com.mcmenu.app.repository.NutritionSummaryRepository;
import com.mcmenu.app.service.NutritionSummaryService;
import com.mcmenu.app.service.dto.NutritionSummaryDTO;
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
 * REST controller for managing {@link com.mcmenu.app.domain.NutritionSummary}.
 */
@RestController
@RequestMapping("/api")
public class NutritionSummaryResource {

    private final Logger log = LoggerFactory.getLogger(NutritionSummaryResource.class);

    private static final String ENTITY_NAME = "nutritionSummary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NutritionSummaryService nutritionSummaryService;

    private final NutritionSummaryRepository nutritionSummaryRepository;

    public NutritionSummaryResource(
        NutritionSummaryService nutritionSummaryService,
        NutritionSummaryRepository nutritionSummaryRepository
    ) {
        this.nutritionSummaryService = nutritionSummaryService;
        this.nutritionSummaryRepository = nutritionSummaryRepository;
    }

    /**
     * {@code POST  /nutrition-summaries} : Create a new nutritionSummary.
     *
     * @param nutritionSummaryDTO the nutritionSummaryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new nutritionSummaryDTO, or with status {@code 400 (Bad Request)} if the nutritionSummary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/nutrition-summaries")
    public ResponseEntity<NutritionSummaryDTO> createNutritionSummary(@Valid @RequestBody NutritionSummaryDTO nutritionSummaryDTO)
        throws URISyntaxException {
        log.debug("REST request to save NutritionSummary : {}", nutritionSummaryDTO);
        if (nutritionSummaryDTO.getId() != null) {
            throw new BadRequestAlertException("A new nutritionSummary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NutritionSummaryDTO result = nutritionSummaryService.save(nutritionSummaryDTO);
        return ResponseEntity
            .created(new URI("/api/nutrition-summaries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /nutrition-summaries/:id} : Updates an existing nutritionSummary.
     *
     * @param id the id of the nutritionSummaryDTO to save.
     * @param nutritionSummaryDTO the nutritionSummaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nutritionSummaryDTO,
     * or with status {@code 400 (Bad Request)} if the nutritionSummaryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the nutritionSummaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/nutrition-summaries/{id}")
    public ResponseEntity<NutritionSummaryDTO> updateNutritionSummary(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody NutritionSummaryDTO nutritionSummaryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NutritionSummary : {}, {}", id, nutritionSummaryDTO);
        if (nutritionSummaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nutritionSummaryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nutritionSummaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NutritionSummaryDTO result = nutritionSummaryService.update(nutritionSummaryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nutritionSummaryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /nutrition-summaries/:id} : Partial updates given fields of an existing nutritionSummary, field will ignore if it is null
     *
     * @param id the id of the nutritionSummaryDTO to save.
     * @param nutritionSummaryDTO the nutritionSummaryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated nutritionSummaryDTO,
     * or with status {@code 400 (Bad Request)} if the nutritionSummaryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the nutritionSummaryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the nutritionSummaryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/nutrition-summaries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NutritionSummaryDTO> partialUpdateNutritionSummary(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody NutritionSummaryDTO nutritionSummaryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NutritionSummary partially : {}, {}", id, nutritionSummaryDTO);
        if (nutritionSummaryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, nutritionSummaryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!nutritionSummaryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NutritionSummaryDTO> result = nutritionSummaryService.partialUpdate(nutritionSummaryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, nutritionSummaryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /nutrition-summaries} : get all the nutritionSummaries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of nutritionSummaries in body.
     */
    @GetMapping("/nutrition-summaries")
    public List<NutritionSummaryDTO> getAllNutritionSummaries() {
        log.debug("REST request to get all NutritionSummaries");
        return nutritionSummaryService.findAll();
    }

    /**
     * {@code GET  /nutrition-summaries/:id} : get the "id" nutritionSummary.
     *
     * @param id the id of the nutritionSummaryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the nutritionSummaryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/nutrition-summaries/{id}")
    public ResponseEntity<NutritionSummaryDTO> getNutritionSummary(@PathVariable Long id) {
        log.debug("REST request to get NutritionSummary : {}", id);
        Optional<NutritionSummaryDTO> nutritionSummaryDTO = nutritionSummaryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(nutritionSummaryDTO);
    }

    /**
     * {@code DELETE  /nutrition-summaries/:id} : delete the "id" nutritionSummary.
     *
     * @param id the id of the nutritionSummaryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/nutrition-summaries/{id}")
    public ResponseEntity<Void> deleteNutritionSummary(@PathVariable Long id) {
        log.debug("REST request to delete NutritionSummary : {}", id);
        nutritionSummaryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
