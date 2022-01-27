package utils;

public class UserCreationCheckers {
	
	public static boolean checkPassword(String Password) {
		if(Password.length() < 6) {
			return false; 
		}	
		char[] chars = Password.toCharArray(); 
		return findPasswordRequierments(chars);
	}
	
	private static boolean findPasswordRequierments(char[] chars) {
		boolean lowerCase = false; 
		boolean upperCase = false; 
		boolean digit = false; 
		
		for (char c : chars) {
				if(!lowerCase && Character.isLowerCase(c)) {
					lowerCase = true; 
				}

				if(!upperCase && Character.isUpperCase(c)) {
					upperCase = true; 
				}
				
				if(!digit && Character.isDigit(c)) {
					digit = true; 
				}
				if(lowerCase && upperCase && digit) {
					return true; 
				}
		}
		
		return (lowerCase && upperCase && digit)? true:false; 
	}
	
	public static boolean checkNumber(String PhoneNumber) {		
		if(PhoneNumber.length() != 8) {
			return false; 
		}	
		char[] chars = PhoneNumber.toCharArray();
		
		if(chars[3] != '-') {
			return false;
		}
		
		chars[3] = '0'; 
		
		for (char c : chars) {
			if(!Character.isDigit(c)) {
				return false; 
			}
		}
		return true; 		
	}
}
