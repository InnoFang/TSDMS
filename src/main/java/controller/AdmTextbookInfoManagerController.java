package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import controller.base.BaseDialogController;
import dao.Textbook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.JDBCUtils;
import utils.TextUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

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

    private ObservableList<Textbook> textbookObservableList = FXCollections.observableArrayList();
    private String[] columns = {"no", "name", "version", "price", "num", "pressName"};
    private int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textbookNo.setText("");
        textbookName.setText("");
        textbookVersion.setText("");
        textbookPrice.setText("");
        textbookStore.setText("");
        textbookPress.setText("");

        try {
            List<Map<String, Object>> list = JDBCUtils.get().getQueryResults(
                    "SELECT Bno,Bname,Bversion,Bprice,Bnum,Pname FROM textbook, press WHERE textbook.Pno=press.Pno",
                    new ArrayList<>());
            list.forEach(map -> {
                Textbook textbook = new Textbook();
                textbook.setNo((String) map.get("Bno"));
                textbook.setName((String) map.get("Bname"));
                textbook.setVersion((String) map.get("Bversion"));
                textbook.setPrice((Float) map.get("Bprice"));
                textbook.setNum((Integer) map.get("Bnum"));
                textbook.setPressName((String) map.get("Pname"));
                textbookObservableList.add(textbook);
            });

            index = 0;
            textbookTable.getColumns().forEach(textbookTableColumn ->
                    textbookTableColumn.setCellValueFactory(new PropertyValueFactory<>(columns[index++]))
            );

            textbookTable.setItems(textbookObservableList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onHandleSearch() {
        String no = searchTextbookNo.getText();
        if (!TextUtils.isEmpty(no)) {
            try {
                Map<String, Object> map = JDBCUtils.get()
                        .getQueryResult("SELECT Bno,Bname,Bversion,Bprice,Bnum,Pname FROM textbook, press WHERE textbook.Pno=press.Pno AND textbook.Bno=?",
                                Arrays.asList(no));
                if (!map.isEmpty()) {
                    textbookNo.setText((String) map.get("Bno"));
                    textbookName.setText((String) map.get("Bname"));
                    textbookVersion.setText((String) map.get("Bversion"));
                    textbookPrice.setText(String.valueOf(map.get("Bprice")));
                    textbookStore.setText(String.valueOf( map.get("Bnum")));
                    textbookPress.setText((String) map.get("Pname"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onHandleShowPressInfo() {
        showDialog("../../resources/Adm_PressInfoDialog.fxml", "出版社信息管理");
    }

    public void onHandleAddTextbook() {
        boolean okCLicked = showDialog("../../resources/Adm_TextbookAddDialog.fxml", "添加教材");
        if (okCLicked) {
            try {
                // 清空已有的
                textbookObservableList.clear();
                // 重新查询已有数据
                JDBCUtils.get().getQueryResults(
                        "SELECT Bno,Bname,Bversion,Bprice,Bnum,Pname FROM textbook, press WHERE textbook.Pno=press.Pno",
                        new ArrayList<>()).forEach(map -> {
                    Textbook textbook = new Textbook();
                    textbook.setNo((String) map.get("Bno"));
                    textbook.setNum((Integer) map.get("Bnum"));
                    textbook.setVersion((String) map.get("Bversion"));
                    textbook.setPrice((Float) map.get("Bprice"));
                    textbook.setName((String) map.get("Bname"));
                    textbook.setPressName((String) map.get("Pname"));
                    textbookObservableList.add(textbook);
                });

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private <T extends BaseDialogController> boolean showDialog(String src, String title) {
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
            return t.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
