package one;

public class dynamic_programming {
	/*
	 * 10. Regular Expression Matching
	 * Implement regular expression matching with support for '.' and '*'.
	 * '.' Matches any single character.
	 * '*' Matches zero or more of the preceding element.
	 * The matching should cover the entire input string (not partial).
	 * The function prototype should be:
	 * bool isMatch(const char *s, const char *p)
	 * Some examples:
	 * isMatch("aa","a") → false
	 * isMatch("aa","aa") → true
	 * isMatch("aaa","aa") → false
	 * isMatch("aa", "a*") → true
	 * isMatch("aa", ".*") → true
	 * isMatch("ab", ".*") → true
	 * isMatch("aab", "c*a*b") → true
	 * 
	 * string1 是 string2的子字符串
	 * DP[i][j]表示 s1的前i个是否符合s2的前j个。 =dp[i][j-1]=true, =dp[i][j-1]=false则若S1[i-1]=S2[j-1],则=dp[i-1][j-1] 否则false
	 * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
	 * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
	 * 3, If p.charAt(j) == '*': 
	 * here are two sub conditions:
               1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
               2   if p.charAt(j-1) == s.charAt(i) or p.charAt(i-1) == '.':
                              dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
                           or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
                           or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
	 */
	public boolean isMatch(String s, String p){
		if (s == null || p == null) {
	        return false;
	    }
		boolean[][] dp=new boolean[s.length()+1][p.length()+1];
		dp[0][0] = true;
	    for (int i = 0; i < p.length(); i++) {
	        if (p.charAt(i) == '*' && dp[0][i-1]) {
	            dp[0][i+1] = true;
	        }
	    }
	    for (int i = 0 ; i < s.length(); i++) {
	        for (int j = 0; j < p.length(); j++) {
	            if (p.charAt(j) == '.') {
	                dp[i+1][j+1] = dp[i][j];
	            }
	            if (p.charAt(j) == s.charAt(i)) {
	                dp[i+1][j+1] = dp[i][j];
	            }
	            if (p.charAt(j) == '*') {
	                if (p.charAt(j-1) != s.charAt(i) && p.charAt(j-1) != '.') {
	                    dp[i+1][j+1] = dp[i+1][j-1];
	                } else {
	                    dp[i+1][j+1] = (dp[i+1][j] || dp[i][j+1] || dp[i+1][j-1]);
	                }
	            }
	        }
	    }
	    return dp[s.length()][p.length()];
	}
	/*
	 * 44. Wildcard Matching
	 * Implement wildcard pattern matching with support for '?' and '*'.
	 * '?' Matches any single character.
	 * '*' Matches any sequence of characters (including the empty sequence).
	 * The matching should cover the entire input string (not partial).
	 * The function prototype should be:
	 * bool isMatch(const char *s, const char *p)
	 * Some examples:
	 * isMatch("aa","a") → false
	 * isMatch("aa","aa") → true
	 * isMatch("aaa","aa") → false
	 * isMatch("aa", "*") → true
	 * isMatch("aa", "a*") → true
	 * isMatch("ab", "?*") → true
	 * isMatch("aab", "c*a*b") → false
	 * just similar as above
	 * p[j]='*'时，就dp[i+1][j+1]=||dp[<=i+1][j]
	 */
    public boolean isMatch2(String s, String p) {
    	if (s == null || p == null) {
	        return false;
	    }
    	boolean[][] dp=new boolean[s.length()+1][p.length()+1];//默认false
    	dp[0][0]=true;
    	for (int i = 0; i < p.length(); i++) {
	        if (p.charAt(i) == '*' && dp[0][i]) {
	            dp[0][i+1] = true;
	        }
	    }
    	for (int i = 0 ; i < s.length(); i++) {
	        for (int j = 0; j < p.length(); j++) {
	            if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
	                dp[i+1][j+1] = dp[i][j];
	            }
	            if (p.charAt(j) == '*') {
	                for(int k=0;k<=i+1;k++){
	                	if(dp[k][j]==true) {
	                		dp[i+1][j+1]=true;
	                		break;
	                	}
	                }
	            }
	        }
	    }
    	return dp[s.length()][p.length()];
    }

}
