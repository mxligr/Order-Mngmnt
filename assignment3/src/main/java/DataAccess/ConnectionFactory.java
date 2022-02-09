package DataAccess;

import Model.Client;
import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/ (with modifications from Grama Malina Bianca)
 * @Since: Apr 03, 2017
 * @Source: http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/
 */

public class ConnectionFactory {
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/assignment3";
    private static final String USER = "root";
    private static final String PASS = "password";

    private static ConnectionFactory singleInstance = new ConnectionFactory();

    public ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occurred while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the connection");
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the statement");
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occurred while trying to close the ResultSet");
            }
        }
    }

    /**
     *
     * @return list -> an observable list made of the records from the client table, used for updating the tableView in the GUI
     */
    public static ObservableList<Client> getDataClients() {
        Connection con = getConnection();
        ObservableList list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT idClient, name, email, phoneNumber FROM client");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add(new Client(rs.getInt("idClient"), rs.getString("name"), rs.getString("email"), rs.getString("phoneNumber")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     *
     * @return list -> an observable list made of the records from the product table, used for updating the tableView in the GUI
     */
    public static ObservableList<Product> getDataProducts() {
        Connection con = getConnection();
        ObservableList list = FXCollections.observableArrayList();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT idProduct, name, price, stock FROM product");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                list.add(new Product(rs.getInt("idProduct"), rs.getString("name"), rs.getInt("price"), rs.getInt("stock")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
