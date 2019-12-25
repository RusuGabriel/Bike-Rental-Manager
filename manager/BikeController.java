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
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class BikeController implements Initializable {
    public static final String TABLE_NAME = "Bike";

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
        b.setColor(colorTxt.getText());
        b.setSpeed(Integer.parseInt(speedTxt.getText()));
        BikeCategory bC = new BikeCategory();
        bC.setCategory(categoryTxt.getText());
        BikeBrand bB = new BikeBrand();
        bB.setBrand(brandTxt.getText());
        b.setBikeBrand(bB);
        b.setBikeCategory(bC);
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
        brandTxt.clear();
        colorTxt.clear();
        categoryTxt.clear();
        speedTxt.clear();
    }

    public void deleteData(ActionEvent e) {
        ObservableList<Bike> selected, items;
        items = table.getItems();
        selected = table.getSelectionModel().getSelectedItems();
        Database.delete(selected.get(0));
        for (Bike b : selected)
            items.remove(b);
    }

    @FXML
    public void commitChangeCategory(Event e) {
        return;
    }

    @FXML
    public void commitChangeSpeed(Event e) {
        TableColumn.CellEditEvent<Bike, Integer> ce;
        ce = (TableColumn.CellEditEvent<Bike, Integer>) e;
        Bike b = ce.getRowValue();
        b.setSpeed(ce.getNewValue());
        Database.update(b);
    }

    @FXML
    public void commitChangeColor(Event e) {
        TableColumn.CellEditEvent<Bike, String> ce;
        ce = (TableColumn.CellEditEvent<Bike, String>) e;
        Bike b = ce.getRowValue();
        b.setColor(ce.getNewValue());
        Database.update(b);
    }


    @FXML
    private void goHome(ActionEvent e) {
        GUI.display(GUI.HOME);
    }


    @FXML
    public void commitChangeBrand(Event e) {
        TableColumn.CellEditEvent<Bike, String> ce;
        ce = (TableColumn.CellEditEvent<Bike, String>) e;
        Bike b = ce.getRowValue();
        b.getBikeBrand().setBrand(ce.getNewValue());
        b.getBikeBrand().setPrimaryKey(/**/BikeRepository.getKeyOf(ce.getNewValue())/**//*Database.getKeyOf(ce.getNewValue())*/);
        Database.update(b);
    }

    private void loadData() {
        Thread loadThread = new Thread(() -> {
            Database database = Database.getInstance();
            database.loadFrom(TABLE_NAME, BikeRepository.getInstance());
            table.getItems().addAll(BikeRepository.getInstance().getData());
        });
        loadThread.start();
    }

}

