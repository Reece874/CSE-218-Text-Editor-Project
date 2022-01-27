package app;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataCenter;

public class Main extends Application{

	public static void main(String[] args) {
		DataCenter.load();
		launch(args);

	}
	
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/view/SignInFXML.fxml"));
		Scene scene = new Scene(root); 
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.show();
		
	}
	
	public void stop() throws Exception{
		DataCenter.save();
		super.stop();
	}
	
}
