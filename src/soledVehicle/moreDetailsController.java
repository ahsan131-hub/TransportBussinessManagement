package soledVehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class moreDetailsController {
    @FXML
    private Label Vname;

    @FXML
    private Label Vtype;

    @FXML
    private Label chasis;

    @FXML
    private Label engine;

    @FXML
    private Label error;

    @FXML
    private Label price;

    @FXML
    private Label Vdescription;

    @FXML
    private Label dateBuyed;

    @FXML
    private Label lName;

    @FXML
    private Label lNic;

    @FXML
    private Label address;

    @FXML
    private Label lphone;

    @FXML
    private Label ldescription;

    @FXML
    private Label revenue;

    @FXML
    private Label pName;

    @FXML
    private Label pNic;

    @FXML
    private Label pPhone;

    @FXML
    private Label pAddress;

    @FXML
    private Label salePrice;

    @FXML
    private Label Pdescription;

    @FXML
    private Label dateSoled;

    @FXML
    private JFXButton closebtn;
    @FXML
    void close(ActionEvent event) {
        Stage stage= (Stage) dateSoled.getScene().getWindow();
        stage.close();

    }

    public void setValues(soledVehicle.soledListController.soled vSoled) {

        Vname.setText(vSoled.getVehicleName());
        Vtype.setText(vSoled.getType());
        chasis.setText(vSoled.getChasisNo());
        engine.setText(vSoled.getEngineNo());
        error.setText(vSoled.geterror());
        price.setText(vSoled.getTotalPrice());
        Vdescription.setText(vSoled.getVehicleDecription());
        dateBuyed.setText(String.valueOf(vSoled.getTimeOfPurchase()));
        lNic.setText(vSoled.getNic());
        address.setText(vSoled.getAddress());
        lphone.setText(vSoled.getPhoneNO());
        ldescription.setText(vSoled.getLastOwnerDecription());
        Integer i =   vSoled.getSaleAmount()- Integer.parseInt(vSoled.getTotalPrice());
        revenue.setText(i.toString());
        pName.setText(vSoled.getSaleTo());
        pNic.setText(vSoled.getpNic());
        pPhone.setText(vSoled.getpPhone());
        pAddress.setText(vSoled.getpAddress());
        salePrice.setText(String.valueOf(vSoled.getSaleAmount()));
        Pdescription.setText(vSoled.getSaleDescription());
        dateSoled.setText(vSoled.getTimeOfSale().toString());
        salePrice.setText(String.valueOf(vSoled.getSaleAmount()));


    }
}
