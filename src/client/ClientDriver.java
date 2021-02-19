package client;

import gui.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class ClientDriver extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../gui/sample.fxml"));
        Application app = Controller.getInstance();
        app.start(primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
