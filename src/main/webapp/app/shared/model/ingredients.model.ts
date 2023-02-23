import { IProduct } from 'app/shared/model/product.model';

export interface IIngredients {
  id?: number;
  name?: string;
  imageUrl?: string;
  insideIngredients?: string | null;
  insideContains?: string | null;
  mayContains?: string | null;
  products?: IProduct[] | null;
}

export const defaultValue: Readonly<IIngredients> = {};
