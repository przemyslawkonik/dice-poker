package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.dice.DiceBox;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        new DiceBox(5).roll(5);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
