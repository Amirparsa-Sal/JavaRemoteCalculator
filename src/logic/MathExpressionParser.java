package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MathExpressionParser {

    private static ArrayList<Operator> operators;

    static {
        Operator add = new Operator('+',1) {
            @Override
            public Double action(Double a, Double b) {
                return a + b;
            }
        };

        Operator mul = new Operator('*',2) {
            @Override
            public Double action(Double a, Double b) {
                return a * b;
            }
        };

        Operator sub = new Operator('-',1) {
            @Override
            public Double action(Double a, Double b) {
                return a - b;
            }
        };

        Operator div = new Operator('/',2) {
            @Override
            public Double action(Double a, Double b) {
                return a / b;
            }
        };

        operators = new ArrayList<>();
        operators.add(add);
        operators.add(mul);
        operators.add(sub);
        operators.add(div);
    }

    public static ArrayList<Operator> getOperators() {
        return operators;
    }

    public static ArrayList<String> expressionToList(String exp){
        StringBuilder temp = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < exp.length(); i++){
            char c = exp.charAt(i);
            if (isOperator(c) || c == '(' || c == ')') {
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

    public static ArrayList<String> infixToPostfix(String infix){
        ArrayList<String> list = expressionToList(infix);
        Stack<String> opStack = new Stack<>();
        ArrayList<String> output = new ArrayList<>();
        for(String s : list){
            if(isOperator(s)){
                Operator o = getOperatorBySign(s);
                Operator lastOperator = null;
                if(!opStack.isEmpty())
                    lastOperator = getOperatorBySign(opStack.peek());
                if(opStack.isEmpty() || opStack.peek().equals("(") || o.hasMorePrecedence(lastOperator))
                    opStack.push(s);
                else{
                    while (!opStack.isEmpty() && !opStack.peek().equals("(") && !o.hasMorePrecedence(getOperatorBySign(opStack.peek())))
                        output.add(opStack.pop());
                    opStack.push(s);
                }
            }
            else{
                if(s.equals("("))
                    opStack.push(s);
                else if(s.equals(")")){
                    while (!opStack.peek().equals("("))
                        output.add(opStack.pop());
                    opStack.pop();
                }
                else
                    output.add(s);
            }
        }
        while(!opStack.isEmpty())
            output.add(opStack.pop());
        return output;
    }

    public static Double calculatePostfix(ArrayList<String> list){
        Stack<Double> stack = new Stack<>();
        for(String s: list){
            System.out.println(s);
            if(isOperator(s)){
                Double operand2 = stack.pop();
                Double operand1 = stack.pop();
                Operator o = getOperatorBySign(s);
                stack.push(o.action(operand1,operand2));
            }
            else
                stack.push(Double.parseDouble(s));
        }
        return stack.peek();
    }

    public static boolean isOperator(char c){
        for(Operator o : operators)
            if(o.getSign() == c)
                return true;
        return false;
    }

    public static boolean isOperator(String s){
        return isOperator(s.charAt(0));
    }

    private static Operator getOperatorBySign(char c){
        for(Operator o : operators)
            if(o.getSign() == c)
                return o;
        return null;
    }

    private static Operator getOperatorBySign(String s){
        for(Operator o : operators)
            if(o.getSign() == s.charAt(0))
                return o;
        return null;
    }

}
