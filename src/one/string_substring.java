package one;

public class string_substring {
	/*
	 * 3. Longest Substring Without Repeating Characters
	 * Given a string, find the length of the longest substring without repeating characters.
	 * Examples:
	 * Given "abcabcbb", the answer is "abc", which the length is 3.
	 * Given "bbbbb", the answer is "b", with the length of 1.
	 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
	 * Subscribe to see which companies asked this question.
	 * so substring begin and end and counter and length=0
	 * string-char-max128, so int[128], first is 0 and then if find is 0 so end++ counter++, if find is not 0 so is repeat then compare counter and length, and then begin++ find begin same with end.
	 * so for substring we do as this: int[128], begin=0, end=0.
	 */
	public int lengthOfLongestSubstring(String s){
		//40ms
		int begin=0,end=0,counter=0,length=0;
		int[] map=new int[128];//same as hashset in this, also could as hash-map just map[s[i]]=i.
		while(end<s.length()){
			if(++map[s.charAt(end++)]==1) counter++;
			else{
				length=Math.max(length, counter);
				while(--map[s.charAt(begin++)]==0) counter--;
			}
		}
		return length=Math.max(length, counter);
		/* 40ms
		 * also could use hashmap
		if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max=0;
        for (int i=0, j=0; i<s.length(); ++i){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j,map.get(s.charAt(i))+1); //j in the repeat number indice before
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-j+1);
        }
        return max;
		 */
		
	}

}
