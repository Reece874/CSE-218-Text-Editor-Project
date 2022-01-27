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

public class SignInController {
	
	public Button ButtonsignIn; 
	public Button ButtonsignUp;
	public TextField FieldUsername; 
	public PasswordField FieldPassword; 
	
	public void signUpClicked(MouseEvent mouseEvent) {
		SwapScene(mouseEvent, "/view/SignUpFXML.fxml", "Sign Up");
	}
	
	public void SignInMouseClicked(MouseEvent mouseEvent) {
		
		if(DataCenter.getInstance().setUser(FieldUsername.getText(), FieldPassword.getText())) {
				SwapScene(mouseEvent, "/view/MainFXML.fxml", "Text Editor");
		}else {
			utils.InfoDisplays.displayGenericError("Username Or Password is incorrect");
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
			utils.InfoDisplays.displayGenericError("An Error Occured"); 
			e.printStackTrace();
		}
	}

}
