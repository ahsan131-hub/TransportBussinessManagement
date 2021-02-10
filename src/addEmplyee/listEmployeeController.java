package addEmplyee;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class listEmployeeController implements Initializable {
    dataBaseLoader handler= dataBaseLoader.getInstance();
    ObservableList<employee> list= FXCollections.observableArrayList();


    @FXML
    private TableView<employee> tableView;

    @FXML
    private TableColumn<employee,String> nameCol;

    @FXML
    private TableColumn<employee,String> phoneCol;

    @FXML
    private TableColumn<employee,String> nicCol;

    @FXML
    private TableColumn<employee,String> addressCol;

    @FXML
    private TableColumn<employee,String> salaryCol;

    @FXML
    private TableColumn<employee,String> desCol;

    @FXML
    private JFXButton delete;

    @FXML
    private JFXButton close;
    @FXML
    private JFXButton refresh;

    @FXML
    void loadCloseAction(ActionEvent event) {
        Stage stage= (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadDeleteAction(ActionEvent event) {
        employee emp=tableView.getSelectionModel().getSelectedItem();
        handler.deleteEmployee(emp);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeCol();
        LoadData();
    }

    private void LoadData() {
        list.clear();
        String query="select * from employees";
        ResultSet rs=handler.execQuery(query);
        try {
            while (rs.next()) {
                String dName=rs.getString("dName");
                String dni=rs.getString("dnic");
                String dcontact=rs.getString("dcontact");
                String daddress=rs.getString("daddress");
                Integer dsalary=rs.getInt("dsalary");
                String dDescription=rs.getString("dDescription");

                list.add(new employee(dName, dni, dcontact, daddress, dsalary, dDescription));


            }
        }catch(Throwable e){
            System.out.println(e.getStackTrace());
        }
    tableView.setItems(list);

    }
    @FXML
    void loadRefreshAction(ActionEvent event) {
        LoadData();
    }

    private void initializeCol() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        nicCol.setCellValueFactory(new PropertyValueFactory<>("dnic"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("dcontact"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("daddress"));
        salaryCol.setCellValueFactory(new PropertyValueFactory<>("dsalary"));
        desCol.setCellValueFactory(new PropertyValueFactory<>("des"));

    }
    public class employee{
        private String Name;
        private String dnic;
        private String dcontact;
        private String daddress;
        private int dsalary;
        private String des;

        public employee(String dName, String dnic, String dcontact, String daddress,int dsalary, String dDescription) {
            this.Name = dName;
            this.dnic = dnic;
            this.dcontact = dcontact;
            this.daddress = daddress;
            this.dsalary = dsalary;
            this.des = dDescription;
        }

        public String getName() {
            return Name;
        }

        public String getDes() {
            return des;
        }

        public String getDnic() {
            return dnic;
        }

        public String getDcontact() {
            return dcontact;
        }

        public String getDaddress() {
            return daddress;
        }

        public int  getDsalary() {
            return dsalary;
        }

    }


}
