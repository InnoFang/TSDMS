package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.base.BaseDialogController;
import dao.Press;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.JDBCUtils;
import utils.TextUtils;

import java.net.URL;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class AdmPressInfoDialogController extends BaseDialogController implements Initializable {

    @FXML
    TableView<Press> pressTable;

    @FXML
    TableColumn<Press, String> noColumn, nameColumn, telColumn;

    @FXML
    JFXTextField nameInput, noInput, telInput;

    @FXML
    JFXButton okButton, cancelButton;

    @FXML
    Label message;

    private ObservableList<Press> pressObservableList = FXCollections.observableArrayList();
    private String[] columns = {"no", "name", "tel"};
    private int index = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        message.setText("");

        try {
            List<Map<String, Object>> list = JDBCUtils.get().getQueryResults("SELECT * FROM press", new ArrayList<>());
            list.forEach(map -> {
                Press press = new Press();
                press.setNo((String) map.get("Pno"));
                press.setName((String) map.get("Pname"));
                press.setTel((String) map.get("Ptel"));
                pressObservableList.add(press);
            });

            index = 0;
            pressTable.getColumns().forEach(pressTableColumn ->
                    pressTableColumn.setCellValueFactory(new PropertyValueFactory<>(columns[index++]))
            );

            pressTable.setItems(pressObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onHandleOk() {
        String no = noInput.getText();
        String name = nameInput.getText();
        String tel = telInput.getText();

        if (TextUtils.isEmpty(no) || TextUtils.isEmpty(name) || TextUtils.isEmpty(tel)) {
            message.setText("信息不能为空");
            return;
        }

        try {
            boolean res = JDBCUtils.get().update("INSERT INTO press VALUES(?, ?, ?)", Arrays.asList(no, name, tel));
            if (res) {

                Press press = new Press(no, name, tel);
                pressObservableList.add(press);
//                pressTable.refresh();

                noInput.setText("");
                nameInput.setText("");
                telInput.setText("");
            } else {
                message.setText("信息添加失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onHandleCancel() {
        dialogStage.close();
    }

//    public void onHandleDeleteRow() {
//        pressTable.getItems().removeAll(pressTable.getSelectionModel().getSelectedItem());
//    }
}
