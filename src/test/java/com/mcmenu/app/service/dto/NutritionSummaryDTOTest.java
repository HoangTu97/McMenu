package com.mcmenu.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mcmenu.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class NutritionSummaryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(NutritionSummaryDTO.class);
        NutritionSummaryDTO nutritionSummaryDTO1 = new NutritionSummaryDTO();
        nutritionSummaryDTO1.setId(1L);
        NutritionSummaryDTO nutritionSummaryDTO2 = new NutritionSummaryDTO();
        assertThat(nutritionSummaryDTO1).isNotEqualTo(nutritionSummaryDTO2);
        nutritionSummaryDTO2.setId(nutritionSummaryDTO1.getId());
        assertThat(nutritionSummaryDTO1).isEqualTo(nutritionSummaryDTO2);
        nutritionSummaryDTO2.setId(2L);
        assertThat(nutritionSummaryDTO1).isNotEqualTo(nutritionSummaryDTO2);
        nutritionSummaryDTO1.setId(null);
        assertThat(nutritionSummaryDTO1).isNotEqualTo(nutritionSummaryDTO2);
    }
}
