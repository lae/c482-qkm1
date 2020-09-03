package ims;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml")));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory Management System");
        primaryStage.sizeToScene();
        primaryStage.show();
        /* Ensures that the main screen can't be resized to smaller than necessary. */
        primaryStage.setMinHeight(primaryStage.getHeight());
        primaryStage.setMinWidth(primaryStage.getWidth());
    }
}
