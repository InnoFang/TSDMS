package controller;

import app.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/4.
 */
public class RegisterController implements Initializable {

    @FXML
    Button register;
    @FXML
    TextField userId;
    @FXML
    TextField contactInfo;
    @FXML
    PasswordField password;
    @FXML
    PasswordField repeatPassword;
    @FXML
    RadioButton admin;
    @FXML
    RadioButton teacher;
    @FXML
    Label errorMessage;

    ToggleGroup group;
    private MainApp app;

    public void setApp(MainApp app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
        group = new ToggleGroup();
        teacher.setToggleGroup(group);
        admin.setToggleGroup(group);
        admin.setSelected(true);
    }

    public void onHandleRegister(javafx.event.ActionEvent event) {

        app.goToLogin();
    }

    public void onHandleGoBackLogin(){
        app.goToLogin();
    }
}
