package manager;

import javafx.fxml.*;

import java.net.*;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.Timer;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.event.*;
import javafx.scene.control.cell.*;
import javafx.beans.property.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.util.converter.*;

public class BikeController implements Initializable {
    private static final String TABLE_NAME = "Bike";

    @FXML
    private TableColumn<Bike, String> categoryCol;
    @FXML
    private TextField categoryTxt;

    @FXML
    private TableColumn<Bike, String> brandCol;
    @FXML
    private TextField brandTxt;

    @FXML
    private TableColumn<Bike, String> colorCol;
    @FXML
    private TextField colorTxt;

    @FXML
    private TableColumn<Bike, Integer> speedCol;
    @FXML
    private TextField speedTxt;

    @FXML
    private TableView<Bike> table;
    @FXML
    private static Parent root;

    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        brandCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("Brand"));
        brandCol.setCellFactory(TextFieldTableCell.forTableColumn());
        brandCol.setOnEditCommit(e -> commitChangeBrand(e));

        colorCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("Color"));
        colorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        colorCol.setOnEditCommit(e -> commitChangeColor(e));

        categoryCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("Category"));
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setOnEditCommit(e -> commitChangeCategory(e));

        speedCol.setCellValueFactory(new PropertyValueFactory<Bike, Integer>("Speed"));
        speedCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        speedCol.setOnEditCommit(e -> commitChangeSpeed(e));
        table.setEditable(true);

    }

    @FXML
    public void addData(ActionEvent e) {
        Bike b = new Bike();
        b.setBrand(brandTxt.getText());
        b.setColor(colorTxt.getText());
        b.setSpeed(Integer.parseInt(speedTxt.getText()));
        BikeCategory bC = new BikeCategory();
        bC.setCategory(categoryTxt.getText());
        BikeBrand bB = new BikeBrand();
        bB.setBrand(brandTxt.getText());
        b.setBikeBrand(bB);
        b.setBikeCategory(bC);
        table.getItems().add(b);
        Thread add = new Thread(){
            @Override
            public void run() {
                Database database = Database.getInstance();
                database.saveTo(TABLE_NAME,b);
            }
        };
        add.start();
        clearFields();
    }

    private void clearFields()
    {
        brandTxt.clear();
        colorTxt.clear();
        categoryTxt.clear();
        speedTxt.clear();
    }

    public void deleteData(ActionEvent e) {
        ObservableList<Bike> selected, items;
        items = table.getItems();
        selected = table.getSelectionModel().getSelectedItems();

        for (Bike b : selected)
            items.remove(b);
    }

    @FXML
    public void commitChangeCategory(Event e){

    }

    @FXML
    public void commitChangeSpeed(Event e) {
        TableColumn.CellEditEvent<Bike, Integer> ce;
        ce = (TableColumn.CellEditEvent<Bike, Integer>) e;
        Bike b = ce.getRowValue();
        b.setSpeed(ce.getNewValue());
    }

    @FXML
    public void commitChangeColor(Event e) {
        TableColumn.CellEditEvent<Bike, String> ce;
        ce = (TableColumn.CellEditEvent<Bike, String>) e;
        Bike b = ce.getRowValue();
        b.setColor(ce.getNewValue());
    }


    @FXML
    private void goHome(ActionEvent e) {
        GUI.display(GUI.HOME);
    }


    @FXML
    public void commitChangeBrand(Event e) {
    }

    private void loadData(){
        Thread loadThread = new Thread(){
            @Override
            public void run() {
                Database database = Database.getInstance();
                database.loadFrom(TABLE_NAME,BikeRepository.getInstance());
                table.getItems().addAll(BikeRepository.getInstance().getData());
            }
        };
        loadThread.start();
    }

}

