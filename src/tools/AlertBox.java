package tools;

import controllers.InfoBoxController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class AlertBox {

    public void displayInfo(String message) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/infoBox.fxml"));

        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);

        InfoBoxController infoBoxController = loader.getController();
        infoBoxController.setMessage(message);

        stage.showAndWait();
    }
}