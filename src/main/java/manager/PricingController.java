package manager;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class PricingController implements Initializable {
    @FXML
    public TableView<BikePrice> table;
    @FXML
    public TableColumn<BikePrice,String> brandCol;
    @FXML
    public TableColumn<BikePrice,String> categoryCol;
    @FXML
    public TableColumn<BikePrice,String> speedCol;
    @FXML
    public TableColumn<BikePrice,String> colorCol;
    @FXML
    public TableColumn<BikePrice,String> priceCol;

    public void goHome(ActionEvent actionEvent) {
        GUI.getInstance().display(GUI.HOME);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        brandCol.setCellValueFactory(new PropertyValueFactory<BikePrice, String>("Brand"));
        brandCol.setCellFactory(TextFieldTableCell.forTableColumn());

        colorCol.setCellValueFactory(new PropertyValueFactory<BikePrice, String>("Color"));
        colorCol.setCellFactory(TextFieldTableCell.forTableColumn());

        categoryCol.setCellValueFactory(new PropertyValueFactory<BikePrice, String>("Category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());

        speedCol.setCellValueFactory(new PropertyValueFactory<BikePrice, String>("Speed"));
        speedCol.setCellFactory(TextFieldTableCell.forTableColumn());

        priceCol.setCellValueFactory(new PropertyValueFactory<BikePrice, String>("Price"));
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn());
        priceCol.setOnEditCommit(e -> commitChangePrice(e));
        table.setEditable(true);
    }

    private void loadData() {
        Thread loadThread = new Thread(() -> {
            Database database = Database.getInstance();
            database.loadFrom(BikePriceRepository.getInstance());
            table.getItems().addAll(BikePriceRepository.getInstance().getData());
        });
        loadThread.start();
    }

    private void commitChangePrice(Event e) {
        TableColumn.CellEditEvent<BikePrice, String> ce;
        ce = (TableColumn.CellEditEvent<BikePrice, String>) e;
        BikePrice b = ce.getRowValue();
        b.setPrice(ce.getNewValue());
        Database.update(b);
    }
}
