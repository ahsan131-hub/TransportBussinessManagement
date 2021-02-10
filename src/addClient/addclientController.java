package addClient;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class addclientController {

    @FXML
    private JFXTextField companyName;

    @FXML
    private JFXTextField clientName;

    @FXML
    private JFXTextField contact;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXButton loadSaveAction;

    @FXML
    private JFXButton loadCancelAction;
    private dataBaseLoader dataHandler=dataBaseLoader.getInstance();

    @FXML
    void loadCancelAction(ActionEvent event) {
        Stage stage= (Stage) contact.getScene().getWindow();
        stage.close();


    }
    @FXML
    void loadSaveAction(ActionEvent event) {
    if (companyName.getText().equals("")||clientName.getText().equals("")||contact.getText().equals("")){
        Alert alert =new Alert(Alert.AlertType.ERROR);
        alert.setTitle("atleast provide basic information");
        alert.setContentText("comapany name, client name, contact are mendatory to be filled");
        alert.show();


    }else {
        String qu = "insert into addClient values(" +
                "'" + companyName.getText() + "'," +
                "'" + clientName.getText() + "'," +
                "'" + contact.getText() + "'," +
                "'" + address.getText() + "'" +
                ")";

        if (dataHandler.execAction(qu)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ADDED");
            alert.setContentText("succesfully Added client");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Could not Added");
            alert.setContentText("could not add the vehicle ");
            alert.show();

        }

    }

    }
}
