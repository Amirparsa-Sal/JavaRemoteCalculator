package gui;


import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.Lighting;
import javafx.util.Duration;

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
        return getText().equals("");
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
        if (isEmpty())
            return;
        String text = getText().substring(0, getText().length() - 1);
        setText(text);
    }

    public boolean isNumeric() {
        if (isEmpty())
            return false;
        String text = getText();
        for (int i = 0; i < text.length(); i++)
            if ((text.charAt(i) < '0' || text.charAt(i) > '9') && text.charAt(i) != '.')
                return false;
        return true;
    }

    public boolean isOperator() {
        if (isNumeric())
            return false;
        String text = getText();
        for (int i = 0; i < text.length(); i++)
            if ((text.charAt(i) >= 'a' && text.charAt(i) <= 'z') || (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z'))
                return false;
        return true;
    }

    public boolean isLastNumeric() {
        if (isEmpty())
            return false;
        return lastChar() >= '0' && lastChar() <= '9';
    }

    public Character lastChar() {
        if (isEmpty())
            return '\0';
        String text = getText();
        return text.charAt(text.length() - 1);
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

