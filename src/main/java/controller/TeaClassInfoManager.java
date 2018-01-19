package main.java.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import main.java.dao.ClassInfo;
import main.java.db.UserManager;
import main.java.utils.JDBCUtils;
import main.java.utils.TextUtils;
import main.java.utils.Toast;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class TeaClassInfoManager implements Initializable {

    @FXML
    AnchorPane pane;
    @FXML
    JFXTextField classNoInput, studentNumInput, courseInput, monitorInput;
    @FXML
    JFXButton addButton;
    @FXML
    TableView<ClassInfo> classTable;

    private ObservableList<ClassInfo> classInfoObservableList = FXCollections.observableArrayList();
    private String[] columns = {"classNo", "course", "stuNumber", "monitor"};
    private int index = 0;
    private String teacherNo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teacherNo = (String) UserManager.CURRENT_USR.get("Tno");

        try {
            JDBCUtils.get().getQueryResults(
                    "SELECT teaching.Cno,course,Cnumber,Cmonitor FROM class,teaching " +
                            "WHERE class.Cno=teaching.Cno AND teaching.Tno=?",
                    Arrays.asList(teacherNo)
            ).forEach(map -> {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setCourse((String) map.get("course"));
                classInfo.setClassNo((String) map.get("Cno"));
                classInfo.setStuNumber((Integer) map.get("Cnumber"));
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

    public void onHandleAddClassInfo() {
        String classNo = classNoInput.getText();
        String studentNum = studentNumInput.getText();
        String course = courseInput.getText();
        String monitor = monitorInput.getText();

        if (TextUtils.isEmpty(classNo) || !TextUtils.isDigitsOnly(studentNum) || TextUtils.isEmpty(course) || TextUtils.isEmpty(monitor)) {
            Toast.show(pane, "输入信息不能为空");
            return;
        }

        try {
            boolean res = JDBCUtils.get().update("INSERT INTO teaching VALUES(?, ?, ?)", Arrays.asList(teacherNo, classNo, course));
            res = JDBCUtils.get().update("INSERT INTO class VALUES(?, ?, ?)", Arrays.asList(classNo, studentNum, monitor));
            if (res) {
                ClassInfo classInfo = new ClassInfo(classNo, course, Integer.valueOf(studentNum), monitor);
                classInfoObservableList.add(classInfo);
                Toast.show(pane, "添加信息成功");
            } else {
                Toast.show(pane, "添加信息失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
