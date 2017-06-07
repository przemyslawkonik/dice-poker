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
        playerArrangementController.getArrangement().setDices(playerDicesController.getDiceBox().getDices());
        enemyArrangementController.getArrangement().setDices(enemyDicesController.getDiceBox().getDices());

        playerDicesController.getDiceBox().setVisibleAll(false);

        enemyDicesController.getDiceBox().setDisableAll(true);
        enemyDicesController.getDiceBox().setOpacityAll(1);
        enemyDicesController.getDiceBox().setVisibleAll(false);

        playerArrangementController.getArrangement().setVisible(false);
        enemyArrangementController.getArrangement().setVisible(false);

    }

    @FXML
    public void handleAction() {
        playerDicesController.getDiceBox().rollSelected();
        enemyDicesController.getDiceBox().rollAll();

        playerArrangementController.getArrangement().calculate();
        enemyArrangementController.getArrangement().calculate();

        playerDicesController.getDiceBox().setVisibleAll(true);
        enemyDicesController.getDiceBox().setVisibleAll(true);

        playerArrangementController.getArrangement().setVisible(true);
        enemyArrangementController.getArrangement().setVisible(true);
    }
}
