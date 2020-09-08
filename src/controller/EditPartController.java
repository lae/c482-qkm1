package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.util.Optional;

import static ims.Main.fixAlertDisplay;
import static ims.Main.loadView;

/**
 * @author Musee Ullah
 */
public class EditPartController {
    private final String labelAltTextInHouse = "Machine ID";
    private final String labelAltTextOutsourced = "Company Name";
    private Stage stage;
    private Parent scene;
    @FXML
    private Label labelViewTitle, labelAlt;
    @FXML
    private ToggleGroup selectPartSource;
    @FXML
    private RadioButton inputSourceIn, inputSourceOut;
    @FXML
    private TextField inputId, inputName, inputStock, inputPrice, inputMax, inputMin, inputAlt;

    /**
     * Updates the view to that of an Add Part form.
     */
    public void startAdd() {
        labelViewTitle.setText("Add Part");
        labelAlt.setText(labelAltTextInHouse);
        inputSourceIn.setSelected(true);
        inputId.setText("To be calculated.");
    }

    /**
     * Updates the view to that of an Edit Part form.
     * This populates fields with information from an existing part.
     *
     * @param selectedPart the Part to edit.
     */
    public void startEdit(Part selectedPart) {
        labelViewTitle.setText("Edit Part");
        if (selectedPart instanceof InHouse) {
            labelAlt.setText(labelAltTextInHouse);
            inputSourceIn.setSelected(true);
            inputAlt.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        } else if (selectedPart instanceof Outsourced) {
            labelAlt.setText(labelAltTextOutsourced);
            inputSourceOut.setSelected(true);
            inputAlt.setText(String.valueOf(((Outsourced) selectedPart).getCompanyName()));
        }
        inputId.setText(String.valueOf(selectedPart.getId()));
        inputName.setText(selectedPart.getName());
        inputStock.setText(String.valueOf(selectedPart.getStock()));
        inputPrice.setText(String.valueOf(selectedPart.getPrice()));
        inputMax.setText(String.valueOf(selectedPart.getMax()));
        inputMin.setText(String.valueOf(selectedPart.getMin()));
    }

    /**
     * Updates UI to reflect a Part's sourcing.
     * This updates available fields in the edit dialog depending on whether Outsourced or In-House is selected.
     *
     * @param actionEvent A user input event.
     */
    @FXML
    public void onActionChangeSource(ActionEvent actionEvent) {
        if (inputSourceIn.isSelected()) {
            labelAlt.setText(labelAltTextInHouse);
        } else if (inputSourceOut.isSelected()) {
            labelAlt.setText(labelAltTextOutsourced);
        }
    }

    /**
     * Saves changes and returns to the Main Screen.
     * This confirms changes with the user about the Part they're adding or modifying and then saves those changes.
     *
     * @param actionEvent A user input event.
     * @throws IOException さあ
     */
    @FXML
    public void onActionSavePart(ActionEvent actionEvent) throws IOException {
        String confirmData = String.format(
                "Name: %s\nInventory: %s\nUnit Price: %s\nMax: %s\nMin: %s\n%s: %s", inputName.getText(),
                inputStock.getText(), inputPrice.getText(), inputMax.getText(),
                inputMin.getText(), labelAlt.getText(), inputAlt.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Label confirmLabel = new Label("Please review the following details and confirm they are correct.");
        TextArea confirmTA = new TextArea(confirmData);
        confirmTA.setEditable(false);
        confirmTA.setWrapText(true);
        confirmTA.setMaxHeight(Double.MAX_VALUE);
        GridPane.setHgrow(confirmTA, Priority.ALWAYS);
        GridPane confirmContent = new GridPane();
        confirmContent.add(confirmLabel, 0, 0);
        confirmContent.add(confirmTA, 0, 1);
        alert.getDialogPane().setContent(confirmContent);

        fixAlertDisplay(alert);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        loadView(stage, "/view/MainScreen.fxml");
    }

    /**
     * Cancels changes to return to Main Screen.
     * This checks with the user whether or not they want to discard their changes and go back.
     *
     * @param actionEvent A user input event.
     * @throws IOException さあ
     */
    @FXML
    public void onActionCancel(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Your changes will be discarded. Is this OK?");
        fixAlertDisplay(alert);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        loadView(stage, "/view/MainScreen.fxml");
    }
}
