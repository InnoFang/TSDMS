package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.base.BaseDialogController;
import dao.Textbook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class AdmTextbookInfoManagerController implements Initializable {

    @FXML
    TableView<Textbook> textbookTable;

    @FXML
    TableColumn<Textbook, String> noColumn, nameColumn, versionColumn, pressColumn;
    @FXML
    TableColumn<Textbook, Float> priceColumn;
    @FXML
    TableColumn<Textbook, Integer> storeColumn;

    @FXML
    JFXTextField searchTextbookNo;

    @FXML
    JFXButton addTextbookButton, pressInfoButton, searchButton;

    @FXML
    Label textbookNo, textbookName, textbookVersion, textbookPrice, textbookStore, textbookPress;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onHandleSearch() {

    }

    public void onHandleShowPressInfo() {
        showDialog("../../resources/Adm_PressInfoDialog.fxml", "出版社信息管理", AdmPressInfoDialogController.class);
    }

    public void onHandleAddTextbook() {
        showDialog("../../resources/Adm_TextbookAddDialog.fxml", "添加教材", AdmTextbookAddDialogController.class);
    }

    private <T extends BaseDialogController> void showDialog(String src, String title, Class<T> clazz){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(src));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            T t = loader.getController();
            t.setDialogStage(dialogStage);

            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
