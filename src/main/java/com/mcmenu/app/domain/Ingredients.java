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
 * A Ingredients.
 */
@Entity
@Table(name = "ingredients")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "ingredients")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Ingredients implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @Size(max = 5000)
    @Column(name = "inside_ingredients", length = 5000)
    private String insideIngredients;

    @Size(max = 255)
    @Column(name = "inside_contains", length = 255)
    private String insideContains;

    @Size(max = 255)
    @Column(name = "may_contains", length = 255)
    private String mayContains;

    @ManyToMany(mappedBy = "ingredients")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @org.springframework.data.annotation.Transient
    @JsonIgnoreProperties(value = { "nutritionSummaries", "ingredients", "categories", "meals" }, allowSetters = true)
    private Set<Product> products = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Ingredients id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Ingredients name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Ingredients imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getInsideIngredients() {
        return this.insideIngredients;
    }

    public Ingredients insideIngredients(String insideIngredients) {
        this.setInsideIngredients(insideIngredients);
        return this;
    }

    public void setInsideIngredients(String insideIngredients) {
        this.insideIngredients = insideIngredients;
    }

    public String getInsideContains() {
        return this.insideContains;
    }

    public Ingredients insideContains(String insideContains) {
        this.setInsideContains(insideContains);
        return this;
    }

    public void setInsideContains(String insideContains) {
        this.insideContains = insideContains;
    }

    public String getMayContains() {
        return this.mayContains;
    }

    public Ingredients mayContains(String mayContains) {
        this.setMayContains(mayContains);
        return this;
    }

    public void setMayContains(String mayContains) {
        this.mayContains = mayContains;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        if (this.products != null) {
            this.products.forEach(i -> i.removeIngredients(this));
        }
        if (products != null) {
            products.forEach(i -> i.addIngredients(this));
        }
        this.products = products;
    }

    public Ingredients products(Set<Product> products) {
        this.setProducts(products);
        return this;
    }

    public Ingredients addProduct(Product product) {
        this.products.add(product);
        product.getIngredients().add(this);
        return this;
    }

    public Ingredients removeProduct(Product product) {
        this.products.remove(product);
        product.getIngredients().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ingredients)) {
            return false;
        }
        return id != null && id.equals(((Ingredients) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Ingredients{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", insideIngredients='" + getInsideIngredients() + "'" +
            ", insideContains='" + getInsideContains() + "'" +
            ", mayContains='" + getMayContains() + "'" +
            "}";
    }
}
