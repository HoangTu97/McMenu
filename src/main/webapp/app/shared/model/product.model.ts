import { INutritionSummary } from 'app/shared/model/nutrition-summary.model';
import { IIngredients } from 'app/shared/model/ingredients.model';
import { ICategory } from 'app/shared/model/category.model';
import { IMeal } from 'app/shared/model/meal.model';

export interface IProduct {
  id?: number;
  name?: string;
  imageUrl?: string;
  description?: string | null;
  isLimitedTimeOnly?: boolean | null;
  relatedProductId?: number | null;
  displayOrder?: number | null;
  label?: string | null;
  abbrLabel?: string | null;
  isDefault?: boolean | null;
  nutritionSummaries?: INutritionSummary[] | null;
  ingredients?: IIngredients[] | null;
  categories?: ICategory[] | null;
  meals?: IMeal[] | null;
}

export const defaultValue: Readonly<IProduct> = {
  isLimitedTimeOnly: false,
  isDefault: false,
};
