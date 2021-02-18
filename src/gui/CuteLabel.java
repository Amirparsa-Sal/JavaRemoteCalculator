package gui;


import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;

public class CuteLabel extends Label {

    private final String style = "-fx-font-size: 50; -fx-font-family: Arial;";
    private String normalStyle = style;

    public CuteLabel(String text, String backgroundColor, String foregroundColor){
        super(text);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setEffect(new Lighting());
        setColor(backgroundColor, foregroundColor);
        setColor(backgroundColor, foregroundColor);
    }

    public void setColor(String background, String foreground){
        normalStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        setStyle(normalStyle);
    }

}
