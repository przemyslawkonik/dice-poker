package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import model.bet.Bet;
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
    private boolean firstTurn = true;
    private boolean secondTurn = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //playerArrangementController.getArrangement().setDices(playerDicesController.getDiceBox().getDices());
        //enemyArrangementController.getArrangement().setDices(enemyDicesController.getDiceBox().getDices());
        human = new Player(playerDicesController.getDiceBox(), playerArrangementController.getArrangement(), new Money(500));
        enemy = new Player(enemyDicesController.getDiceBox(), enemyArrangementController.getArrangement(), new Money(500));

        playerMoneyController.getMoney().textProperty().bind(human.getMoney().valueProperty().asString());
        //human.bet(300, potController.getPot());

        game = new Game(human, enemy);
        game.prepare();
    }

    @FXML
    public void handleAction() throws Exception{
        if(firstTurn) {
            game.prepare();
            if(new Bet().set(human, potController.getPot(), "Set bet")) {
                game.playFirstRound();
                firstTurn = false;
                secondTurn = true;
            }
        } else if(secondTurn){
            if(new Bet().set(human, potController.getPot(), "Increase bet")) {
                game.playSecondRound();
                secondTurn = false;
            }
        }
        else /*if(!firstTurn && !secondTurn)*/{
            //testy
            /*
            if(playerArrangementController.getArrangement().getCombination().getWorth() > enemyArrangementController.getArrangement().getCombination().getWorth()) {
                System.out.println("win");
            } else if(playerArrangementController.getArrangement().getCombination().getWorth() < enemyArrangementController.getArrangement().getCombination().getWorth()) {
                System.out.println("lost");
            } else {
                if(playerArrangementController.getArrangement().getCombinationValue() > enemyArrangementController.getArrangement().getCombinationValue()) {
                    System.out.println("win");
                } else if(playerArrangementController.getArrangement().getCombinationValue() < enemyArrangementController.getArrangement().getCombinationValue()) {
                    System.out.println("lost");
                } else {
                    System.out.println("draw");
                }
            }
            */
            if(playerArrangementController.getArrangement().getCombinationValue() > enemyArrangementController.getArrangement().getCombinationValue()) {
                System.out.println("win");
            } else if(playerArrangementController.getArrangement().getCombinationValue() < enemyArrangementController.getArrangement().getCombinationValue()) {
                System.out.println("lost");
            } else {
                System.out.println("draw");
            }
            //koniec testow
            System.out.println(playerArrangementController.getArrangement().getCombinationValue());
            System.out.println(enemyArrangementController.getArrangement().getCombinationValue());

            firstTurn = true;
            secondTurn = false;
        }
    }
}
