package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.game.Statistics;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class StatisticsController implements Initializable {

    @FXML
    private Label lostStats;

    @FXML
    private Label wonStats;

    @FXML
    private Label drawStats;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void bind(Statistics statistics) {
        wonStats.textProperty().bind(statistics.wonProperty().asString());
        lostStats.textProperty().bind(statistics.lostProperty().asString());
        drawStats.textProperty().bind(statistics.drawProperty().asString());
    }
}
