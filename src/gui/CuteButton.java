package gui;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;

public class CuteButton extends Button {

    private final String style = "-fx-text-alignment: center; -fx-font-size: 50;";
    private String normalStyle = style;
    private String hoverStyle = style;

    public CuteButton(String text, String backgroundColor, String foregroundColor){
        super(text);

        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        setNormalColors(backgroundColor,foregroundColor);
        setStyle(normalStyle);
        updateHoverEvent();

        setEffect(new Lighting());
    }

    public void setNormalColors(String background, String foreground){
        normalStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        updateExitEvent();
    }

    public void setHoverColors(String background, String foreground){
        hoverStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        updateHoverEvent();
    }

    private void updateHoverEvent(){
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(hoverStyle);
            }
        });
    }

    private void updateExitEvent(){
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(normalStyle);
            }
        });
    }
}
