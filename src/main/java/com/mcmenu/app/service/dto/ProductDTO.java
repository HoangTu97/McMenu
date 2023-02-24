package com.mcmenu.app.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mcmenu.app.domain.Product} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String imageUrl;

    @Size(max = 5000)
    private String description;

    private Boolean isLimitedTimeOnly;

    private Long relatedProductId;

    @Min(value = 0)
    private Integer displayOrder;

    private String label;

    private String abbrLabel;

    private Boolean isDefault;

    private Set<IngredientsDTO> ingredients = new HashSet<>();

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

    public Boolean getIsLimitedTimeOnly() {
        return isLimitedTimeOnly;
    }

    public void setIsLimitedTimeOnly(Boolean isLimitedTimeOnly) {
        this.isLimitedTimeOnly = isLimitedTimeOnly;
    }

    public Long getRelatedProductId() {
        return relatedProductId;
    }

    public void setRelatedProductId(Long relatedProductId) {
        this.relatedProductId = relatedProductId;
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Set<IngredientsDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientsDTO> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProductDTO)) {
            return false;
        }

        ProductDTO productDTO = (ProductDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, productDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", description='" + getDescription() + "'" +
            ", isLimitedTimeOnly='" + getIsLimitedTimeOnly() + "'" +
            ", relatedProductId=" + getRelatedProductId() +
            ", displayOrder=" + getDisplayOrder() +
            ", label='" + getLabel() + "'" +
            ", abbrLabel='" + getAbbrLabel() + "'" +
            ", isDefault='" + getIsDefault() + "'" +
            ", ingredients=" + getIngredients() +
            "}";
    }
}
