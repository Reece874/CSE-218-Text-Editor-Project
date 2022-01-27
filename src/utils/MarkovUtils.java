package utils;

import java.util.TreeMap;

import model.Link;

public class MarkovUtils {
	
	
	public static TreeMap<String, Link> setTreeMap(String text) {
		String[] arr = prepareString(text); 
		TreeMap<String, Link> parentMap = new TreeMap<>();
		parentMap.put(arr[0], new Link(arr[0]));
		addAlltoTreeMap(parentMap, arr);
		return parentMap;
	}
	
	private static String[] prepareString(String str) {
		String[] arr = str.toLowerCase().replaceAll("[^a-zA-Z ]", "").split(" "); 
		return arr; 
	}
	
	private static void addAlltoTreeMap(TreeMap<String, Link> parentList, String[] arr){	
		Link previous = parentList.get(arr[0]);
		
		for (int i = 1; i < arr.length; i++) {
			Link current = parentList.get(arr[i]); 
			if(current == null) {
				current = new Link(arr[i]); 
				parentList.put(arr[i], current); 
			}
			previous.insertBabyList(current);
			previous = current; 
		}
	}

	public static String checkAndGenerateSentence(TreeMap<String, Link> parent, String starterWord, int length) {
		Link loc = parent.get(starterWord.toLowerCase()); 
		if(loc == null) {
			InfoDisplays.displayGenericError("Word is not in Given Learn File");
			return ""; 
		}else {
			return createSentence(loc, parent, length);
		} 		
	}
	
	private static String createSentence(Link loc, TreeMap<String, Link> parent, int length) {
		String sentence = "";
		sentence = loc.getStr()  + " "; 
		for (int i = 0; i < length-1; i++) {
			if(loc.getBabyList().isEmpty()) {
				return sentence; 
			}
			loc = loc.getBabyList().get((int)(Math.random()*loc.getBabyList().size())); 
			sentence += loc.getStr() + " "; 
		}
		return sentence; 
	}
}
