import { IProduct } from 'app/shared/model/product.model';
import { IOrders } from 'app/shared/model/orders.model';

export interface IOrderProduct {
  id?: number;
  quantity?: number | null;
  unitPrice?: number | null;
  product?: IProduct | null;
  orders?: IOrders | null;
}

export const defaultValue: Readonly<IOrderProduct> = {};
