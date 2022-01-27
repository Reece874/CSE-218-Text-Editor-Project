package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCounters {
	
	public static int getWordCount(String str) {
		int num = 0; 
		String pattern = "[a-zA-Z']+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);
		
		while(m.find()) {
			num++; 
		}
		return num; 
	}
	
	public static int getSentenceCount(String str) {
		int num = 0; 
		String pattern = "[.?!]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);
		
		while(m.find()) {
			num++; 
		}
		return num; 
	}
	
	public static int getSyllableCount(String str) {
		int num = 0; 
		String pattern = "[AEIOUYaeiou]+";
		Pattern tokenSplitter = Pattern.compile(pattern);
		Matcher m = tokenSplitter.matcher(str);
		
		String lastToken = "";
		
		while(m.find()) {
			num++; 
			lastToken = m.group();
		}
		
		if(lastToken.equals("e") && num > 1 && str.charAt(str.length()-1) == 'e') {
			num--; 
		}
		return num; 
	}
	
	public static double getFleschScore(String str) {
		int words = getWordCount(str);
		int sentences = getSentenceCount(str);
		if(sentences != 0 && words != 0) {
			double ASL = (double)words/sentences;
			double ASW = (double)getSyllableCount(str)/words;
			return Math.round((206.835-(1.015*ASL)-(84.6*ASW))*1000.0)/1000.0;
		}
		return 0; 
	}

}
