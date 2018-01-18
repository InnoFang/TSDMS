package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.ClassInfo;
import db.UserManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.JDBCUtils;
import utils.TextUtils;
import utils.Toast;

import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class TeaTextbookSubscribeController implements Initializable {
    @FXML
    AnchorPane pane;

    @FXML
    JFXTextField teacherNoInput, subscribeNumInput, textbookNoInput;
    @FXML
    JFXButton subscribeButton;

    @FXML
    TableView<ClassInfo> classTable;

    private ObservableList<ClassInfo> classInfoObservableList = FXCollections.observableArrayList();
    private String[] columns = {"classNo", "course", "stuNumber", "monitor"};
    private int index = 0;
    private String teacherNo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teacherNo = (String) UserManager.CURRENT_USR.get("Tno");
        teacherNoInput.setText(teacherNo);
        try {
            JDBCUtils.get().getQueryResults(
                    "SELECT teaching.Cno,course,Cnumber,Cmonitor FROM class,teaching " +
                            "WHERE class.Cno=teaching.Cno AND teaching.Tno=?",
                    Arrays.asList(teacherNo)
            ).forEach(map -> {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassNo((String) map.get("Cno"));
                classInfo.setStuNumber((Integer) map.get("Cnumber"));
                classInfo.setCourse((String) map.get("course"));
                classInfo.setMonitor((String) map.get("Cmonitor"));
                classInfoObservableList.add(classInfo);
            });

            index = 0;
            classTable.getColumns().forEach(classInfoTableColumn ->
                    classInfoTableColumn.setCellValueFactory(new PropertyValueFactory<>(columns[index++]))
            );

            classTable.setItems(classInfoObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onHandleSubscribe() {
        String subscribeNum = subscribeNumInput.getText();
        String textbookNo = textbookNoInput.getText();

        if (TextUtils.isEmpty(subscribeNum) || !TextUtils.isDigitsOnly(subscribeNum) || TextUtils.isEmpty(textbookNo)) {
            Toast.show(pane, "征订信息不能为空");
            return;
        }

        String now = new SimpleDateFormat("yyyy/MM").format(new Date());
        String[] yearAndMonth = now.split("/");
        String date = yearAndMonth[0] + "年";
        if (Integer.valueOf(yearAndMonth[1]) > 7)
            date += "秋季学期";
        else
            date += "春季学期";


        try {
            boolean res = JDBCUtils.get()
                    .update("INSERT INTO subscription VALUES(?, ?, ?, ?, ?)",
                            Arrays.asList(teacherNo, textbookNo, subscribeNum, "N", date));
            if (res) {
                Toast.show(pane, "征订申请成功");
            } else {
                Toast.show(pane, "征订申请失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
