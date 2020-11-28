package manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class SearchController implements Initializable {
    public static final int LOADRENTS2HOURS = 0 ;
    public static final int RENTSOFROADBIKES = 1;
    public static final int RENTSBYCATEGORY = 2;
    public static final int RENTSBYUSERS = 3;
    @FXML
    TableColumn<SearchResult,String> firstColumn;
    @FXML
    TableColumn<SearchResult,String> secondColumn;
    @FXML
    TableView<SearchResult> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData(LOADRENTS2HOURS);
        firstColumn.setCellValueFactory(new PropertyValueFactory<SearchResult, String>("FirstColumn"));
        firstColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        secondColumn.setCellValueFactory(new PropertyValueFactory<SearchResult, String>("SecondColumn"));
        secondColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstColumn.setText("Username");
        secondColumn.setText("City");
        table.setEditable(true);
    }

    private void loadData(int option) {
        Thread loadThread = new Thread(() -> {
            Database database = Database.getInstance();
            database.load(option,SearchResultRepository.getInstance());
            table.getItems().addAll(SearchResultRepository.getInstance().getData());
        });
        loadThread.start();
    }

    @FXML
    private void loadRents2Hours(ActionEvent e){
        clearOldData();
        firstColumn.setText("Username");
        secondColumn.setText("City");
        loadData(LOADRENTS2HOURS);
    }

    @FXML
    private void rentsOfRoadBikes(ActionEvent e){
        clearOldData();
        firstColumn.setText("Username");
        secondColumn.setText("Number of Rents");
        loadData(RENTSOFROADBIKES);
    }
    @FXML
    private void rentsByCategory(ActionEvent e){
        clearOldData();
        firstColumn.setText("Category");
        secondColumn.setText("Number of Rents");
        loadData(RENTSBYCATEGORY);
    }

    private void clearOldData() {
        SearchResultRepository.getInstance().getData().clear();
        table.getItems().clear();
    }

    @FXML
    private void rentsByUsers(ActionEvent e){
        clearOldData();
        firstColumn.setText("Username");
        secondColumn.setText("Number of Rents");
        loadData(RENTSBYUSERS);
    }
    @FXML
    private void goHome(ActionEvent e){
        GUI.getInstance().display(GUI.HOME);
    }
}
