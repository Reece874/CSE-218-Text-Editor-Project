package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Dictionary implements Serializable{
	
	private static final File dictionary = new File("./src/textfiles/dictionary.txt"); 
	private HashSet<String> words = new HashSet<>(200000);
	
	public HashSet<String> getWords() {
		return words; 
	}
	
	public void loadWords() {
		try {
			Scanner sc = new Scanner(dictionary);
				while(sc.hasNext()) {
					words.add(sc.next().toLowerCase());
					}	
				sc.close();
			} catch(FileNotFoundException e) {
				System.out.println("File could not be found or was deleted");
			}
		}

}
