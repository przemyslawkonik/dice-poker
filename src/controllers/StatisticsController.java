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
    private Label lost;

    @FXML
    private Label won;

    @FXML
    private Label draw;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void bind(Statistics statistics) {
        won.textProperty().bind(statistics.wonProperty().asString());
        lost.textProperty().bind(statistics.lostProperty().asString());
        draw.textProperty().bind(statistics.drawProperty().asString());
    }
}
