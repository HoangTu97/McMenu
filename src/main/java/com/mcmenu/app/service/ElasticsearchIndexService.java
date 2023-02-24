package com.mcmenu.app.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mcmenu.app.domain.Category;
import com.mcmenu.app.domain.Ingredients;
import com.mcmenu.app.domain.Meal;
import com.mcmenu.app.domain.NutritionSummary;
import com.mcmenu.app.domain.Product;
import com.mcmenu.app.repository.CategoryRepository;
import com.mcmenu.app.repository.IngredientsRepository;
import com.mcmenu.app.repository.MealRepository;
import com.mcmenu.app.repository.NutritionSummaryRepository;
import com.mcmenu.app.repository.ProductRepository;
import com.mcmenu.app.repository.search.CategorySearchRepository;
import com.mcmenu.app.repository.search.IngredientsSearchRepository;
import com.mcmenu.app.repository.search.MealSearchRepository;
import com.mcmenu.app.repository.search.NutritionSummarySearchRepository;
import com.mcmenu.app.repository.search.ProductSearchRepository;
import io.micrometer.core.annotation.Timed;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import javax.persistence.ManyToMany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ElasticsearchIndexService {

    private static final Lock reindexLock = new ReentrantLock();

    private static final Logger log = LoggerFactory.getLogger(ElasticsearchIndexService.class);

    private final CategoryRepository categoryRepository;
    private final CategorySearchRepository categorySearchRepository;
    private final IngredientsRepository ingredientsRepository;
    private final IngredientsSearchRepository ingredientsSearchRepository;
    private final MealRepository mealRepository;
    private final MealSearchRepository mealSearchRepository;
    private final NutritionSummaryRepository nutritionSummaryRepository;
    private final NutritionSummarySearchRepository nutritionSummarySearchRepository;
    private final ProductRepository productRepository;
    private final ProductSearchRepository productSearchRepository;

    public ElasticsearchIndexService(
        CategoryRepository categoryRepository,
        CategorySearchRepository categorySearchRepository,
        IngredientsRepository ingredientsRepository,
        IngredientsSearchRepository ingredientsSearchRepository,
        MealRepository mealRepository,
        MealSearchRepository mealSearchRepository,
        NutritionSummaryRepository nutritionSummaryRepository,
        NutritionSummarySearchRepository nutritionSummarySearchRepository,
        ProductRepository productRepository,
        ProductSearchRepository productSearchRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.categorySearchRepository = categorySearchRepository;
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientsSearchRepository = ingredientsSearchRepository;
        this.mealRepository = mealRepository;
        this.mealSearchRepository = mealSearchRepository;
        this.nutritionSummaryRepository = nutritionSummaryRepository;
        this.nutritionSummarySearchRepository = nutritionSummarySearchRepository;
        this.productRepository = productRepository;
        this.productSearchRepository = productSearchRepository;
    }

    @Async
    @Timed
    public void reindexAll() {
        if (reindexLock.tryLock()) {
            try {
                reindexForClass(Category.class, categoryRepository, categorySearchRepository);
                reindexForClass(Ingredients.class, ingredientsRepository, ingredientsSearchRepository);
                reindexForClass(Meal.class, mealRepository, mealSearchRepository);
                reindexForClass(NutritionSummary.class, nutritionSummaryRepository, nutritionSummarySearchRepository);
                reindexForClass(Product.class, productRepository, productSearchRepository);

                log.info("Elasticsearch: Successfully performed reindexing");
            } finally {
                reindexLock.unlock();
            }
        } else {
            log.info("Elasticsearch: concurrent reindexing attempt");
        }
    }

    @SuppressWarnings("unchecked")
    private <T, ID extends Serializable> void reindexForClass(
        Class<T> entityClass,
        JpaRepository<T, ID> jpaRepository,
        ElasticsearchRepository<T, ID> elasticsearchRepository
    ) {
        //        client.deleteIndex(entityClass);
        //        try {
        //            client.createIndex(entityClass);
        //        } catch (ResourceAlreadyExistsException e) {
        //            // Do nothing. Index was already concurrently recreated by some other service.
        //        }
        //        client.putMapping(entityClass);
        if (jpaRepository.count() > 0) {
            // if a JHipster entity field is the owner side of a many-to-many relationship, it should be loaded manually
            List<Method> relationshipGetters = Arrays
                .stream(entityClass.getDeclaredFields())
                .filter(field -> field.getType().equals(Set.class))
                .filter(field -> field.getAnnotation(ManyToMany.class) != null)
                .filter(field -> field.getAnnotation(ManyToMany.class).mappedBy().isEmpty())
                .filter(field -> field.getAnnotation(JsonIgnore.class) == null)
                .map(field -> {
                    try {
                        return new PropertyDescriptor(field.getName(), entityClass).getReadMethod();
                    } catch (IntrospectionException e) {
                        log.error(
                            "Error retrieving getter for class {}, field {}. Field will NOT be indexed",
                            entityClass.getSimpleName(),
                            field.getName(),
                            e
                        );
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            elasticsearchRepository.deleteAll();
            int size = 100;
            for (int i = 0; i <= jpaRepository.count() / size; i++) {
                Pageable page = PageRequest.of(i, size);
                log.info("Indexing page {} of {}, size {}", i, jpaRepository.count() / size, size);
                Page<T> results = jpaRepository.findAll(page);
                results.map(result -> {
                    // if there are any relationships to load, do it now
                    relationshipGetters.forEach(method -> {
                        try {
                            // eagerly load the relationship set
                            ((Set) method.invoke(result)).size();
                        } catch (Exception ex) {
                            log.error(ex.getMessage());
                        }
                    });
                    return result;
                });
                elasticsearchRepository.saveAll(results.getContent());
            }
        }
        log.info("Elasticsearch: Indexed all rows for {}", entityClass.getSimpleName());
    }
}
