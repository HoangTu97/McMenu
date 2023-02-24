package com.mcmenu.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "product")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotNull
    @Size(max = 255)
    @Column(name = "image_url", length = 255, nullable = false)
    private String imageUrl;

    @Size(max = 5000)
    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "is_limited_time_only")
    private Boolean isLimitedTimeOnly;

    @Column(name = "related_product_id")
    private Long relatedProductId;

    @Min(value = 0)
    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "label")
    private String label;

    @Column(name = "abbr_label")
    private String abbrLabel;

    @Column(name = "is_default")
    private Boolean isDefault;

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "product" }, allowSetters = true)
    private Set<NutritionSummary> nutritionSummaries = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_product__ingredients",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "products" }, allowSetters = true)
    private Set<Ingredients> ingredients = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "products", "meals" }, allowSetters = true)
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "products", "categories" }, allowSetters = true)
    private Set<Meal> meals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Product imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return this.description;
    }

    public Product description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsLimitedTimeOnly() {
        return this.isLimitedTimeOnly;
    }

    public Product isLimitedTimeOnly(Boolean isLimitedTimeOnly) {
        this.setIsLimitedTimeOnly(isLimitedTimeOnly);
        return this;
    }

    public void setIsLimitedTimeOnly(Boolean isLimitedTimeOnly) {
        this.isLimitedTimeOnly = isLimitedTimeOnly;
    }

    public Long getRelatedProductId() {
        return this.relatedProductId;
    }

    public Product relatedProductId(Long relatedProductId) {
        this.setRelatedProductId(relatedProductId);
        return this;
    }

    public void setRelatedProductId(Long relatedProductId) {
        this.relatedProductId = relatedProductId;
    }

    public Integer getDisplayOrder() {
        return this.displayOrder;
    }

    public Product displayOrder(Integer displayOrder) {
        this.setDisplayOrder(displayOrder);
        return this;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getLabel() {
        return this.label;
    }

    public Product label(String label) {
        this.setLabel(label);
        return this;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAbbrLabel() {
        return this.abbrLabel;
    }

    public Product abbrLabel(String abbrLabel) {
        this.setAbbrLabel(abbrLabel);
        return this;
    }

    public void setAbbrLabel(String abbrLabel) {
        this.abbrLabel = abbrLabel;
    }

    public Boolean getIsDefault() {
        return this.isDefault;
    }

    public Product isDefault(Boolean isDefault) {
        this.setIsDefault(isDefault);
        return this;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Set<NutritionSummary> getNutritionSummaries() {
        return this.nutritionSummaries;
    }

    public void setNutritionSummaries(Set<NutritionSummary> nutritionSummaries) {
        if (this.nutritionSummaries != null) {
            this.nutritionSummaries.forEach(i -> i.setProduct(null));
        }
        if (nutritionSummaries != null) {
            nutritionSummaries.forEach(i -> i.setProduct(this));
        }
        this.nutritionSummaries = nutritionSummaries;
    }

    public Product nutritionSummaries(Set<NutritionSummary> nutritionSummaries) {
        this.setNutritionSummaries(nutritionSummaries);
        return this;
    }

    public Product addNutritionSummary(NutritionSummary nutritionSummary) {
        this.nutritionSummaries.add(nutritionSummary);
        nutritionSummary.setProduct(this);
        return this;
    }

    public Product removeNutritionSummary(NutritionSummary nutritionSummary) {
        this.nutritionSummaries.remove(nutritionSummary);
        nutritionSummary.setProduct(null);
        return this;
    }

    public Set<Ingredients> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public Product ingredients(Set<Ingredients> ingredients) {
        this.setIngredients(ingredients);
        return this;
    }

    public Product addIngredients(Ingredients ingredients) {
        this.ingredients.add(ingredients);
        ingredients.getProducts().add(this);
        return this;
    }

    public Product removeIngredients(Ingredients ingredients) {
        this.ingredients.remove(ingredients);
        ingredients.getProducts().remove(this);
        return this;
    }

    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        if (this.categories != null) {
            this.categories.forEach(i -> i.removeProduct(this));
        }
        if (categories != null) {
            categories.forEach(i -> i.addProduct(this));
        }
        this.categories = categories;
    }

    public Product categories(Set<Category> categories) {
        this.setCategories(categories);
        return this;
    }

    public Product addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
        return this;
    }

    public Product removeCategory(Category category) {
        this.categories.remove(category);
        category.getProducts().remove(this);
        return this;
    }

    public Set<Meal> getMeals() {
        return this.meals;
    }

    public void setMeals(Set<Meal> meals) {
        if (this.meals != null) {
            this.meals.forEach(i -> i.removeProduct(this));
        }
        if (meals != null) {
            meals.forEach(i -> i.addProduct(this));
        }
        this.meals = meals;
    }

    public Product meals(Set<Meal> meals) {
        this.setMeals(meals);
        return this;
    }

    public Product addMeal(Meal meal) {
        this.meals.add(meal);
        meal.getProducts().add(this);
        return this;
    }

    public Product removeMeal(Meal meal) {
        this.meals.remove(meal);
        meal.getProducts().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Product{" +
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
            "}";
    }
}
