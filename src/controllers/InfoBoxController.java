package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class InfoBoxController {

    @FXML
    private Label label;

    public void handleButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setMessage(String message) {
        label.setText(message);
    }

    
}
