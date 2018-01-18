package controller;

import com.jfoenix.controls.*;
import dao.User;
import db.UserManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import utils.JDBCUtils;
import utils.TextUtils;
import utils.Toast;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class UserInfoController implements Initializable {

    @FXML
    AnchorPane pane;
    @FXML
    Label userNo;
    @FXML
    JFXRadioButton male, female;
    @FXML
    JFXTextField nameInput, telInput;
    @FXML
    JFXPasswordField oldPasswordInput, newPasswordInput, repeatPasswordInput;
    @FXML
    JFXButton infoUpdateButton, passwordModifyButton;

    private ToggleGroup group;
    private String userType;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        group = new ToggleGroup();
        male.setToggleGroup(group);
        female.setToggleGroup(group);
        userType = (String) UserManager.CURRENT_USR.get("type");
        if (userType.equals(User.ADMIN)) {
            initInfo("Ano", "Atel", "Aname", "Asex");
        } else {
            initInfo("Tno", "Ttel", "Tname", "Tsex");
        }
    }

    private void initInfo(String no, String tel, String name, String sex) {
        Map<String, Object> map = UserManager.CURRENT_USR;
        userNo.setText((String) map.get(no));
        telInput.setText((String) map.get(tel));
        String s = (String) map.get(sex);
        if (s.equals("男")) {
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
        String n = (String) map.get(name);
        if (null != n) nameInput.setText(n);
    }

    public void onHandleUpdateInfo() {
        String name = nameInput.getText();
        String sex = male.isSelected() ? "男" : "女";
        String tel = telInput.getText();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(sex) || TextUtils.isEmpty(tel)) {
            Toast.show(pane, "更新信息不能为空");
        } else {

            try {
                if (userType.equals(User.ADMIN)) {
                    JDBCUtils.get()
                            .update("UPDATE admin SET Aname=?, Atel=?, Asex=? WHERE Ano=?",
                                    Arrays.asList(name, tel, sex, userNo.getText()));
                    UserManager.CURRENT_USR.put("Ano", userNo.getText());
                    UserManager.CURRENT_USR.put("Aname", name);
                    UserManager.CURRENT_USR.put("Asex", sex);
                    UserManager.CURRENT_USR.put("Atel", tel);
                } else {
                    JDBCUtils.get()
                            .update("UPDATE teacher SET Tname=?, Ttel=?, Tsex=? WHERE Tno=?",
                                    Arrays.asList(name, tel, sex, userNo.getText()));
                    UserManager.CURRENT_USR.put("Tno", userNo.getText());
                    UserManager.CURRENT_USR.put("Tname", name);
                    UserManager.CURRENT_USR.put("Tsex", sex);
                    UserManager.CURRENT_USR.put("Ttel", tel);
                }
                Toast.show(pane, "更新成功");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void onHandleModifyPassword() {
        String old = oldPasswordInput.getText();
        String newP = newPasswordInput.getText();
        String repeat = repeatPasswordInput.getText();
        if (TextUtils.isEmpty(old) || TextUtils.isEmpty(newP) || TextUtils.isEmpty(repeat)) {
            Toast.show(pane, "信息不能为空");
            return;
        }

        if (repeat.length() < 6) {
            Toast.show(pane, "密码长度不足 6 位，请修改");
            return;
        }
        if (TextUtils.isDigitsOnly(repeat)) {
            Toast.show(pane, "密码强度太弱，不能只包含数字，请修改");
            return;
        }


        try {
            String truePassword;
            if (userType.equals(User.ADMIN)) {
                truePassword = (String) JDBCUtils.get().getQueryResult("SELECT Apassword FROM admin WHERE Ano=?", Arrays.asList(userNo.getText())).get("Apassword");
            } else {
                truePassword = (String) JDBCUtils.get().getQueryResult("SELECT Tpassword FROM teacher WHERE Ano=?", Arrays.asList(userNo.getText())).get("Tpassword");
            }
            if (null != truePassword && old.equals(truePassword)) {
                if (newP.equals(repeat)) {
                    boolean modify;
                    if (userType.equals(User.ADMIN)) {
                        modify = JDBCUtils.get().update("UPDATE admin SET Apassword=? WHERE Ano=?", Arrays.asList(repeat, userNo.getText()));
                    } else {
                        modify = JDBCUtils.get().update("UPDATE admin SET Apassword=? WHERE Ano=?", Arrays.asList(repeat, userNo.getText()));
                    }
                    if (modify) Toast.show(pane, "密码更改成功");
                    else Toast.show(pane, "密码更新失败");
                } else {
                    Toast.show(pane, "两次密码输入错误，请重新输入");
                }
            } else {
                Toast.show(pane, "原始密码错误，请重新输入");
            }
            oldPasswordInput.setText("");
            newPasswordInput.setText("");
            repeatPasswordInput.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
