package controller;

import app.MainApp;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/4.
 */
public class TeacherController implements Initializable {

    private MainApp app;

    @FXML
    private JFXButton btnOverview, btnTextbookSearch, btnTextbookSubscribe, btnClassInfo, btnTeacherInfo;

    @FXML
    private AnchorPane pane;

    public void setApp(MainApp app) {
        this.app = app;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void onHandleAction(ActionEvent event) {

        Object source = event.getSource();
        if (source == btnOverview) replaceScene("../../resources/Tea_Overview.fxml");
        else if (source == btnTextbookSearch) replaceScene("../../resources/Tea_TextbookInfoSearch.fxml");
        else if (source == btnTextbookSubscribe) replaceScene("../../resources/Tea_TextbookSubscribe.fxml");
        else if (source == btnClassInfo) replaceScene("../../resources/Tea_ClassInfoManager.fxml");
        else if (source == btnTeacherInfo) replaceScene("../../resources/Tea_TeacherInfoManager.fxml");


    }

    public void replaceScene(String scene) {
        try {
            pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource(scene)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
