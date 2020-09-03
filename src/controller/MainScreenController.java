package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MainScreenController {
    Stage stage;
    Parent scene;

    @FXML
    private TextField partSearch;

    @FXML
    private TextField productSearch;

    @FXML
    public void onKeySearchPart(KeyEvent event) {
        String searchInput = partSearch.getText();

        Platform.runLater(() -> System.out.println("Searched Part: " + searchInput));
    }

    @FXML
    public void onActionAddPart(ActionEvent actionEvent) {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        //scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        System.out.println("Clicked Add Part.");
    }

    @FXML
    public void onActionModifyPart(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionDeletePart(ActionEvent actionEvent) {
    }

    @FXML
    public void onKeySearchProduct(KeyEvent event) {
        String searchInput = productSearch.getText();

        Platform.runLater(() -> System.out.println("Searched Product: " + searchInput));
    }

    @FXML
    public void onActionAddProduct(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionModifyProduct(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionDeleteProduct(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionExit(ActionEvent actionEvent) {
        System.exit(0);
    }
}
