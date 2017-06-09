package tools;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

/**
 * Created by Przemysław Konik on 2017-06-09.
 */
public class Pause {

    private static final double step = 0.1;
    private static final int gap = 200;

    public static void run(ProgressBar progressBar) {
        double max = 1 / step;
        progressBar.setProgress(0);
        progressBar.setVisible(true);

        for (int i = 0; i < max; i++) {
            Platform.runLater(() -> {
                progressBar.setProgress(progressBar.getProgress() + step);
            });
            handleScreen(gap);
        }
        progressBar.setVisible(false);
    }

    private static void handleScreen(int milliSeconds) {
        try{
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {}
    }
}
