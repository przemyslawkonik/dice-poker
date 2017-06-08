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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dices = createList();
    }

    private List<ToggleButton> createList() {
        return new LinkedList<>(Arrays.asList(
                dice1, dice2, dice3, dice4, dice5
        ));
    }

    public void bind(DiceBox diceBox) {
        for(int i=0; i<dices.size(); i++) {
            dices.get(i).textProperty().bind(diceBox.getDice(i).valueProperty().asString());
            dices.get(i).idProperty().bind(diceBox.getDice(i).stateProperty().asString());
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
}
