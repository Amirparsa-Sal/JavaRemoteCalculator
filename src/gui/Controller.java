package gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
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

        Scene scene = new Scene(grid, 700, 650);
        stage.setScene(scene);

        Label saved = new CuteLabel("2 + 5 * 6", "#110d4f", "aqua");
        grid.add(saved,0,0,4,1);

        Label temp = new CuteLabel("32", "#110d4f", "aqua");
        grid.add(temp,0,1,4,1);

        CuteButton b;

        b = grid.addCuteButton("(","#002b00", "aqua","darkgreen","aqua",0,2);
        b.addAccelerator(KeyCode.DIGIT9, KeyCombination.SHIFT_DOWN);

        b = grid.addCuteButton(")","#002b00", "aqua","darkgreen","aqua",1,2);
        b.addAccelerator(KeyCode.DIGIT0, KeyCombination.SHIFT_DOWN);

        b = grid.addCuteButton("/","#002b00", "aqua","darkgreen","aqua",2,2);
        b.addAccelerator(KeyCode.SLASH);
        b.addAccelerator(KeyCode.DIVIDE);

        b = grid.addCuteButton("<","#660b0b", "aqua","#de1d1d","aqua",3,2);
        b.addAccelerator(KeyCode.BACK_SPACE);

        Integer counter = 1;
        for (int i = 3; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                b = grid.addCuteButton(counter.toString(), "black", "aqua", "#1b3232", "aqua", j, i);
                b.addAccelerator(KeyCode.getKeyCode(counter.toString()));
                b.addAccelerator(KeyCode.valueOf("NUMPAD" + counter));
                counter++;
            }
        }
        b = grid.addCuteButton("×","#002b00", "aqua","darkgreen","aqua",3,3);
        b.addAccelerator(KeyCode.MULTIPLY);

        b = grid.addCuteButton("-","#002b00", "aqua","darkgreen","aqua",3,4);
        b.addAccelerator(KeyCode.SUBTRACT);

        b = grid.addCuteButton("+","#002b00", "aqua","darkgreen","aqua",3,5);
        b.addAccelerator(KeyCode.ADD);

        b = grid.addCuteButton("CE","#110d4f", "aqua","#241bab","aqua",0,6);
        b.addAccelerator(KeyCode.ESCAPE);

        b = grid.addCuteButton("0", "black", "aqua", "#1b3232", "aqua", 1, 6);
        b.addAccelerator(KeyCode.DIGIT0);
        b.addAccelerator(KeyCode.NUMPAD0);

        b = grid.addCuteButton("C","#110d4f", "aqua","#241bab","aqua",2,6);
        b.addAccelerator(KeyCode.DELETE);

        b = grid.addCuteButton("=","#353535", "aqua","#87848a","aqua",3,6);
        b.addAccelerator(KeyCode.ENTER);

        stage.show();
    }
}
