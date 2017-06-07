package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.dice.Dice;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class DicesController implements Initializable {

    @FXML
    private Dice dice1;

    @FXML
    private Dice dice2;

    @FXML
    private Dice dice3;

    @FXML
    private Dice dice4;

    @FXML
    private Dice dice5;

    private List<Dice> dices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dices = createList();

    }

    private List<Dice> createList() {
        return new LinkedList<>(Arrays.asList(
                dice1, dice2, dice3, dice4, dice5
        ));
    }

    public void roll(int dice) {
        if(dice < 0 || dice > dices.size()) {
            throw new IndexOutOfBoundsException();
        }
        dices.get(dice).roll();
    }

    public void rollAll() {
        for(Dice d : dices) {
            d.roll();
        }
    }

    public List<Dice> getDices() {
        return dices;
    }
}
