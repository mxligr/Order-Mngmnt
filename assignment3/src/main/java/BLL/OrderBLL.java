package BLL;

import BLL.Vals.QuantityValidator;
import BLL.Vals.Val;
import DAO.OrderDAO;
import DAO.ProductDAO;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Class that calls the Order queries found in AbstractDAO and OrderDAO and implements the validators for the quantity of the order
 */

public class OrderBLL {
    private final List<Val<Orders>> validators;

    public OrderBLL() {
        validators = new ArrayList<>();
        validators.add(new QuantityValidator());
    }

    public Orders findOrderById(int id) {
        Orders st = OrderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The order with id =" + id + " was not found!");
        }
        return st;
    }

    public void createOrder(Orders orders) {
        for (Val<Orders> v : validators) {
            v.validate(orders);
        }
        int productID = orders.getIdProduct();
        ProductBLL pr = new ProductBLL();
        Product prod = pr.findProductById(productID);
        if (prod.getStock() >= orders.getQuantity()) {
            prod.setStock(prod.getStock() - orders.getQuantity());
            ProductDAO.update(prod);
            OrderDAO.insert(orders);
            JOptionPane.showMessageDialog(null, "Order placed successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "Not enough products in stock.");
        }
    }

    public int updateOrder(Orders orders) {
        return OrderDAO.update(orders);
    }

    public void deleteOrder(Orders orders) {
        OrderDAO.delete(orders);
    }
}
