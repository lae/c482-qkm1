package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

        Platform.runLater(() -> System.out.println("Searched Part: " + searchInput));
    }

    @FXML
    public void onActionAddPart(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        loadView(stage, "/view/EditPartView.fxml");
    }

    @FXML
    public void onActionModifyPart(ActionEvent actionEvent) {
    }

    @FXML
    public void onActionDeletePart(ActionEvent actionEvent) {
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
