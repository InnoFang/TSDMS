package controller;

import app.MainApp;
import dao.User;
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
        String type = admin.isSelected() ? User.ADMIN : teacher.isSelected() ? User.TEACHER : "";
        if (type.equals("")) {
            errorMessage.setText("用户类型不能为空");
            return;
        }

        if (!app.userLogging(userId.getText(), password.getText(), type)) {
            errorMessage.setText("用户名或密码错误");
        }
    }

    public void onGoToRegister(ActionEvent event) {
        app.goToRegister();
    }
}
