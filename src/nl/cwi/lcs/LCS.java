package nl.cwi.lcs;

import java.io.IOException;

import nl.cwi.entropy.CalculateEntropy;

public class LCS {

	public static String longestSubstring(String str1, String str2) {
	    long startTime = System.currentTimeMillis();

		StringBuilder sb = new StringBuilder();
		if (str1 == null || str1.isEmpty() || str2 == null || str2.isEmpty())
		  return "";
		 
	
		 
		// java initializes them already with 0
		int[][] num = new int[str1.length()][str2.length()];
		int maxlen = 0;
		int lastSubsBegin = 0;
		 
		for (int i = 0; i < str1.length(); i++) {
		for (int j = 0; j < str2.length(); j++) {
		  if (str1.charAt(i) == str2.charAt(j)) {
		    if ((i == 0) || (j == 0))
		       num[i][j] = 1;
		    else
		       num[i][j] = 1 + num[i - 1][j - 1];
		 
		    if (num[i][j] > maxlen) {
		      maxlen = num[i][j];
		      // generate substring from str1 => i
		      int thisSubsBegin = i - num[i][j] + 1;
		      if (lastSubsBegin == thisSubsBegin) {
		         //if the current LCS is the same as the last time this block ran
		         sb.append(str1.charAt(i));
		      } else {
		         //this block resets the string builder if a different LCS is found
		         lastSubsBegin = thisSubsBegin;
		         sb = new StringBuilder();
		         sb.append(str1.substring(lastSubsBegin, i + 1));
		      }
		   }
		}
		}}
		long endTime = System.currentTimeMillis();
	    System.out.println("Total execution time: " + (endTime-startTime) + "ms");
		return sb.toString();
		}
	
	
	public static int getLCSLength(String first, String second) {
	    long startTime = System.currentTimeMillis();

		
	  
	 
	    int maxLen = 0;
	    int fl = 512;
	   // int fl = first.length();

	    int sl = second.length();
	    int[][] table = new int[fl][sl];
	 
	    for (int i = 0; i < fl; i++) {
	        for (int j = 0; j < sl; j++) {
	            if (first.charAt(i) == second.charAt(j)) {
	                if (i == 0 || j == 0) {
	                    table[i][j] = 1;
	                }
	                else {
	                    table[i][j] = table[i - 1][j - 1] + 1;
	                }
	                if (table[i][j] > maxLen) {
	                    maxLen = table[i][j];
	                }
	            }
	        }
	    }
	   
	    long endTime = System.currentTimeMillis();
	    System.out.println("Total execution time: " + (endTime-startTime) + "ms");
	    return maxLen;
	}
	
	
	private static String getSubstring( String[] str, int lastSubsBegin, int lastIndex){
		String temp = null;
		for(int i =lastSubsBegin; i <= lastIndex; i++){
			int value = Integer.valueOf(str[i]);
			temp+=(char) value ;
		}
		return temp;
	}
}
