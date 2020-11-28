package manager;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;


public class RentController implements Initializable {
    private static final String TABLE_NAME = "RentalInvoice";
    @FXML
    TextField brandNameTxt;
    @FXML
    TextField userNameTxt;
    @FXML
    TextField startTimeTxt;

    //columns
    @FXML
    TableColumn<RentalInvoice, String> userCol;
    @FXML
    TableColumn<RentalInvoice, String> brandCol;
    @FXML
    TableColumn<RentalInvoice, String> startTimeCol;
    @FXML
    TableColumn<RentalInvoice, String> endTimeCol;
    @FXML
    TableColumn<RentalInvoice, Double> priceToPayCol;

    @FXML
    TableView<RentalInvoice> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        userCol.setCellValueFactory(new PropertyValueFactory<RentalInvoice, String>("UserFullName"));
        userCol.setCellFactory(TextFieldTableCell.forTableColumn());
        userCol.setOnEditCommit(e -> commitChangeUser(e));

        brandCol.setCellValueFactory(new PropertyValueFactory<RentalInvoice, String>("BrandName"));
        brandCol.setCellFactory(TextFieldTableCell.forTableColumn());
        brandCol.setOnEditCommit(e -> commitChangeBrand(e));

        startTimeCol.setCellValueFactory(new PropertyValueFactory<RentalInvoice, String>("StartDate"));
        startTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        startTimeCol.setOnEditCommit(e -> commitChangeStartTime(e));

        endTimeCol.setCellValueFactory(new PropertyValueFactory<RentalInvoice, String>("EndDate"));
        endTimeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        endTimeCol.setOnEditCommit(e -> commitChangeEndTime(e));

        priceToPayCol.setCellValueFactory(new PropertyValueFactory<RentalInvoice, Double>("Charge"));
        priceToPayCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        priceToPayCol.setOnEditCommit(e -> commitChangePrice(e));
        table.setEditable(true);
    }

    @FXML
    private void goHome(ActionEvent e) {
        GUI.getInstance().display(GUI.HOME);
    }

    private void commitChangeUser(Event e) {

    }

    private void commitChangeBrand(Event e) {

    }


    private void commitChangePrice(Event e) {
        TableColumn.CellEditEvent<RentalInvoice, Double> ce;
        ce = (TableColumn.CellEditEvent<RentalInvoice, Double>) e;
        RentalInvoice ri = ce.getRowValue();
        ri.setCharge(ce.getNewValue());
        table.refresh();
        Thread update = new Thread(() -> {
            Database.update(ri);
        });
        update.start();

    }

    private void commitChangeEndTime(Event e) {
        TableColumn.CellEditEvent<RentalInvoice, String> ce;
        ce = (TableColumn.CellEditEvent<RentalInvoice, String>) e;
        RentalInvoice ri = ce.getRowValue();
        ri.setEndDate(GUI.sdf.format(new Date()));
        table.refresh();
        Thread update = new Thread(() -> {
            Database.update(ri);
        });
        update.start();

    }

    private void commitChangeStartTime(Event e) {
        TableColumn.CellEditEvent<RentalInvoice, String> ce;
        ce = (TableColumn.CellEditEvent<RentalInvoice, String>) e;
        RentalInvoice ri = ce.getRowValue();
        ri.setStartDate(GUI.sdf.format(new Date()));
        table.refresh();
        Thread update = new Thread(() -> {
            Database.update(ri);
        });
        update.start();
    }

    private void loadData() {
        Thread loadThread = new Thread(() -> {
            Database database = Database.getInstance();
            database.loadFrom(TABLE_NAME, RentalInvoiceRepository.getInstance());
            Database.loadFrom(BikeController.TABLE_NAME, BikeRepository.getInstance());
            Database.loadFrom(UserController.TABLE_NAME, UserRepository.getInstance());
            table.getItems().addAll(RentalInvoiceRepository.getInstance().getData());
        });
        loadThread.start();
    }

    @FXML
    private void addInovice(ActionEvent e) {
        RentalInvoice b = new RentalInvoice();
        b.setBrandID(BikeRepository.getKeyOf(brandNameTxt.getText()));
        b.setBrandName(brandNameTxt.getText());
        b.setUserFullName(userNameTxt.getText());
        b.setUserID(UserRepository.getKeyOf(userNameTxt.getText()));
        b.setStartDate(GUI.sdf.format(new Date()));
        b.setEndDate("not specified");
        RentalInvoiceRepository.getInstance().getData().add(b);
        table.getItems().add(b);

        Thread add = new Thread() {
            @Override
            public void run() {
                Database database = Database.getInstance();
                database.saveTo(TABLE_NAME, b);
            }
        };
        add.start();
        clearFields();
    }

    private void clearFields() {
        brandNameTxt.clear();
        userNameTxt.clear();
        startTimeTxt.clear();
    }

    @FXML
    private void deleteInvoice(ActionEvent e) {
        ObservableList<RentalInvoice> selected, items;
        items = table.getItems();
        selected = table.getSelectionModel().getSelectedItems();
        Database.delete(selected.get(0));
        ArrayList<RentalInvoice> data = RentalInvoiceRepository.getInstance().getData();
        data.remove(selected.get(0));
        items.remove(selected.get(0));

        table.refresh();
    }

}
