package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.money.Money;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class MoneyController implements Initializable {

    @FXML
    private Label money;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void bind(Money money) {
        this.money.textProperty().bind(money.valueProperty().asString());
    }
}
