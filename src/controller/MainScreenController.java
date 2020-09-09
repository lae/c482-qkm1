package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static ims.Main.fixAlertDisplay;
import static ims.Main.loadView;

/**
 * @author Musee Ullah
 */
public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private TextField partSearch, productSearch;

    @FXML
    private TableView<Part> partTableView;

    @FXML
    private TableColumn<Part, Integer> partIdCol, partInventoryCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private TableColumn<Product, Integer> productIdCol, productInventoryCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    /**
     * Filters the PartTable by user-provided query.
     *
     * @param event any keyboard action the user performs.
     */
    @FXML
    public void onKeySearchPart(KeyEvent event) {
        String searchInput = partSearch.getText();
        partTableView.setItems(Inventory.getFilteredParts(searchInput));
    }

    /**
     * Switches to an Add Part form.
     *
     * @param actionEvent an action a user performs.
     * @throws IOException an I/O error.
     */
    @FXML
    public void onActionAddPart(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = loadView(stage, "/view/EditPartView.fxml");
        EditPartController editCtrl = loader.getController();
        editCtrl.startAdd();
    }

    /**
     * Switches to a Modify Part form.
     *
     * @param actionEvent an action a user performs.
     * @throws IOException an I/O error.
     */
    @FXML
    public void onActionModifyPart(ActionEvent actionEvent) throws IOException {
        if (partTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "You must select a part to modify!");
            fixAlertDisplay(alert);
            alert.showAndWait();
            return;
        }

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        FXMLLoader loader = loadView(stage, "/view/EditPartView.fxml");
        EditPartController editCtrl = loader.getController();
        editCtrl.startEdit(partTableView.getSelectionModel().getSelectedItem());
    }

    /**
     * Deletes a Part that the user has selected.
     *
     * @param actionEvent an action a user performs.
     */
    @FXML
    public void onActionDeletePart(ActionEvent actionEvent) {
        if (partTableView.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "You must select a part to delete!");
            fixAlertDisplay(alert);
            alert.showAndWait();
            return;
        }

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete the following part?\n\n" + selectedPart.getName());
        fixAlertDisplay(alert);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Inventory.deletePart(selectedPart);
        }
    }

    /**
     * Filters the ProductTable by user-provided query.
     *
     * @param event any keyboard action the user performs.
     */
    @FXML
    public void onKeySearchProduct(KeyEvent event) {
        String searchInput = productSearch.getText();
        productTableView.setItems(Inventory.getFilteredProducts(searchInput));
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

    /**
     * Initializes the MainScreen controller.
     * Initializes the TableViews with initial contents and TableColumn/model associations.
     *
     * @param url            The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        partTableView.setItems(Inventory.getAllParts());
        productTableView.setItems(Inventory.getAllProducts());
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
