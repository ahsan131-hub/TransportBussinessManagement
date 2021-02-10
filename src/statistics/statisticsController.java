package statistics;

import DataBaseHandler.dataBaseLoader;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class statisticsController implements Initializable {
    dataBaseLoader handler= dataBaseLoader.getInstance();

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;
    @FXML
    private Label profitLabel;
    int profit=0;


    public void cal(){

        String q="select *,'DATEDIFF(DAY ,timeArrived ,CURRENT_TIMESTAMP)' as Diff from bstatistics where 'DATEDIFF(DAY ,timeArrived ,CURRENT_TIMESTAMP)' between 0 and 1 ";
      ResultSet rs=handler.execQuery(q);
        XYChart.Series<String,Number> series = new XYChart.Series<String,Number>();
        try{
            while(rs.next()){
                
                //series.getData().add(new XYChart.Data(i++, rs.getInt("profit")));
                System.out.print(rs.getInt("profit"));
                System.out.print(rs.getTimestamp("timeArrived").toLocalDateTime().toString());
                rs.getTimestamp("timeArrived");
                series.getData().add(new XYChart.Data(String.valueOf(rs.getTimestamp("timeArrived").toLocalDateTime().getDayOfMonth()),rs.getInt("profit") ));
                profit+=rs.getInt("profit");

            }
        }catch (Exception e){
            e.getStackTrace();
        }


        lineChart.getData().add(series);
        profitLabel.setText(String.valueOf(profit));
        System.out.print("Executed");
    }
    @FXML
    private JFXButton month;

    @FXML
    void loadMonthlyAction(ActionEvent event) {
        lineChart.getData().clear();
        cal();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {





    }
}
