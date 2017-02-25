package one;

public class number_change {
	/*
	 * 8. String to Integer (atoi)
	 * Implement atoi to convert a string to an integer.
	 * Hint: Carefully consider all possible input cases. 
	 * If you want a challenge, please do not see below and ask yourself what are the possible input cases.
	 * 注意：头尾空格去除，010表示10，10.12表示10，即非数字时候结束，+10与-10余10之间区别，overflow问题
	 */
	public int myAtoi(String str) {
        // 1. null or empty string  
        if (str == null || str.length() == 0) return 0;  
        // 2. whitespaces  
        str = str.trim();    
        // 3. +/- sign  
        boolean positive = true;  
        int i = 0;  
        if (str.charAt(0) == '+') {  
            i++;  
        } else if (str.charAt(0) == '-') {  
            positive = false;  
            i++;  
        }   
        // 4. calculate real value  
        double tmp = 0;  
        for ( ; i < str.length(); i++) {  
            int digit = str.charAt(i) - '0';       
            if (digit < 0 || digit > 9) break;      
            // 5. handle min & max  
            if (positive) {  
                tmp = 10*tmp + digit;  
                if (tmp > Integer.MAX_VALUE) return Integer.MAX_VALUE;  
            } else {  
                tmp = 10*tmp - digit;  
                if (tmp < Integer.MIN_VALUE) return Integer.MIN_VALUE;  
            }  
        }  
          
        int ret = (int)tmp;  
        return ret;
    }
	/*
	 * 65. Valid Number
	 * Validate if a given string is numeric.
	 * Some examples:
	 * "0" => true
	 * " 0.1 " => true
	 * "abc" => false
	 * "1 a" => false
	 * "2e10" => true
	 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
	 * 挺无聊，边界条件特别多
	 */
	public boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
    
    return numberSeen && numberAfterE;
    }

}
