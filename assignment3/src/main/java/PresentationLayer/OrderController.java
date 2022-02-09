package PresentationLayer;

import BLL.ClientBLL;
import BLL.OrderBLL;
import BLL.ProductBLL;
import Model.Client;
import Model.Orders;
import Model.Product;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Class that controls the window for placing an order: the user is able to select an existing product, select an existing client, and insert a desired quantity for the product to create a valid order
 */
public class OrderController {
    @FXML
    private Button exitButton;
    @FXML
    private ChoiceBox<String> productChoiceBox;
    @FXML
    private ChoiceBox<String> clientChoiceBox;
    @FXML
    private TextField QuantityTextField;
    @FXML
    private Text priceText;
    @FXML
    private Button openBillButton;

    ObservableList<String> clientsList;
    ObservableList<String> productsList;
    Client client;
    Product product;
    int total;

    /**
     * Method that uses the listClients method in the ClientBLL and gets the names of the clients to use them in the ChoiceBox
     * @throws Exception
     */
    private void getClientsList() throws Exception {
        ClientBLL cl = new ClientBLL();
        ArrayList<Client> clients = cl.listClients();
        ArrayList<String> names = new ArrayList<>();
        for (Client client : clients) {
             names.add(client.getName());
        }
        clientsList = FXCollections.observableArrayList(names);
    }

    /**
     * Method that uses the listProducts method in the ProductBLL and gets the names of the products to use them in the ChoiceBox
     * @throws Exception
     */
    private void getProductsList() throws Exception {
        ProductBLL pr = new ProductBLL();
        ArrayList<Product> products = pr.listProducts();
        ArrayList<String> names = new ArrayList<>();
        for (Product product : products) {
            names.add(product.getName());
        }
        productsList = FXCollections.observableArrayList(names);
    }

    /**
     * Method that sets the names in the ChoiceBoxes
     * @throws Exception
     */
    @FXML
    private void initialize() throws Exception {
        getClientsList();
        getProductsList();
        clientChoiceBox.setItems(clientsList);
        productChoiceBox.setItems(productsList);
    }

    /**
     * Method that gets the names of the client and product chosen, searches for them in the ArrayLists and forms the Orders Object; also computes the total for the order; all of this happens when the "Checkout" button is pressed
     * @return orders -> retruns the Orders Object
     */
    @FXML
    private Orders checkout() {
        String clientName = clientChoiceBox.getSelectionModel().getSelectedItem();
        String productName = productChoiceBox.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(QuantityTextField.getText());
        ClientBLL cl = new ClientBLL();
        client = cl.findClientByName(clientName);
        ProductBLL pr = new ProductBLL();
        product = pr.findProductByName(productName);
        Orders orders = new Orders(client.getIdClient(), product.getIdProduct(), quantity);
        total = (product.getPrice() * orders.getQuantity());
        priceText.setText(String.valueOf(total));
        return orders;
    }

    /**
     * Method that adds the order to the database and generates the Bill as a .pdf text, when the "Place Order" button is clicked
     * @throws DocumentException
     * @throws FileNotFoundException
     */
    @FXML
    private void placeOrder() throws DocumentException, FileNotFoundException {
        Orders order = checkout();
        OrderBLL or = new OrderBLL();
        or.createOrder(order);
        openBillButton.setVisible(true);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("OrderBill.pdf"));

        document.open();
        Font titleFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font font = FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK);

        Chunk chunk1 = new Chunk("Order Bill\n", titleFont);
        Paragraph para1 = new Paragraph();
        para1.add(chunk1);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Chunk chunk2 = new Chunk("Date and Time of the order: " + dtf.format(now), font);
        Paragraph para2 = new Paragraph();
        para2.add(chunk2);

        Chunk chunk3 = new Chunk("Name of the product ordered: " + product.getName(), font);
        Paragraph para3 = new Paragraph();
        para3.add(chunk3);

        Chunk chunk5 = new Chunk("Name of the client that placed the order: " + client.getName(), font);
        Paragraph para5 = new Paragraph();
        para5.add(chunk5);

        Chunk chunk6 = new Chunk("Quantity ordered: " + order.getQuantity(), font);
        Paragraph para6 = new Paragraph();
        para6.add(chunk6);

        Chunk chunk4 = new Chunk("Total price of the order: " + total + "$", font);
        Paragraph para4 = new Paragraph();
        para4.add(chunk4);

        document.add(para1);
        document.add(para2);
        document.add(para3);
        document.add(para5);
        document.add(para6);
        document.add(para4);
        document.close();
    }

    @FXML
    private void openBill() {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File("C:\\Users\\Stela\\IdeaProjects\\OrderMng\\OrderBill.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
