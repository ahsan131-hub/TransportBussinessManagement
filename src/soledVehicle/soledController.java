package soledVehicle;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import vehicleList.VdetailsController;
import vehicleList.listVehicleController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class soledController implements Initializable {


    dataBaseLoader dataBase= dataBaseLoader.getInstance();

    @FXML
    private JFXTextField Vno;

    @FXML
    private JFXTextField saleTo;

    @FXML
    private JFXTextField pNic;

    @FXML
    private JFXTextField Paddress;

    @FXML
    private JFXTextField pPhone;

    @FXML
    private JFXTextField totalAmount;

    @FXML
    private JFXTextArea saleDescrption;

    @FXML
    private JFXButton salebtn;

    @FXML
    private JFXButton cancelbtn;

    listVehicleController.Vehicle vehicle=null;
    @FXML
    void loadCancelAction(ActionEvent event) {
        Stage stage= (Stage) saleDescrption.getScene().getWindow();
        stage.close();
    }
    public void setValues(listVehicleController.Vehicle vehicle) {
        this.vehicle=vehicle;
        // String num=Vno.getText();
        Vno.setText(vehicle.getVno());

    }
    @FXML
    void loadSaleAction(ActionEvent event) {

        String num=Vno.getText();
        String saleto=saleTo.getText();
        String pnic=pNic.getText();
        String phone=pPhone.getText();
        String amount=totalAmount.getText();
        String address=Paddress.getText();
        String saledescrption=saleDescrption.getText();
        if(num.equals("")||saleto.equals("")||pnic.equals("")||amount.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot remain blank");
            alert.setContentText("mendentory to give vehicle no#,vehicle name, NIC and price ");
            alert.show();


        }else {

            try {
                if (dataBase.saleVehicle(num, saleto, pnic, phone, amount, address, saledescrption)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle(" vehicle soled");
                    alert.setContentText("succesfully soled vehicle ");
                    alert.show();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("/vehicleList/listVehicle.fxml"));
                    Parent root= null;
                    try {
                        root = loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    listVehicleController ref=loader.getController();
                         ref.removeV(Vno.getText());
//                    String queryDel="DELETE FROM vehicles where vehicleNo ="+"'"+Vno.getText()+"'";
//                    if(dataBase.execAction(queryDel)){
//                        System.out.println("vehicles deleted");
//                    }else{
//                        System.out.println("vehicles cannot be deleted");
//                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("cannot soled vehicle ");
                    alert.show();


                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }





}
