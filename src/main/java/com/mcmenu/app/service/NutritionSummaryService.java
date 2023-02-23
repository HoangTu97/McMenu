package com.mcmenu.app.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.mcmenu.app.domain.NutritionSummary;
import com.mcmenu.app.repository.NutritionSummaryRepository;
import com.mcmenu.app.repository.search.NutritionSummarySearchRepository;
import com.mcmenu.app.service.dto.NutritionSummaryDTO;
import com.mcmenu.app.service.mapper.NutritionSummaryMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NutritionSummary}.
 */
@Service
@Transactional
public class NutritionSummaryService {

    private final Logger log = LoggerFactory.getLogger(NutritionSummaryService.class);

    private final NutritionSummaryRepository nutritionSummaryRepository;

    private final NutritionSummaryMapper nutritionSummaryMapper;

    private final NutritionSummarySearchRepository nutritionSummarySearchRepository;

    public NutritionSummaryService(
        NutritionSummaryRepository nutritionSummaryRepository,
        NutritionSummaryMapper nutritionSummaryMapper,
        NutritionSummarySearchRepository nutritionSummarySearchRepository
    ) {
        this.nutritionSummaryRepository = nutritionSummaryRepository;
        this.nutritionSummaryMapper = nutritionSummaryMapper;
        this.nutritionSummarySearchRepository = nutritionSummarySearchRepository;
    }

    /**
     * Save a nutritionSummary.
     *
     * @param nutritionSummaryDTO the entity to save.
     * @return the persisted entity.
     */
    public NutritionSummaryDTO save(NutritionSummaryDTO nutritionSummaryDTO) {
        log.debug("Request to save NutritionSummary : {}", nutritionSummaryDTO);
        NutritionSummary nutritionSummary = nutritionSummaryMapper.toEntity(nutritionSummaryDTO);
        nutritionSummary = nutritionSummaryRepository.save(nutritionSummary);
        NutritionSummaryDTO result = nutritionSummaryMapper.toDto(nutritionSummary);
        nutritionSummarySearchRepository.index(nutritionSummary);
        return result;
    }

    /**
     * Update a nutritionSummary.
     *
     * @param nutritionSummaryDTO the entity to save.
     * @return the persisted entity.
     */
    public NutritionSummaryDTO update(NutritionSummaryDTO nutritionSummaryDTO) {
        log.debug("Request to update NutritionSummary : {}", nutritionSummaryDTO);
        NutritionSummary nutritionSummary = nutritionSummaryMapper.toEntity(nutritionSummaryDTO);
        nutritionSummary = nutritionSummaryRepository.save(nutritionSummary);
        NutritionSummaryDTO result = nutritionSummaryMapper.toDto(nutritionSummary);
        nutritionSummarySearchRepository.index(nutritionSummary);
        return result;
    }

    /**
     * Partially update a nutritionSummary.
     *
     * @param nutritionSummaryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NutritionSummaryDTO> partialUpdate(NutritionSummaryDTO nutritionSummaryDTO) {
        log.debug("Request to partially update NutritionSummary : {}", nutritionSummaryDTO);

        return nutritionSummaryRepository
            .findById(nutritionSummaryDTO.getId())
            .map(existingNutritionSummary -> {
                nutritionSummaryMapper.partialUpdate(existingNutritionSummary, nutritionSummaryDTO);

                return existingNutritionSummary;
            })
            .map(nutritionSummaryRepository::save)
            .map(savedNutritionSummary -> {
                nutritionSummarySearchRepository.save(savedNutritionSummary);

                return savedNutritionSummary;
            })
            .map(nutritionSummaryMapper::toDto);
    }

    /**
     * Get all the nutritionSummaries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NutritionSummaryDTO> findAll() {
        log.debug("Request to get all NutritionSummaries");
        return nutritionSummaryRepository
            .findAll()
            .stream()
            .map(nutritionSummaryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one nutritionSummary by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NutritionSummaryDTO> findOne(Long id) {
        log.debug("Request to get NutritionSummary : {}", id);
        return nutritionSummaryRepository.findById(id).map(nutritionSummaryMapper::toDto);
    }

    /**
     * Delete the nutritionSummary by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NutritionSummary : {}", id);
        nutritionSummaryRepository.deleteById(id);
        nutritionSummarySearchRepository.deleteById(id);
    }

    /**
     * Search for the nutritionSummary corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NutritionSummaryDTO> search(String query) {
        log.debug("Request to search NutritionSummaries for query {}", query);
        return StreamSupport
            .stream(nutritionSummarySearchRepository.search(query).spliterator(), false)
            .map(nutritionSummaryMapper::toDto)
            .collect(Collectors.toList());
    }
}
