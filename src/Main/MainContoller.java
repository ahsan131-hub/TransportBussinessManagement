package Main;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Collection;

public class MainContoller {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton loadv;

    @FXML
    private JFXButton loadb;

    @FXML
    private JFXButton loads;

    @FXML
    private JFXButton setting;

    @FXML
    private JFXButton GO_BACK;
    private LoaderClass loader=new LoaderClass();

    @FXML
    void loadB(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Booking.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorPane.getChildren().setAll(root);
    }

    @FXML
    void loadS(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/statistics/statistics.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        anchorPane.getChildren().setAll(root);
    }

    @FXML
    void loadV(ActionEvent event) {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("vehicles.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        anchorPane.getChildren().setAll(root);
    }
    @FXML
    void goBackAction(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        anchorPane.getChildren().setAll(root.getChildrenUnmodifiable().get(0));
    }
    @FXML
    void loadSettingAction(ActionEvent event) {

        loader.loadWindow("setting.fxml","Settings");
    }

}
