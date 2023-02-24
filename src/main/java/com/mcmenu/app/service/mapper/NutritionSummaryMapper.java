package com.mcmenu.app.service.mapper;

import com.mcmenu.app.domain.NutritionSummary;
import com.mcmenu.app.domain.Product;
import com.mcmenu.app.service.dto.NutritionSummaryDTO;
import com.mcmenu.app.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link NutritionSummary} and its DTO {@link NutritionSummaryDTO}.
 */
@Mapper(componentModel = "spring")
public interface NutritionSummaryMapper extends EntityMapper<NutritionSummaryDTO, NutritionSummary> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    NutritionSummaryDTO toDto(NutritionSummary s);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);
}
