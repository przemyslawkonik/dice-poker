package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.game.Game;
import model.money.Money;
import model.player.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class MainViewController implements Initializable {

    @FXML
    private Button rollButton;

    @FXML
    private DicesController enemyDicesController;

    @FXML
    private ArrangementController enemyArrangementController;

    @FXML
    private DicesController playerDicesController;

    @FXML
    private ArrangementController playerArrangementController;

    @FXML
    private MoneyController playerMoneyController;

    @FXML
    private PotController potController;

    private Player human;
    private Player enemy;
    private Game game;
    private boolean first = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //playerArrangementController.getArrangement().setDices(playerDicesController.getDiceBox().getDices());
        //enemyArrangementController.getArrangement().setDices(enemyDicesController.getDiceBox().getDices());
        human = new Player(playerDicesController.getDiceBox(), playerArrangementController.getArrangement(), new Money(500));
        enemy = new Player(enemyDicesController.getDiceBox(), enemyArrangementController.getArrangement(), new Money(500));

        playerMoneyController.getMoney().textProperty().bind(human.getMoney().valueProperty().asString());
        human.bet(300, potController.getPot());

        game = new Game(human, enemy);
        game.prepare();
    }

    @FXML
    public void handleAction() {
        if(first) {
            game.prepare();
            game.playFirstRound();
            first = false;
        } else {
            game.playSecondRound();
            first = true;
        }
    }
}
