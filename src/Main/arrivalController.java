package Main;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import rentAvehicle.ListReceiptController;
import vehicleList.listVehicleController;

import java.sql.ResultSet;
import java.util.Optional;

public class arrivalController {
    private  listVehicleController.Vehicle vehicle;
    String city;
    int TotalRent=0;
    DataBaseHandler.dataBaseLoader handler= dataBaseLoader.getInstance();
    @FXML
    private JFXTextField diesel;

    @FXML
    private JFXTextField food;

    @FXML
    private JFXTextField police;

    @FXML
    private JFXTextField challan;

    @FXML
    private JFXTextField vMaintanence;

    @FXML
    private JFXTextField distance;

    @FXML
    private JFXTextField returnRent;
    @FXML
    private JFXTextField description;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton cancel;

    public  void setV(listVehicleController.Vehicle v) {
        vehicle=v;

    String query="select * from receipts where cmpyVehicleNo='"+vehicle.getVno()+"'";
        ResultSet rs=handler.execQuery(query);
      try {
          while (rs.next()) {
            TotalRent=rs.getInt("rent");
            city=rs.getString("destination");
          }
      }catch(Exception e){e.getStackTrace();}
        System.out.print("sucess gett");
    }

    @FXML
    void loadCAncelAction(ActionEvent event) {
        Stage stage= (Stage) distance.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadSaveAction(ActionEvent event) {

        String Vno=vehicle.getVno();
        int police=Integer.parseInt(this.police.getText());
        int diesel =Integer.parseInt(this.diesel.getText());;
        int food=Integer.parseInt(this.food.getText());;
        int vMaintanence=Integer.parseInt(this.vMaintanence.getText());;
        String dDescription=(this.description.getText());
        int returnRent=Integer.parseInt(this.returnRent.getText());;
        int distance=Integer.parseInt(this.distance.getText());;
        int profit=TotalRent-diesel-food-vMaintanence-police+returnRent;
        System.out.print(profit);
        String query2="insert into bstatistics(" +
                "Vno," +
                " police, " +
                "diesel," +
                " food," +
                " vMaintanence," +
                " dDescription, " +
                "returnRent," +
                " distance," +
                " profit," +
                " city) values(" +
                "'" + Vno+"',"+
                "'" + police+"',"+
                "'" + diesel+"',"+
                "'" + food+"',"+
                "'" + vMaintanence+"',"+
                "'" + dDescription+"',"+
                "'" + returnRent+"',"+
                "'" + distance+"',"+
                "'" + profit+"',"+
                "'" + city+"'"+
                ")";
        Alert alert2=new Alert(Alert.AlertType.CONFIRMATION);
        alert2.setTitle("Sucess");
        alert2.setHeaderText(null);
        alert2.setContentText("Do you want to submit the with the profit of "+profit);
        Optional<ButtonType> response=alert2.showAndWait();
        if(response.get()==ButtonType.OK) {
            if(handler.execAction(query2)){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucessfully arrived");
            alert.setContentText("");
            alert.showAndWait();
            String query="update vehicles set isavail='1' where vehicleNo='"+vehicle.getVno()+"'";
            if(handler.execAction(query)){
                Alert alert3=new Alert(Alert.AlertType.INFORMATION);
                alert3.setTitle("success");
                alert3.setContentText("vehicle arrival completed");
                alert3.showAndWait();
            }else{
                Alert alert3=new Alert(Alert.AlertType.ERROR);
                alert3.setTitle("fail");
                alert3.setContentText("vehicle arrival  cannot be completed");
                alert3.showAndWait();

            }

        }else {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("fail");
            alert.setContentText("vehicle arrival  cannot be completed");
            alert.showAndWait();

        }

        }
    }
}
