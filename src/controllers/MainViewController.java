package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerArrangementController.getArrangement().setDices(playerDicesController.getDices());
        enemyArrangementController.getArrangement().setDices(enemyDicesController.getDices());

    }

    @FXML
    public void handleAction() {
        playerDicesController.rollAll();
        enemyDicesController.rollAll();

        playerArrangementController.calculate();
        enemyArrangementController.calculate();
    }
}
