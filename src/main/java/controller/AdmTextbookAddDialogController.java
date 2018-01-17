package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.base.BaseDialogController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import utils.JDBCUtils;
import utils.TextUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class AdmTextbookAddDialogController extends BaseDialogController implements Initializable {

    @FXML
    JFXTextField noInput, nameInput, versionInput, priceInput, storeInput, pressInput;
    @FXML
    JFXButton okButton, resetButton, cancelButton;
    @FXML
    Label errorMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorMessage.setText("");
    }

    public void onHandleOk() {
        String no = noInput.getText();
        String name = nameInput.getText();
        String version = versionInput.getText();
        String priceStr = priceInput.getText();
        String storeStr = storeInput.getText();
        String press = pressInput.getText();

        if (TextUtils.isEmpty(no) || TextUtils.isEmpty(name) || TextUtils.isEmpty(version)
                || TextUtils.isEmpty(priceStr) || !TextUtils.isDigitsOnly(storeStr) || TextUtils.isEmpty(press)) {
            errorMessage.setText("信息不能为空");
            return;
        }

        float price = Float.valueOf(priceInput.getText());
        int store = Integer.valueOf(storeInput.getText());

        try {
            // 如果原表中有该教材数据，那么更新数据，否则插入
            if (!JDBCUtils.get().getQueryResult("SELECT * FROM textbook WHERE Bno=?", Arrays.asList(no)).isEmpty()) {
                JDBCUtils.get().update(
                        "UPDATE textbook " +
                                "SET Bname=?, " +
                                "Bversion=?, " +
                                "Bprice=?, " +
                                "Bnum=?, " +
                                "Pno IN (SELECT Pno WHERE Pname=?) " +
                                "WHERE Pno=?",
                        Arrays.asList(name, version, price, store, press, no)
                );
            } else {

                String pno = (String) JDBCUtils.get()
                        .getQueryResult("SELECT Pno FROM press WHERE Pname=?", Arrays.asList(press))
                        .get("Pno");

                if (null == pno) {
                    errorMessage.setText("还没有该出版社的记录，需要先添加改出版社记录");
                    return;
                }

                JDBCUtils.get().update(
                        "INSERT INTO textbook VALUES(?, ?, ?, ?, ?, ?)",
                        Arrays.asList(no, name, version, price, store, pno)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorMessage.setText("添加失败");
            return;
        }
        okClicked = true;
        dialogStage.close();
    }

    public void onHandleReset() {
        noInput.setText("");
        nameInput.setText("");
        priceInput.setText("");
        versionInput.setText("");
        storeInput.setText("");
        pressInput.setText("");
    }

    public void onHandleCancel() {
        dialogStage.close();
    }
}
