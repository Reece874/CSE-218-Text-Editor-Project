package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

@SuppressWarnings("serial")
public class User implements Comparable<User>, Serializable{
	
	private String Username; 
	private String Password; 
	private String PhoneNumber; 
	private File lastFile; 
	
	public User(String Username, String Password, String PhoneNumber) {
		this.Username =  Username; 
		this.Password = Password; 
		this.PhoneNumber = PhoneNumber; 
		lastFile = null; 
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	
	public File getLastFile() {
		return lastFile;
	}

	public void setLastFile(File lastFile) {
		this.lastFile = lastFile;
	}
		
	public String loadFile() {
		StringBuilder data = new StringBuilder();
		try {
			Scanner sc = new Scanner(lastFile);
			while(sc.hasNextLine()) {
				data.append(sc.nextLine() + "\n");
			}
			sc.close();
		} catch (FileNotFoundException e) {
			return "File was not found or was deleted"; 
		}
		return data.toString(); 
	}
	
	public boolean SaveFile(String str) {
		PrintWriter pf = null; 
		
		if(lastFile == null) {
			return false;
		}
		
		try {
			pf = new PrintWriter(lastFile);
			pf.println(str);
			return true; 
		} catch (FileNotFoundException e) {
			return false; 
		}finally {
			pf.close();
		}
	}
	
	public void saveToDataSubFolder(String str) {
		PrintWriter pf = null; 
		
		try {
			pf = new PrintWriter("./src/Data/" + lastFile.getName());
			pf.println(str);
		} catch(FileNotFoundException e) {
			
		}finally {
			pf.close();
		}
		
	}

	@Override
	public String toString() {
		return "User [Username=" + Username + ", Password=" + Password + ", PhoneNumber=" + PhoneNumber + "]";
	}

	@Override
	public int compareTo(User o) {
		return Username.compareTo(o.Username);
	}
	
	

}
