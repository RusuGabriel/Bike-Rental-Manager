package manager;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

public class HomeController implements Initializable {

    @FXML
    private PieChart pieChart;
    private ObservableList<PieChart.Data> chartData;

    @FXML
    private void goRegistredUsers(ActionEvent e)
    {
        GUI.getInstance().display(GUI.REGISTRED_USERS);
    }

    @FXML
    private void goBicycleStock(ActionEvent e)
    {
        GUI.getInstance().display(GUI.REGISTRED_BIKES);
    }
    @FXML
    private  void goRentalInvoices(ActionEvent e)
    {
        //TODO: Design the rental invoices App
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chartData = FXCollections.observableArrayList();
        chartData.add(new PieChart.Data("Road",20));
        chartData.add(new PieChart.Data("Fitness",12));
        chartData.add(new PieChart.Data("Triathlon",5));
        chartData.add(new PieChart.Data("Cyclocross",10));
        //TODO: Get real data from data base to display in pieChart
        pieChart.getData().addAll(chartData);

    }
}
