package tools;

import controllers.BetController;
import controllers.ChoiceBoxController;
import controllers.InfoBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.game.Pot;
import model.player.Player;

import java.io.IOException;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class AlertBox {

    public void displayInfo(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/infoBox.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        InfoBoxController infoBoxController = loader.getController();
        infoBoxController.setMessage(message);

        stage.showAndWait();
    }

    public boolean displayChoice(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/choiceBox.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        ChoiceBoxController choiceBoxController = loader.getController();
        choiceBoxController.setMessage(message);

        stage.showAndWait();

        return choiceBoxController.getResult();
    }

    public boolean setBet(Player player, Pot pot, String message) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bet.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        BetController betController = loader.getController();
        betController.setMessage(message);
        betController.setSlider(player);
        betController.bindZero();

        stage.showAndWait();

        if(betController.isBet()) {
            player.getMoney().decrease(betController.getValue());
            pot.increase(betController.getValue() * 2);
            return true;
        }
        return false;
    }

    public boolean increaseBet(Player player, Pot pot, String message) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bet.fxml"));

        Stage stage = new Stage();
        stage.setScene(new Scene(loader.load()));
        stage.initModality(Modality.APPLICATION_MODAL);

        BetController betController = loader.getController();
        betController.setMessage(message);
        betController.setSlider(player);

        stage.showAndWait();

        if(betController.isBet()) {
            player.getMoney().decrease(betController.getValue());
            pot.increase(betController.getValue() * 2);
            return true;
        }
        return false;
    }
}
