package rentAvehicle;

import DataBaseHandler.dataBaseLoader;
import addClient.listClient;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.collections.ObservableSequentialListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.io.filefilter.TrueFileFilter;
import vehicleList.listVehicleController;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

public class bookVehicleController implements Initializable {
    List<listClient.client> availClient= FXCollections.observableArrayList();
    List<listVehicleController.Vehicle> availVehicles= FXCollections.observableArrayList();
//    List<listClient> availVehicles= FXCollections.observableArrayList();



    @FXML
    private Label digReceipt;

    @FXML
    private JFXTextField manualreceipt;

    @FXML
    private DatePicker datePicker;

    @FXML
    private JFXTextField DriverName;

    @FXML
    private RadioButton privateBtn;

    @FXML
    private RadioButton companyBtn;

    @FXML
    private JFXTextField vehicleText;

    @FXML
    private MenuButton dropMenuV;

    @FXML
    private MenuItem menuItem;

    @FXML
    private RadioButton LocalClient;

    @FXML
    private RadioButton officialClient;

    @FXML
    private JFXTextField clientName;

    @FXML
    private MenuButton dropMenuClient;

    @FXML
    private MenuItem menuItemClient;

    @FXML
    private JFXTextField from;

    @FXML
    private JFXTextField destination;

    @FXML
    private JFXTextField Stuff;

    @FXML
    private JFXTextField totalRent;

    @FXML
    private JFXTextField commission;

    @FXML
    private JFXTextArea description;

    @FXML
    private JFXTextField driverPhone;


    @FXML
    private JFXTextField clientPhone;

    @FXML
    private Label officeNo;

    @FXML
    private JFXButton savebtn;

    @FXML
    private JFXButton printBtn;

    @FXML
    private JFXButton cancelbtn;

    ToggleGroup radioGroup = new ToggleGroup();
    ToggleGroup radioGroup2 = new ToggleGroup();
    private boolean bool=true;
    private boolean boolClient=true;
    private ArrayList<MenuItem> itemClient=new ArrayList<MenuItem>();;
    ArrayList<MenuItem> item = new ArrayList<MenuItem>();
    private dataBaseLoader dataBase=dataBaseLoader.getInstance();

    @FXML
    void datePicker(ActionEvent event) {

    }
     @FXML
    void menuItemAction(ActionEvent event) {
       // System.out.println(event.getEventType().getName());
         creatClientAction();
    }
    void setGroup(){
        privateBtn.setToggleGroup(radioGroup);
        companyBtn.setToggleGroup(radioGroup);
        officialClient.setToggleGroup(radioGroup2);
        LocalClient.setToggleGroup(radioGroup2);
    }

