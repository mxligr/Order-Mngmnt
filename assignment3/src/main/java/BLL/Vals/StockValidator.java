package BLL.Vals;

import Model.Product;
/**
 *
 * @author Grama Malina Bianca
 * @since 13 May, 2021
 *
 *	Validates the stock of a product
 */
public class StockValidator implements Val<Product>{

    private boolean isValid(Product product) {
        return product.getStock() >= 0;
    }

    @Override
    public void validate(Product product) {
        if(!isValid(product)) {
            throw new IllegalArgumentException("Stock is not a valid stock! Please input a positive integer.");
        }
    }
}
