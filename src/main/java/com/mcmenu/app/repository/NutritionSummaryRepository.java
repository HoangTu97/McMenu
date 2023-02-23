package com.mcmenu.app.repository;

import com.mcmenu.app.domain.NutritionSummary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NutritionSummary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NutritionSummaryRepository extends JpaRepository<NutritionSummary, Long> {}
