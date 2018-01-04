package app;

import com.sun.javafx.robot.impl.FXRobotHelper;
import controller.LoginController;
import controller.RegisterController;
import db.UserManager;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
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
            this.primaryStage.setHeight(450);
            this.primaryStage.setWidth(540);
            login.setApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToRegister() {
        try {
            RegisterController register = (RegisterController) replaceSceneContent("../../resources/Register.fxml");
            this.primaryStage.setHeight(530);
            this.primaryStage.setWidth(540);
            register.setApp(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean userLogging(String userId, String password) {
        if (UserManager.validate(userId, password)) {
            // 页面跳转
            ObservableList<Stage> stages = FXRobotHelper.getStages();
            try {
                Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../../resources/Operation.fxml")));
                this.primaryStage.setHeight(600);
                this.primaryStage.setWidth(1000);
                stages.get(0).setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else {
            return false;
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

        return (Initializable) loader.getController();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
