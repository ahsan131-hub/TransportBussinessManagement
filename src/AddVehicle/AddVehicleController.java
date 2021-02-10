package AddVehicle;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import vehicleList.listVehicleController;
import vehicleList.listVehicleController.Vehicle;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.RenderedImage;
import java.io.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AddVehicleController {

    dataBaseLoader dataHandler=dataBaseLoader.getInstance();

    @FXML
    private JFXTextField lastOwnerName;

    @FXML
    private JFXTextField nic;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextArea ownerDescription;

    @FXML
    private JFXTextField vehicleName;

    @FXML
    private JFXTextField vehicleType;

    @FXML
    private JFXTextField vehicleNo;

    @FXML
    private JFXTextField chasisNo;

    @FXML
    private JFXTextField engineNo;

    @FXML
    private JFXTextField vehicleError;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextArea vehicleDescription;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton cancel;
    private boolean isEditable=false;
    Timestamp timeOfPurchase=null;
    private Boolean isAvail=null;

    @FXML
    void handleDragOver(DragEvent event) {
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);

        }


    }

    @FXML
    void handleDrop(DragEvent event) {
        List<File> files=event.getDragboard().getFiles();

        try {
            Image image=null;
            if(files.size()<3) {
                  image = new Image(new FileInputStream(files.get(0)));
                 imageView.setImage(image);

               // File file = new File("D:");
                //ImageOutputStream Iout = new FileImageOutputStream(file);
              //  ImageIO.write((RenderedImage) image, ".jpg", File.createTempFile(vehicleNo.getText(),"",file));

            }else{
                System.out.println("pic are greater than 3");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void loadCancelAction(ActionEvent event) {
        Stage stage=(Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void loadSaveAction(ActionEvent event) {
        if(vehicleNo.getText().equals("")||vehicleName.getText().equals("")||price.getText().equals("")||lastOwnerName.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot remain blank");
            alert.setContentText("mendentory to give vehicle no#,vehicle name and price ");
            alert.show();


        }else {
            if(isEditable){
                handlerUpdateOperation();
                return;
            }

            String qu = "insert into vehicles(vehicleNo,type,chasisNo ,engineNo,totalPrice,error,vehicleDecription,lastOwnerName,nic,phoneNO,lastOwnerDecription,vehicleName,images,address) values(" +
                    "'" + vehicleNo.getText() + "'," +
                    "'" + vehicleType.getText() + "'," +
                    "'" + chasisNo.getText() + "'," +
                    "'" + engineNo.getText() + "'," +
                    "'" + price.getText() + "'," +
                    "'" + vehicleError.getText() + "'," +
                    "'" + vehicleDescription.getText() + "'," +
                    //"'" +( new Timestamp(System.currentTimeMillis()))+ "'," +
                    "'" + lastOwnerName.getText() + "'," +
                    "'" + nic.getText() + "'," +
                    "'" + phone.getText() + "'," +
                    "'" + ownerDescription.getText() + "'," +
                    "'" + vehicleName.getText() + "'," +
                    "'" + imageView.getImage() + "'," +
                    "'" + address.getText() + "'" +
                    ")";


            if (dataHandler.AddVechicle(qu)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ADDED");
                alert.setContentText("succesfully Added sucessfully");
                alert.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Could not Added");
                alert.setContentText("could not add the vehicle ");
                alert.show();

            }
        }

    }

    private void handlerUpdateOperation() {
        Vehicle vehicle=new Vehicle(vehicleName.getText(), vehicleNo.getText(),isAvail, vehicleType.getText(), Integer.parseInt(price.getText()),timeOfPurchase, chasisNo.getText(), engineNo.getText(), vehicleError.getText(), vehicleDescription.getText(), lastOwnerName.getText(), nic.getText(), phone.getText(),ownerDescription.getText(),imageView.getImage(),address.getText());
        dataHandler.UpdateVehicle(vehicle);
        
    }




    public void intiaFeild(Vehicle vehicle) {
        vehicleName.setText(vehicle.getVname());
        vehicleNo.setText(vehicle.getVno());
        vehicleType.setText(vehicle.getVtype());
        this.price.setText(Integer.valueOf(vehicle.getPrice()).toString());
        this.chasisNo.setText(vehicle.getChasisNo());
        this.engineNo.setText(vehicle.getEngineNo());
        this.vehicleError.setText(vehicle.getError());
        vehicleDescription.setText(vehicle.getVehicleDecription());
        this.lastOwnerName.setText(vehicle.getLastOwnerName());
        this.nic.setText(vehicle.getNic());
        phone.setText(vehicle.getPhoneNO());
        this.ownerDescription.setText(vehicle.getLastOwnerDecription());
        imageView.setImage((Image)vehicle.getImage());
        timeOfPurchase=vehicle.getDate();
        isAvail=vehicle.getIsavail();
        this.address.setText(vehicle.getAddress());
        isEditable=true;


    }
}
