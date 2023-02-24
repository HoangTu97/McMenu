package com.mcmenu.app.service.mapper;

import com.mcmenu.app.domain.Meal;
import com.mcmenu.app.domain.Product;
import com.mcmenu.app.service.dto.MealDTO;
import com.mcmenu.app.service.dto.ProductDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Meal} and its DTO {@link MealDTO}.
 */
@Mapper(componentModel = "spring")
public interface MealMapper extends EntityMapper<MealDTO, Meal> {
    @Mapping(target = "products", source = "products", qualifiedByName = "productIdSet")
    MealDTO toDto(Meal s);

    @Mapping(target = "removeProduct", ignore = true)
    Meal toEntity(MealDTO mealDTO);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("productIdSet")
    default Set<ProductDTO> toDtoProductIdSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductId).collect(Collectors.toSet());
    }
}
