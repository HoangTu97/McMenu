package com.mcmenu.app.service.dto;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsDTO {

    private Long id;

    private String name;

    private String imageUrl;

    private String description;

    private List<ProductDetailsDTO> products = new ArrayList<>();

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

    public List<ProductDetailsDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDetailsDTO> products) {
        this.products = products;
    }
}
