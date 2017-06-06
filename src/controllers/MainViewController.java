package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Przemysław Konik on 2017-06-06.
 */
public class MainViewController {

    @FXML
    private Button rollButton;

    @FXML
    private DicesController enemyDicesController;

    @FXML
    private DicesController playerDicesController;

    @FXML
    public void handleAction() {
        playerDicesController.getDices().rollAll();
    }
}
