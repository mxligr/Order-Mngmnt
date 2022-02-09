package PresentationLayer;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author Grama Malina Bianca
 * @since May 13, 2021
 * Class that controls the window for the homepage: the user can pick which window he wants to see: the client window, the products window or the place order window.
 */
public class HomeController {

    @FXML
    private Button exitButton;

    @FXML
    private void clientButtonAction() {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("client.fxml"));
            Stage clientStage = new Stage();
            clientStage.initStyle(StageStyle.UNDECORATED);
            clientStage.setScene(new Scene(root, 700, 500));
            clientStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
        Stage stage = (Stage)this.exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void productButtonAction() {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("product.fxml"));
            Stage productStage = new Stage();
            productStage.initStyle(StageStyle.UNDECORATED);
            productStage.setScene(new Scene(root, 700, 500));
            productStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        Stage stage = (Stage)this.exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void createOrderAction() {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("order.fxml"));
            Stage productStage = new Stage();
            productStage.initStyle(StageStyle.UNDECORATED);
            productStage.setScene(new Scene(root, 700, 500));
            productStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        Stage stage = (Stage)this.exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void exitButtonAction() {
        Stage stage = (Stage)this.exitButton.getScene().getWindow();
        stage.close();
        Platform.exit();
    }
}
