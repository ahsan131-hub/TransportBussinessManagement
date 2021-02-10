package rentAvehicle;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vehicleList.VdetailsController;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class ListReceiptController implements Initializable {

    @FXML
    private TableView<receipt> tableView;

    @FXML
    private TableColumn<receipt, String> receiptCol;

    @FXML
    private TableColumn<receipt, String> vNoCol;

    @FXML
    private TableColumn<receipt, String> rentCol;

    @FXML
    private TableColumn<receipt, String> desCol;

    @FXML
    private TableColumn<receipt, String> pvtVNoCol;

    @FXML
    private TableColumn<receipt, String> mnlRepCol;

    @FXML
    private JFXTextField searchD;

    @FXML
    private JFXTextField searchM;

    @FXML
    private JFXButton viewMore;

    dataBaseLoader dataBase=dataBaseLoader.getInstance();

    ObservableList<receipt> list= FXCollections.observableArrayList();

    FilteredList<receipt>  filteredList = new FilteredList(list,e->true);





    @FXML
    void searchActionD(KeyEvent event) {
        System.out.println("hitted the key");

        searchD.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate((Predicate<? super receipt>)(receipt std)->{
                String lowerCase=newValue.toLowerCase();
                if(newValue.isEmpty()||newValue==null){

                    System.out.println(" key");
                    return true;

                }else if (std.getDigReceipt().indexOf(lowerCase) != -1){
                    System.out.println("the key");
                    return  true;
                }else if (std.getMnlReceiptNO().indexOf(lowerCase) != -1){
                    System.out.println("the key");
                    return  true;
                }else if (std.getCmpyVehicleNo().indexOf(lowerCase) != -1){
                    System.out.println("the key");
                    return  true;
                }else if (std.getPvtVehicleNo().indexOf(lowerCase) != -1){
                    System.out.println("the key");
                    return  true;
                }
                return false;
            });

        });


        SortedList sort=new SortedList(filteredList);
        sort.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sort);
    }


    @FXML
    void viewMoreDetails(ActionEvent event) {
        receipt rep=tableView.getSelectionModel().getSelectedItem();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCol();
        loadData();

    }

    private void loadData() {
        String query="Select * from receipts";
        ResultSet rs=dataBase.execQuery(query);
          String digReceipt="";
         String mnlReceiptNO="";
         String Driver="";
         String pvtVehicleNo="";
         String cmpyVehicleNo="";
         String Client="";
         String Stufffrom="";
         String destination="";
         String Stuff="";
         String rent="";
         String comission="";
         String description="";
         String driverPhone="";
         String clientphone="";
         String OfficePhone="";
         String Rtime="";

        try{
            while (rs.next()) {
                mnlReceiptNO=rs.getString("mnlReceiptNO");
                digReceipt=rs.getString("digReceipt");
                 Driver=rs.getString("Driver");
                 pvtVehicleNo=rs.getString("pvtVehicleNo");
                 cmpyVehicleNo=rs.getString("cmpyVehicleNo");
                 Client=rs.getString("Client");
                 Stufffrom=rs.getString("Stufffrom");
                 destination=rs.getString("destination");
                 Stuff=rs.getString("Stuff");
                 rent=rs.getString("rent");
                 comission=rs.getString("comission");
                 description=rs.getString("description");
                 driverPhone=rs.getString("driverPhone");
                 clientphone=rs.getString("clientphone");
                 OfficePhone=rs.getString("OfficePhone");
                 Rtime=rs.getString("Rtime");

            list.add(new receipt(digReceipt, mnlReceiptNO, Driver, pvtVehicleNo, cmpyVehicleNo, Client, Stufffrom, destination, Stuff, rent, comission, description, driverPhone, clientphone, OfficePhone, Rtime));

            }
        } catch(Exception e){System.out.println(e.getMessage());}
    tableView.setItems(list);
        tableView.itemsProperty().set(filteredList);
    }

    private void initializeCol() {
        receiptCol.setCellValueFactory(new PropertyValueFactory<>("digReceipt"));
        desCol.setCellValueFactory(new PropertyValueFactory<>("destination"));
        rentCol.setCellValueFactory(new PropertyValueFactory<>("rent"));
        vNoCol.setCellValueFactory(new PropertyValueFactory<>("cmpyVehicleNo"));
        pvtVNoCol.setCellValueFactory(new PropertyValueFactory<>("pvtVehicleNo"));
        mnlRepCol.setCellValueFactory(new PropertyValueFactory<>("mnlReceiptNO"));
    }

    public static class receipt  {

        private  String digReceipt="";
        private String mnlReceiptNO="";
        private String Driver="";
        private String pvtVehicleNo="";
        private String cmpyVehicleNo="";
        private String Client="";
        private String Stufffrom="";
        private String destination="";
        private String Stuff="";
        private String rent="";
        private String comission="";
        private String description="";
        private String driverPhone="";
        private String clientphone="";
        private String OfficePhone="";
        private String Rtime="";

        public receipt(String digReceipt, String mnlReceiptNO, String driver, String pvtVehicleNo, String cmpyVehicleNo, String client, String stufffrom, String destination, String stuff, String rent, String comission, String description, String driverPhone, String clientphone, String officePhone, String rtime) {
            this.digReceipt = digReceipt;
            this.mnlReceiptNO = mnlReceiptNO;
            Driver = driver;
            this.pvtVehicleNo = pvtVehicleNo;
            this.cmpyVehicleNo = cmpyVehicleNo;
            Client = client;
            Stufffrom = stufffrom;
            this.destination = destination;
            Stuff = stuff;
            this.rent = rent;
            this.comission = comission;
            this.description = description;
            this.driverPhone = driverPhone;
            this.clientphone = clientphone;
            OfficePhone = officePhone;
            Rtime = rtime;
        }

        public String getDigReceipt() {
            return digReceipt;
        }

        public String getMnlReceiptNO() {
            return mnlReceiptNO;
        }

        public String getDriver() {
            return Driver;
        }

        public String getPvtVehicleNo() {
            return pvtVehicleNo;
        }

        public String getCmpyVehicleNo() {
            return cmpyVehicleNo;
        }

        public String getClient() {
            return Client;
        }

        public String getStufffrom() {
            return Stufffrom;
        }

        public String getDestination() {
            return destination;
        }

        public String getStuff() {
            return Stuff;
        }

        public String getRent() {
            return rent;
        }

        public String getComission() {
            return comission;
        }

        public String getDescription() {
            return description;
        }

        public String getDriverPhone() {
            return driverPhone;
        }

        public String getClientphone() {
            return clientphone;
        }

        public String getOfficePhone() {
            return OfficePhone;
        }

        public String getRtime() {
            return Rtime;
        }
    }






}

