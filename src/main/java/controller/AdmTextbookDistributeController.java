package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import dao.Subscription;
import db.UserManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import utils.JDBCUtils;
import utils.TextUtils;
import utils.Toast;

import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Inno Fang on 2018/1/18.
 */
public class AdmTextbookDistributeController implements Initializable {

    @FXML
    StackPane stackPane;
    @FXML
    AnchorPane pane;
    @FXML
    TableView<Subscription> subscriptionTable;
    @FXML
    JFXButton okButton, cancelButton;
    @FXML
    Label teacherNo, textbookNo, adminNo, textbookName;
    @FXML
    JFXTextField number, collector;


    private ObservableList<Subscription> subscriptionObservableList = FXCollections.observableArrayList();
    private String[] columns = {"teacherNo", "textbookNo", "textbookName", "version", "press", "number", "subscribeDate"};
    private int index = 0;
    private String admin;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        admin = (String) UserManager.CURRENT_USR.get("Ano");
        clean();
        try {
            JDBCUtils.get()
                    .getQueryResults(
                            "SELECT Tno,A.Bno,Snum,Sdate,Bname,Bversion,Pname FROM  " +
                                    "(SELECT * FROM subscription WHERE Sdo='N') AS A LEFT JOIN " +
                                    "(SELECT Bno,Pname,Bversion,Bname FROM textbook,press WHERE textbook.Pno=press.Pno ) AS B " +
                                    "on A.Bno=B.Bno;",
                            new ArrayList<>()
                    ).forEach(map -> {
                Subscription subscription = new Subscription();
                subscription.setTeacherNo((String) map.get("Tno"));
                subscription.setTextbookNo((String) map.get("Bno"));
                subscription.setTextbookName((String) map.get("Bname"));
                subscription.setVersion((String) map.get("Bversion"));
                subscription.setNumber((Integer) map.get("Snum"));
                subscription.setPress((String) map.get("Pname"));
                subscription.setSubscribeDate((String) map.get("Sdate"));
                subscriptionObservableList.add(subscription);
            });

            index = 0;
            subscriptionTable.getColumns().forEach(subscriptionTableColumn ->
                    subscriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>(columns[index++]))
            );

