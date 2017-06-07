package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class BetController implements Initializable {

    @FXML
    private Slider slider;

    @FXML
    private Button submit;

    @FXML
    private Label message;

    @FXML
    private Label betValue;

    private boolean isBet;

    public BetController() {
        isBet = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void handleSubmit(ActionEvent event) {
        isBet = true;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public boolean getResult() {
        return isBet;
    }

    public Slider getSlider() {
        return slider;
    }

    public Label getBetValue() {
        return betValue;
    }
}
