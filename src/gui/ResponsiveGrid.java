package gui;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class ResponsiveGrid extends GridPane {

    public ResponsiveGrid(int columnNumber, int rowNumber){
        super();

        for(int i = 0; i < columnNumber; i++){
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100f/columnNumber);
            getColumnConstraints().add(column);
        }
        for(int i = 0; i < rowNumber; i++){
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100f/rowNumber);
            getRowConstraints().add(row);
        }
    }

    public CuteButton addCuteButton(String text, String normalBackgroundColor, String normalForegroundColor, String hoverBackgroundColor,
                              String hoverForegroundColor, int columnIndex, int rowIndex){
        CuteButton button = new CuteButton(text,normalBackgroundColor, normalForegroundColor);
        button.setHoverColors(hoverBackgroundColor,hoverForegroundColor);
        add(button,columnIndex,rowIndex);
        GridPane.setFillWidth(button, true);
        GridPane.setFillHeight(button, true);
        return button;
    }
}

