package model.game;

import javafx.application.Platform;
import model.player.Player;

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
            handleScreen(2000);
            secondTurn(human);

            human.getDiceBox().setSelectedAll(false);

            enemy.getArrangement().setVisible(false);
            enemy.getDiceBox().setVisibleSelected(false);
            handleScreen(2000);
            secondTurn(enemy);

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
        });
    }

    private void handleScreen(int miliseconds) {
        try{
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {}
    }


}
