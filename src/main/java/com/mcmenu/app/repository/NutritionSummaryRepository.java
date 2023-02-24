package com.mcmenu.app.repository;

import com.mcmenu.app.domain.NutritionSummary;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the NutritionSummary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NutritionSummaryRepository extends JpaRepository<NutritionSummary, Long> {
    @Query("select n from NutritionSummary n where n.product.id in ?1")
    List<NutritionSummary> findByProduct_IdIn(Collection<Long> ids);

    @Query("select n from NutritionSummary n where n.product.id = ?1")
    List<NutritionSummary> findByProduct_Id(Long id);
}
