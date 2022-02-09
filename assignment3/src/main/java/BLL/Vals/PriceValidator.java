package BLL.Vals;

import Model.Product;
/**
 *
 * @author Grama Malina Bianca
 * @since 13 May, 2021
 *
 *	Validates the price of a product
 */
public class PriceValidator implements Val<Product>{

    private boolean isValid(Product product) {
        return product.getPrice() >= 0;
    }

    @Override
    public void validate(Product t) {
        if(!isValid(t)) {
            throw new IllegalArgumentException("Price is not a valid price! Please input a positive integer.");
        }
    }
}
