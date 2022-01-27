package model;

import java.io.Serializable;
import java.util.TreeMap;

import utils.InfoDisplays;
import utils.UserCreationCheckers;

@SuppressWarnings("serial")
public class UserBase implements Serializable{
	
	TreeMap<String, User> users;
	
	public UserBase() {
		users = new TreeMap<>();
	}
	
	public boolean createNewUser(String Username, String Password, String ConfirmPassword, String PhoneNumber) {
		if(users.containsKey(Username) || Username.isEmpty()) {
			InfoDisplays.displayGenericError("Someone with that Username Already Exists or No Username has been entered");
			return false; 
		}
		
		if(!UserCreationCheckers.checkNumber(PhoneNumber)) {
			InfoDisplays.displayGenericError("Phone number is invalid\n\nEnter in format:\n123-4567");
			return false;
		}
		
		if(!UserCreationCheckers.checkPassword(Password)) {
			InfoDisplays.displayGenericError("Password is Insufficient.\n\nPassword Must Contain:\n-At Least One Digit\n-One Upper case and One Lower case letter\n-Minimum of 6 Characters");
			return false; 
		}
		if(!ConfirmPassword.equals(Password)) {
			InfoDisplays.displayGenericError("Password and Confirm Password do not match");
			return false; 
		}
			users.put(Username, new User(Username, Password, PhoneNumber));
			return true; 	
	}
	
	public User findUser(String Username, String Password) {
		User attempt = users.get(Username); 
		if(attempt == null) {
			return null; 
		}
		if(attempt.getPassword().equals(Password)) {
			return attempt;
		}
		return null; 
	}
		
}
