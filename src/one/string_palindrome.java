package one;

public class string_palindrome {
	/*
	 * 5. Longest Palindromic Substring
	 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
	 * how to find is palindrome -- 从中心开始，1.中心一个为i, s[i-j]=s[i+j]; 2.中心为i和i+1 s[i-j]=s[i+1+j] //use helper function local variation will not change
	 */
	public String longestPalindrome(String s){
		int[] res=new int[2];//first-length,second-begin,third-end;
		for(int i=0;i<s.length();i++){
			findPalindromeMax(s,i,i,res);
			findPalindromeMax(s,i,i+1,res);
		}
		return s.substring(res[1],res[1]+res[0]);//begin-end
		
	}
	private void findPalindromeMax(String s, int i, int j, int[] res){
		while(i>=0&&j<=s.length()-1&&s.charAt(i)==s.charAt(j)){
			i--;j++;
		}
		if(res[0]<(j-i-1)){
			res[0]=j-i-1;
			res[1]=i+1;
		}
	}
	/*
	 * 214. Shortest Palindrome
	 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. 
	 * Find and return the shortest palindrome you can find by performing this transformation.
	 * For example:
	 * Given "aacecaaa", return "aaacecaaa".
	 * Given "abcd", return "dcbabcd".
	 * 1.以第一个字母开头最长回文,外围到中圈--340ms
	 * 2.以第一个字母开头最长回文,从尾不断排除不可能为回文的--因为回文从尾到头和从头到尾必须为出现2次
	 * 3.KMP-solution？
	 */
    public String shortestPalindrome(String s) {
    	//solution 1
        int res=0,begin=0,end=0;//res to store 最远i坐标
        for(int i=s.length()-1;i>=0;i--){
        	if(s.charAt(i)==s.charAt(0)){
        		begin=0;end=i;
        		while(s.charAt(begin)==s.charAt(end)){
        			begin++;end--;
        			if(begin>=end) {
        				res=i;break;
        			}
        		}
        	if(res>0) break;	
        	}     	
        }
        StringBuilder result=new StringBuilder();
        for(int i=s.length()-1;i>res;i--){
        	result.append(s.charAt(i));
        }
        result.append(s);
        return result.toString();
        /*
        int j = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) { j += 1; }
        }
        if (j == s.length()) { return s; }
        String suffix = s.substring(j);
        return new StringBuffer(suffix).reverse().toString() + shortestPalindrome(s.substring(0, j)) + suffix;
         */
    }
    /*
     * 516. Longest Palindromic Subsequence
     * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
     * Example 1:
     * Input:
     * "bbbab"
     * Output:
     * 4
     * One possible longest palindromic subsequence is "bbbb".
     * subsequence-顺序，需要按照顺序
     */
    public int longestPalindromeSubseq(String s) {
    	//dp
    	//s:i-j; if s[i]==s[j], s:i+1-j-1 +2; else s:i+1-j / s:i-j-1 max
    	/*however TLE--because is not memory and need to calculate repeat
    	if(s.length()==0 || s.length()==1) return s.length();
    	if(s.charAt(0)==s.charAt(s.length()-1)) return longestPalindromeSubseq(s.substring(1,s.length()-1))+2;
    	else return Math.max(longestPalindromeSubseq(s.substring(0,s.length()-1)), longestPalindromeSubseq(s.substring(1,s.length())));
    	*/
    	//so we use int[][] to store;
    	//dp[i][j]-i为起点，j为终点的答案；if(s[i]==s[j]) dp[i][j]=dp[i+1][j-1]+2; else =max(dp[i][j-1],dp[i+1][j])
    	//init dp[i][i]=1,求dp[0][s.length()-1] i<-i+1,j<-j-1-->i逆序j顺序
    	int[][] res=new int[s.length()-1][s.length()-1];
    	for(int i=s.length()-1;i>=0;i--){
    		res[i][i]=1;
    		for(int j=i;j<s.length();j++){
    			if (s.charAt(i) == s.charAt(j)) {
                    res[i][j] = res[i+1][j-1] + 2;
                } else {
                    res[i][j] = Math.max(res[i+1][j], res[i][j-1]);
                }
    		}
    	}
    	return res[0][s.length()-1];
    	/* improve space
    	int n = s.length();
        int[] dp = new int[n];
        for (int j = 0; j < n; ++j) {
            dp[j] = 1;
            int prev = 0;
            for (int i = j - 1; i >= 0; --i) {
                int tmp = dp[i];
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i] = 2 + prev;
                } else {
                    dp[i] = Math.max(dp[i + 1], dp[i]);
                }
                prev = tmp;
            }
        }
        return dp[0];
    	 */
    	
    }

}
