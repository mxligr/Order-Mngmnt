package DAO;

import DataAccess.ConnectionFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Abstract class for CRUD operations (insert, delete, update)
 * @param <T>
 */

public class AbstractDAO<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     *
     * @param object INSERT -> inserts a new entry into the database in the specified table
     * @return insertedID -> the id that was generated after the specific object was added to the table
     * @throws SQLException if the query is incorrect, or if the query couldn't be executed
     * @throws Exception if the fields of the object couldn't be accessed or appended to the query string
     */
    public static int insert(Object object) {
        Connection dbConnection = ConnectionFactory.getConnection();
        String tableName = object.getClass().getSimpleName();
        StringBuilder insertStatementString = new StringBuilder();
        StringBuilder values = new StringBuilder();
        insertStatementString.append("INSERT INTO assignment3.").append(tableName).append(" (");
        Field[] allFields = object.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < allFields.length - 1; i++) {
                allFields[i].setAccessible(true);
                insertStatementString.append(allFields[i].getName());
                insertStatementString.append(", ");
                Object value = allFields[i].get(object);
                String fieldType = allFields[i].getType().getSimpleName();
                if (fieldType.equals("String"))
                    values.append("\"").append(value).append("\"");
                else
                    values.append(value);
                values.append(", ");
            }
            int lastFieldIndex = allFields.length - 1;
            allFields[lastFieldIndex].setAccessible(true);
            insertStatementString.append(allFields[lastFieldIndex].getName());
            Object value = allFields[lastFieldIndex].get(object);
            String fieldType = allFields[lastFieldIndex].getType().getSimpleName();
            if (fieldType.equals("String"))
                values.append("\"").append(value).append("\"");
            else
                values.append(value);
            insertStatementString.append(") values (").append(values).append(" )");
            System.out.println(insertStatementString);
        } catch (Exception e) {
            System.out.println("Error at inserting");
        }

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(String.valueOf(insertStatementString), Statement.RETURN_GENERATED_KEYS);
            insertStatement.executeUpdate();
            System.out.println("Record inserted successfully!");

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     *
     * @param object UPDATE -> updates an existing entry into the database in the specified table
     * @return updatedID -> the id of the record that was modified in the table
     * @throws SQLException if the query is incorrect, or if the query couldn't be executed
     * @throws Exception if the fields of the object couldn't be accessed or appended to the query string
     */
    public static int update(Object object) {
        Connection dbConnection = ConnectionFactory.getConnection();
        String tableName = object.getClass().getSimpleName();
        StringBuilder updateStatementString = new StringBuilder();
        updateStatementString.append("UPDATE assignment3.").append(tableName).append(" SET ");
        Field[] allFields = object.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < allFields.length - 1; i++) {
                allFields[i].setAccessible(true);
                updateStatementString.append(allFields[i].getName());
                updateStatementString.append(" = ");
                Object value = allFields[i].get(object);
                String fieldType = allFields[i].getType().getSimpleName();
                if (fieldType.equals("String"))
                    updateStatementString.append("\"").append(value).append("\"");
                else
                    updateStatementString.append(value);
                updateStatementString.append(", ");
            }
        } catch (Exception e) {
            System.out.println("Error at updating.");
        }
        int lastFieldIndex = allFields.length - 1;
        allFields[lastFieldIndex].setAccessible(true);
        updateStatementString.append(allFields[lastFieldIndex].getName());
        updateStatementString.append(" = ");
        try {
            Object value = allFields[lastFieldIndex].get(object);
            String fieldType = allFields[lastFieldIndex].getType().getSimpleName();
            if (fieldType.equals("String"))
                updateStatementString.append("\"").append(value).append("\"");
            else
                updateStatementString.append(value);
        } catch (Exception e) {
            System.out.println("Error at updating.");
        }

        updateStatementString.append(" WHERE ");
        Field firstField = allFields[0];
        firstField.setAccessible(true);
        String fieldName = firstField.getName();

        updateStatementString.append(fieldName).append(" = ");

        try {
            Object value = firstField.get(object);
            updateStatementString.append(value);
        } catch (Exception e) {
            System.out.println("Error at getting id value");
        }
        PreparedStatement updateStatement = null;
        int updatedId = -1;
        try {
            updateStatement = dbConnection.prepareStatement(String.valueOf(updateStatementString), Statement.RETURN_GENERATED_KEYS);
            updateStatement.executeUpdate();
            System.out.println("Record updated successfully!");

            ResultSet rs = updateStatement.getGeneratedKeys();
            if (rs.next()) {
                updatedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }

        return updatedId;
    }

    /**
     *
     * @param object DELETE -> deletes an existing entry from the specified table
     * @throws SQLException if the query is incorrect, or if the query couldn't be executed
     * @throws Exception if the id field of the object couldn't be accessed or appended to the query string
     */
    public static void delete(Object object){
        Connection dbConnection = ConnectionFactory.getConnection();
        String tableName = object.getClass().getSimpleName();
        StringBuilder deleteStatementString = new StringBuilder();
        deleteStatementString.append("DELETE FROM assignment3.").append(tableName).append(" WHERE ");
        Field[] allFields = object.getClass().getDeclaredFields();
        Field firstField = allFields[0];
        firstField.setAccessible(true);
        String fieldName = firstField.getName();
        deleteStatementString.append(fieldName).append(" = ");
        try {
            Object value = firstField.get(object);
            deleteStatementString.append(value);
        } catch (Exception e) {
            System.out.println("Error at getting id value");
        }
        System.out.println(deleteStatementString);
        PreparedStatement deleteStatement = null;
        try {
            deleteStatement = dbConnection.prepareStatement(String.valueOf(deleteStatementString), Statement.RETURN_GENERATED_KEYS);
            deleteStatement.executeUpdate();
            System.out.println("Record deleted successfully!");
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "AbstractDAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
    }

}
