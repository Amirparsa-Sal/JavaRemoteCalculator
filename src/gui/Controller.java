package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Controller extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinHeight(650);
        stage.setMinWidth(700);

        ResponsiveGrid grid = new ResponsiveGrid(4,7);
        grid.setPrefSize(700, 650); // Default width and height
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        Label label = new Label("2 + 5 * 6");
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setEffect(new Lighting());
        label.setStyle("-fx-background-color: #110d4f; -fx-text-fill: aqua; -fx-font-size: 50; -fx-font-family: Arial;");
        grid.add(label,0,0,4,1);

        Label label2 = new Label("32");
        label2.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label2.setEffect(new Lighting());
        label2.setStyle("-fx-background-color: #110d4f; -fx-text-fill: aqua; -fx-font-size: 50; -fx-font-family: Arial;");
        grid.add(label2,0,1,4,1);

        grid.addCuteButton("(","#002b00", "aqua","darkgreen","aqua",0,2);
        grid.addCuteButton(")","#002b00", "aqua","darkgreen","aqua",1,2);
        grid.addCuteButton("/","#002b00", "aqua","darkgreen","aqua",2,2);
        grid.addCuteButton("<","#660b0b", "aqua","#de1d1d","aqua",3,2);

        Integer counter = 1;
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                grid.addCuteButton(counter.toString(), "black", "aqua", "#1b3232", "aqua", j, i);
                counter++;
            }
        }
        grid.addCuteButton("×","#002b00", "aqua","darkgreen","aqua",3,3);
        grid.addCuteButton("-","#002b00", "aqua","darkgreen","aqua",3,4);
        grid.addCuteButton("+","#002b00", "aqua","darkgreen","aqua",3,5);

        grid.addCuteButton("CE","#110d4f", "aqua","#241bab","aqua",0,6);
        grid.addCuteButton("0", "black", "aqua", "#1b3232", "aqua", 1, 6);
        grid.addCuteButton("C","#110d4f", "aqua","#241bab","aqua",2,6);
        grid.addCuteButton("=","#353535", "aqua","#87848a","aqua",3,6);


        Scene scene = new Scene(grid, 700, 650);
        stage.setScene(scene);
        stage.show();
    }
}
