package soledVehicle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Tutil;
import vehicleList.VdetailsController;
import vehicleList.listVehicleController;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class soledListController implements Initializable {

    dataBaseLoader database= dataBaseLoader.getInstance();
    ObservableList<soled> list = FXCollections.observableArrayList();
    @FXML
    private TableView<soled> tableView;

    @FXML
    private TableColumn<soled, String> noCol;

    @FXML
    private TableColumn<soled, String> vNameCol;

    @FXML
    private TableColumn<soled, String> priceCol;

    @FXML
    private TableColumn<soled, String> dateCol;

    @FXML
    private JFXButton more;

    @FXML
    void moreDetailsAction(ActionEvent event) {
        soledListController.soled vSoled =tableView.getSelectionModel().getSelectedItem();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("moreDetailsUi.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        moreDetailsController ref=(moreDetailsController)loader.getController();
        // librarymanageUtil.setIcon(stg);
        ref.setValues(vSoled);
        Stage stg=new Stage(StageStyle.DECORATED);
        Scene scene =new Scene(root);
        Tutil.setIcon(stg);
        stg.setTitle("details ");
        stg.setScene(scene);
        stg.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        initializeCol();

    }

    private void loadData() {

        String query ="select * from soled";
        ResultSet rs=database.execQuery(query);
       try{
           while(rs.next()){
                 String vehicleNo=rs.getString("vehicleNo");
                 String vehicleName=rs.getString("vehicleName");
                 String type=rs.getString("type");
                 String chasisNo =rs.getString("chasisNo");
                 String engineNo=rs.getString("engineNo");
                 String totalPrice=rs.getString("totalPrice");
                 String error=rs.getString("error");
                 String vehicleDecription=rs.getString("vehicleDecription");
                 Timestamp timeOfPurchase=rs.getTimestamp("timeOfPurchase");
                 String lastOwnerName=rs.getString("lastOwnerName");
                 String nic=rs.getString("nic");
                 String phoneNO=rs.getString("phoneNO");
                 String lastOwnerDecription=rs.getString("lastOwnerDecription");
                 Boolean isavail=rs.getBoolean("isavail");
                 String address=rs.getString("address");
                 String saleTo=rs.getString("saleTo");
                 String pNic=rs.getString("pNic");
                 String pAddress=rs.getString("pAddress");
                 String pPhone=rs.getString("pPhone");
                 int saleAmount=rs.getInt("saleAmount");

                 Timestamp timeOfSale=rs.getTimestamp("timeOfSale");
                 String saleDescription =rs.getString("saleDescription");

               list.add(new soled(vehicleNo, vehicleName, type, chasisNo, engineNo, totalPrice, error, vehicleDecription, timeOfPurchase, lastOwnerName, nic, phoneNO, lastOwnerDecription, isavail, address, saleTo, pNic, pAddress, pPhone, saleAmount, timeOfSale, saleDescription));

              // String queryDel="DELETE FROM vehicles where vehicleNo="+"'"+vehicleNo+"'";
              // database.deletVehicle(queryDel);

           }

       }catch(Throwable e){
           System.out.println(e.getStackTrace());
       }

            tableView.setItems(list);

    }

    private void initializeCol() {
        noCol.setCellValueFactory(new PropertyValueFactory<>("vehicleNo"));
        vNameCol.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("saleAmount"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("timeOfSale"));
    }


    public class soled{
        private  String vehicleNo;
        private  String vehicleName;
        private  String type;
        private  String chasisNo ;
        private  String engineNo;
        private  String totalPrice;
        private  String error;
        private  String vehicleDecription;
        private Timestamp timeOfPurchase;
        private  String lastOwnerName;
        private  String nic;
        private  String phoneNO;
        private  String lastOwnerDecription;
        private  Boolean isavail ;
        private  String address;
        private  String saleTo;
        private  String pNic;
        private  String pAddress;
        private  String pPhone;
        private  int saleAmount;
        private Timestamp timeOfSale;
        private  String saleDescription ;


        public soled(String vehicleNo, String vehicleName,
                     String type, String chasisNo, String engineNo,
                     String totalPrice, String error, String vehicleDecription,
                     Timestamp timeOfPurchase, String lastOwnerName, String nic,
                     String phoneNO, String lastOwnerDecription, Boolean isavail,
                     String address, String saleTo, String pNic, String pAddress, String pPhone,
                     int saleAmount, Timestamp timeOfSale, String saleDescription) {
            this.vehicleNo = vehicleNo;
            this.vehicleName = vehicleName;
            this.type = type;
            this.chasisNo = chasisNo;
            this.engineNo = engineNo;
            this.totalPrice = totalPrice;
            this.error = error;
            this.vehicleDecription = vehicleDecription;
            this.timeOfPurchase = timeOfPurchase;
            this.lastOwnerName = lastOwnerName;
            this.nic = nic;
            this.phoneNO = phoneNO;
            this.lastOwnerDecription = lastOwnerDecription;
            this.isavail = isavail;
            this.address = address;
            this.saleTo = saleTo;
            this.pNic = pNic;
            this.pAddress = pAddress;
            this.pPhone = pPhone;
            this.saleAmount = saleAmount;
            this.timeOfSale = timeOfSale;
            this.saleDescription = saleDescription;
        }


        public String getVehicleNo() {
            return vehicleNo;
        }

        public String getVehicleName() {
            return vehicleName;
        }

        public String getType() {
            return type;
        }

        public String getChasisNo() {
            return chasisNo;
        }

        public String getEngineNo() {
            return engineNo;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public String geterror() {
            return error;
        }

        public String getVehicleDecription() {
            return vehicleDecription;
        }

        public Timestamp getTimeOfPurchase() {
            return timeOfPurchase;
        }

        public String getLastOwnerName() {return lastOwnerName;}

        public String getNic() {return nic; }

        public String getPhoneNO() {return phoneNO;}

        public String getLastOwnerDecription() {return lastOwnerDecription;}

        public Boolean getIsavail() {return isavail;}

        public String getAddress() { return address;}

        public String getSaleTo() {return saleTo;}

        public String getpNic() {return pNic;}

        public String getpAddress() {return pAddress;}

        public String getpPhone() { return pPhone; }

        public int getSaleAmount() {return saleAmount;}

        public Timestamp getTimeOfSale() { return timeOfSale;  }

        public String getSaleDescription() { return saleDescription; }
    }






}
