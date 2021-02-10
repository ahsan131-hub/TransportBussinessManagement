package addEmplyee;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class addemplyeeController {
    @FXML
    private JFXTextField dName;

    @FXML
    private JFXTextField dphoneNo;

    @FXML
    private JFXTextField dAddress;

    @FXML
    private JFXTextField dnic;

    @FXML
    private JFXTextField dSalary;

    @FXML
    private JFXTextField dDescription;

    @FXML
    private JFXButton add;
    @FXML
    private JFXButton cancel;
    private dataBaseLoader dataHandler=dataBaseLoader.getInstance();

    @FXML
    void LoadEditAction(ActionEvent event) {

    }

    @FXML
    void loadAddAction(ActionEvent event) {
        if (dName.getText().equals("")||dnic.getText().equals("")||dphoneNo.getText().equals("")){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("atleast provide basic information");
            alert.setContentText("Driver name,NIC,contact are mendatory to be filled");
            alert.show();
        }
        else{


                String query = "insert into employees(dName, dnic, dcontact, daddress, dsalary, dDescription)values(" +
                        "'" + dName.getText() + "'," +
                        "'" + dnic.getText() + "'," +
                        "'" + dphoneNo.getText() + "'," +
                        "'" + dAddress.getText() + "'," +
                        "'" + Integer.parseInt(dSalary.getText()) + "'," +
                        "'" + dDescription.getText() + "'" +
                        ")";


                if (dataHandler.addEmployee(query)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("ADDED");
                    alert.setContentText("succesfully Added sucessfully");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Could not Added");
                    alert.setContentText("could not add the vehicle ");
                    alert.show();

                }
            }
    }

    @FXML
    void loadCancelAction(ActionEvent event) {
        Stage stage= (Stage) cancel.getScene().getWindow();
        stage.close();
    }


}
