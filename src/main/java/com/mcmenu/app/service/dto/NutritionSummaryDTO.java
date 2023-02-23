package com.mcmenu.app.service.dto;

import com.mcmenu.app.domain.enumeration.NutritionKey;
import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.mcmenu.app.domain.NutritionSummary} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NutritionSummaryDTO implements Serializable {

    private Long id;

    @NotNull
    private NutritionKey key;

    @Min(value = 0)
    private Integer quantityMg;

    @Min(value = 0)
    private Integer percentDailyValues;

    private ProductDTO product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NutritionKey getKey() {
        return key;
    }

    public void setKey(NutritionKey key) {
        this.key = key;
    }

    public Integer getQuantityMg() {
        return quantityMg;
    }

    public void setQuantityMg(Integer quantityMg) {
        this.quantityMg = quantityMg;
    }

    public Integer getPercentDailyValues() {
        return percentDailyValues;
    }

    public void setPercentDailyValues(Integer percentDailyValues) {
        this.percentDailyValues = percentDailyValues;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutritionSummaryDTO)) {
            return false;
        }

        NutritionSummaryDTO nutritionSummaryDTO = (NutritionSummaryDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, nutritionSummaryDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NutritionSummaryDTO{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", quantityMg=" + getQuantityMg() +
            ", percentDailyValues=" + getPercentDailyValues() +
            ", product=" + getProduct() +
            "}";
    }
}
