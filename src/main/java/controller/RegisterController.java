package controller;

import app.MainApp;
import dao.User;
import db.UserManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import utils.TextUtils;

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
        if (TextUtils.isEmpty(userId.getText())) { errorMessage.setText("用户名不能为空"); return; }
        if (TextUtils.isEmpty(password.getText())) { errorMessage.setText("密码不能为空"); return; }
        if (TextUtils.isEmpty(repeatPassword.getText())) { errorMessage.setText("重复密码不能为空"); return; }
        if (TextUtils.isEmpty(contactInfo.getText())) { errorMessage.setText("联系方式不能为空"); return; }
        if (!TextUtils.equals(password.getText(), repeatPassword.getText())) { errorMessage.setText("两次密码不相等"); return; }

        String type = admin.isSelected() ? User.ADMIN : teacher.isSelected() ? User.TEACHER : "";
        if (type.equals("")) { errorMessage.setText("用户类型不能为空"); return; }

        User user = new User(userId.getText(), password.getText(), contactInfo.getText(), type);

        if (!UserManager.register(user)) { errorMessage.setText("注册失败，请更换用户名试一试"); return; }

        app.goToLogin();
    }


    public void onHandleGoBackLogin(){
        app.goToLogin();
    }
}
