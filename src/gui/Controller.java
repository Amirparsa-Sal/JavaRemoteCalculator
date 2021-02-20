package gui;

import client.Client;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import logic.MathExpressionParser;
import logic.StringUtils;

/**
 * Represents a class for controlling gui.
 *
 * @author Amirparsa Salmankhah
 */
public class Controller extends Application {

    //Upper calculation label
    private CuteLabel saved;
    //Label in front.
    private CuteLabel temp;
    //Difference of opened and closed parenthesis.
    private int parenthesis = 0;
    //Real infix string to be calculated.
    private String infix = "";
    //flag for checking the use of operators at the first of the expression.
    boolean operatorAtFirst = false;
    //Controller instance
    private static Controller instance = null;

    /**
     * Gets controller instance.
     * @return Controller instance.
     */
    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    /**
     * Initializes gui.
     * @param stage Current stage.
     * @throws Exception
     */
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

    /**
     * handles action of a button.
     * @param button Triggered button.
     */
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
            	//floating point at the end
                if (temp.lastChar() != '.') {
                	//adding temp number to saved
                    if (temp.isNumeric()) {
                    	System.out.println("here");
                        if (StringUtils.lastChar(infix) == ')'){
                            saved.addText("*");
                            infix += "*";
                        }
                        saved.addText(temp.getText());
                    	infix += temp.getText();
                    	if(operatorAtFirst){
                    		operatorAtFirst = false;
                    		infix += ")";
                    	}
                    }
                    //check if last character is number or parenthesis
                    if (StringUtils.isLastNumeric(infix) || StringUtils.lastChar(infix) == ')') {
                        saved.addText(text);
                        infix += text;
                        temp.clear();
                    } 
                    //if we have operaters at first
                    else if((text.equals("+") || text.equals("-")) && (StringUtils.isEmpty(infix) || StringUtils.lastChar(infix) == '(')){
                    	saved.addText(text);
                    	infix += "(0" + text;
                    	operatorAtFirst = true;
                    }
                    else
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
                    if (text.equals(")") && StringUtils.lastChar(infix) == '(' && !temp.isNumeric()) {
                        temp.raiseError("Empty parenthesis!");
                        parenthesis++;
                    } else {

                        if (temp.lastChar() != '.') {
                            if (temp.isNumeric()) {
                                saved.addText(temp.getText());
                                infix += temp.getText();
                                if(operatorAtFirst){
		                    		operatorAtFirst = false;
		                    		infix += ")";
		                    	}
                                if (text.equals("(")){
                                    if(operatorAtFirst){
			                    		operatorAtFirst = false;
			                    		infix += ")";
			                    	}
                                    infix += "*";
                                }
                            }
                            if(StringUtils.lastChar(infix)==')' && text.equals("(")){
                            	if(operatorAtFirst){
		                    		operatorAtFirst = false;
		                    		infix += ")";
		                    	}
                            	infix += "*";
                            }
                            
                            saved.addText(text);
                            infix += text;
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
                infix = "";
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
                } else if (temp.isNumeric()){
                    saved.addText(temp.getText());
                    infix += temp.getText();
                }
                if (parenthesis != 0){
                    for(int i =0; i < parenthesis; i++)
                    	infix += ")";
    				parenthesis = 0;
                }
                if (MathExpressionParser.isOperator(StringUtils.lastChar(infix)))
                    temp.raiseError("Operator at end!");
                else if (!temp.getText().equals("") && StringUtils.lastChar(infix) != ')' && temp.isOperator())
                    temp.raiseError("Operator at end!");
                else if (!temp.isNumeric() && !temp.isOperator())
                    break;
                else {
                    temp.clear();
                    try {
                        Client client = new Client();
                        client.sendRequest(infix);
                        String response = client.getResponse();
                        saved.clear();
                        saved.setText(response);
                        infix = new String(response);
                        if(response.charAt(0) == '-')
                        	infix = "(0" + infix + ")";
                    } catch (Exception e) {
                        temp.raiseError("Server not found!");
                    }

                }
                break;
        }
    }

}
