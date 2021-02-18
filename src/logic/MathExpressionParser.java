package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MathExpressionParser {

    private static HashMap<Character,Integer> operators;

    static {
        operators = new HashMap<>();
        operators.put('+',1);
        operators.put('-',1);
        operators.put('×',2);
        operators.put('/',2);
    }

    public static HashMap<Character, Integer> getOperators() {
        return operators;
    }

    public static ArrayList<String> expressionToList(String exp){
        StringBuilder temp = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < exp.length(); i++){
            if (operators.keySet().contains(exp.charAt(i)) || exp.charAt(i) == '(' || exp.charAt(i) == ')') {
                if(!temp.toString().equals("")){
                    list.add(temp.toString());
                    temp = new StringBuilder();
                }
                list.add(String.valueOf(exp.charAt(i)));
            }
            else
                temp.append(exp.charAt(i));
        }
        if(!temp.toString().equals(""))
            list.add(temp.toString());
        return list;
    }


}
