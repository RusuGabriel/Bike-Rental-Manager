package manager;


import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import javax.print.attribute.DocAttribute;

public class HomeController implements Initializable {

    @FXML
    private PieChart pieChart;
    private ObservableList<PieChart.Data> chartData  = FXCollections.observableArrayList();;

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
        getStatisticDetails();

        //TODO: Get real data from data base to display in pieChart
        pieChart.getData().addAll(chartData);
    }

    private void getStatisticDetails()
    {

                Database database = Database.getInstance();
                Database.loadFrom("Bike",BikeRepository.getInstance());
                HashMap<String, Integer> hashData = BikeRepository.getTypeStatistics();
                for (String elem : hashData.keySet())
                    chartData.add(new PieChart.Data(elem, hashData.get(elem)));

    }
}
