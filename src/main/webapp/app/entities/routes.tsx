import Category from './category';
import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';
import Ingredients from './ingredients';
import Meal from './meal';
import NutritionSummary from './nutrition-summary';
import Product from './product';
import React from 'react';
import { Route } from 'react-router-dom';

/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="category/*" element={<Category />} />
        <Route path="meal/*" element={<Meal />} />
        <Route path="product/*" element={<Product />} />
        <Route path="nutrition-summary/*" element={<NutritionSummary />} />
        <Route path="ingredients/*" element={<Ingredients />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
