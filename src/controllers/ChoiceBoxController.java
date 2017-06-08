package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class ChoiceBoxController {

    @FXML
    private Label label;

    private boolean result;

    public ChoiceBoxController() {
        result = false;
    }

    public void handleYes(ActionEvent event) {
        result = true;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void handleNo(ActionEvent event) {
        result = false;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public boolean getResult() {
        return result;
    }

    public void setMessage(String message) {
        label.setText(message);
    }
}
