import { IProduct } from 'app/shared/model/product.model';
import { NutritionKey } from 'app/shared/model/enumerations/nutrition-key.model';

export interface INutritionSummary {
  id?: number;
  key?: NutritionKey;
  quantityMg?: number | null;
  percentDailyValues?: number | null;
  product?: IProduct | null;
}

export const defaultValue: Readonly<INutritionSummary> = {};
