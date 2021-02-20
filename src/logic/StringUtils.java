package logic;

/**
 * Represents a class for String utilities.
 *
 * @author Amirparsa Salmankhah
 */
public class StringUtils {

    /**
     * Deletes last character of the string.
     *
     * @param s input string.
     * @return output string.
     */
    public static String deleteLast(String s) {
        if (isEmpty(s))
            return new String("");
        return s.substring(0, s.length() - 1);

    }

    /**
     * Checks if an String contains numeric content.
     *
     * @param s input string.
     * @return true if yes and false if not.
     */
    public static boolean isNumeric(String s) {
        if (isEmpty(s))
            return false;
        for (int i = 0; i < s.length(); i++)
            if ((s.charAt(i) < '0' || s.charAt(i) > '9') && s.charAt(i) != '.' && s.charAt(i) != '-')
                return false;
        return true;
    }

    /**
     * Checks if an String is an operator.
     *
     * @param s input string.
     * @return true if yes and false if not.
     */
    public static boolean isOperator(String s) {
        if (isNumeric(s))
            return false;
        for (int i = 0; i < s.length(); i++)
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'))
                return false;
        return true;
    }

    /**
     * Checks if the last character of the string is numeric.
     *
     * @param s input string.
     * @return true if yes and false if not.
     */
    public static boolean isLastNumeric(String s) {
        if (isEmpty(s))
            return false;
        return lastChar(s) >= '0' && lastChar(s) <= '9';
    }

    /**
     * Finds last character of the string.
     *
     * @param s input string.
     * @return last character of the given string.
     */
    public static Character lastChar(String s) {
        if (isEmpty(s))
            return '\0';
        return s.charAt(s.length() - 1);
    }

    /**
     * Checks if the string is empty.
     *
     * @param s input string.
     * @return true if yes and false if not.
     */
    public static boolean isEmpty(String s) {
        return s.length() == 0;
    }
}