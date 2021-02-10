package Main;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class settingController {
    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField password;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton cancel;
    @FXML
    private JFXTextField password2;

    @FXML
    void cancelAction(ActionEvent event) {
        Stage stg=(Stage)cancel.getScene().getWindow();
        stg.close();
    }

    @FXML
    void saveAction(ActionEvent event) {



            String username=userName.getText();
            String password=password2.getText();


            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to cahnge the settings ?");
            Optional<ButtonType> response=alert.showAndWait();


            if(response.get()==ButtonType.OK) {
                if(username.equals("")||(!(this.password.getText().equals(password)))||password.equals("")){
                    Alert alert2=new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("ERROR in feilds");
                    alert2.setHeaderText(null);
                    alert2.setContentText("MISSING OR password doesnot match");
                    alert2.showAndWait();
                    return;
                }
                Preference preference=Preference.getPreference();

                preference.setUserName(username);
                preference.setPassword(password);
                if(Preference.initPrefernce(preference)) {
                    Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Sucess");
                    alert1.setContentText("setting changed");
                    alert1.show();

                }else {
                    Alert alert1=new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Sucess");
                    alert1.setContentText("setting changed");
                    alert1.show();
                }
            }



    }
}
