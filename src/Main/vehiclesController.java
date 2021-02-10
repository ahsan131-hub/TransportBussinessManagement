package Main;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class vehiclesController {

    @FXML
    private JFXTextField checkV;

    @FXML
    private Label checkVName;

    @FXML
    private Label checkkVno;
    @FXML
    private MenuItem Fileclose;
    @FXML
    private Label checkVLast;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private MenuItem aboutUs;
    @FXML
    private JFXButton addNewVbtn;

    @FXML
    private JFXButton viewListBtn;

    @FXML
    private JFXButton saleVBtn;

    @FXML
    private JFXButton viewList;

    @FXML
    private JFXButton bookingBtn;

    @FXML
    private JFXButton receiptsList;
    @FXML
    private Label checkSaleTo;
//    @FXML
//    private JFXButton GO_BACK;

    @FXML
    private JFXButton addcBtn;

    @FXML
    private JFXButton viewCBtn;

    @FXML
    private JFXButton employeeBtn;

    @FXML
    private JFXButton viewEmpBtn;
    LoaderClass loader=new LoaderClass();
    private dataBaseLoader handler=dataBaseLoader.getInstance();

    @FXML
    void addClientAction(ActionEvent event) {
        loader.loadWindow("/addClient/ADDCLIENT.fxml","ADD new Employee ");
    }

    @FXML
    void aboutUs(ActionEvent event) {
        loader.loadWindow("about.fxml","About US ");
    }
    @FXML
    void addEmployeeAction(ActionEvent event) {
        loader.loadWindow("/addEmplyee/addEmplyeeUi.fxml","View Clients ");
    }
    @FXML
    void listClientAction(ActionEvent event) {
        loader.loadWindow("/addClient/listClientUi.fxml","View Clients ");

    }
    @FXML
    void viewEmployeeAction(ActionEvent event) {
        loader.loadWindow("/addEmplyee/listEmployeeUi.fxml","View Employees ");
    }

    @FXML
    void addVAction(ActionEvent event) {
        loader.loadWindow("/AddVehicle/addVehicle.fxml","PURCHASE NEW VEHICLE ");
    }

    @FXML
    void saleAction(ActionEvent event) {

        loader.loadWindow("/soledVehicle/soledUi.fxml","Available Vehicles ");
    }

    @FXML
    void viewAvailAction(ActionEvent event) {
        loader.loadWindow("/vehicleList/listVehicle.fxml","SALE  VEHICLE ");
    }

    @FXML
    void viewSoledAction(ActionEvent event) {
        loader.loadWindow("/soledVehicle/soledListUi.fxml","Soled Vehicles ");
    }
    @FXML
    void closeProgram(ActionEvent event) {
        System.exit(0);
    }

//    @FXML
//    void goBackAction(ActionEvent event) {
//        AnchorPane root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        anchorRoot.getChildren().setAll(root);
//
//
//    }




    @FXML
    void checkVLoader(ActionEvent event) {
        String query = "select * from vehicles where vehicleNo='" + checkV.getText() + "'";
        ResultSet rs = handler.execQuery(query);
        Boolean flag = false;
        try {
            while (rs.next()) {
                checkVName.setText(rs.getString("vehicleName"));
                checkVLast.setText(rs.getString("lastOwnerName"));
                checkkVno.setText(rs.getString("engineNo"));
                checkSaleTo.setText("Not soled to any one");
                flag = true;
            }
            if (!flag) {
                String query2 = "select * from soled where vehicleNo='" + checkV.getText() + "'";
                ResultSet rs2 = handler.execQuery(query2);
                while (rs2.next()) {
                    checkVName.setText(rs2.getString("vehicleName"));
                    checkVLast.setText(rs2.getString("lastOwnerName"));
                    checkkVno.setText(rs2.getString("engineNo"));
                    checkSaleTo.setText(rs2.getString("saleTo"));
                    flag = true;
                }
                if (!flag) {
                    checkVName.setText("No such Such available");
                }

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
