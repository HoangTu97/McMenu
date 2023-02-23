package com.mcmenu.app.service.mapper;

import com.mcmenu.app.domain.Ingredients;
import com.mcmenu.app.domain.Product;
import com.mcmenu.app.service.dto.IngredientsDTO;
import com.mcmenu.app.service.dto.ProductDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Product} and its DTO {@link ProductDTO}.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    @Mapping(target = "ingredients", source = "ingredients", qualifiedByName = "ingredientsIdSet")
    ProductDTO toDto(Product s);

    @Mapping(target = "removeIngredients", ignore = true)
    Product toEntity(ProductDTO productDTO);

    @Named("ingredientsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    IngredientsDTO toDtoIngredientsId(Ingredients ingredients);

    @Named("ingredientsIdSet")
    default Set<IngredientsDTO> toDtoIngredientsIdSet(Set<Ingredients> ingredients) {
        return ingredients.stream().map(this::toDtoIngredientsId).collect(Collectors.toSet());
    }
}
