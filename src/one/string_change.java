package one;

public class string_change {
	/*
	 * 6. ZigZag Conversion
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
	 * P   A   H   N
	 * A P L S I I G
	 * Y   I   R
	 * And then read line by line: "PAHNAPLSIIGYIR"
	 * Write the code that will take a string and make this conversion given a number of rows:
	 * string convert(string text, int nRows);
	 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	 * 1./(2n-2),and a1+b1+c1+...+a2+a(2n-2)+b2+b(2n-2)+...an+bn+...//however not good-n=1则例外
	 * 2.n个stringbuilder，i++，对应stringbuilder添加s[i];
	 */
	public String convert(String s, int numRows) {
		StringBuilder[] res=new StringBuilder[numRows];
		for (int i = 0; i < res.length; i++) res[i] = new StringBuilder();//if no this, every is null
		//down - then up
		int i=0;
		while(i<s.length()){
			for(int idx=0;idx<numRows&& i<s.length();idx++,i++){
				res[idx].append(s.charAt(i));
			}
			for(int idx=numRows-2;idx>0 && i<s.length(); idx--,i++){
				res[idx].append(s.charAt(i));
			}
		}
		StringBuilder result=new StringBuilder();
		for(int j=0;j<numRows;j++){
			result.append(res[j]);
		}
		return result.toString();
		
	}

}
