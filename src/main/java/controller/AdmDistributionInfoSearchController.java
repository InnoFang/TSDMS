package main.java.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import main.java.dao.Distribution;
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
import main.java.utils.JDBCUtils;
import main.java.utils.TextUtils;
import main.java.utils.Toast;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/18.
 */
public class AdmDistributionInfoSearchController implements Initializable {

    @FXML
    AnchorPane pane;
    @FXML
    TableView<Distribution> distributionTable;
    @FXML
    Label receiver, number, press, textbookNo, textbookName, distributionDate, adminNo;
    @FXML
    JFXButton searchTextbookNo, searchTextbook, searchDistributionDate, searchAdminNo;
    @FXML
    JFXTextField adminNoInput, textbookNoInput, textbookNameInput, distributionDateInput;

    private ObservableList<Distribution> distributionObservableList = FXCollections.observableArrayList();
    private List<Distribution> distributionList = new ArrayList<>();
    private String[] columns = {"adminNo", "textbookNo", "textbookName", "press", "number", "distributionDate", "receiver"};
    private int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clean();

        try {
            JDBCUtils.get()
                    .getQueryResults(
                            "SELECT Ano,distribution.Bno, distribution.Bname, Pname, distribution.Bnum, Ddate, Lname " +
                                    "FROM distribution, textbook, press " +
                                    "WHERE distribution.Bno=textbook.Bno " +
                                    "AND textbook.Pno=press.Pno",
                            new ArrayList<>()
                    ).forEach(map -> {
                Distribution distribution = new Distribution();
                distribution.setAdminNo((String) map.get("Ano"));
                distribution.setTextbookNo((String) map.get("Bno"));
                distribution.setTextbookName((String) map.get("Bname"));
                distribution.setPress((String) map.get("Pname"));
                distribution.setNumber((Integer) map.get("Bnum"));
                distribution.setDistributionDate((String) map.get("Ddate"));
                distribution.setReceiver((String) map.get("Lname"));

                distributionObservableList.add(distribution);
                distributionList.add(distribution);
            });

            index = 0;
            distributionTable.getColumns().forEach(distributionTableColumn ->
                    distributionTableColumn.setCellValueFactory(new PropertyValueFactory<>(columns[index++]))
            );

            distributionTable.setItems(distributionObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        distributionTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Distribution>() {
            @Override
            public void changed(ObservableValue<? extends Distribution> observable, Distribution oldValue, Distribution newValue) {
                Distribution value = observable.getValue();
                if (null == value) return;
                adminNo.setText(value.getAdminNo());
                textbookNo.setText(value.getTextbookNo());
                textbookName.setText(value.getTextbookName());
                press.setText(value.getPress());
                number.setText(value.getNumber() + "");
                distributionDate.setText(value.getDistributionDate());
                receiver.setText(value.getReceiver());
            }
        });
    }

    private void clean() {
        receiver.setText("");
        press.setText("");
        number.setText(" ");
        textbookNo.setText("");
        distributionDate.setText("");
        adminNo.setText("");
        textbookName.setText("");
    }

    public void onHandleSearch(ActionEvent event) {
        Object button = event.getSource();
        if (button == searchAdminNo) {
            String admin = adminNoInput.getText();
            if (TextUtils.isEmpty(admin)) {
                Toast.show(pane, "查询信息不能为空");
                return;
            }
            distributionObservableList.clear();
            distributionList.stream()
                    .filter(distribution -> distribution.getAdminNo().contains(admin))
                    .forEach(distribution -> distributionObservableList.add(distribution));
        } else if (button == searchTextbookNo) {
            String bookNo = textbookNoInput.getText();
            if (TextUtils.isEmpty(bookNo)) {
                Toast.show(pane, "查询信息不能为空");
                return;
            }
            distributionObservableList.clear();
            distributionList.stream()
                    .filter(distribution -> distribution.getTextbookNo().contains(bookNo))
                    .forEach(distribution -> distributionObservableList.add(distribution));
        } else if (button == searchTextbook) {
            String bookName = textbookNameInput.getText();
            if (TextUtils.isEmpty(bookName)) {
                Toast.show(pane, "查询信息不能为空");
                return;
            }
            distributionObservableList.clear();
            distributionList.stream()
                    .filter(distribution -> distribution.getTextbookName().contains(bookName))
                    .forEach(distribution -> distributionObservableList.add(distribution));
        } else if (button == searchDistributionDate) {
            String date = distributionDateInput.getText();
            if (TextUtils.isEmpty(date)) {
                Toast.show(pane, "查询信息不能为空");
                return;
            }
            distributionObservableList.clear();
            distributionList.stream()
                    .filter(distribution -> distribution.getDistributionDate().contains(date))
                    .forEach(distribution -> distributionObservableList.add(distribution));
        }

        clean();
    }
}
