package vehicleList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VdetailsController {

  //  listVehicleController.Vehicle vehicle=

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
    private Label lName;

    @FXML
    private Label lNic;

    @FXML
    private Label address;

    @FXML
    private Label lphone;

    @FXML
    private Label ldescription;


    public void data(String vname, String vtype, String engineNo, String chasisNo, String error, String toString, String vehicleDecription, String lastOwnerName, String nic, String address, String phoneNO, String lastOwnerDecription) {

        Vname.setText(vname);
        Vtype.setText(vtype);
        engine.setText(engineNo);
        chasis.setText(chasisNo);
        this.error.setText(error);
        price.setText(toString);
        Vdescription.setText(vehicleDecription);
        lName.setText(lastOwnerName);
        lNic.setText(nic);
        this.address.setText(address);
        lphone.setText(phoneNO);
        ldescription.setText(vehicleDecription);


    }
}
