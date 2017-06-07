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

    public void play() {
        playRound();
    }

    public void prepare() {
        human.getDiceBox().setVisibleAll(false);
        human.getArrangement().setVisible(false);

        enemy.getDiceBox().setVisibleAll(false);
        enemy.getDiceBox().setDisableAll(true);
        enemy.getDiceBox().setOpacityAll(1);
        enemy.getArrangement().setVisible(false);
    }

    private void playRound() {
        new Thread( () -> {
            handleScreen(2000);
            firstTurn(human);
            handleScreen(2000);
            firstTurn(enemy);
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

    private void handleScreen(int miliseconds) {
        try{
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {}
    }


}
