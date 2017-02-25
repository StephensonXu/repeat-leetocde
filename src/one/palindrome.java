package one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class palindrome {
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
    	/* improve space ***
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
    /*
     * 336. Palindrome Pairs ***
     * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
     * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
     * 1.word[i]+word[j]=reverse.word[j]+reverse.word[i],则成立- however reverse makes long time- TLE
     * 2.reverse[word[i]+word[j]]=word[i]+word[j]--回文
     */
    public List<List<Integer>> palindromePairs(String[] words) {
    	/*
    	List<List<Integer>> res=new ArrayList<List<Integer>>();
        String[] compare=new String[words.length];
        for(int i=0;i<words.length;i++){
        	//compare[i]=new StringBuffer(words[i]).reverse().toString();//其余的必须自己倒转
        	//also TLE
        	compare[i]="";
        	for(int j=words[i].length()-1;j>=0;j--){
        		compare[i]+=words[i].charAt(j);
        	}
        }
        for(int i=0;i<words.length;i++){
        	for(int j=0;j<words.length;j++){
        		if(j==i) continue;
        		if((words[i]+words[j]).equals(compare[j]+compare[i])) res.add(Arrays.asList(i,j));
        	}
        }
        return res;
    	 */
        
        List<List<Integer>> ret = new ArrayList<>(); 
        if (words == null || words.length < 2) return ret;
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i=0; i<words.length; i++) map.put(words[i], i);
        for (int i=0; i<words.length; i++) {
            // System.out.println(words[i]);
            for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(map.get(str2rvs));
                        list.add(i);
                        ret.add(list);
                        // System.out.printf("isPal(str1): %s\n", list.toString());
                    }
                }
                if (isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i && str2.length()!=0) { 
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(map.get(str1rvs));
                        ret.add(list);
                        // System.out.printf("isPal(str2): %s\n", list.toString());
                    }
                }
            }
        }
        return ret;
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            if (str.charAt(left++) !=  str.charAt(right--)) return false;
        }
        return true;
    }
    /*
     * 9. Palindrome Number
     * Determine whether an integer is a palindrome. Do this without extra space.
     * 1.reverse x: overflow-> false
     * 2.reverse half
     */
    public boolean isPalindrome(int x){
    	if(x<0 || (x!=0&&x%10==0)) return false;
    	int rev=0;
    	while(rev<x){
    		rev=rev*10+x%10;
    		x=x/10;
    	}
    	return x==rev || rev/10==x;
    }
    /*
     * 125. Valid Palindrome
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     * For example,
     * "A man, a plan, a canal: Panama" is a palindrome.
     * "race a car" is not a palindrome.
     * " " or "" is vaild
     * to lower case and '0'-'9','A'-'Z','a'-'z'char number around (字符包括数字) charat(i)-'x'>0?进行判断
     * 或用方法 Character.isLetterOrDigit，Character.toLowerCase
     * 或用现成方法 
     * String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
       String rev = new StringBuffer(actual).reverse().toString();
       return actual.equals(rev);
     */
    public boolean isPalindrome_string(String s) {
    	s=s.toLowerCase();//to lower case
        int head=0,tail=s.length()-1;
        while(head<=tail){
        	while(head<=tail && !Character.isLetterOrDigit(s.charAt(head))){
        		head++;
        	}
        	while(head<=tail && !Character.isLetterOrDigit(s.charAt(tail))){
        		tail--;
        	}
        	if(head<=tail && s.charAt(head++)!=s.charAt(tail--)){
        		return false;
        	}
        }
        return true;
    }
    /*
     * 234. Palindrome Linked List
     * Given a singly linked list, determine if it is a palindrome.
     * Follow up:
     * Could you do it in O(n) time and O(1) space?
     * 1.reverse it -> ListNode rev and compare
     * 2.rev just half and compare
     * 3.but 0(1) space so just 认为在原来node上rev为0(1)空间
     * 4.对于链表可用快慢节点法找中点
     */
    public class ListNode {
    	 int val;
    	 ListNode next;
    	 ListNode(int x) { val = x; }
    }
    public boolean isPalindrome(ListNode head){
    	ListNode slow=head;  
    	ListNode fast=head;  
    	   
    	if(fast==null||fast.next==null)//0个节点或是1个节点  
    	     return true;  
    	  
    	  
    	while(fast.next!=null&&fast.next.next!=null)  {  
    	    fast=fast.next.next;  
    	    slow=slow.next;  
    	}  
    	//对链表后半段进行反转  
    	ListNode midNode=slow;  
    	ListNode firNode=slow.next;//后半段链表的第一个节点  
    	ListNode cur=firNode.next;//插入节点从第一个节点后面一个开始  
    	firNode.next=null;//第一个节点最后会变最后一个节点  
    	while(cur!=null){  
    	    ListNode nextNode=cur.next;//保存下次遍历的节点  
    	    cur.next=midNode.next;  
    	    midNode.next=cur;  
    	    cur=nextNode;  
    	}  
    	   
    	//反转之后对前后半段进行比较  
    	slow=head;  
    	fast=midNode.next;  
    	while(fast!=null){  
    	    if(fast.val!=slow.val)  
    	        return false;  
    	    slow=slow.next;  
    	    fast=fast.next;  
    	}  
    	return true;
    }

}
