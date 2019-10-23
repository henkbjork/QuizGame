package modell;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public abstract class ApiCall {

    public static String apiCall() {
        HttpsURLConnection con;
        BufferedReader in = null;
        StringBuilder response = new StringBuilder();
        try {
            URL URLobj = new URL(apiString());
            con = (HttpsURLConnection) URLobj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str = response.toString();
        return str.substring(29);
    }

    private static String apiString() {
        StringBuilder sb = new StringBuilder();
        sb.append("https://opentdb.com/api.php?amount=1&category=");
        if (MultiPlayerGame.getCategory() == null) {
            sb.append("");
        } else {
            sb.append(MultiPlayerGame.getCategory());
        }
        sb.append("&difficulty=");
        if (MultiPlayerGame.getDifficulty() == null) {
            sb.append("easy");
        } else {
            sb.append(MultiPlayerGame.getDifficulty());
        }
        sb.append("&type=multiple&encode=base64");
        return sb.toString();
    }

}
