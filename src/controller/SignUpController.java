package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.DataCenter;

public class SignUpController {
	
	public Button ButtonSignUp; 
	public TextField TextFieldUsername; 
	public TextField TextFieldPhoneNumber; 
	public PasswordField PasswordFieldEnter;
	public PasswordField PasswordFieldConfirm;

	
	public void OnSignUpClicked(MouseEvent mouseEvent) {
				
		if(DataCenter.getInstance().getUserBase().createNewUser(TextFieldUsername.getText(), PasswordFieldEnter.getText(), PasswordFieldConfirm.getText(), TextFieldPhoneNumber.getText())) {
			SwapScene(mouseEvent, "/view/SignInFXML.fxml", "Sign In");	
		}		
	}
	
	public void SwapScene(MouseEvent mouseEvent, String resource, String SceneName){
		try {
			Parent secondRoot = FXMLLoader.load(getClass().getResource(resource));
			Scene secondScene = new Scene(secondRoot);
			Stage window = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
			window.setScene(secondScene);
			window.setTitle(SceneName); 
			window.show();	
		}catch(IOException e) {
			utils.InfoDisplays.displayGenericError("An Error has Occured");
		}
	}

}
