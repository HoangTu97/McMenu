package com.mcmenu.app.service;

import com.mcmenu.app.domain.Ingredients;
import com.mcmenu.app.repository.IngredientsRepository;
import com.mcmenu.app.service.dto.IngredientsDTO;
import com.mcmenu.app.service.mapper.IngredientsMapper;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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

    public IngredientsService(IngredientsRepository ingredientsRepository, IngredientsMapper ingredientsMapper) {
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientsMapper = ingredientsMapper;
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

    @Transactional(readOnly = true)
    public List<IngredientsDTO> findAllByProductId(Long id) {
        return ingredientsRepository
            .findByProducts_Id(id)
            .stream()
            .map(ingredientsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Transactional(readOnly = true)
    public Map<Long, List<IngredientsDTO>> findMapByProductIds(List<Long> productIds) {
        List<Ingredients> ingredients = ingredientsRepository.findByProducts_IdIn(productIds);
        Map<Long, List<IngredientsDTO>> ret = new HashMap<>();
        for (Long productId : productIds) {
            List<IngredientsDTO> list = ingredients
                .stream()
                .filter(i -> i.getProducts().stream().anyMatch(p -> p.getId().equals(productId)))
                .map(ingredientsMapper::toDto)
                .collect(Collectors.toList());
            ret.put(productId, list);
        }
        return ret;
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
    }
}
