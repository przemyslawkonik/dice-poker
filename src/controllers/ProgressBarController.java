package controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

/**
 * Created by Przemysław Konik on 2017-06-08.
 */
public class ProgressBarController {

    @FXML
    private ProgressBar progress;

    private final double step = 0.1;
    private final int gap = 250;

    public void setVisible(boolean visibility) {
        progress.setVisible(visibility);
    }

    public void run() {
        double max = 1 / step;
        progress.setProgress(0);
        progress.setVisible(true);

        for (int i = 0; i < max; i++) {
            Platform.runLater(() -> {
                progress.setProgress(progress.getProgress() + step);
            });
            handleScreen(gap);
        }
        progress.setVisible(false);
    }

    private void handleScreen(int miliseconds) {
        try{
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {}
    }
}
