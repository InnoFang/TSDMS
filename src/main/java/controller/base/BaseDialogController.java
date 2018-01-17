package controller.base;

import javafx.stage.Stage;

/**
 * Created by Inno Fang on 2018/1/17.
 */
public class BaseDialogController {

    protected Stage dialogStage;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
