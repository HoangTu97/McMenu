package com.mcmenu.app.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.mcmenu.app.domain.Ingredients;
import com.mcmenu.app.repository.IngredientsRepository;
import com.mcmenu.app.repository.search.IngredientsSearchRepository;
import com.mcmenu.app.service.dto.IngredientsDTO;
import com.mcmenu.app.service.mapper.IngredientsMapper;
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
 * Service Implementation for managing {@link Ingredients}.
 */
@Service
@Transactional
public class IngredientsService {

    private final Logger log = LoggerFactory.getLogger(IngredientsService.class);

    private final IngredientsRepository ingredientsRepository;

    private final IngredientsMapper ingredientsMapper;

    private final IngredientsSearchRepository ingredientsSearchRepository;

    public IngredientsService(
        IngredientsRepository ingredientsRepository,
        IngredientsMapper ingredientsMapper,
        IngredientsSearchRepository ingredientsSearchRepository
    ) {
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientsMapper = ingredientsMapper;
        this.ingredientsSearchRepository = ingredientsSearchRepository;
    }

    /**
     * Save a ingredients.
     *
     * @param ingredientsDTO the entity to save.
     * @return the persisted entity.
     */
    public IngredientsDTO save(IngredientsDTO ingredientsDTO) {
        log.debug("Request to save Ingredients : {}", ingredientsDTO);
        Ingredients ingredients = ingredientsMapper.toEntity(ingredientsDTO);
        ingredients = ingredientsRepository.save(ingredients);
        IngredientsDTO result = ingredientsMapper.toDto(ingredients);
        ingredientsSearchRepository.index(ingredients);
        return result;
    }

    /**
     * Update a ingredients.
     *
     * @param ingredientsDTO the entity to save.
     * @return the persisted entity.
     */
    public IngredientsDTO update(IngredientsDTO ingredientsDTO) {
        log.debug("Request to update Ingredients : {}", ingredientsDTO);
        Ingredients ingredients = ingredientsMapper.toEntity(ingredientsDTO);
        ingredients = ingredientsRepository.save(ingredients);
        IngredientsDTO result = ingredientsMapper.toDto(ingredients);
        ingredientsSearchRepository.index(ingredients);
        return result;
    }

    /**
     * Partially update a ingredients.
     *
     * @param ingredientsDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<IngredientsDTO> partialUpdate(IngredientsDTO ingredientsDTO) {
        log.debug("Request to partially update Ingredients : {}", ingredientsDTO);

        return ingredientsRepository
            .findById(ingredientsDTO.getId())
            .map(existingIngredients -> {
                ingredientsMapper.partialUpdate(existingIngredients, ingredientsDTO);

                return existingIngredients;
            })
            .map(ingredientsRepository::save)
            .map(savedIngredients -> {
                ingredientsSearchRepository.save(savedIngredients);

                return savedIngredients;
            })
            .map(ingredientsMapper::toDto);
    }

    /**
     * Get all the ingredients.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IngredientsDTO> findAll() {
        log.debug("Request to get all Ingredients");
        return ingredientsRepository.findAll().stream().map(ingredientsMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ingredients by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IngredientsDTO> findOne(Long id) {
        log.debug("Request to get Ingredients : {}", id);
        return ingredientsRepository.findById(id).map(ingredientsMapper::toDto);
    }

    /**
     * Delete the ingredients by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ingredients : {}", id);
        ingredientsRepository.deleteById(id);
        ingredientsSearchRepository.deleteById(id);
    }

    /**
     * Search for the ingredients corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IngredientsDTO> search(String query) {
        log.debug("Request to search Ingredients for query {}", query);
        return StreamSupport
            .stream(ingredientsSearchRepository.search(query).spliterator(), false)
            .map(ingredientsMapper::toDto)
            .collect(Collectors.toList());
    }
}
