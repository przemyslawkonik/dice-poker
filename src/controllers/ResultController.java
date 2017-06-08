package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.game.Game;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class ResultController implements Initializable {

    @FXML
    private Label result;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setVisible(boolean visibility) {
        result.setVisible(visibility);
    }

    public void bind(Game game) {
        this.result.textProperty().bind(game.resultProperty().asString());
        this.result.idProperty().bind(game.resultProperty().asString());
    }
}