            subscriptionTable.setItems(subscriptionObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        subscriptionTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Subscription>() {
            @Override
            public void changed(ObservableValue<? extends Subscription> observable, Subscription oldValue, Subscription newValue) {
                if (null == observable.getValue()) return;
                teacherNo.setText(observable.getValue().getTeacherNo());
                textbookNo.setText(observable.getValue().getTextbookNo());
                textbookName.setText(observable.getValue().getTextbookName());
                number.setText(observable.getValue().getNumber() + "");
                collector.setText("");
            }
        });
    }

    private void clean() {
        adminNo.setText(admin);
        textbookNo.setText("");
        textbookName.setText("");
        teacherNo.setText("");
        number.setText("");
        collector.setText("");
    }

    public void onHandleOk() {
        String teacher = teacherNo.getText();
        String bookNo = textbookNo.getText();
        String bookName = textbookName.getText();
        String num = number.getText();
        String person = collector.getText();

        if (TextUtils.isEmpty(teacher) || TextUtils.isEmpty(bookNo) || TextUtils.isEmpty(bookName)
                || TextUtils.isEmpty(num) || !TextUtils.isDigitsOnly(num) || TextUtils.isEmpty(person)) {

            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("注  意"));
            content.setBody(new Text("发放信息有误，请检查后重新输入！"));
            JFXButton button = new JFXButton("知道了");
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            button.setOnAction(event -> dialog.close());
            content.setActions(button);
            dialog.show();

            return;
        }

        StringBuilder text = new StringBuilder();
        text.append("管理员编号\t").append(admin).append("\t\t").append("教材名称\t").append(bookName).append("\n")
                .append("教 材 编 号\t").append(bookNo).append("\t\t").append("发放数量\t").append(num).append("\n")
                .append("教 师 编 号\t").append(teacher).append("\t\t").append("领  书  人\t").append(person);

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("请确认信息"));
        content.setBody(new Text(text.toString()));
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton confirm = new JFXButton("确  认");
        confirm.setOnAction(event -> distributeTextbook(dialog));
        JFXButton cancel = new JFXButton("取  消");
        cancel.setOnAction(event -> dialog.close());
        content.setActions(confirm, cancel);
        dialog.show();
    }

    private void distributeTextbook(JFXDialog dialog) {

        String teacher = teacherNo.getText();
        String bookNo = textbookNo.getText();
        String bookName = textbookName.getText();
        String num = number.getText();
        String person = collector.getText();
        String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        try {

            int textBookStoreNumber = (int) JDBCUtils.get()
                    .getQueryResult("SELECT Bnum FROM textbook WHERE Bno=?", Arrays.asList(bookNo))
                    .get("Bnum");

            // 检查库存
            if (textBookStoreNumber < Integer.valueOf(num)) {
                JFXDialogLayout content = new JFXDialogLayout();
                content.setHeading(new Text("教材发放失败"));
                String text = "教材 " + bookName + " (编号 " + bookNo + ")" +
                        " 库存不足(" + textBookStoreNumber + "<" + num + ")";
                content.setBody(new Text(text));
                JFXDialog alert = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
                JFXButton confirm = new JFXButton("知道了");
                confirm.setOnAction(event -> alert.close());
                content.setActions(confirm);
                alert.show();
                return;
            }

            boolean res = JDBCUtils.get()
                    .update("INSERT INTO distribution VALUES(?, ?, ?, ?, ?, ?)",
                            Arrays.asList(admin, bookNo, bookName, num, person, date));
            if (res) {
                res = JDBCUtils.get()
                        .update(
                                "UPDATE subscription " +
                                        "SET Sdo='Y' " +
                                        "WHERE Tno=? AND Bno=?",
                                Arrays.asList(teacher, bookNo)
                        );
                if (res) {
                    // 教材已发放，移除选中的征订请求
                    subscriptionTable.getItems().removeAll(subscriptionTable.getSelectionModel().getSelectedItem());
                    Toast.show(pane, "教材发放成功");

                    // 更新教材库存信息
                    updateTextbookInfo(bookNo, Integer.valueOf(num), textBookStoreNumber);
                    dialog.close();
                    clean();
                }
            } else {
                Toast.show(pane, "教材发放失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.show(pane, "教材发放失败");
        }
    }

    private void updateTextbookInfo(String bookNo, int num, int textBookStoreNumber) {
        try {
            // 如果库存与取出数量相等，则删除该教材库存信息
            // 否则更新教材的库存数量
            if (num == textBookStoreNumber) {
                JDBCUtils.get()
                        .update("DELETE FROM textbook WHERE Bno=?", Arrays.asList(bookNo));
            } else {
                int surplus = textBookStoreNumber - num;
                JDBCUtils.get()
                        .update("UPDATE textbook " +
                                        "SET Bnum=? " +
                                        "WHERE Bno=?",
                                Arrays.asList(surplus, bookNo));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onHandleCancel() {
        String teacher = teacherNo.getText();
        String bookNo = textbookNo.getText();

        if (TextUtils.isEmpty(teacher) || TextUtils.isEmpty(bookNo)) {
            JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text("注  意"));
            content.setBody(new Text("你还没选中相关信息，请重新操作"));
            JFXButton button = new JFXButton("知道了");
            JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
            button.setOnAction(event -> dialog.close());
            content.setActions(button);
            dialog.show();
            return;
        }

        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("注  意"));
        content.setBody(new Text("你将拒绝该教材征订请求，请确认"));
        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton confirm = new JFXButton("确  认");
        confirm.setOnAction(event -> cancelSubscription(dialog));
        JFXButton cancel = new JFXButton("取  消");
        cancel.setOnAction(event -> dialog.close());
        content.setActions(confirm, cancel);
        dialog.show();
    }

    private void cancelSubscription(JFXDialog dialog) {
        String teacher = teacherNo.getText();
        String bookNo = textbookNo.getText();

        try {
            boolean res = JDBCUtils.get()
                    .update(
                            "UPDATE subscription " +
                                    "SET Sdo='Y' " +
                                    "WHERE Tno=? AND Bno=?",
                            Arrays.asList(teacher, bookNo)
                    );
            if (res) {
                // 征订请求已拒绝，移除选中的征订请求
                subscriptionTable.getItems().removeAll(subscriptionTable.getSelectionModel().getSelectedItem());
                Toast.show(pane, "征订请求拒绝成功");
                dialog.close();
                clean();
            } else {
                Toast.show(pane, "征订请求拒绝失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.show(pane, "征订请求拒绝失败");
        }
    }
}
