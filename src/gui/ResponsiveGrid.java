package gui;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 * Represents a class for responsive grid.
 *
 * @author Amirparsa Salmankhah
 */
public class ResponsiveGrid extends GridPane {

    /**
     * Constructor with 2 arguments.
     * @param columnNumber Number of the columns.
     * @param rowNumber Number of the rows.
     */
    public ResponsiveGrid(int columnNumber, int rowNumber) {
        super();

        for (int i = 0; i < columnNumber; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(100f / columnNumber);
            getColumnConstraints().add(column);
        }
        for (int i = 0; i < rowNumber; i++) {
            RowConstraints row = new RowConstraints();
            row.setPercentHeight(100f / rowNumber);
            getRowConstraints().add(row);
        }
    }

    /**
     * Adds a cute button to the grid.
     * @param text Text of the button.
     * @param normalBackgroundColor normal background of the color.
     * @param normalForegroundColor normal foreground of the color.
     * @param hoverBackgroundColor hover background of the color.
     * @param hoverForegroundColor hover foreground of the color.
     * @param columnIndex Number of the columns.
     * @param rowIndex Number of the rows.
     * @return Created cute button.
     */
    public CuteButton addCuteButton(String text, String normalBackgroundColor, String normalForegroundColor, String hoverBackgroundColor,
                                    String hoverForegroundColor, int columnIndex, int rowIndex) {
        CuteButton button = new CuteButton(text, normalBackgroundColor, normalForegroundColor);
        button.setHoverColors(hoverBackgroundColor, hoverForegroundColor);
        add(button, columnIndex, rowIndex);
        GridPane.setFillWidth(button, true);
        GridPane.setFillHeight(button, true);
        return button;
    }
}

