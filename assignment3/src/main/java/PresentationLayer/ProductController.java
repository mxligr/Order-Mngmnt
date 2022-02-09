package PresentationLayer;

import BLL.ProductBLL;
import DataAccess.ConnectionFactory;
import Model.Product;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Class that controls the window for product operations: add new product, edit product, delete product, view all products in a table
 */
public class ProductController {

    @FXML
    private Button exitButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField stockTextField;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, Integer> idProductColumn;
    @FXML
    private TableColumn<Product, String> nameColumn;
    @FXML
    private TableColumn<Product, Integer> priceColumn;
    @FXML
    private TableColumn<Product, Integer> stockColumn;

    int index = -1;
    ObservableList<Product> listM;

    /**
     *
     * Method that gets the values from the table columns and writes them in the TextFields when the table is clicked
     */
    @FXML
    private void getSelectedValues(MouseEvent event) {
        index = productTable.getSelectionModel().getSelectedIndex();
        if (this.index > -1) {
            idTextField.setText(String.valueOf(idProductColumn.getCellData(index)));
            nameTextField.setText(nameColumn.getCellData(index));
            priceTextField.setText(String.valueOf(priceColumn.getCellData(index)));
            stockTextField.setText(String.valueOf(stockColumn.getCellData(index)));
        }
    }

    /**
     * Method that clears the text fields when the C button is pressed
     */
    @FXML
    private void clearFields() {
        idTextField.setText("");
        nameTextField.setText("");
        priceTextField.setText("");
        stockTextField.setText("");
    }

    /**
     * Method that updates the table with the records from the database
     */
    public void updateTable() {
        idProductColumn.setCellValueFactory(new PropertyValueFactory("idProduct"));
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        stockColumn.setCellValueFactory(new PropertyValueFactory("stock"));
        listM = ConnectionFactory.getDataProducts();
        productTable.setItems(listM);
    }

    /**
     * Method that refreshes the table when the "Refresh Table" button is pressed
     */
    @FXML
    private void refreshTable() {
        updateTable();
    }

    /**
     * Method that Adds a product with the data written in the TextFields when the "Add" button is pressed
     */
    @FXML
    private void addProduct() {
        ProductBLL pr = new ProductBLL();
        String name = nameTextField.getText();
        int price = Integer.parseInt(priceTextField.getText());
        int stock = Integer.parseInt(stockTextField.getText());
        Product product = new Product(name, price, stock);
        pr.insertProduct(product);
        updateTable();
        JOptionPane.showMessageDialog(null, "Product added successfully.");
    }

    /**
     * Method that Updates a product with the data written in the TextFields when the "Edit" button is pressed
     */
    @FXML
    private void editProduct() {
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        int price = Integer.parseInt(priceTextField.getText());
        int stock = Integer.parseInt(stockTextField.getText());
        ProductBLL pr = new ProductBLL();
        Product updateProduct = new Product(id, name, price, stock);
        pr.updateProduct(updateProduct);
        updateTable();
        JOptionPane.showMessageDialog(null, "Product edited successfully.");
    }

    /**
     * Method that Deletes a product with the data written in the TextFields when the "Delete" button is pressed
     */
    @FXML
    private void deleteProduct() {
        int id = Integer.parseInt(idTextField.getText());
        String name = nameTextField.getText();
        int price = Integer.parseInt(priceTextField.getText());
        int stock = Integer.parseInt(stockTextField.getText());
        ProductBLL pr = new ProductBLL();
        Product deleteProduct = new Product(id, name, price, stock);
        pr.deleteProduct(deleteProduct);
        updateTable();
        JOptionPane.showMessageDialog(null, "Product deleted successfully.");
    }

    /**
     * Method that Searches for a product with the idProduct written in the TextField when the "Search" button is pressed
     */
    @FXML
    private void searchProduct() {
        int id = Integer.parseInt(searchTextField.getText());
        ProductBLL pr = new ProductBLL();
        Product searchedProduct = pr.findProductById(id);
        if (searchedProduct != null) {
            JOptionPane.showMessageDialog(null, "Searched product ID: " + searchedProduct.getIdProduct() + "\nName: " + searchedProduct.getName() + "\nPrice: " + searchedProduct.getPrice() + "\nStock: " + searchedProduct.getStock());
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
        Stage stage = (Stage)this.exitButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }

}