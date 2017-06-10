package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Przemysław Konik on 2017-06-05.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Dice poker");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
