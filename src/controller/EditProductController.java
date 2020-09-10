package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import model.Part;

public class EditProductController {
    @FXML
    private Label labelViewTitle, partTablePlaceholder;
    @FXML
    private TextField inputId, inputName, inputStock, inputPrice, inputMax, inputMin;
    @FXML
    private TextField partSearch;
    @FXML
    private TableView<Part> partTableView, associatedPartTableView;
    @FXML
    private TableColumn<Part, Integer> partIdCol, partInventoryCol, associatedPartIdCol, associatedPartInventoryCol;
    @FXML
    private TableColumn<Part, String> partNameCol, associatedPartNameCol;
    @FXML
    private TableColumn<Part, Double> partPriceCol, associatedPartPriceCol;

    public void onKeySearchPart(KeyEvent keyEvent) {
    }

    public void onActionAssociatePart(ActionEvent actionEvent) {
    }

    public void onActionDeassociatePart(ActionEvent actionEvent) {
    }

    public void onActionSavePart(ActionEvent actionEvent) {
    }

    public void onActionCancel(ActionEvent actionEvent) {
    }
}
