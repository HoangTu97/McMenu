import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Meal from './meal';
import MealDetail from './meal-detail';
import MealUpdate from './meal-update';
import MealDeleteDialog from './meal-delete-dialog';

const MealRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Meal />} />
    <Route path="new" element={<MealUpdate />} />
    <Route path=":id">
      <Route index element={<MealDetail />} />
      <Route path="edit" element={<MealUpdate />} />
      <Route path="delete" element={<MealDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default MealRoutes;
