package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Controller extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinHeight(600);
        stage.setMinWidth(700);

        ResponsiveGrid grid = new ResponsiveGrid(4,5);
        grid.setPrefSize(700, 500); // Default width and height
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

//        Label b = new Label("This is a row!");
//        grid.add(b,0,0,4,1);

        grid.addCuteButton("(","#002b00", "aqua","darkgreen","aqua",0,0);
        grid.addCuteButton(")","#002b00", "aqua","darkgreen","aqua",1,0);
        grid.addCuteButton("/","#002b00", "aqua","darkgreen","aqua",2,0);
        grid.addCuteButton("<","#660b0b", "aqua","#de1d1d","aqua",3,0);

        Integer counter = 1;
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                grid.addCuteButton(counter.toString(), "black", "aqua", "#1b3232", "aqua", j, i);
                counter++;
            }
        }
        grid.addCuteButton("×","#002b00", "aqua","darkgreen","aqua",3,1);
        grid.addCuteButton("-","#002b00", "aqua","darkgreen","aqua",3,2);
        grid.addCuteButton("+","#002b00", "aqua","darkgreen","aqua",3,3);

        grid.addCuteButton("CE","#110d4f", "aqua","#241bab","aqua",0,4);
        grid.addCuteButton("0", "black", "aqua", "#1b3232", "aqua", 1, 4);
        grid.addCuteButton("C","#110d4f", "aqua","#241bab","aqua",2,4);
        grid.addCuteButton("=","#3f3e40", "aqua","#87848a","aqua",3,4);

        Scene scene = new Scene(grid, 700, 600);
        stage.setScene(scene);
        stage.show();
    }
}
