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

public class BikeController implements Initializable, Runnable {


    @FXML
    private TableColumn<Bike, String> sizeCol;
    @FXML
    private TextField sizeTxt;

    @FXML
    private TableColumn<Bike, String> brandCol;
    @FXML
    private TextField brandTxt;

    @FXML
    private TableColumn<Bike, String> colorCol;
    @FXML
    private TextField colorTxt;

    @FXML
    private TableColumn<Bike, Double> weightCol;
    @FXML
    private TextField weightTxt;

    @FXML
    private TableView<Bike> table;
    @FXML
    private static Parent root;

    public void initialize(URL url, ResourceBundle rb) {
        Thread myThread = new Thread(this);
        myThread.start();
        brandCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("Brand"));
        brandCol.setCellFactory(TextFieldTableCell.forTableColumn());
        brandCol.setOnEditCommit(e -> commitChangeBrand(e));

        colorCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("Culoare"));
        colorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        colorCol.setOnEditCommit(e -> commitChangeCuloare(e));

        sizeCol.setCellValueFactory(new PropertyValueFactory<Bike, String>("Marime"));
        sizeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        sizeCol.setOnEditCommit(e -> commitChangeMarime(e));

        weightCol.setCellValueFactory(new PropertyValueFactory<Bike, Double>("Diameter"));
        weightCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        weightCol.setOnEditCommit(e -> commitChangeDiameter(e));
        table.setEditable(true);
//        Database database = Database.getInstance();
//        Database.loadFrom("U", UserRepository.getInstance());
//        long estimatedTime = System.currentTimeMillis() - startTime;
//        System.out.println("Estimated time: "+estimatedTime);

    }

    @FXML
    public void addData(ActionEvent e) {
        Bike b = new Bike();
        //b.setBrand(brandTxt.getText());
        //b.setCuloare(colorTxt.getText());
        //b.setMarime(sizeTxt.getText());
        //b.setDiameter(Double.parseDouble(weightTxt.getText()));
        table.getItems().add(b);
        brandTxt.clear();
        colorTxt.clear();
        sizeTxt.clear();
        weightTxt.clear();

    }

    public void deleteData(ActionEvent e) {
        ObservableList<Bike> selected, items;
        items = table.getItems();
        selected = table.getSelectionModel().getSelectedItems();

        for (Bike b : selected)
            items.remove(b);
    }

    public void changeMode(ActionEvent e) {
        root = GUI.getParent();
        if (root == null) {
            System.out.println("Nu merge");
            return;
        }
        GUI.getInstance().display(GUI.REGISTRED_USERS);
    }

    @FXML
    public void commitChangeDiameter(Event e) {
        TableColumn.CellEditEvent<Bike, Double> ce;
        ce = (TableColumn.CellEditEvent<Bike, Double>) e;
        Bike b = ce.getRowValue();
        //b.setDiameter(ce.getNewValue());
    }

    @FXML
    public void commitChangeMarime(Event e) {
    }

    @FXML
    public void commitChangeCuloare(Event e) {
    }

    @FXML
    public void commitChangeBrand(Event e) {
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        Database database = Database.getInstance();
        Database.loadFrom("B", BikeRepository.getInstance());
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Estimated time: " + estimatedTime);
        table.getItems().addAll(BikeRepository.getInstance().getData());
    }
}

