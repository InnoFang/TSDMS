package controller;

import app.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/4.
 */
public class LoginController implements Initializable {

    @FXML
    TextField userId;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorMessage;
    @FXML
    Hyperlink register;
    @FXML
    RadioButton admin;
    @FXML
    RadioButton teacher;

    ToggleGroup group;
    private MainApp app;

    public void setApp(MainApp app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group = new ToggleGroup();
        teacher.setToggleGroup(group);
        admin.setToggleGroup(group);
        admin.setSelected(true);
        errorMessage.setText("");
    }

    public void onHandleLogin(ActionEvent event) {

    }

    public void onGoToRegister(ActionEvent event) {
        app.goToRegister();
    }
}
