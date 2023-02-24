package com.mcmenu.app.service.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsDTO {

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private Boolean isLimitedTimeOnly;

    private Integer displayOrder;

    private String label;

    private String abbrLabel;

    private Boolean isDefault;

    private List<IngredientsDTO> ingredients = new ArrayList<>();
    private List<NutritionSummaryDTO> nutritionSummaries = new ArrayList<>();
    private List<ProductDTO> relatedItems = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLimitedTimeOnly() {
        return isLimitedTimeOnly;
    }

    public void setLimitedTimeOnly(Boolean limitedTimeOnly) {
        isLimitedTimeOnly = limitedTimeOnly;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAbbrLabel() {
        return abbrLabel;
    }

    public void setAbbrLabel(String abbrLabel) {
        this.abbrLabel = abbrLabel;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public List<IngredientsDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientsDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public List<NutritionSummaryDTO> getNutritionSummaries() {
        return nutritionSummaries;
    }

    public void setNutritionSummaries(List<NutritionSummaryDTO> nutritionSummaries) {
        this.nutritionSummaries = nutritionSummaries;
    }

    public List<ProductDTO> getRelatedItems() {
        return relatedItems;
    }

    public void setRelatedItems(List<ProductDTO> relatedItems) {
        this.relatedItems = relatedItems;
    }
}
