package com.mcmenu.app.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mcmenu.app.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MealDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MealDTO.class);
        MealDTO mealDTO1 = new MealDTO();
        mealDTO1.setId(1L);
        MealDTO mealDTO2 = new MealDTO();
        assertThat(mealDTO1).isNotEqualTo(mealDTO2);
        mealDTO2.setId(mealDTO1.getId());
        assertThat(mealDTO1).isEqualTo(mealDTO2);
        mealDTO2.setId(2L);
        assertThat(mealDTO1).isNotEqualTo(mealDTO2);
        mealDTO1.setId(null);
        assertThat(mealDTO1).isNotEqualTo(mealDTO2);
    }
}
