package model.bet;

import controllers.BetController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.player.Player;
import model.pot.Pot;

/**
 * Created by Przemysław Konik on 2017-06-07.
 */
public class Bet {

    public boolean set(Player player, Pot pot, String message) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/bet.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        BetController betController = loader.getController();
        betController.setMessage(message);
        betController.setSlider(player);

        stage.showAndWait();

        if(betController.isBet()) {
            player.getMoney().decrease(betController.getValue());
            pot.increase(betController.getValue()*2);
            return true;
        }
        return false;
    }
}
