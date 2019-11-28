package manager;

import com.sun.javafx.geom.transform.Identity;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserController implements Initializable {
    public static final String TABLE_NAME = "User";
    //text fields variables
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField secondNameField;
    @FXML
    private TextField cnpField;
    @FXML
    private TextField cardNumberField;
    @FXML
    private TextField cardSeriesField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField streetNumberField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField stateField;
    @FXML
    private TextField sexField;

    //table columns variables
    @FXML
    private TableColumn<User, String> firstNameCol;
    @FXML
    private TableColumn<User, String> secondNameCol;
    @FXML
    private TableColumn<User, String> cnpCol;
    @FXML
    private TableColumn<User, Integer> identityNumberCol;
    @FXML
    private TableColumn<User, String> identitySeriesCol;
    @FXML
    private TableColumn<User, String> streetCol;
    @FXML
    private TableColumn<User, Integer> streetNumberCol;
    @FXML
    private TableColumn<User, String> cityCol;
    @FXML
    private TableColumn<User, String> countryCol;
    @FXML
    private TableColumn<User, String> stateCol;
    @FXML
    private TableColumn<User, String> sexCol;

    @FXML
    private TableView<User> table;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
        firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(e -> commitChangeFirstName(e));

        secondNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("SecondName"));
        secondNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        secondNameCol.setOnEditCommit(e -> commitChangeSecondName(e));

        cnpCol.setCellValueFactory(new PropertyValueFactory<User, String>("CNP"));
        cnpCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cnpCol.setOnEditCommit(e -> commitChangeCNP(e));

        identityNumberCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("Number"));
        identityNumberCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        identityNumberCol.setOnEditCommit(e -> commitChangeIdentityCardNumber(e));

        identitySeriesCol.setCellValueFactory(new PropertyValueFactory<User, String>("Series"));
        identitySeriesCol.setCellFactory(TextFieldTableCell.forTableColumn());
        identitySeriesCol.setOnEditCommit(e -> commitChangeIdentityCardSeries(e));

        streetCol.setCellValueFactory(new PropertyValueFactory<User, String>("Street"));
        streetCol.setCellFactory(TextFieldTableCell.forTableColumn());
        streetCol.setOnEditCommit(e -> commitChangeStreet(e));

        streetNumberCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("StreetNumber"));
        streetNumberCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        streetNumberCol.setOnEditCommit(e -> commitChangeStreetNumber(e));

        cityCol.setCellValueFactory(new PropertyValueFactory<User, String>("City"));
        cityCol.setCellFactory(TextFieldTableCell.forTableColumn());
        cityCol.setOnEditCommit(e -> commitChangeCity(e));

        countryCol.setCellValueFactory(new PropertyValueFactory<User, String>("Country"));
        countryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        countryCol.setOnEditCommit(e -> commitChangeCity(e));

        stateCol.setCellValueFactory(new PropertyValueFactory<User, String>("State"));
        stateCol.setCellFactory(TextFieldTableCell.forTableColumn());
        stateCol.setOnEditCommit(e -> commitChangeCity(e));

        sexCol.setCellValueFactory(new PropertyValueFactory<User, String>("Sex"));
        sexCol.setCellFactory(TextFieldTableCell.forTableColumn());
        sexCol.setOnEditCommit(e -> commitChangeCity(e));
    }

    private void commitChangeCity(TableColumn.CellEditEvent<User, String> e) {
        TableColumn.CellEditEvent<User, String> ce;
        ce = (TableColumn.CellEditEvent<User, String>) e;
        User u = ce.getRowValue();
        u.getAddress().setCity(ce.getNewValue());
        Database.update(u);
    }

    private void commitChangeStreetNumber(Event e) {
        TableColumn.CellEditEvent<User, Integer> ce;
        ce = (TableColumn.CellEditEvent<User, Integer>) e;
        User u = ce.getRowValue();
        u.getAddress().setNumber(ce.getNewValue());
        Database.update(u);
    }

    private void commitChangeStreet(Event e) {
        TableColumn.CellEditEvent<User, String> ce;
        ce = (TableColumn.CellEditEvent<User, String>) e;
        User u = ce.getRowValue();
        u.getAddress().setStreet(ce.getNewValue());
        Database.update(u);
    }

    private void commitChangeIdentityCardSeries(Event e) {
    }

    private void commitChangeIdentityCardNumber(Event e) {
    }

    private void commitChangeCNP(Event e) {
    }

    private void commitChangeSecondName(Event e) {
    }

    @FXML
    public void addRecord(ActionEvent e) {
        User user = new User();
        user.setFirstName(firstNameField.getText());
        user.setSecondName(secondNameField.getText());
        Address a = new Address();
        user.setAddress(a);
        a.setCountry(countryField.getText());
        a.setState(stateField.getText());
        a.setNumber(Integer.parseInt(streetNumberField.getText()));
        a.setCity(cityField.getText());
        a.setStreet(streetField.getText());
        IdentityCard id = new IdentityCard();
        id.setCNP(cnpField.getText());
        id.setSeries(cardSeriesField.getText());
        id.setNumber(Integer.parseInt(cardNumberField.getText()));
        id.setSex(sexField.getText().charAt(0));
        user.setUserCard(id);
        cleanFields();
        addRecord(user);
        table.getItems().add(user);
    }

    private void cleanFields() {
        firstNameField.clear();
        secondNameField.clear();
        cnpField.clear();
        cardNumberField.clear();
        cardSeriesField.clear();
        streetField.clear();
        streetNumberField.clear();
        stateField.clear();
        sexField.clear();
        cityField.clear();
        countryField.clear();
    }

    @FXML
    public void deleteRecord(ActionEvent e) {
        ObservableList<User> selected, items;
        items = table.getItems();
        selected = table.getSelectionModel().getSelectedItems();
        Database.delete(selected.get(0));
        ArrayList<User> data = UserRepository.getInstance().getData();
        data.remove(selected.get(0));
        items.remove(selected.get(0));

        table.refresh();
    }

    @FXML
    public void goHome(ActionEvent e) {
        GUI.getInstance().display(GUI.HOME);
    }


    private void loadData() {
        Thread loadThread = new Thread() {
            @Override
            public void run() {
                Database database = Database.getInstance();
                database.loadFrom(TABLE_NAME,UserRepository.getInstance());
                table.getItems().addAll(UserRepository.getInstance().getData());
            }
        };
        loadThread.start();
    }

    public void commitChangeFirstName(Event e) {
        TableColumn.CellEditEvent<User, String> ce;
        ce = (TableColumn.CellEditEvent<User, String>) e;
        User u = ce.getRowValue();
        u.setFirstName(ce.getNewValue());
        Database.update(u);
    }


    public void addRecord(User user) {
        Database database = Database.getInstance();
        Thread saveThread = new Thread() {
            @Override
            public void run() {
                Database.saveTo(TABLE_NAME, user);
            }
        };
        saveThread.start();
    }
}
