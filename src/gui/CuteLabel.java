package gui;


import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.util.Duration;
import logic.StringUtils;

public class CuteLabel extends Label {

    private final String style = "-fx-font-size: 50; -fx-font-family: Arial;";
    private String normalStyle = style;

    public CuteLabel(String text, String backgroundColor, String foregroundColor) {
        super(text);

        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        setEffect(new Lighting());
        setColor(backgroundColor, foregroundColor);
        setColor(backgroundColor, foregroundColor);
    }

    public void setColor(String background, String foreground) {
        normalStyle = style + "-fx-background-color:" + background + ";-fx-text-fill: " + foreground + ";";
        setStyle(normalStyle);
    }

    public boolean isEmpty() {
        return StringUtils.isEmpty(getText());
    }

    public void addText(String text) {
        if (text.equals(""))
            return;
        String lastText = getText();
        lastText += text;
        setText(lastText);
    }

    public void clear() {
        setText("");
    }

    public void deleteLast() {
        setText(StringUtils.deleteLast(getText()));
    }

    public boolean isNumeric() {
        return StringUtils.isNumeric(getText());
    }

    public boolean isOperator() {
        return StringUtils.isOperator(getText());
    }

    public boolean isLastNumeric() {
        return StringUtils.isLastNumeric(getText());
    }

    public Character lastChar() {
        return StringUtils.lastChar(getText());
    }

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

