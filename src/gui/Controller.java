package gui;

import client.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logic.MathExpressionParser;

public class Controller extends Application {

    private CuteLabel saved;
    private CuteLabel temp;
    private int parenthesis = 0;

    private static Controller instance = null;

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinHeight(650);
        stage.setMinWidth(700);

        ResponsiveGrid grid = new ResponsiveGrid(4, 7);
        grid.setPrefSize(700, 650); // Default width and height
        grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        Scene scene = new Scene(grid, 700, 650);
        stage.setScene(scene);

        saved = new CuteLabel("", "black", "aqua");
        grid.add(saved, 0, 0, 4, 1);

        temp = new CuteLabel("", "black", "aqua");
        grid.add(temp, 0, 1, 4, 1);

        CuteButton b;

        b = grid.addCuteButton("(", "#a5a5a5", "black", "#dbdbdb", "black", 0, 2);
        b.addAccelerator(KeyCode.DIGIT9, KeyCombination.SHIFT_DOWN);
        b.setButtonType(ButtonType.PARENTHESIS);

        b = grid.addCuteButton(")", "#a5a5a5", "black", "#dbdbdb", "black", 1, 2);
        b.addAccelerator(KeyCode.DIGIT0, KeyCombination.SHIFT_DOWN);
        b.setButtonType(ButtonType.PARENTHESIS);

        b = grid.addCuteButton("<", "#a5a5a5", "black", "#dbdbdb", "black", 2, 2);
        b.addAccelerator(KeyCode.BACK_SPACE);
        b.setButtonType(ButtonType.DELETE);

        b = grid.addCuteButton("/", "#fe9c09", "white", "#ffc832", "aqua", 3, 2);
        b.addAccelerator(KeyCode.SLASH);
        b.addAccelerator(KeyCode.DIVIDE);
        b.setButtonType(ButtonType.OPERATOR);


        Integer counter = 9;
        for (int i = 3; i < 6; i++) {
            for (int j = 2; j >= 0; j--) {
                b = grid.addCuteButton(counter.toString(), "#343434", "white", "#737272", "white", j, i);
                b.addAccelerator(KeyCode.getKeyCode(counter.toString()));
                b.addAccelerator(KeyCode.valueOf("NUMPAD" + counter));
                b.setButtonType(ButtonType.NUMBER);
                counter--;
            }
        }
        b = grid.addCuteButton("*", "#fe9c09", "white", "#ffc832", "aqua", 3, 3);
        b.addAccelerator(KeyCode.MULTIPLY);
        b.setButtonType(ButtonType.OPERATOR);

        b = grid.addCuteButton("-", "#fe9c09", "white", "#ffc832", "aqua", 3, 4);
        b.addAccelerator(KeyCode.SUBTRACT);
        b.setButtonType(ButtonType.OPERATOR);

        b = grid.addCuteButton("+", "#fe9c09", "white", "#ffc832", "aqua", 3, 5);
        b.addAccelerator(KeyCode.ADD);
        b.setButtonType(ButtonType.OPERATOR);

        b = grid.addCuteButton("C", "#343434", "white", "#737272", "white", 0, 6);
        b.addAccelerator(KeyCode.DELETE);
        b.setButtonType(ButtonType.C);


        b = grid.addCuteButton("0", "#343434", "white", "#737272", "white", 1, 6);
        b.addAccelerator(KeyCode.DIGIT0);
        b.addAccelerator(KeyCode.NUMPAD0);
        b.setButtonType(ButtonType.NUMBER);


        b = grid.addCuteButton(".", "#343434", "white", "#737272", "white", 2, 6);
        b.addAccelerator(KeyCode.PERIOD)    ;
        b.setButtonType(ButtonType.DOT);

        b = grid.addCuteButton("=", "#fe9c09", "white", "#ffc832", "aqua", 3, 6);
        b.addAccelerator(KeyCode.ENTER);
        b.addAccelerator(KeyCode.EQUALS);
        b.setButtonType(ButtonType.EQUAL);

        stage.show();
    }

    void handleActions(CuteButton button) {
        String text = button.getText();
        switch (button.getType()) {
            case NUMBER:
                String newText;
                try {
                    newText = String.valueOf(Integer.parseInt(temp.getText() + text));
                } catch (Exception e) {
                    newText = String.valueOf(Double.parseDouble(temp.getText() + text));
                }
                temp.setText(newText);
                break;

            case OPERATOR:
                if (temp.lastChar() != '.') {
                    if (temp.isNumeric()) {
                        if (saved.lastChar() == ')')
                            saved.addText("×");
                        saved.addText(temp.getText());
                    }

                    if (saved.isLastNumeric() || saved.lastChar() == ')') {
                        saved.addText(text);
                        temp.clear();
                    } else
                        temp.raiseError("Invalid input!");
                } else
                    temp.raiseError("Invalid number!");
                break;

            case PARENTHESIS:
                if (text.equals("("))
                    parenthesis++;
                else
                    parenthesis--;
                if (parenthesis < 0) {
                    parenthesis++;
                    temp.raiseError("Parenthesis error!");
                } else {
                    if (text.equals(")") && saved.lastChar() == '(' && !temp.isNumeric()) {
                        temp.raiseError("Empty parenthesis!");
                        parenthesis++;
                    } else {
                        if (temp.lastChar() != '.') {
                            if (temp.isNumeric()) {
                                saved.addText(temp.getText());
                                if (text.equals("("))
                                    saved.addText("×");
                            }
                            saved.addText(text);
                            temp.clear();
                        } else
                            temp.raiseError("Invalid number!");
                    }
                }
                break;

            case DELETE:
                temp.deleteLast();
                break;

            case C:
                temp.clear();
                saved.clear();
                break;

            case DOT:
                if (temp.isNumeric())
                    temp.addText(".");
                else
                    temp.raiseError("Can't place \".\"");
                break;

            case EQUAL:
                if (temp.lastChar() == '.') {
                    temp.raiseError("Invalid number!");
                    break;
                } else if (temp.isNumeric())
                    saved.addText(temp.getText());
                if (parenthesis != 0)
                    temp.raiseError("Invalid parenthesis!");
                else if (MathExpressionParser.isOperator(saved.lastChar()))
                    temp.raiseError("Operator at end!");
                else if (!temp.getText().equals("") && saved.lastChar() != ')' && temp.isOperator())
                    temp.raiseError("Operator at end!");
                else if (!temp.isNumeric() && !temp.isOperator())
                    break;
                else {
                    temp.clear();
                    try {
                        Client client = new Client();
                        client.sendRequest(saved.getText());
                        String response = client.getResponse();
                        saved.clear();
                        temp.setText(response);
                    } catch (Exception e) {
                        temp.raiseError("Server not found!");
                    }

                }
                break;
        }
    }

}
