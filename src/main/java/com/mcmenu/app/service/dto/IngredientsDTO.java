package com.mcmenu.app.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mcmenu.app.domain.Ingredients} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IngredientsDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String imageUrl;

    @Size(max = 5000)
    private String insideIngredients;

    @Size(max = 255)
    private String insideContains;

    @Size(max = 255)
    private String mayContains;

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

    public String getInsideIngredients() {
        return insideIngredients;
    }

    public void setInsideIngredients(String insideIngredients) {
        this.insideIngredients = insideIngredients;
    }

    public String getInsideContains() {
        return insideContains;
    }

    public void setInsideContains(String insideContains) {
        this.insideContains = insideContains;
    }

    public String getMayContains() {
        return mayContains;
    }

    public void setMayContains(String mayContains) {
        this.mayContains = mayContains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IngredientsDTO)) {
            return false;
        }

        IngredientsDTO ingredientsDTO = (IngredientsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ingredientsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IngredientsDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", insideIngredients='" + getInsideIngredients() + "'" +
            ", insideContains='" + getInsideContains() + "'" +
            ", mayContains='" + getMayContains() + "'" +
            "}";
    }
}
