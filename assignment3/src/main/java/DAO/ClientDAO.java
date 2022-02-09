package DAO;

import DataAccess.ConnectionFactory;
import Model.Client;

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
 * Client class for CRUD operations (insert, delete, update + findByID, findByName)
 *
 */
public class ClientDAO extends AbstractDAO<Client>{
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private final static String findStatementString = "SELECT * FROM client where idClient = ?";
    private final static String findByNameStatementString = "SELECT * FROM client where name = ?";
    private final static String listStatementString = "SELECT * FROM client";

    /**
     *
     * @return roReturn -> an array list of Client objects made of the records from the client table
     */
    public static ArrayList<Client> returnClients() {
        ArrayList<Client> toReturn = new ArrayList<>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement listStatement = null;
        ResultSet rs = null;

        try{
            listStatement = dbConnection.prepareStatement(listStatementString);
            rs = listStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("idClient");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                Client client = new Client(id, name, email, phoneNumber);
                toReturn.add(client);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO: returnClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(listStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * Method that searches for the record with the specified ID and returns a Client object with the specific ID
     * @return roReturn -> a Client object with the fields taken from the client table
     */
    public static Client findById(int clientID) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, clientID);
            rs = findStatement.executeQuery();
            rs.next();

            String name = rs.getString("name");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phoneNumber");
            int id = rs.getInt("idClient");
            toReturn = new Client(name, email, phoneNumber);
            toReturn.setIdClient(id);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }

    /**
     * Method that searches for the record with the specified name and returns a Client object; used for generating the order after the name of the client is specified in the GUI
     * @return roReturn -> a Client object with the fields taken from the client table
     */
    public static Client findByName(String name) {
        Client toReturn = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findByNameStatementString);
            findStatement.setString(1, name);
            rs = findStatement.executeQuery();
            rs.next();

            String clname = rs.getString("name");
            String email = rs.getString("email");
            String phoneNumber = rs.getString("phoneNumber");
            int id = rs.getInt("idClient");
            toReturn = new Client(clname, email, phoneNumber);
            toReturn.setIdClient(id);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return toReturn;
    }
}
