package logic;

public class StringUtils{

	public static String deleteLast(String s) {
        if (isEmpty(s))
            return new String("");
        return s.substring(0, s.length() - 1);
        
    }

    public static boolean isNumeric(String s) {
        if (isEmpty(s))
            return false;
        for (int i = 0; i < s.length(); i++)
            if ((s.charAt(i) < '0' || s.charAt(i) > '9') && s.charAt(i) != '.' && s.charAt(i) != '-')
                return false;
        return true;
    }

    public static boolean isOperator(String s) {
        if (isNumeric(s))
            return false;
        for (int i = 0; i < s.length(); i++)
            if ((s.charAt(i) >= 'a' && s.charAt(i) <= 'z') || (s.charAt(i) >= 'A' && s.charAt(i) <= 'Z'))
                return false;
        return true;
    }

    public static boolean isLastNumeric(String s) {
        if (isEmpty(s))
            return false;
        return lastChar(s) >= '0' && lastChar(s) <= '9';
    }

    public static Character lastChar(String s) {
        if (isEmpty(s))
            return '\0';
        return s.charAt(s.length() - 1);
    }

    public static boolean isEmpty(String s){
    	return s.length() == 0;
    }
}