import { IProduct } from 'app/shared/model/product.model';
import { IMeal } from 'app/shared/model/meal.model';

export interface ICategory {
  id?: number;
  name?: string;
  imageUrl?: string;
  products?: IProduct[] | null;
  meals?: IMeal[] | null;
}

export const defaultValue: Readonly<ICategory> = {};
