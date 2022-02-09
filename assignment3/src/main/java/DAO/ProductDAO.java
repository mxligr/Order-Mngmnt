package DAO;

import DataAccess.ConnectionFactory;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Product class for CRUD operations (insert, delete, update + findByID, findByName)
 *
 */
public class ProductDAO extends AbstractDAO<Product>{
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private final static String findStatementString = "SELECT * FROM product where idProduct = ?";
    private final static String findByNameStatementString = "SELECT * FROM product where name = ?";
    private final static String listStatementString = "SELECT * FROM product";

    /**
     *
     * @return roReturn -> an array list of Product objects made of the records from the product table
     */
    public static ArrayList<Product> returnProducts() {
        ArrayList<Product> toReturn = new ArrayList<>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement listStatement = null;
        ResultSet rs = null;

        try{
            listStatement = dbConnection.prepareStatement(listStatementString);
            rs = listStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("idProduct");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int stock = rs.getInt("stock");
                Product product = new Product(id, name, price, stock);
                toReturn.add(product);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO: returnProducts " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(listStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * Method that searches for the record with the specified ID and returns a Product object with the specific ID
     * @return roReturn -> a Product object with the fields taken from the product table
     */
    public static Product findById(int productID) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productID);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");
            int id = rs.getInt("idProduct");
            toReturn = new Product(name, price, stock);
            toReturn.setIdProduct(id);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * Method that searches for the record with the specified name and returns a Product object; used for generating the order after the name of the product is specified in the GUI
     * @return roReturn -> a Product object with the fields taken from the product table
     */
    public static Product findByName(String name) {
        Product toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findByNameStatementString);
            findStatement.setString(1, name);
            rs = findStatement.executeQuery();
            rs.next();

            String prname = rs.getString("name");
            int price = rs.getInt("price");
            int stock = rs.getInt("stock");
            int id = rs.getInt("idProduct");
            toReturn = new Product(prname, price, stock);
            toReturn.setIdProduct(id);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
