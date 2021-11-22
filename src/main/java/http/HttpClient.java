/*
 * Here should be licence
 */
package http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author mcaikovs
 */
public class HttpClient {

    public static String get(String url) throws Exception {

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        }
    }
}
