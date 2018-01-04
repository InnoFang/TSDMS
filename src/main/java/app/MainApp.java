package app;

import controller.LoginController;
import controller.RegisterController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * Created by Inno Fang on 2018/1/4.
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private Group root = new Group();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        this.primaryStage.setScene(new Scene(createContent()));
        this.primaryStage.show();
    }

    private Parent createContent() {
        goToLogin();
        return root;
    }

    public void goToLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("../../resources/Login.fxml");
            this.primaryStage.setHeight(460);
            this.primaryStage.setWidth(540);
            login.setApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToRegister() {
        try {
            RegisterController register = (RegisterController) replaceSceneContent("../../resources/Register.fxml");
            this.primaryStage.setHeight(545);
            this.primaryStage.setWidth(540);
            register.setApp(this);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = MainApp.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(MainApp.class.getResource(fxml));
        AnchorPane page;

        try {
            page = loader.load(in);
        } finally {
            in.close();
        }
        root.requestFocus();
        root.getChildren().removeAll();
        root.getChildren().clear();
        root.getChildren().addAll(page);

        return (Initializable)loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
