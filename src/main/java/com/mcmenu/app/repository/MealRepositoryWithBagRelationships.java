package com.mcmenu.app.repository;

import com.mcmenu.app.domain.Meal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface MealRepositoryWithBagRelationships {
    Optional<Meal> fetchBagRelationships(Optional<Meal> meal);

    List<Meal> fetchBagRelationships(List<Meal> meals);

    Page<Meal> fetchBagRelationships(Page<Meal> meals);
}
