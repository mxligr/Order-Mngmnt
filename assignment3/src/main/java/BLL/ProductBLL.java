package BLL;

import BLL.Vals.*;
import DAO.ProductDAO;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Class that calls the Product queries found in AbstractDAO and ProductDAO and implements the validators for the price and stock of the products
 */

public class ProductBLL {
    private final List<Val<Product>> validators;

    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new PriceValidator());
        validators.add(new StockValidator());
    }

    public ArrayList<Product> listProducts() throws Exception {
        ArrayList<Product> arr = ProductDAO.returnProducts();
        if(arr == null) {
            throw new Exception("No products in the table!");
        }
        return arr;
    }

    public Product findProductByName(String name) {
        Product st = ProductDAO.findByName(name);
        if (st == null) {
            JOptionPane.showMessageDialog(null,"The product with name " + name + "is not in the database.");
            throw new NoSuchElementException("The product with name =" + name + " was not found!");
        }
        return st;
    }

    public Product findProductById(int id) {
        Product st = ProductDAO.findById(id);
        if (st == null) {
            JOptionPane.showMessageDialog(null,"The client with ID " + id + "is not in the database.");
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }

    public int insertProduct(Product product) {
        for (Val<Product> v : validators) {
            v.validate(product);
        }
        return ProductDAO.insert(product);
    }

    public int updateProduct(Product product) {
        return ProductDAO.update(product);
    }

    public void deleteProduct(Product product) {
        ProductDAO.delete(product);
    }
}
