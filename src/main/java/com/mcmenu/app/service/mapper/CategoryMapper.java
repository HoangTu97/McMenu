package com.mcmenu.app.service.mapper;

import com.mcmenu.app.domain.Category;
import com.mcmenu.app.domain.Meal;
import com.mcmenu.app.domain.Product;
import com.mcmenu.app.service.dto.CategoryDTO;
import com.mcmenu.app.service.dto.MealDTO;
import com.mcmenu.app.service.dto.ProductDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {
    @Mapping(target = "products", source = "products", qualifiedByName = "productIdSet")
    @Mapping(target = "meals", source = "meals", qualifiedByName = "mealIdSet")
    CategoryDTO toDto(Category s);

    @Mapping(target = "removeProduct", ignore = true)
    @Mapping(target = "removeMeal", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("productIdSet")
    default Set<ProductDTO> toDtoProductIdSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductId).collect(Collectors.toSet());
    }

    @Named("mealId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    MealDTO toDtoMealId(Meal meal);

    @Named("mealIdSet")
    default Set<MealDTO> toDtoMealIdSet(Set<Meal> meal) {
        return meal.stream().map(this::toDtoMealId).collect(Collectors.toSet());
    }
}
