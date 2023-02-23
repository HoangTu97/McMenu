package com.mcmenu.app.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NutritionSummaryMapperTest {

    private NutritionSummaryMapper nutritionSummaryMapper;

    @BeforeEach
    public void setUp() {
        nutritionSummaryMapper = new NutritionSummaryMapperImpl();
    }
}
