package com.bowlingsim.msgbox;


import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.awt.*;

public class MsgBox {

    public void showInfoBox(String title, String header, String message, Alert.AlertType alertType){
        Alert userMsg = new Alert(alertType);
        addIcon(userMsg);
        userMsg.setTitle(title);
        userMsg.setHeaderText(header);
        userMsg.setContentText(message);
        Toolkit.getDefaultToolkit().beep();
        userMsg.showAndWait();
    }

    public void addIcon(Alert msg){
        final Image APP_ICON = new Image("/com/bowlingsim/res/bowlingIcon.png");
        Stage infoStage = (Stage) msg.getDialogPane().getScene().getWindow();
        infoStage.getIcons().add(APP_ICON);
    }
}
