package com.mcmenu.app.repository;

import com.mcmenu.app.domain.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface CategoryRepositoryWithBagRelationships {
    Optional<Category> fetchBagRelationships(Optional<Category> category);

    List<Category> fetchBagRelationships(List<Category> categories);

    Page<Category> fetchBagRelationships(Page<Category> categories);
}
