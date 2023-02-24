package com.mcmenu.app.service;

import static org.elasticsearch.index.query.QueryBuilders.*;

import com.mcmenu.app.domain.Meal;
import com.mcmenu.app.repository.MealRepository;
import com.mcmenu.app.repository.search.MealSearchRepository;
import com.mcmenu.app.service.dto.MealDTO;
import com.mcmenu.app.service.mapper.MealMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Meal}.
 */
@Service
@Transactional
public class MealService {

    private final Logger log = LoggerFactory.getLogger(MealService.class);

    private final MealRepository mealRepository;

    private final MealMapper mealMapper;

    private final MealSearchRepository mealSearchRepository;

    public MealService(MealRepository mealRepository, MealMapper mealMapper, MealSearchRepository mealSearchRepository) {
        this.mealRepository = mealRepository;
        this.mealMapper = mealMapper;
        this.mealSearchRepository = mealSearchRepository;
    }

    /**
     * Save a meal.
     *
     * @param mealDTO the entity to save.
     * @return the persisted entity.
     */
    public MealDTO save(MealDTO mealDTO) {
        log.debug("Request to save Meal : {}", mealDTO);
        Meal meal = mealMapper.toEntity(mealDTO);
        meal = mealRepository.save(meal);
        MealDTO result = mealMapper.toDto(meal);
        mealSearchRepository.index(meal);
        return result;
    }

    /**
     * Update a meal.
     *
     * @param mealDTO the entity to save.
     * @return the persisted entity.
     */
    public MealDTO update(MealDTO mealDTO) {
        log.debug("Request to update Meal : {}", mealDTO);
        Meal meal = mealMapper.toEntity(mealDTO);
        meal = mealRepository.save(meal);
        MealDTO result = mealMapper.toDto(meal);
        mealSearchRepository.index(meal);
        return result;
    }

    /**
     * Partially update a meal.
     *
     * @param mealDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<MealDTO> partialUpdate(MealDTO mealDTO) {
        log.debug("Request to partially update Meal : {}", mealDTO);

        return mealRepository
            .findById(mealDTO.getId())
            .map(existingMeal -> {
                mealMapper.partialUpdate(existingMeal, mealDTO);

                return existingMeal;
            })
            .map(mealRepository::save)
            .map(savedMeal -> {
                mealSearchRepository.save(savedMeal);

                return savedMeal;
            })
            .map(mealMapper::toDto);
    }

    /**
     * Get all the meals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MealDTO> findAll() {
        log.debug("Request to get all Meals");
        return mealRepository.findAll().stream().map(mealMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public List<MealDTO> findAllByCate(Long id) {
        log.debug("Request to get all Meals");
        return mealRepository.findByCategories_Id(id).stream().map(mealMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the meals with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<MealDTO> findAllWithEagerRelationships(Pageable pageable) {
        return mealRepository.findAllWithEagerRelationships(pageable).map(mealMapper::toDto);
    }

    /**
     * Get one meal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MealDTO> findOne(Long id) {
        log.debug("Request to get Meal : {}", id);
        return mealRepository.findOneWithEagerRelationships(id).map(mealMapper::toDto);
    }

    /**
     * Delete the meal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Meal : {}", id);
        mealRepository.deleteById(id);
        mealSearchRepository.deleteById(id);
    }

    /**
     * Search for the meal corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<MealDTO> search(String query) {
        log.debug("Request to search Meals for query {}", query);
        return StreamSupport
            .stream(mealSearchRepository.search(query).spliterator(), false)
            .map(mealMapper::toDto)
            .collect(Collectors.toList());
    }
}
