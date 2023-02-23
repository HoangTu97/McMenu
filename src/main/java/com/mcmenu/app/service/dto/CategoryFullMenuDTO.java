package com.mcmenu.app.service.dto;

import java.util.Objects;

public class CategoryFullMenuDTO {

    private Long id;
    private String name;
    private String imageUrl;

    private ItemType itemType;

    public enum ItemType {
        PRODUCT,
        MEAL,
    }

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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryFullMenuDTO that = (CategoryFullMenuDTO) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(imageUrl, that.imageUrl)) return false;
        return itemType == that.itemType;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (itemType != null ? itemType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return (
            "CategoryFullMenuDTO{" +
            "id=" +
            id +
            ", name='" +
            name +
            '\'' +
            ", imageUrl='" +
            imageUrl +
            '\'' +
            ", itemType=" +
            itemType +
            '}'
        );
    }
}
