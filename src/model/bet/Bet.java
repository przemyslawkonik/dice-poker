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

        betController.getSlider().setMax(player.getMoney().getValue());
        betController.getSlider().setValue(0);

        betController.getSlider().valueProperty().addListener((observable, oldValue, newValue) -> {
            betController.getSlider().setValue(newValue.intValue());
            betController.getBetValue().setText(""+newValue.intValue());
        });

        stage.showAndWait();


        if(betController.getResult()) {
            int tmp = Integer.parseInt(betController.getBetValue().getText());
            player.getMoney().decrease(tmp);
            pot.increase(tmp);
            return true;
        }

        return false;
    }
}
