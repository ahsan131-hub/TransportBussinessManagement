package Main;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import soledVehicle.soledController;
import util.Tutil;
import vehicleList.listVehicleController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class BookingController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private JFXButton bookingBtn;

    @FXML
    private JFXButton receiptsList;
    @FXML
    private JFXButton arrival;

    @FXML
    private JFXButton refereshBtn;

    @FXML
    private TableView<listVehicleController.Vehicle> tableView;

    @FXML
    private TableColumn<listVehicleController.Vehicle, String> noCol;


    dataBaseLoader dataBase=dataBaseLoader.getInstance();

    private LoaderClass loader=new LoaderClass();
    private ObservableList<listVehicleController.Vehicle> list= FXCollections.observableArrayList();

    @FXML
    void receiptsListAcion(ActionEvent event) {
        loader.loadWindow("/rentAvehicle/listReceipt.fxml","Receipts List ");

    }
    @FXML
    void bookingAction(ActionEvent event) {
        loader.loadWindow("/rentAvehicle/bookVehicleUi.fxml","Book vehicle ");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        initializeCol();
    }

    private void loadData() {
        list.clear();
        String query="Select * from vehicles where isavail='0'";
        ResultSet rs=dataBase.execQuery(query);
       try {
           while (rs.next()) {
               String Vname = rs.getString("vehicleName");
               String Vno = rs.getString("VehicleNo");
               String Vtype = rs.getString("type");
               Boolean avail = rs.getBoolean("isavail");
               int price = rs.getInt("totalPrice");
               Timestamp date = rs.getTimestamp("timeOfPurchase");
               String VLastOwner = rs.getString("lastOwnerName");
               String lastOwnerDecription = rs.getString("lastOwnerDecription");
               String phoneNO = rs.getString("phoneNO");
               String vehicleDecription = rs.getString("vehicleDecription");
               String error = rs.getString("error");
               String chasisNo = rs.getString("chasisNo");
               String engineNo = rs.getString("engineNo");
               String nic = rs.getString("nic");
               String address = rs.getString("address");

               Image image = null;
               InputStream fis = (rs.getBlob("images")).getBinaryStream();
               image = new Image(fis);

               list.add(new listVehicleController.Vehicle(Vname, Vno, avail, Vtype, price, date, chasisNo, engineNo, error, vehicleDecription, VLastOwner, nic, phoneNO, lastOwnerDecription, image, address));

           }
       }catch(Exception e){ System.out.print("erroe in add v into arrivall ");e.getStackTrace();}
        tableView.setItems(list);

    }

    private void initializeCol() {
        noCol.setCellValueFactory(new PropertyValueFactory<>("Vno"));












    }

    @FXML
    void loadRefreshAction(ActionEvent event) {
        loadData();
    }

    @FXML
    void loadArriveAction(ActionEvent event) {
        listVehicleController.Vehicle v=tableView.getSelectionModel().getSelectedItem();

        FXMLLoader loader=new FXMLLoader(getClass().getResource("arrival.fxml"));


        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        arrivalController ref= loader.getController();
        if(v==null){
            Alert al=new Alert(Alert.AlertType.INFORMATION);
            al.setContentText("Select Vehicle");
            al.showAndWait();
            return;

        }
        ref.setV(v);
        Stage stg=new Stage(StageStyle.DECORATED);
         Tutil.setIcon(stg);
        Scene scene =new Scene(root);
        stg.setTitle("details ");
        stg.setScene(scene);
        stg.show();
    }


}
