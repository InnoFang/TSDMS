package controller;

import app.MainApp;
import com.jfoenix.controls.JFXButton;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/4.
 */
public class OperationController implements Initializable {

    private MainApp app;

    @FXML
    private JFXButton btnOverview, btnTextbookSearch, btnTextbookSubscription, btnClassInfo, btnTeacherInfo;

    @FXML
    private AnchorPane paneOperation;

    public void setApp(MainApp app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onHandleAction(ActionEvent event) {
        Object source = event.getSource();
        try {
            if (source == btnOverview) {
                paneOperation.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../../resources/Test1.fxml")));
            } else if (source == btnTextbookSearch) {
                paneOperation.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../../resources/Test2.fxml")));
            } else if (source == btnTextbookSubscription) {
                paneOperation.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../../resources/Test3.fxml")));
            } else if (source == btnClassInfo) {
                paneOperation.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../../resources/Test4.fxml")));
            } else if (source == btnTeacherInfo) {
                paneOperation.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource("../../resources/Test5.fxml")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void replaceScene(String scene) {

    }
}
