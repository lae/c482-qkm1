package ims;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

import java.io.IOException;

public class Main extends Application {
    /**
     * Main entrypoint into application.
     * Populates the Inventory with a couple of Parts and Products, then starts the JavaFX application.
     *
     * @param args The command line arguments passed to the application.
     */
    public static void main(String[] args) {
        Inventory.addPart(new Outsourced(1, "Cinelli Vigorelli Shark frameset", 999, 2, 0, 5, "Cinelli"));
        Inventory.addPart(new InHouse(2, "Retrogression RG50 carbon wheelset", 795, 0, 0, 10, 0));
        Inventory.addPart(new InHouse(3, "All-City Thunderdome frameset", 750, 2, 0, 10, 0));
        Inventory.addPart(new InHouse(4, "curved-blade lugged 1 1/8\" threadless fork", 70, 1, 0, 5, 0));
        Inventory.addPart(new Outsourced(5, "FSA Omega handlebar", 45, 2, 0, 10, "Full Speed Ahead"));
        Inventory.addPart(new Outsourced(6, "Brooks B17 Narrow saddle", 130, 4, 0, 20, "Brooks"));
        Inventory.addPart(new Outsourced(7, "NOS Selle Italia Flite saddle", 225, 2, 0, 10, "Selle Italia"));

        Inventory.addProduct(new Product(1, "All-City Big Block complete bike - Midnight/Frost/Violet", 999, 0, 0, 5));
        Inventory.addProduct(new Product(2, "All-City Thunderdome complete bike - Polished Pearl", 1599, 5, 0, 20));
        Inventory.addProduct(new Product(3, "State Bicycle Co. Undefeated II complete bike - Black Prism", 979, 0, 0, 10));
        Inventory.addProduct(new Product(4, "5 Bling Streamline track gloves - black", 38, 4, 2, 10));
        Inventory.addProduct(new Product(5, "Park Tool IB-1 multi-tool", 15.95, 10, 2, 20));

        launch(args);
    }

    /**
     * Loads a new View.
     * This updates the stage to a new View with some size bounding.
     *
     * @param stage The stage to update.
     * @param view  Path to the FXML document for the new View.
     * @throws IOException さあ
     */
    public static void loadView(Stage stage, String view) throws IOException {
        Scene scene = new Scene(FXMLLoader.load(Main.class.getResource(view)));
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        /* Ensures that the main screen can't be resized to smaller than necessary. */
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    /**
     * Wrapper around Alert to fix sizing.
     * On my Linux system alert dialogs do not show properly via normal usage, so this provides a quick fix.
     * See https://github.com/javafxports/openjdk-jfx/issues/222
     *
     * @param alert The instantiated Alert object to apply changes on.
     */
    public static void fixAlertDisplay(Alert alert) {
        alert.setResizable(true);
        Platform.runLater(() -> alert.setResizable(false));
    }

    /**
     * The main entry point for this JavaFX application.
     * This is called after init and sets up the MainScreen View as our entry scene.
     *
     * @param primaryStage The primary stage for this application, onto which the application scene can be set.
     * @throws Exception Who knows.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        loadView(primaryStage, "/view/MainScreen.fxml");
        primaryStage.setTitle("Inventory Management System");
    }
}
