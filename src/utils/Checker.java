package utils;

import java.util.Arrays;
import java.util.HashSet;

import model.DataCenter;

public class Checker {
	
	public static String getMisspelledWords(String str) {
		String[] arr = str.replaceAll("[^a-zA-Z' \n]", "").split("[ \n]+"); 
		HashSet<String> words = DataCenter.getInstance().getDictionary().getWords(); 	
		StringBuilder sb = new StringBuilder(); 
		Arrays.stream(arr).filter(s -> !words.contains(s.toLowerCase())).forEach(s -> sb.append(s + "\n"));
		return sb.toString();
	
	}
	
}
