package Main;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import util.Tutil;

import java.io.IOException;

public class LoaderClass {


   public void loadWindow(String loc, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(loc));
            Stage stg = new Stage(StageStyle.DECORATED);
            Scene scene = new Scene(root);
            Tutil.setIcon(stg);
            stg.setTitle(title);
            stg.setScene(scene);
            //       librarymanageUtil.setIcon(stg);
            stg.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public AnchorPane fadeAnimate(String url)  {
        AnchorPane v= null;
        try {
            v = FXMLLoader.load(getClass().getResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(v);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
//        double w = datePane.getWidth();
//        double h = datePane.getHeight();
        v.setPrefHeight(600);
        v.setPrefWidth(600);

        ft.play();
        return v;


    }
}
