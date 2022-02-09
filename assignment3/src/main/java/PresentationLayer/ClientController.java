package PresentationLayer;

import DataAccess.ConnectionFactory;
import Model.Client;
import BLL.ClientBLL;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Class that controls the window for client operations: add new client, edit client, delete client, view all clients in a table
 */
public class ClientController {
    @FXML
    private Button exitButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TableView<Client> tableClients;
    @FXML
    private TableColumn<Client, Integer> idClientColumn;
    @FXML
    private TableColumn<Client, String> nameColumn;
    @FXML
    private TableColumn<Client, String> emailColumn;
    @FXML
    private TableColumn<Client, String> phoneNumberColumn;
    @FXML
    private TextField searchTextField;

    int index = -1;
    ObservableList<Client> listM;

    /**
     *
     * Method that gets the values from the table columns and writes them in the textFields when the table is clicked
     */
    @FXML
    private void getSelectedValues(MouseEvent event) {
        index = tableClients.getSelectionModel().getSelectedIndex();
        if (index > -1) {
            idTextField.setText(String.valueOf(idClientColumn.getCellData(index)));
            nameTextField.setText(nameColumn.getCellData(index));
            emailTextField.setText(emailColumn.getCellData(index));
            phoneNumberTextField.setText(phoneNumberColumn.getCellData(index));
        }
    }

    /**
     * Method that updates the table with the records from the database
     */
    public void updateTable() {
        idClientColumn.setCellValueFactory(new PropertyValueFactory("idClient"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory("phoneNumber"));
        listM = ConnectionFactory.getDataClients();
        tableClients.setItems(listM);
    }

    /**
     * Method that clears the text fields when the C button is pressed
     */
    @FXML
    private void clearFields() {
        idTextField.setText("");
        nameTextField.setText("");
        emailTextField.setText("");
        phoneNumberTextField.setText("");
    }

    /**
     * Method that refreshes the table when the "Refresh Table" button is pressed
     */
    @FXML
    private void refreshTable() {
        updateTable();
    }

    /**
     * Method that Adds a client with the data written in the TextFields when the "Add" button is pressed
     */
    @FXML
    private void Add() {
        ClientBLL cl = new ClientBLL();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String phoneNum = phoneNumberTextField.getText();
        Client client = new Client(name, email, phoneNum);
        cl.insertClient(client);
        updateTable();
        JOptionPane.showMessageDialog(null, "Client added successfully.");
    }

    /**
     * Method that Updates a client with the data written in the TextFields when the "Edit" button is pressed
     */
    @FXML
    private void Edit() {
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String phoneNb = phoneNumberTextField.getText();
        ClientBLL cl = new ClientBLL();
        Client updateClient = new Client(id, name, email, phoneNb);
        cl.updateClient(updateClient);
        updateTable();
        JOptionPane.showMessageDialog(null, "Client edited successfully.");
    }

    /**
     * Method that Deletes a client with the data written in the TextFields when the "Delete" button is pressed
     */
    @FXML
    private void Delete() {
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        String phoneNb = phoneNumberTextField.getText();
        ClientBLL cl = new ClientBLL();
        Client deleteClient = new Client(id, name, email, phoneNb);
        cl.deleteClient(deleteClient);
        updateTable();
        JOptionPane.showMessageDialog(null, "Client deleted successfully.");
    }

    /**
     * Method that Searches for a client with the idClient written in the TextField when the "Search" button is pressed
     */
    @FXML
    private void SearchClient() {
        int id = Integer.parseInt(searchTextField.getText());
        ClientBLL cl = new ClientBLL();
        Client searchedClient = cl.findClientById(id);
        if (searchedClient != null) {
            JOptionPane.showMessageDialog(null, "Searched client ID: " + searchedClient.getIdClient() + "\nName: " + searchedClient.getName() + "\nE-Mail: " + searchedClient.getEmail() + "\nPhone Number: " + searchedClient.getPhoneNumber());
        }
    }

    @FXML
    private void homeButtonAction() {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("home.fxml"));
            Stage homeStage = new Stage();
            homeStage.initStyle(StageStyle.UNDECORATED);
            homeStage.setScene(new Scene(root, 700, 500));
            homeStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    public void exitButtonAction() {
        Stage stage = (Stage) this.exitButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

}
