package util;

import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Tutil {
    private static String icon="util/simple.jpg";
    public static void setIcon(Stage stage) {
        stage.getIcons().add(new Image(icon));

    }


}
