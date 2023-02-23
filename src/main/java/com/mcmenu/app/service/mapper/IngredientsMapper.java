package com.mcmenu.app.service.mapper;

import com.mcmenu.app.domain.Ingredients;
import com.mcmenu.app.service.dto.IngredientsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Ingredients} and its DTO {@link IngredientsDTO}.
 */
@Mapper(componentModel = "spring")
public interface IngredientsMapper extends EntityMapper<IngredientsDTO, Ingredients> {}
