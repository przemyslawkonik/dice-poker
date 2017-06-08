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
    private Label moneyLabel;

    private Money money;

    public MoneyController() {
        money = new Money(1000);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        moneyLabel.textProperty().bind(money.valueProperty().asString());
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
