package logic;

public abstract class Operator {

    private char sign;
    private int precedence;

    public Operator(char sign, int precedence){
        this.sign = sign;
        this.precedence = precedence;
    }

    public char getSign() {
        return sign;
    }

    public boolean hasMorePrecedence(Operator o){
        return precedence > o.precedence;
    }

    abstract public Double action(Double a, Double b);

}
