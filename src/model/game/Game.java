package model.game;

import javafx.application.Platform;
import model.dice.Dice;
import model.dice.State;
import model.player.Player;

import java.util.Random;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Game {

    private Player human;
    private Player enemy;

    public Game(Player human, Player enemy) {
        this.human = human;
        this.enemy = enemy;
    }

    public void prepare() {
        human.getDiceBox().setVisibleAll(false);
        human.getDiceBox().setSelectedAll(false);
        human.getArrangement().setVisible(false);

        enemy.getDiceBox().setVisibleAll(false);
        enemy.getDiceBox().setSelectedAll(false);
        enemy.getDiceBox().setDisableAll(true);
        enemy.getArrangement().setVisible(false);
    }

    public void playFirstRound() {
        new Thread( () -> {
            handleScreen(2000);
            firstTurn(human);

            human.getDiceBox().setDisableAll(true);

            handleScreen(2000);
            firstTurn(enemy);

            human.getDiceBox().setDisableAll(false);
        }).start();
    }

    public void playSecondRound() {
        new Thread( () -> {
            human.getArrangement().setVisible(false);
            human.getDiceBox().setVisibleSelected(false);
            human.getDiceBox().setDisableAll(true);
            human.getDiceBox().setStateAll(State.UNMARKED);
            handleScreen(2000);
            secondTurn(human);

            //human.getDiceBox().setSelectedAll(false);

            enemyAI();
            enemy.getArrangement().setVisible(false);
            enemy.getDiceBox().setVisibleSelected(false);
            enemy.getDiceBox().setStateAll(State.UNMARKED);
            handleScreen(2000);
            secondTurn(enemy);

            //enemy.getDiceBox().setSelectedAll(false);
            //human.getDiceBox().setSelectedAll(false);
            //human.getDiceBox().setDisableAll(false);

        }).start();
    }


    private void firstTurn(Player player) {
        Platform.runLater( () -> {
            player.getDiceBox().rollAll();
            player.getArrangement().calculate();
            player.getDiceBox().setVisibleAll(true);
            player.getArrangement().setVisible(true);
        });
    }

    private void secondTurn(Player player) {
        Platform.runLater( () -> {
            player.getDiceBox().rollSelected();
            player.getArrangement().calculate();
            player.getDiceBox().setVisibleAll(true);
            player.getArrangement().setVisible(true);

            player.getDiceBox().setSelectedAll(false);
        });
    }

    private void handleScreen(int miliseconds) {
        try{
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {}
    }

    private void enemyAI() {
        for(Dice d : enemy.getDiceBox().getDices()) {
            int x = new Random().nextInt(2);
            if(x==0) {
                d.setSelected(true);
            } else {
                d.setSelected(false);
            }
            }
    }

}
