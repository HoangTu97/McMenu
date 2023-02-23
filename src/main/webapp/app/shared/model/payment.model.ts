import { IUser } from 'app/shared/model/user.model';
import { IOrders } from 'app/shared/model/orders.model';
import { PaymentType } from 'app/shared/model/enumerations/payment-type.model';

export interface IPayment {
  id?: number;
  paymentDate?: number | null;
  amount?: number | null;
  paymentType?: PaymentType | null;
  user?: IUser | null;
  orders?: IOrders | null;
}

export const defaultValue: Readonly<IPayment> = {};
