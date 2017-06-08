package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import model.dice.DiceBox;

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
    private ToggleButton dice1;

    @FXML
    private ToggleButton dice2;

    @FXML
    private ToggleButton dice3;

    @FXML
    private ToggleButton dice4;

    @FXML
    private ToggleButton dice5;

    private List<ToggleButton> dices;
    private DiceBox diceBox;

    public DicesController() {
        diceBox = new DiceBox(5);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dices = createList();
        initBindings();
    }

    private List<ToggleButton> createList() {
        return new LinkedList<>(Arrays.asList(
                dice1, dice2, dice3, dice4, dice5
        ));
    }

    private void initBindings() {
        for(int i=0; i<dices.size(); i++) {
            dices.get(i).textProperty().bind(diceBox.getDice(i).valueProperty().asString());
            dices.get(i).idProperty().bind(diceBox.getDice(i).stateProperty().asString());
        }
    }

    public DiceBox getDiceBox() {
        return diceBox;
    }

    public void setDiceBox(DiceBox diceBox) {
        this.diceBox = diceBox;
    }

    public void rollSelected() {
        for(int i=0; i<dices.size(); i++) {
            if(dices.get(i).isSelected()) {
                diceBox.getDice(i).roll();
            }
        }
    }

    public void setSelectedAll(boolean selected) {
        for(ToggleButton d : dices) {
            d.setSelected(selected);
        }
    }

    public void setVisibleSelected(boolean visibility) {
        for(ToggleButton d : dices) {
            if(d.isSelected()) {
                d.setVisible(visibility);
            }
        }
    }

    public void setVisibleAll(boolean visibility) {
        for(ToggleButton d : dices) {
            d.setVisible(visibility);
        }
    }

    public void setDisableAll(boolean disability) {
        for(ToggleButton d : dices) {
            d.setDisable(disability);
        }
    }

    public ToggleButton getDice1() {
        return dice1;
    }

    public void setDice1(ToggleButton dice1) {
        this.dice1 = dice1;
    }

    public ToggleButton getDice2() {
        return dice2;
    }

    public void setDice2(ToggleButton dice2) {
        this.dice2 = dice2;
    }

    public ToggleButton getDice3() {
        return dice3;
    }

    public void setDice3(ToggleButton dice3) {
        this.dice3 = dice3;
    }

    public ToggleButton getDice4() {
        return dice4;
    }

    public void setDice4(ToggleButton dice4) {
        this.dice4 = dice4;
    }

    public ToggleButton getDice5() {
        return dice5;
    }

    public void setDice5(ToggleButton dice5) {
        this.dice5 = dice5;
    }

    public List<ToggleButton> getDices() {
        return dices;
    }

    public void setDices(List<ToggleButton> dices) {
        this.dices = dices;
    }
}
