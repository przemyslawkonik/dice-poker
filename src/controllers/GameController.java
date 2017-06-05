package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class GameController implements Initializable{

    @FXML
    private ToggleButton playerDice1;
    @FXML
    private ToggleButton playerDice2;
    @FXML
    private ToggleButton playerDice3;
    @FXML
    private ToggleButton playerDice4;
    @FXML
    private ToggleButton playerDice5;
    @FXML
    private ToggleButton enemyDice1;
    @FXML
    private ToggleButton enemyDice2;
    @FXML
    private ToggleButton enemyDice3;
    @FXML
    private ToggleButton enemyDice4;
    @FXML
    private ToggleButton enemyDice5;
    @FXML
    private Label playerCombination;
    @FXML
    private Label enemyCombination;
    @FXML
    private Circle playerWinMark1;
    @FXML
    private Circle playerWinMark2;
    @FXML
    private Circle enemyWinMark1;
    @FXML
    private Circle enemyWinMark2;
    @FXML
    private Label wonLabel;
    @FXML
    private Label lostLabel;
    @FXML
    private Label drawLabel;
    @FXML
    private Label potLabel;
    @FXML
    private Button actionButton;

    private List<ToggleButton> playerDices;
    private List<ToggleButton> enemyDices;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerDices = new LinkedList<>(Arrays.asList(
                playerDice1, playerDice2, playerDice3, playerDice4, playerDice5
        ));
        enemyDices = new LinkedList<>(Arrays.asList(
                enemyDice1, enemyDice2, enemyDice3, enemyDice4, enemyDice5
        ));
    }

}
