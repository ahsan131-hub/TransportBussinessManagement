package Main;

import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class loginController {
	  
    @FXML
    private JFXTabPane tabpane;

	    @FXML
	    private JFXTextField userName;

	    @FXML
	    private JFXPasswordField password;

	    @FXML
	    private JFXButton loginButton;
	//private Preference preference;
	LoaderClass load=new LoaderClass();
	@FXML
	    void loadCancelAction(ActionEvent event) {
		   System.exit(0);
	    }
	    @FXML
	    void loadLoginAction(ActionEvent event) {
			Preference preference = Preference.getPreference();
	    	if(userName.getText().equals(preference.getUserName())&&DigestUtils.shaHex(password.getText()).equals(preference.getPassword())) {
	    		closeStage();
	    		load.loadWindow("MainUi.fxml","TMS");
	    	
	    	}else {
	    		userName.getStyleClass().add("wrong-credentials");
	    		password.getStyleClass().add("wrong-credentials");
	    	}
	    	
	    }


	    void closeStage(){
	    	 Stage stage=(Stage) userName.getScene().getWindow();
		    	stage.close();
	    }
	    
}
