import category from 'app/entities/category/category.reducer';
import ingredients from 'app/entities/ingredients/ingredients.reducer';
import meal from 'app/entities/meal/meal.reducer';
import nutritionSummary from 'app/entities/nutrition-summary/nutrition-summary.reducer';
import product from 'app/entities/product/product.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  category,
  meal,
  product,
  nutritionSummary,
  ingredients,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
