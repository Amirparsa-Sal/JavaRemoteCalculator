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

/**
 * Represents a cure button in gui.
 *
 * @author Amirparsa Salmankhah
 */
public class CuteButton extends Button {

    //default css style
    private final String style = "-fx-text-alignment: center; -fx-font-size: 50;";
    //normal css style
    private String normalStyle = style;
    //hover css style
    private String hoverStyle = style;
    //type of the button
    private ButtonType type = ButtonType.UNKNOWN;

    /**
     * Constructor with 3 arguments.
     * @param text text of the button
     * @param backgroundColor background color of the button.
     * @param foregroundColor foreground color of the button.
     */
    public CuteButton(String text, String backgroundColor, String foregroundColor) {
        super(text);
        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        setNormalColors(backgroundColor, foregroundColor);
        setStyle(normalStyle);
        updateHoverEvent();
        updateClickEvent();

        setEffect(new Lighting());
    }

    /**
     * Adds an accelerator to the button.
     * @param keyCode key code of the main button.
     * @param modifiers combination of modifier buttons.
     */
    public void addAccelerator(KeyCode keyCode, KeyCombination.Modifier... modifiers) {
        KeyCombination kc = new KeyCodeCombination(keyCode, modifiers);
        Runnable acceleration = new AcceleratorHandler();
        this.getScene().getAccelerators().put(kc, acceleration);
    }

    /**
     * Sets normal color of the button.
     * @param background normal background color of the button.
     * @param foreground normal foreground color of the button.
     */
    public void setNormalColors(String background, String foreground) {
        normalStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        updateExitEvent();
    }

    /**
     * Sets hover color of the button.
     * @param background hover background color of the button.
     * @param foreground hover foreground color of the button.
     */
    public void setHoverColors(String background, String foreground) {
        hoverStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        updateHoverEvent();
    }

    /**
     * Sets type of the button.
     * @param type Type of the button.
     */
    public void setButtonType(ButtonType type) {
        this.type = type;
    }

    /**
     * Gets type of the button.
     * @return Type of the button.
     */
    public ButtonType getType() {
        return type;
    }

    /**
     * Updates click event.
     */
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

    /**
     * Updates hover event.
     */
    private void updateHoverEvent() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(hoverStyle);
            }
        });
    }

    /**
     * Updates mouse exit event.
     */
    private void updateExitEvent() {
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setStyle(normalStyle);
            }
        });
    }

    /**
     * Represents a class for handling accelerator.
     */
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
