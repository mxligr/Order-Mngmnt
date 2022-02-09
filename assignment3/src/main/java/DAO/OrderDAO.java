package DAO;

import DataAccess.ConnectionFactory;
import Model.Orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Order class for CRUD operations (insert, delete, update + findByID, findByName)
 *
 */
public class OrderDAO extends AbstractDAO<Orders> {

    protected static final Logger LOGGER = Logger.getLogger(OrderDAO.class.getName());
    private final static String findStatementString = "SELECT * FROM orders where idOrder = ?";

    /**
     * Method that searches for the record with the specified ID and returns an Order object with the specific ID
     * @return roReturn -> a Order object with the fields taken from the orders table
     */
    public static Orders findById(int orderID) {
        Orders toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, orderID);
            rs = findStatement.executeQuery();
            rs.next();

            int clientID = rs.getInt("idClient");
            int productID = rs.getInt("idProduct");
            int quantity = rs.getInt("phoneNumber");
            int id = rs.getInt("idOrder");
            toReturn = new Orders(clientID, productID, quantity);
            toReturn.setIdOrder(id);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
