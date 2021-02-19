package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import logic.MathExpressionParser;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Application app = Controller.getInstance();
        app.start(primaryStage);
    }


    public static void main(String[] args) {
//        String s = new String("1+2*(3/(4+5))*6-3");
//        System.out.println(MathExpressionParser.calculatePostfix(MathExpressionParser.infixToPostfix(s)));
        launch(args);
    }
}
