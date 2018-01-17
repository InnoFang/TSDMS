package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dao.Textbook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.*;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class TeaTextbookInfoSearchController implements Initializable {

    @FXML
    AnchorPane pane;
    @FXML
    TableView<Textbook> textbookTable;
    @FXML
    JFXTextField searchTextbookNo;
    @FXML
    JFXButton searchButton;
    @FXML
    Label textbookNo, textbookName, textbookVersion, textbookPrice, textbookStore, textbookPress;


    private ObservableList<Textbook> textbookObservableList = FXCollections.observableArrayList();
    private String[] columns = {"no", "name", "version", "price", "num", "pressName"};
    private int index = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textbookNo.setText("");
        textbookStore.setText("");
        textbookVersion.setText("");
        textbookPrice.setText("");
        textbookName.setText("");
        textbookPress.setText("");

        try {
            JDBCUtils.get().getQueryResults(
                    "SELECT Bno,Bname,Bversion,Bprice,Bnum,Pname FROM textbook, press WHERE textbook.Pno=press.Pno",
                    new ArrayList<>()).forEach(map -> {

                Textbook textbook = new Textbook();
                textbook.setNo((String) map.get("Bno"));
                textbook.setVersion((String) map.get("Bversion"));
                textbook.setPrice((Float) map.get("Bprice"));
                textbook.setName((String) map.get("Bname"));
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
                if (map.isEmpty()) {
                    Toast.show(pane, "请输入要搜索的教材编号");
                    return;
                }
                textbookNo.setText((String) map.get("Bno"));
                textbookName.setText((String) map.get("Bname"));
                textbookVersion.setText((String) map.get("Bversion"));
                textbookPrice.setText(String.valueOf(map.get("Bprice")));
                textbookStore.setText(String.valueOf(map.get("Bnum")));
                textbookPress.setText((String) map.get("Pname"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
