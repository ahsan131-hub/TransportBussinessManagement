package rentAvehicle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PrintReceiptController {

    @FXML
    private Label digReceipt;

    @FXML
    private Label manualreceipt;

    @FXML
    private Label datePicker;

    @FXML
    private Label DriverName;

    @FXML
    private Label vehicleText;

    @FXML
    private Label clientName;

    @FXML
    private Label from;

    @FXML
    private Label destination;

    @FXML
    private Label stuff;

    @FXML
    private Label totalRent;

    @FXML
    private Label commission;

    @FXML
    private Label description;

    @FXML
    private Label driverPhone;

    @FXML
    private Label clientPhone;

    @FXML
    private Label officeNo;

    @FXML
    private JFXButton cancelbtn;

    @FXML
    void loadCancelAction(ActionEvent event) {
    Stage stg= (Stage) cancelbtn.getScene().getWindow();
    stg.close();
    }

    public void setValues(ListReceiptController.receipt rep) {
        digReceipt.setText(rep.getDigReceipt());
        manualreceipt.setText(rep.getMnlReceiptNO());
        datePicker.setText(rep.getRtime());
        DriverName.setText(rep.getDriver());
        clientName.setText(rep.getClient());
        vehicleText.setText(rep.getCmpyVehicleNo()!=null ? rep.getCmpyVehicleNo():rep.getPvtVehicleNo());
        from.setText(rep.getStufffrom());
        destination.setText(rep.getDestination());
        stuff.setText(rep.getStuff());
        totalRent.setText(rep.getRent());
        commission.setText(rep.getComission());
        description.setText(rep.getDescription());
        driverPhone.setText(rep.getDriverPhone());
        clientPhone.setText(rep.getClientphone());
        officeNo.setText(rep.getOfficePhone());




    }
}
