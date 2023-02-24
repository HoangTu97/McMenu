package com.mcmenu.app.web.rest;

import com.mcmenu.app.repository.CategoryRepository;
import com.mcmenu.app.service.CategoryService;
import com.mcmenu.app.service.MealService;
import com.mcmenu.app.service.ProductService;
import com.mcmenu.app.service.dto.CategoryDTO;
import com.mcmenu.app.service.dto.CategoryFullMenuDTO;
import com.mcmenu.app.service.dto.MealDTO;
import com.mcmenu.app.service.dto.ProductDTO;
import com.mcmenu.app.web.rest.errors.BadRequestAlertException;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicCategoryResource {

    private final Logger log = LoggerFactory.getLogger(PublicCategoryResource.class);

    private static final String ENTITY_NAME = "category";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    private final ProductService productService;
    private final MealService mealService;

    public PublicCategoryResource(
        CategoryService categoryService,
        CategoryRepository categoryRepository,
        ProductService productService,
        MealService mealService
    ) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
        this.mealService = mealService;
    }

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        log.debug("REST request to get all Categories");
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}/full-menu")
    public ResponseEntity<List<CategoryFullMenuDTO>> getCategoryFullMenu(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        if (!categoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        List<ProductDTO> productDTOS = productService.findAllByCate(id);
        List<CategoryFullMenuDTO> list = productDTOS
            .stream()
            .map(e -> {
                CategoryFullMenuDTO dto = new CategoryFullMenuDTO();
                dto.setId(e.getId());
                dto.setItemType(CategoryFullMenuDTO.ItemType.PRODUCT);
                dto.setName(e.getName());
                dto.setImageUrl(e.getImageUrl());
                return dto;
            })
            .collect(Collectors.toList());

        List<MealDTO> mealDTOS = mealService.findAllByCate(id);
        list.addAll(
            mealDTOS
                .stream()
                .map(e -> {
                    CategoryFullMenuDTO dto = new CategoryFullMenuDTO();
                    dto.setId(e.getId());
                    dto.setItemType(CategoryFullMenuDTO.ItemType.MEAL);
                    dto.setName(e.getName());
                    dto.setImageUrl(e.getImageUrl());
                    return dto;
                })
                .collect(Collectors.toList())
        );

        return ResponseEntity.ok(list);
    }
}
