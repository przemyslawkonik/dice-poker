package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by Przemysław Konik on 2017-06-10.
 */
public class ResourceReader {

    public String readText(String fileName) {
        String text = "";
        try {
            URL url = getClass().getResource("/" + fileName + ".txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line;

            while ((line = in.readLine()) != null) {
                text += line + "\n";
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
