package addClient;

import DataBaseHandler.dataBaseLoader;
import addEmplyee.listEmployeeController;
import com.jfoenix.controls.JFXButton;
import com.mysql.cj.xdevapi.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class listClient implements Initializable {



    ObservableList<client> list= FXCollections.observableArrayList();
    @FXML
    private javafx.scene.control.TableView<client> TableView;

    @FXML
    private TableColumn<client,String> companyCol;

    @FXML
    private TableColumn<client,String> clientCol;

    @FXML
    private TableColumn<client,String> contactCol;

    @FXML
    private TableColumn<client,String> AddressCol;

    @FXML
    private JFXButton refresh;
    @FXML
    private JFXButton DeleteClient;
    private dataBaseLoader handler=dataBaseLoader.getInstance();

    @FXML
    void DeleteClientAction(ActionEvent event) {
        client cl=TableView.getSelectionModel().getSelectedItem();
        String query="DELETE from addclient where contact='"+cl.getContact()+"'";
        if(handler.execAction(query)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Deleted ");
            alert.setContentText("succesfully Deleted client");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Could not Delete");
            alert.setContentText("could not Delete the Client ");
            alert.show();

        }
    }
    @FXML
    void loadRefreshAction(ActionEvent event) {
        loadData();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        initializeCol();
    }

    private void initializeCol() {
        clientCol.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        companyCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadData() {
        list.clear();
        String query="select * from addclient";
        ResultSet rs=handler.execQuery(query);

        try {
            while(rs.next()) {

                String companyName=rs.getString("companyName");
                String  clientName=rs.getString("clientName");
                String contact=rs.getString("contact");
                String address=rs.getString("address");

                list.add(new client(companyName, clientName, contact, address));

            }
        }catch(Throwable e){
            System.out.println(e.getStackTrace());
        }
        TableView.setItems(list);


    }


    public static class client{
    private String companyName;
    private String clientName;
    private String contact;
    private String address;

        public client(String companyName, String clientName, String contact, String address) {
            this.companyName = companyName;
            this.clientName = clientName;
            this.contact = contact;
            this.address = address;
        }

        public String getCompanyName() {
            return companyName;
        }

        public String getClientName() {
            return clientName;
        }

        public String getContact() {
            return contact;
        }

        public String getAddress() {
            return address;
        }
    }









}
