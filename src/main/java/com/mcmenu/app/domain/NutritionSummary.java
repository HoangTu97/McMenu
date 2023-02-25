package com.mcmenu.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mcmenu.app.domain.enumeration.NutritionKey;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A NutritionSummary.
 */
@Entity
@Table(name = "nutrition_summary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class NutritionSummary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "key", nullable = false)
    private NutritionKey key;

    @Min(value = 0)
    @Column(name = "quantity_mg")
    private Integer quantityMg;

    @Min(value = 0)
    @Column(name = "percent_daily_values")
    private Integer percentDailyValues;

    @ManyToOne
    @JsonIgnoreProperties(value = { "nutritionSummaries", "ingredients", "categories", "meals" }, allowSetters = true)
    private Product product;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public NutritionSummary id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NutritionKey getKey() {
        return this.key;
    }

    public NutritionSummary key(NutritionKey key) {
        this.setKey(key);
        return this;
    }

    public void setKey(NutritionKey key) {
        this.key = key;
    }

    public Integer getQuantityMg() {
        return this.quantityMg;
    }

    public NutritionSummary quantityMg(Integer quantityMg) {
        this.setQuantityMg(quantityMg);
        return this;
    }

    public void setQuantityMg(Integer quantityMg) {
        this.quantityMg = quantityMg;
    }

    public Integer getPercentDailyValues() {
        return this.percentDailyValues;
    }

    public NutritionSummary percentDailyValues(Integer percentDailyValues) {
        this.setPercentDailyValues(percentDailyValues);
        return this;
    }

    public void setPercentDailyValues(Integer percentDailyValues) {
        this.percentDailyValues = percentDailyValues;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public NutritionSummary product(Product product) {
        this.setProduct(product);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutritionSummary)) {
            return false;
        }
        return id != null && id.equals(((NutritionSummary) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "NutritionSummary{" +
            "id=" + getId() +
            ", key='" + getKey() + "'" +
            ", quantityMg=" + getQuantityMg() +
            ", percentDailyValues=" + getPercentDailyValues() +
            "}";
    }
}
