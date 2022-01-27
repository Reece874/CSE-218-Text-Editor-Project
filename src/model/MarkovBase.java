package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

import utils.MarkovUtils;

public class MarkovBase{
	
	private File file = new File("./src/textfiles/data.txt");
	private TreeMap<String, Link> list;
	
	public MarkovBase() {
		loadFile();
	}
	
	public File getFile() {
		return file; 
	}
	
	public String getFileName() {
		return file.getName();
	}
	
	public void SetFile(File file) {
		this.file = file; 
		loadFile();
	}
	
	public void loadFile() {
		StringBuilder data = new StringBuilder();
		try {
			Scanner sc = new Scanner(file);
			while(sc.hasNext()) {
				data.append(sc.next() + " ");
			}
			sc.close();
		} catch (FileNotFoundException e) {
			SetFile(new File("./src/textfiles/data.txt"));
		}
		list = MarkovUtils.setTreeMap(data.toString());
	}
	
	public String createSentence(String startingWord, int length) {
		return MarkovUtils.checkAndGenerateSentence(list, startingWord, length);
	}

}
