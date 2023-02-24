package com.mcmenu.app.repository;

import com.mcmenu.app.domain.Meal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class MealRepositoryWithBagRelationshipsImpl implements MealRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Meal> fetchBagRelationships(Optional<Meal> meal) {
        return meal.map(this::fetchProducts);
    }

    @Override
    public Page<Meal> fetchBagRelationships(Page<Meal> meals) {
        return new PageImpl<>(fetchBagRelationships(meals.getContent()), meals.getPageable(), meals.getTotalElements());
    }

    @Override
    public List<Meal> fetchBagRelationships(List<Meal> meals) {
        return Optional.of(meals).map(this::fetchProducts).orElse(Collections.emptyList());
    }

    Meal fetchProducts(Meal result) {
        return entityManager
            .createQuery("select meal from Meal meal left join fetch meal.products where meal is :meal", Meal.class)
            .setParameter("meal", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Meal> fetchProducts(List<Meal> meals) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, meals.size()).forEach(index -> order.put(meals.get(index).getId(), index));
        List<Meal> result = entityManager
            .createQuery("select distinct meal from Meal meal left join fetch meal.products where meal in :meals", Meal.class)
            .setParameter("meals", meals)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
