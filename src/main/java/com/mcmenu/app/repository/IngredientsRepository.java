package com.mcmenu.app.repository;

import com.mcmenu.app.domain.Ingredients;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Ingredients entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
    @Query("select i from Ingredients i inner join i.products products where products.id = ?1")
    List<Ingredients> findByProducts_Id(Long id);

    @EntityGraph(attributePaths = { "products" })
    @Query("select i from Ingredients i inner join i.products products where products.id in ?1")
    List<Ingredients> findByProducts_IdIn(Collection<Long> ids);
}
