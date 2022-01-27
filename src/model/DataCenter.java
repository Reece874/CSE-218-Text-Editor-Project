package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


@SuppressWarnings("serial")
public class DataCenter implements Serializable{
	
	private static DataCenter dc = null; 
	private UserBase userbase; 
	private User CurrentUser; 
	private Dictionary dictionary; 
	private transient MarkovBase markovbase; 
	
	
	private DataCenter() {
		userbase = new UserBase();
		dictionary = new Dictionary();
	}
	
	public static DataCenter getInstance() {
		if(dc == null) {
			dc = new DataCenter();
			dc.dictionary.loadWords();
		}
		return dc; 
	}
	
	public static DataCenter load() {
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File("./src/data.dat"))))) 
		{
			dc = (DataCenter)ois.readObject();
		}
		catch(IOException | ClassNotFoundException e) {
			getInstance();	
		}
		dc.markovbase = new MarkovBase(); 
		return dc;
	}
	
	public static void save() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream (new File("./src/data.dat"))))) 
		{
			oos.writeObject(dc);
		}
		catch(IOException e) {
			
		}
	}
	
	public boolean setUser(String username, String password) {
		CurrentUser = userbase.findUser(username, password); 
		if(CurrentUser != null) {
			return true; 
		}
		return false; 
	}
	
	public void clearUser() {
		CurrentUser = null; 
	}
	
	public UserBase getUserBase() {
		return userbase; 
	}
	
	public User getCurrentUser() {
		return CurrentUser; 
	}
	
	public Dictionary getDictionary() {
		return dictionary; 
	}
	
	public MarkovBase getMarkovBase() {
		return markovbase; 
	}
}
