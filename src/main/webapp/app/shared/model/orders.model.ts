import { IPayment } from 'app/shared/model/payment.model';
import { IOrderProduct } from 'app/shared/model/order-product.model';
import { IUser } from 'app/shared/model/user.model';

export interface IOrders {
  id?: number;
  orderDate?: number | null;
  pickupDate?: number | null;
  payments?: IPayment[] | null;
  orderProducts?: IOrderProduct[] | null;
  user?: IUser | null;
}

export const defaultValue: Readonly<IOrders> = {};
