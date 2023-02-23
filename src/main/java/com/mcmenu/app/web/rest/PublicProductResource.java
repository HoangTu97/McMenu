package com.mcmenu.app.web.rest;

import com.mcmenu.app.service.IngredientsService;
import com.mcmenu.app.service.NutritionSummaryService;
import com.mcmenu.app.service.ProductService;
import com.mcmenu.app.service.dto.ProductDTO;
import com.mcmenu.app.service.dto.ProductDetailsDTO;
import com.mcmenu.app.web.rest.errors.BadRequestAlertException;
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
public class PublicProductResource {

    private final Logger log = LoggerFactory.getLogger(ProductResource.class);

    private static final String ENTITY_NAME = "product";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductService productService;

    private final IngredientsService ingredientsService;
    private final NutritionSummaryService nutritionSummaryService;

    public PublicProductResource(
        ProductService productService,
        IngredientsService ingredientsService,
        NutritionSummaryService nutritionSummaryService
    ) {
        this.productService = productService;
        this.ingredientsService = ingredientsService;
        this.nutritionSummaryService = nutritionSummaryService;
    }

    @GetMapping("/products/{id}")
    public ProductDetailsDTO getProductIngredients(@PathVariable Long id) {
        Optional<ProductDTO> optionalProductDTO = productService.findOne(id);
        if (optionalProductDTO.isEmpty()) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ProductDTO productDTO = optionalProductDTO.get();

        ProductDetailsDTO dto = new ProductDetailsDTO();
        dto.setId(productDTO.getId());
        dto.setName(productDTO.getName());
        dto.setImageUrl(productDTO.getImageUrl());
        dto.setDescription(productDTO.getDescription());
        dto.setLimitedTimeOnly(productDTO.getIsLimitedTimeOnly());
        dto.setDisplayOrder(productDTO.getDisplayOrder());
        dto.setLabel(productDTO.getLabel());
        dto.setAbbrLabel(productDTO.getAbbrLabel());
        dto.setDefault(productDTO.getIsDefault());

        dto.setIngredients(ingredientsService.findAllByProductId(id));
        dto.setNutritionSummaries(nutritionSummaryService.findAllByProductId(id));

        if (productDTO.getRelatedProductId() != null) {
            dto.setRelatedItems(
                productService
                    .findAllByRelatedProductId(productDTO.getRelatedProductId())
                    .stream()
                    .filter(e -> !dto.getId().equals(e.getId()))
                    .collect(Collectors.toList())
            );
        }

        return dto;
    }
}
