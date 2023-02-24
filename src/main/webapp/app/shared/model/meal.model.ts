import { IProduct } from 'app/shared/model/product.model';
import { ICategory } from 'app/shared/model/category.model';

export interface IMeal {
  id?: number;
  name?: string;
  imageUrl?: string;
  description?: string | null;
  products?: IProduct[] | null;
  categories?: ICategory[] | null;
}

export const defaultValue: Readonly<IMeal> = {};
