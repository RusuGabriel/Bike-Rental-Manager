package manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public PasswordField password;
    @FXML
    public TextField username;
    @FXML
    public Label text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void goHome(ActionEvent actionEvent) {
        GUI.getInstance().display(GUI.HOME);
    }

    public void goToPricing(ActionEvent actionEvent) {
        if(password.getText().equals("admin")&&username.getText().equals("admin"))
            GUI.getInstance().display(GUI.CHANGE_PRICING);
        else {
            text.setText("Invalid username or password try again");
            clearFields();
        }
    }

    private void clearFields() {
        password.setText("");
        username.setText("");
    }
}
