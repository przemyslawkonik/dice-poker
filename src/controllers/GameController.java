package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

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
