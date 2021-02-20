package logic;

/**
 * Represents a class for operators.
 *
 * @author Amirparsa Salmankhah
 */
public abstract class Operator {

    //sign of the operator
    private char sign;
    //precedence of the operator
    private int precedence;

    /**
     * Constructor with 2 arguments.
     *
     * @param sign       sign of the operator.
     * @param precedence precedence of the operator.
     */
    public Operator(char sign, int precedence) {
        this.sign = sign;
        this.precedence = precedence;
    }

    /**
     * Gets sign of the operator.
     *
     * @return sign of the operator.
     */
    public char getSign() {
        return sign;
    }

    /**
     * Checks if the operator has more precedence than another one.
     *
     * @param o another operator.
     * @return true if yes and false if not.
     */
    public boolean hasMorePrecedence(Operator o) {
        return precedence > o.precedence;
    }

    /**
     * Performs operator's action.
     *
     * @param a first operand.
     * @param b second operand.
     * @return Amount of output.
     */
    abstract public Double action(Double a, Double b);

}
