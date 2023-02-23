package com.mcmenu.app.repository;

import com.mcmenu.app.domain.Ingredients;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Ingredients entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {}
