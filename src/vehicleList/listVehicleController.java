package vehicleList;

import AddVehicle.AddVehicleController;
import DataBaseHandler.dataBaseLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import rentAvehicle.ListReceiptController;
import rentAvehicle.PrintReceiptController;
import soledVehicle.soledController;
import util.Tutil;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class listVehicleController implements Initializable{

    @FXML
    private TableView<Vehicle> tableView;
    @FXML
    private TableColumn<Vehicle, String> vehicleNameCol;

    @FXML
    private TableColumn<Vehicle, String> vehicleNoCol;

    @FXML
    private TableColumn<Vehicle, String> typeCol;

    @FXML
    private TableColumn<Vehicle, String> availCol;

    @FXML
    private TableColumn<Vehicle, String> worthCol;

    @FXML
    private TableColumn<Vehicle, String> timeCol;

    dataBaseLoader dataBase=dataBaseLoader.getInstance();

    ObservableList<Vehicle> list= FXCollections.observableArrayList();

    @FXML
    void loadEditAction(ActionEvent event) {
        Vehicle vehicle=tableView.getSelectionModel().getSelectedItem();

        if(vehicle!=null){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucess");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to edit  the Vehicle");
            Optional<ButtonType> response=alert.showAndWait();

            if(response.get()==ButtonType.OK) {
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/AddVehicle/addVehicle.fxml"));
                Parent root= null;
                try {
                    root = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                AddVehicleController ref= loader.getController();
               // librarymanageUtil.setIcon(stg);
                ref.intiaFeild(vehicle);
                Stage stg=new Stage(StageStyle.DECORATED);
                Scene scene =new Scene(root);
                stg.setTitle("Edit Vehicle ");
                stg.setScene(scene);
                stg.show();

            }
        }
    }

    @FXML
    void loadRefreshAction(ActionEvent event) {
        try {
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    soledController ref=null;
    @FXML
    void loadSaleAction(ActionEvent event) {
       Vehicle vehicle=tableView.getSelectionModel().getSelectedItem();


        if(vehicle==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select the vehicle from table");
            alert.show();

            return;

        }

      FXMLLoader loader=new FXMLLoader(getClass().getResource("/soledVehicle/soledUi.fxml"));


        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        soledController ref= loader.getController();
        ref.setValues(vehicle);

        Stage stg=new Stage(StageStyle.DECORATED);

        Tutil.setIcon(stg);
        Scene scene =new Scene(root);
        stg.setTitle("details ");
        stg.setScene(scene);
        stg.show();




    }

    @FXML
    void loaddetailsAction(ActionEvent event) {
        Vehicle vehicle=tableView.getSelectionModel().getSelectedItem();
        if(vehicle==null){

            return;
        }
         FXMLLoader loader=new FXMLLoader(getClass().getResource("Vdetails.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        VdetailsController ref= loader.getController();
        // librarymanageUtil.setIcon(stg);
        ref.data(vehicle.getVname(),vehicle.getVtype(),vehicle.getEngineNo(),vehicle.getChasisNo(),vehicle.getError(),Integer.valueOf(vehicle.getPrice()).toString(),vehicle.getVehicleDecription(),vehicle.getLastOwnerName(),vehicle.getNic(),vehicle.getAddress(),vehicle.getPhoneNO(),vehicle.getLastOwnerDecription());
        Stage stg=new Stage(StageStyle.DECORATED);
        Scene scene =new Scene(root);
        Tutil.setIcon(stg);
        stg.setTitle("details ");
        stg.setScene(scene);
        stg.show();


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        initializeCol();

    }

    private void loadData() throws SQLException {
        list.clear();
        String query="Select * from vehicles";
        ResultSet rs=dataBase.execQuery(query);
        while (rs.next()){
            String Vname=rs.getString("vehicleName");
            String Vno=rs.getString("VehicleNo");
            String Vtype=rs.getString("type");
            Boolean avail=rs.getBoolean("isavail");
            int price=rs.getInt("totalPrice");
            Timestamp date=rs.getTimestamp("timeOfPurchase");
            String VLastOwner=rs.getString("lastOwnerName");
            String lastOwnerDecription=rs.getString("lastOwnerDecription");
            String phoneNO=rs.getString("phoneNO");
            String vehicleDecription=rs.getString("vehicleDecription");
            String error=rs.getString("error");
            String chasisNo=rs.getString("chasisNo");
            String engineNo=rs.getString("engineNo");
            String nic=rs.getString("nic");
            String address=rs.getString("address");

            Image image = null;
            InputStream fis= (rs.getBlob("images")).getBinaryStream();
            image=  new Image(fis);

            list.add(new  Vehicle(Vname, Vno, avail,Vtype,price, date, chasisNo,engineNo, error,vehicleDecription, VLastOwner,nic,phoneNO, lastOwnerDecription, image,address) );

        }

        tableView.setItems(list);
    }


    private void initializeCol() {
        vehicleNameCol.setCellValueFactory(new PropertyValueFactory<>("Vname"));
        vehicleNoCol.setCellValueFactory(new PropertyValueFactory<>("Vno"));
        availCol.setCellValueFactory(new PropertyValueFactory<>("isavail"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("Vtype"));
        worthCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    public List<Vehicle> getList() {
//        try {
//            loadData();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
       return list;
    }


    public void removeV(String str) {
    //    list.remove(vehicle);
    String q="delete  from vehicles where vehicleNo='"+str+"'";
        if(dataBase.execAction(q)){
            System.out.print("Removed");

        }else{
            System.out.print("!Removed");
        }
  }

    public static class Vehicle{
        private String Vname="";
        private String Vno="";
        private Boolean isavail=false;
        private String Vtype="";
        private String Address="";

        private int price=0;
        private Timestamp date=null;
        //above are shown in the TableView
        private final String  chasisNo;
        private final String engineNo;
        private final String error;
        private final String vehicleDecription;
        private final String lastOwnerName;
        private final String nic;
        private final String phoneNO;
        private String lastOwnerDecription="";

        Image image=null;

        public Vehicle(String vname, String vno, Boolean isavail, String vtype, int price, Timestamp date, String chasisNo, String engineNo, String error, String vehicleDecription, String lastOwnerName, String nic, String phoneNO, String lastOwnerDecription, Image image,String address) {
            Vname = vname;
            Vno = vno;
            this.isavail = isavail;
            Vtype = vtype;
            this.price = price;
            this.date = date;
            this.chasisNo = chasisNo;
            this.engineNo = engineNo;
            this.error = error;
            this.vehicleDecription = vehicleDecription;
            this.lastOwnerName = lastOwnerName;
            this.nic = nic;
            this.phoneNO = phoneNO;
            this.lastOwnerDecription = lastOwnerDecription;
            this.image = image;
            Address=address;
        }

        public String getAddress() {
            return Address;
        }

        public String getLastOwnerName() {
            return lastOwnerName;
        }

        public String getNic() {
            return nic;
        }



        public String getChasisNo() {
            return chasisNo;
        }

        public String getEngineNo() {
            return engineNo;
        }

        public String getError() {
            return error;
        }

        public String getVehicleDecription() {
            return vehicleDecription;
        }


        public String getPhoneNO() {
            return phoneNO;
        }

        public String getLastOwnerDecription() {
            return lastOwnerDecription;
        }

        public Image getImage() {
            return image;
        }

        public Timestamp getDate() {
            return date;
        }

        public String getVname() {
            return Vname;
        }

        public String getVno() {
            return Vno;
        }

        public Boolean getIsavail() {
            return isavail;
        }

        public String getVtype() {
            return Vtype;
        }

        public int getPrice() {
            return price;
        }
    }


}
