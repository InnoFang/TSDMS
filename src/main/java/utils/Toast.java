package utils;

import com.jfoenix.controls.JFXSnackbar;
import javafx.scene.layout.Pane;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class Toast {

    public static void show(Pane pane, String message) {
        JFXSnackbar toast = new JFXSnackbar(pane);
        toast.show(message, 3000);
    }

}
