package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DataCenter;

public class MainController implements Initializable{
	
	public MenuBar MenuBarMain; 
	public Menu MenuFile;
	public Menu MenuExit;
	public Menu MenuCreate;
	public Menu MenuText;
	public MenuItem MenuItemNew; 
	public MenuItem MenuItemOpen; 
	public MenuItem MenuItemClose; 
	public MenuItem MenuItemSave; 
	public MenuItem MenuItemSaveAs;
	public MenuItem MenuItemSpellCheck; 
	public MenuItem MenuItemLearn; 
	public MenuItem MenuItemCreate;
	public MenuItem MenuItemExit; 
	public MenuItem MenuItemUndo; 
	public MenuItem MenuItemWordCount; 
	public MenuItem MenuItemSentenceCount; 
	public MenuItem MenuItemFleschScore; 
	public MenuItem MenuItemStartingWord; 
	public MenuItem MenuItemLength;
	public MenuItem MenuItemGenerate;
	public MenuItem MenuItemTextSize;
	public MenuItem MenuItemFont;
	public MenuItem MenuItemFileName; 
	public Label LabelWordCount; 
	public Label LabelSentenceCount;
	public Label LabelFleschScore;
	public TextArea TextAreaMain;
	public TextArea TextAreaInfoDoc;
	public TextField TextFieldStartingWord;
	public Spinner<Integer> SpinnerLength;
	public Spinner<Integer> SpinnerTxtSize;
	public ComboBox<String> FontComboBox;
	SpinnerValueFactory<Integer> svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 5);
	SpinnerValueFactory<Integer> FontSpinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 50, 12);
	
	
	public void OnNewMenuItemClicked(ActionEvent actionEvent) {
		if(DataCenter.getInstance().getCurrentUser().getLastFile() != null) {
			DataCenter.getInstance().getCurrentUser().setLastFile(null);
			TextAreaMain.setText("");
		}
		TextAreaMain.setDisable(false);
	}
	
	public void OnOpenMenuItemClicked(ActionEvent actionEvent) {
		FileChooser fc = new FileChooser(); 
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File f = fc.showOpenDialog(null); 
		if(f != null) {
			DataCenter.getInstance().getCurrentUser().setLastFile(f);
			TextAreaMain.setText(DataCenter.getInstance().getCurrentUser().loadFile());
			TextAreaMain.setDisable(false);
		}
		updateInfo();
	}
	
	public void OnCloseMenuItemClicked(ActionEvent actionEvent) {
		DataCenter.getInstance().getCurrentUser().setLastFile(null);
		TextAreaMain.setText("");
		TextAreaMain.setDisable(true);
		updateInfo();
	}
	
	public void OnSaveMenuItemClicked(ActionEvent actionEvent) {
		if(!DataCenter.getInstance().getCurrentUser().SaveFile(TextAreaMain.getText())) {
			OnSaveAsMenuItemClicked(actionEvent);
		}else {
			DataCenter.getInstance().getCurrentUser().saveToDataSubFolder(TextAreaMain.getText());
		}
		
	}
	
	public void OnSaveAsMenuItemClicked(ActionEvent actionEvent) {
		FileChooser fc = new FileChooser(); 
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File f = fc.showSaveDialog(null); 
		if(f != null) {
			DataCenter.getInstance().getCurrentUser().setLastFile(f);
			DataCenter.getInstance().getCurrentUser().SaveFile(TextAreaMain.getText()); 
			DataCenter.getInstance().getCurrentUser().saveToDataSubFolder(TextAreaMain.getText());
		}
	}
	
	public void OnSpellCheckMenuItemClicked(ActionEvent actionEvent) {
		TextAreaInfoDoc.setText("Misspelled Words:\n" + utils.Checker.getMisspelledWords(TextAreaMain.getText()));
	}
	
	public void OnLearnMenuItemClicked(ActionEvent actionEvent) {
		FileChooser fc = new FileChooser(); 
		fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
		File f = fc.showOpenDialog(null); 
		if(f != null) {
			DataCenter.getInstance().getMarkovBase().SetFile(f);
			MenuItemFileName.setText("File: " + DataCenter.getInstance().getMarkovBase().getFileName());
		}	
	}
	
	public void OnCreateMenuItemClicked(ActionEvent actionEvent) {
		DataCenter.getInstance().getCurrentUser().setLastFile(null);
		TextAreaMain.setText(DataCenter.getInstance().getMarkovBase().createSentence(TextFieldStartingWord.getText(), SpinnerLength.getValue()));
		TextAreaMain.setDisable(false);
		updateInfo();
	}
	
	public void OnExitMenuItemClicked(ActionEvent actionEvent) {	;
		DataCenter.getInstance();
		DataCenter.save();
		System.exit(0);
	}
	
	public void OnUndoMenuItemClicked(ActionEvent actionEvent) {
		TextAreaMain.undo();
	}
	
	public void OnWordCountMenuItemClicked(ActionEvent actionEvent) {
		utils.InfoDisplays.displayGenericInformation(LabelWordCount.getText());
	}
	
	public void OnSentenceCountMenuItemClicked(ActionEvent actionEvent) {
		utils.InfoDisplays.displayGenericInformation(LabelSentenceCount.getText());
	}
	
	public void OnFleschScoreMenuItemClicked(ActionEvent actionEvent) {
		utils.InfoDisplays.displayGenericInformation(LabelFleschScore.getText());
	}
	
	public void OnSpinnerActivated(MouseEvent mouseEvent) {
		TextAreaMain.setFont(Font.font(FontComboBox.getValue(), FontSpinner.getValue()));
	}
	
	public void OnFontAction(ActionEvent actionEvent) {
		TextAreaMain.setFont(Font.font(FontComboBox.getValue(), FontSpinner.getValue()));
	}
	
	public void TextAreaMainOnTyped(KeyEvent keyEvent) {
		if(keyEvent.getCharacter().equals(" ")) {
			updateInfo();
		}	
	}
	
	
	public void SwapScene(ActionEvent actionEvent, String resource, String SceneName){
		try {
			Parent secondRoot = FXMLLoader.load(getClass().getResource(resource));
			Scene secondScene = new Scene(secondRoot);
			Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
			window.setScene(secondScene);
			window.setTitle(SceneName); 
			window.show();	
		}catch(IOException e) {
			utils.InfoDisplays.displayGenericError("An Error has Occured"); 
		}
	}
	
	public void updateInfo() {
		LabelWordCount.setText("Word Count: " + utils.TextCounters.getWordCount(TextAreaMain.getText()));
		LabelSentenceCount.setText("Sentence Count: " + utils.TextCounters.getSentenceCount(TextAreaMain.getText()));
		LabelFleschScore.setText("Flesch Score: " + utils.TextCounters.getFleschScore(TextAreaMain.getText()));
		TextAreaInfoDoc.setText("Misspelled Words:\n" + utils.Checker.getMisspelledWords(TextAreaMain.getText()));
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(DataCenter.getInstance().getCurrentUser().getLastFile() != null) {
			TextAreaMain.setText(DataCenter.getInstance().getCurrentUser().loadFile());
			TextAreaMain.setDisable(false);
			updateInfo();
		}
		MenuItemFileName.setText("File: " + DataCenter.getInstance().getMarkovBase().getFileName());
		SpinnerLength.setValueFactory(svf);
		SpinnerTxtSize.setValueFactory(FontSpinner);
		FontComboBox.getItems().addAll("Verdana", "Helvetica", "Times New Roman", "Comic Sans MS", "Impact", "Lucida Sans Unicode");
	}

}
