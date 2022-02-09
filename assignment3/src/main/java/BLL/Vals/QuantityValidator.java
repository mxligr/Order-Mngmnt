package BLL.Vals;

import Model.Orders;
/**
 *
 * @author Grama Malina Bianca
 * @since 13 May, 2021
 *
 *	Validates the quantity of an order
 */
public class QuantityValidator implements Val<Orders> {

    private boolean isValid(Orders orders) {
        return orders.getQuantity() > 0;
    }

    @Override
    public void validate(Orders orders) {
        if(!isValid(orders)) {
            throw new IllegalArgumentException("Quantity is not valid! Please input a positive integer bigger than 0.");
        }
    }
}
