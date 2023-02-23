package com.mcmenu.app.web.rest;

import com.mcmenu.app.service.IngredientsService;
import com.mcmenu.app.service.MealService;
import com.mcmenu.app.service.NutritionSummaryService;
import com.mcmenu.app.service.ProductService;
import com.mcmenu.app.service.dto.IngredientsDTO;
import com.mcmenu.app.service.dto.MealDTO;
import com.mcmenu.app.service.dto.MealDetailsDTO;
import com.mcmenu.app.service.dto.NutritionSummaryDTO;
import com.mcmenu.app.service.dto.ProductDTO;
import com.mcmenu.app.service.dto.ProductDetailsDTO;
import com.mcmenu.app.web.rest.errors.BadRequestAlertException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public")
public class PublicMealResource {

    private final Logger log = LoggerFactory.getLogger(MealResource.class);

    private static final String ENTITY_NAME = "meal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MealService mealService;
    private final ProductService productService;
    private final IngredientsService ingredientsService;
    private final NutritionSummaryService nutritionSummaryService;

    public PublicMealResource(
        MealService mealService,
        ProductService productService,
        IngredientsService ingredientsService,
        NutritionSummaryService nutritionSummaryService
    ) {
        this.mealService = mealService;
        this.productService = productService;
        this.ingredientsService = ingredientsService;
        this.nutritionSummaryService = nutritionSummaryService;
    }

    @GetMapping("/meals/{id}")
    public MealDetailsDTO getMeal(@PathVariable Long id) {
        Optional<MealDTO> optionalMealDTO = mealService.findOne(id);
        if (optionalMealDTO.isEmpty()) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        MealDTO mealDTO = optionalMealDTO.get();

        MealDetailsDTO dto = new MealDetailsDTO();
        dto.setId(mealDTO.getId());
        dto.setName(mealDTO.getName());
        dto.setImageUrl(mealDTO.getImageUrl());
        dto.setDescription(mealDTO.getDescription());

        List<ProductDTO> productDTOS = productService.findAllByMeal(id);
        List<Long> productIds = productDTOS.stream().map(ProductDTO::getId).collect(Collectors.toList());

        Map<Long, List<IngredientsDTO>> ingredientsMap = ingredientsService.findMapByProductIds(productIds);
        Map<Long, List<NutritionSummaryDTO>> nutritionSummariesMap = nutritionSummaryService.findMapByProductIds(productIds);

        dto.setProducts(
            productDTOS
                .stream()
                .map(e -> {
                    ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
                    productDetailsDTO.setId(e.getId());
                    productDetailsDTO.setName(e.getName());
                    productDetailsDTO.setImageUrl(e.getImageUrl());
                    productDetailsDTO.setDescription(e.getDescription());
                    productDetailsDTO.setLimitedTimeOnly(e.getIsLimitedTimeOnly());
                    productDetailsDTO.setDisplayOrder(e.getDisplayOrder());
                    productDetailsDTO.setLabel(e.getLabel());
                    productDetailsDTO.setAbbrLabel(e.getAbbrLabel());
                    productDetailsDTO.setDefault(e.getIsDefault());

                    productDetailsDTO.setIngredients(ingredientsMap.get(e.getId()));
                    productDetailsDTO.setNutritionSummaries(nutritionSummariesMap.get(e.getId()));

                    return productDetailsDTO;
                })
                .collect(Collectors.toList())
        );

        return dto;
    }
}
