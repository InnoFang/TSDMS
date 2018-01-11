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
public class AdminController implements Initializable {

    private MainApp app;

    @FXML
    private JFXButton btnOverview, btnSubscriptionInfoSearch, btnDistributionInfoSearch, btnTextbookDistribute, btnAdminInfo;

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

        if (source == btnOverview) replacePane("../../resources/Adm_Overview.fxml");
        else if (source == btnSubscriptionInfoSearch) replacePane("../../resources/Adm_SubscriptionInfoSearch.fxml");
        else if (source == btnDistributionInfoSearch) replacePane("../../resources/Adm_DistributionInfoSearch.fxml");
        else if (source == btnTextbookDistribute) replacePane("../../resources/Adm_TextbookDistribute.fxml");
        else if (source == btnAdminInfo) replacePane("../../resources/Adm_AdminInfoManager.fxml");

    }

    private void replacePane(String src) {
        try {
            pane.getChildren().setAll((AnchorPane) FXMLLoader.load(getClass().getResource(src)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
