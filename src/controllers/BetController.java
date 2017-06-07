package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import model.player.Player;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class BetController {

    @FXML
    private Slider slider;

    @FXML
    private Label message;

    @FXML
    private Label value;

    private boolean bet;

    public BetController() {
        bet = false;
    }

    @FXML
    public void handleSubmit(ActionEvent event) {
        bet = true;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public void setSlider(Player player) {
        slider.setMax(player.getMoney().getValue());
        slider.setValue(0);

        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            slider.setValue(newValue.intValue());
            value.setText(""+newValue.intValue());
        });
    }

    public boolean isBet() {
        return bet;
    }

    public int getValue() {
        return Integer.parseInt(value.getText());
    }
}