    Thread t = new Thread(() -> {
        // your code here ...
        String query="Select * from vehicles";
        ResultSet rs=dataBase.execQuery(query);
        try{
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
                //InputStream fis= (rs.getBlob("images")).getBinaryStream();
                image=null;

                availVehicles.add(new listVehicleController.Vehicle(Vname, Vno, avail,Vtype,price, date, chasisNo,engineNo, error,vehicleDecription, VLastOwner,nic,phoneNO, lastOwnerDecription, image,address) );

            }

        }catch(Throwable e){
            System.out.println(e.getLocalizedMessage());
        }
            System.out.print("completed");
    });

    Thread tClient = new Thread(() -> {
        // your code here ...
            String query="select * from addclient";
            ResultSet rs=dataBase.execQuery(query);

            try {
                while(rs.next()) {

                    String companyName=rs.getString("companyName");
                    String  clientName=rs.getString("clientName");
                    String contact=rs.getString("contact");
                    String address=rs.getString("address");

                    availClient.add(new listClient.client(companyName, clientName, contact, address));

                }
            }catch(Throwable e){
                System.out.println(e.getStackTrace());
            }



    });




    @FXML
    void pravateAction(ActionEvent event) {
        vehicleText.setDisable(false);
        dropMenuV.setDisable(true);
    }




    @FXML
    void companyAction(ActionEvent event) {
       dropMenuV.setDisable(false);
       vehicleText.setDisable(true);
     //   availVehicles.clear();
        if(bool){
                initializeDropMenu();



        }
        creatAction();
    }




    @FXML
    void getDriverNo(ActionEvent event) {
        if(!DriverName.equals("")){
        String query="select dcontact from employees where dName='"+DriverName.getText()+"'";
        ResultSet rs=dataBase.execQuery(query);
        try{while(rs.next()){
            driverPhone.setText(rs.getString("dcontact"));
        }}catch (Exception e){
            System.out.println(e.getStackTrace());
        }}
    }




    @FXML
    void LocalClientAction(ActionEvent event) {
        clientName.setDisable(false);
        dropMenuClient.setDisable(true);
   }




     @FXML
    void officialAction(ActionEvent event) {
        dropMenuClient.setDisable(false);
        clientName.setDisable(true);
        if(boolClient){
            Thread temp=new Thread(()->{

                initializeClientDropMenu();
                creatClientAction();
            });
            temp.start();
        }

    }

    private void creatClientAction() {

        for(int i=0;i<itemClient.size();i++){
            MenuItem temp=itemClient.get(i);
            itemClient.get(i).setOnAction((n)->{
                dropMenuClient.setText(temp.getText());
                String query="select contact from addclient where companyName ='"+temp.getText()+"'";
                ResultSet rs=dataBase.execQuery(query);
                try{while(rs.next()){
                    clientPhone.setText(rs.getString("contact"));
                }}catch (Exception e){
                    System.out.println(e.getStackTrace());
                }
             });
        }

    }




    private void initializeClientDropMenu() {
        for(int i=0;i<availClient.size();i++){
                itemClient.add(new MenuItem(availClient.get(i).getCompanyName()));
        }
        dropMenuClient.getItems().setAll(itemClient);
        boolClient=false;

    }




    private void creatAction() {
        for (int i = 0; i < item.size(); i++) {
            MenuItem temp = item.get(i);
            item.get(i).setOnAction((n) -> {
                dropMenuV.setText(temp.getText());

            });
        }

    }



    private void initializeDropMenu() {
        for(int i=0;i<availVehicles.size();i++){
            if(availVehicles.get(i).getIsavail()) {
                item.add(new MenuItem(availVehicles.get(i).getVno()));

            }
        }
        dropMenuV.getItems().setAll(item);
        bool=false;
    }




    @FXML
    void checkIsInt(ActionEvent event) {

        try{
        Double.parseDouble(totalRent.getText());
        }catch(NumberFormatException e){
            totalRent.clear();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Character are not allowed");
            alert.setTitle("Character are not amount");
            alert.showAndWait();
        }
    }


    @FXML
    void checkIsIntC(ActionEvent event) {

        try{
            Double.parseDouble(commission.getText());
        }catch(NumberFormatException e){
            commission.clear();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Character are not allowed");
            alert.setTitle("Character are not amount");
            alert.showAndWait();
        }
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        t.start();
        tClient.start();
        setGroup();
    }






    @FXML
    void loadCancelAction(ActionEvent event) {
        Stage stg= (Stage) savebtn.getScene().getWindow();
        stg.close();
    }




    @FXML
    void loadPrintAction(ActionEvent event) {

     //   ListReceiptController.receipt rep=new ListReceiptController.receipt()
        ListReceiptController.receipt rep=new ListReceiptController.receipt(digReceipt.getText(), manualreceipt.getText(), DriverName.getText(), vehicleText.getText(), dropMenuV.getText(), (clientName!=null?clientName.getText():dropMenuClient.getText()), from.getText(), destination.getText(), Stuff.getText(), totalRent.getText(),commission.getText(),description.getText(),driverPhone.getText(),clientPhone.getText(),officeNo.getText(),String.valueOf(datePicker.getValue()));
        if(rep==null){
            return;

        }
        FXMLLoader loader=new FXMLLoader(getClass().getResource("printReceiptUi.fxml"));


        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintReceiptController ref=(PrintReceiptController)loader.getController();
        ref.setValues(rep);
        // librarymanageUtil.setIcon(stg);
        Stage stg=new Stage(StageStyle.DECORATED);
        Scene scene =new Scene(root);
        stg.setTitle("details ");
        stg.setScene(scene);
        stg.show();

    }






    @FXML
    void loadSaveAction(ActionEvent event) {
        String cNAme = "";

        String pvtV = (vehicleText.isDisable() ? "" : vehicleText.getText());
        String cmpyV = (dropMenuV.isDisable() ? "" : dropMenuV.getText());
        cNAme = (clientName.isDisable() ? dropMenuClient.getText() : clientName.getText() + "-local");
       if ((vehicleText.isDisable()&&dropMenuV.isDisable())|| (destination.getText()).equals("")||cmpyV.equals("Select vehicle")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot remain blank");
            alert.setContentText("mendentory to give vehicle no#,Client Name, totalRent and destination ");
            alert.show();
            return;

       } else {
            String str = "insert into receipts (" + " mnlReceiptNO, Driver, pvtVehicleNo, cmpyVehicleNo, Client, Stufffrom, destination, Stuff, rent, comission, description, driverPhone, clientphone, OfficePhone, Rtime) values(" +//14

                    "'" + manualreceipt.getText() + "'," +   //1
                    "'" + DriverName.getText() + "'," +     //2
                    "'" + pvtV + "'," +//3
                    "'" + cmpyV + "'," +//4
                    "'" + cNAme + "'," +//5
                    "'" + from.getText() + "'," +//6
                    "'" + destination.getText() + "'," +
                    "'" + Stuff.getText() + "'," +//7
                    "'" + (totalRent.getText().equals("")? 0:Integer.parseInt(totalRent.getText())) + "'," +//8
                    "'" + (commission.getText().equals("")? 0:Integer.parseInt(commission.getText())) + "'," +//9
                    "'" + description.getText() + "'," +//10
                    "'" + driverPhone.getText() + "'," +//11
                    "'" + clientPhone.getText() + "'," +//12
                    "'" + "--  03128137265" + "'," +//13
                    "'" + datePicker.getValue() + "'" +//14

                    ")";
            System.out.println("Completed");
            if (dataBase.execAction(str)) {
                System.out.println("suxess");
                String query = "Update vehicles set isAvail='0' where vehicleNo='" + dropMenuV.getText() + "'";
                if (dataBase.execAction(query)) {
                      System.out.println("updated");
                } else {
                    System.out.println("updated not");
                }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setContentText("vehicle is Booked");
                    alert.show();

               } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setContentText("vehicle cannot  Booked ");
                    alert.show();

                System.out.println("suxess not");
            }

        }

      }
}
