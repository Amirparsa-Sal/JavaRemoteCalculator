package gui;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Lighting;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class CuteButton extends Button {

    private final String style = "-fx-text-alignment: center; -fx-font-size: 50;";
    private String normalStyle = style;
    private String hoverStyle = style;
    private ButtonType type = ButtonType.UNKNOWN;

    public CuteButton(String text, String backgroundColor, String foregroundColor) {
        super(text);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        setNormalColors(backgroundColor, foregroundColor);
        setStyle(normalStyle);
        updateHoverEvent();
        updateClickEvent();

        setEffect(new Lighting());
    }

    public void addAccelerator(KeyCode keyCode, KeyCombination.Modifier... modifiers) {
        KeyCombination kc = new KeyCodeCombination(keyCode, modifiers);
        Runnable acceleration = new AcceleratorHandler();
        this.getScene().getAccelerators().put(kc, acceleration);
    }

    public void setNormalColors(String background, String foreground) {
        normalStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        updateExitEvent();
    }

    public void setHoverColors(String background, String foreground) {
        hoverStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        updateHoverEvent();
    }

    public void setButtonType(ButtonType type) {
        this.type = type;
    }

    public ButtonType getType() {
        return type;
    }

    private void updateClickEvent() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Controller.getInstance().handleActions(CuteButton.this);
                CuteButton.this.setStyle(normalStyle);
                PauseTransition pt = new PauseTransition(Duration.millis(100));
                pt.setOnFinished(e -> {
                    if (CuteButton.this.isHover())
                        setStyle(hoverStyle);
                });
                pt.play();
            }
        });
    }

    private void updateHoverEvent() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(hoverStyle);
            }
        });
    }

    private void updateExitEvent() {
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(normalStyle);
            }
        });
    }

    private class AcceleratorHandler implements Runnable {

        @Override
        public void run() {
            Controller.getInstance().handleActions(CuteButton.this);
            CuteButton.this.setStyle(hoverStyle);
            PauseTransition pt = new PauseTransition(Duration.millis(250));
            pt.setOnFinished(event -> {
                CuteButton.this.setStyle(normalStyle);
            });
            pt.play();
        }
    }
}
