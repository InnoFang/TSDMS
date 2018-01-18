package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Subscription;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.JDBCUtils;
import utils.TextUtils;
import utils.Toast;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * Created by Inno Fang on 2018/1/18.
 */
public class AdmSubscriptionInfoSearchController implements Initializable {

    @FXML
    AnchorPane pane;
    @FXML
    TableView<Subscription> subscriptionTable;
    @FXML
    Label teacherNo, textbookNo, textbookName, subscribeDate, number, version, press;
    @FXML
    JFXTextField teacherNoInput, textbookNoInput, textbookNameInput, subscribeDateInput;
    @FXML
    JFXButton searchTeacherNoButton, searchTextbookNoButton, searchTextbookNameButton, searchSubscribeDateButton;

    private ObservableList<Subscription> subscriptionObservableList = FXCollections.observableArrayList();
    private List<Subscription> subscriptionList = new ArrayList<>();
    private String[] columns = {"teacherNo", "textbookNo", "textbookName", "version", "number", "press", "subscribeDate"};
    private int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cleanLabel();


        try {
            JDBCUtils.get()
                    .getQueryResults(
//                            "SELECT Tno,subscription.Bno,Snum,Sdate,Bname,Bversion,Pname " +
//                                    "FROM subscription,textbook,press " +
//                                    "WHERE subscription.Bno=textbook.Bno " +
//                                    "AND textbook.Pno=press.Pno " +
//                                    "AND Sdo='N'",
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
                subscriptionList.add(subscription);
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
                Subscription value = observable.getValue();
                if (null == value) return;
                teacherNo.setText(value.getTeacherNo());
                textbookNo.setText(value.getTextbookNo());
                textbookName.setText(value.getTextbookName());
                subscribeDate.setText(value.getSubscribeDate());
                number.setText(value.getNumber() + "");
                version.setText(value.getVersion());
                press.setText(value.getPress());
            }
        });
    }

    private void cleanLabel() {
        teacherNo.setText("");
        textbookNo.setText("");
        textbookName.setText("");
        subscribeDate.setText("");
        number.setText("");
        version.setText("");
        press.setText("");
    }

    public void onHandleSearch(ActionEvent event) {
        Object button = event.getSource();
        if (button == searchTeacherNoButton) {
            String teacher = teacherNoInput.getText();
            if (TextUtils.isEmpty(teacher)) {
                Toast.show(pane, "查询信息不能为空");
                return;
            }
            subscriptionObservableList.clear();
            subscriptionList.stream()
                    .filter(subscription -> subscription.getTeacherNo().contains(teacher))
                    .forEach(action -> subscriptionObservableList.add(action));

        } else if (button == searchTextbookNoButton) {
            String searchTextbookNo = textbookNoInput.getText();
            if (TextUtils.isEmpty(searchTextbookNo)) {
                Toast.show(pane, "查询信息不能为空");
                return;
            }
            subscriptionObservableList.clear();
            subscriptionList.stream()
                    .filter(subscription -> subscription.getTextbookNo().contains(searchTextbookNo))
                    .forEach(action -> subscriptionObservableList.add(action));
        } else if (button == searchTextbookNameButton) {
            String searchTextbookName = textbookName.getText();
            if (TextUtils.isEmpty(searchTextbookName)) {
                Toast.show(pane, "查询信息不能为空");
                return;
            }
            subscriptionObservableList.clear();
            subscriptionList.stream()
                    .filter(subscription -> subscription.getTextbookName().contains(searchTextbookName))
                    .forEach(action -> subscriptionObservableList.add(action));
        } else if (button == searchSubscribeDateButton) {
            String searchSubscribeDate = subscribeDateInput.getText();
            if (TextUtils.isEmpty(searchSubscribeDate)) {
                Toast.show(pane, "查询信息不能为空");
                return;
            }
            subscriptionObservableList.clear();
            subscriptionList.stream()
                    .filter(subscription -> subscription.getSubscribeDate().contains(searchSubscribeDate))
                    .forEach(action -> subscriptionObservableList.add(action));
        }

        cleanLabel();
    }
}
