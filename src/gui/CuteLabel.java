package gui;


import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.util.Duration;
import logic.StringUtils;

/**
 * Represents a class for a cute label.
 *
 * @author Amirparsa Salmankhah
 */
public class CuteLabel extends Label {

    //default css style
    private final String style = "-fx-font-size: 50; -fx-font-family: Arial;";
    //normal css style
    private String normalStyle = style;

    /**
     * Constructor with 3 arguments.
     *
     * @param text            text of the label.
     * @param backgroundColor background color of the label.
     * @param foregroundColor foreground color of the label.
     */
    public CuteLabel(String text, String backgroundColor, String foregroundColor) {
        super(text);

        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setEffect(new Lighting());
        setColor(backgroundColor, foregroundColor);
        setColor(backgroundColor, foregroundColor);
    }

    /**
     * Sets the color of the label.
     *
     * @param background background color of the label.
     * @param foreground foreground color of the label.
     */
    public void setColor(String background, String foreground) {
        normalStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        setStyle(normalStyle);
    }

    /**
     * Checks if label is empty.
     *
     * @return true if yes and false if not.
     */
    public boolean isEmpty() {
        return StringUtils.isEmpty(getText());
    }

    /**
     * Adds a text to the label.
     *
     * @param text the text to be added.
     */
    public void addText(String text) {
        if (text.equals(""))
            return;
        String lastText = getText();
        lastText += text;
        setText(lastText);
    }

    /**
     * Clears the label.
     */
    public void clear() {
        setText("");
    }

    /**
     * Deletes last character of the label.
     */
    public void deleteLast() {
        setText(StringUtils.deleteLast(getText()));
    }

    /**
     * Checks if label content is numeric.
     *
     * @return true if yes and false if not.
     */
    public boolean isNumeric() {
        return StringUtils.isNumeric(getText());
    }

    /**
     * Checks if label content is an operator.
     *
     * @return true if yes and false if not.
     */
    public boolean isOperator() {
        return StringUtils.isOperator(getText());
    }

    /**
     * Checks if last character of the label is numeric.
     *
     * @return true if yes and false if not.
     */
    public boolean isLastNumeric() {
        return StringUtils.isLastNumeric(getText());
    }

    /**
     * Gets last character of the label.
     *
     * @return Last character of the label.
     */
    public Character lastChar() {
        return StringUtils.lastChar(getText());
    }

    /**
     * Shoes an error inside the label.
     *
     * @param message error message.
     */
    public void raiseError(String message) {
        setStyle(style + "-fx-background-color: darkred; -fx-text-fill: white;");
        setText(message);
        PauseTransition pt = new PauseTransition(Duration.seconds(1));
        pt.setOnFinished(event -> {
            setStyle(normalStyle);
            setText("");
        });
        pt.play();
    }


}

