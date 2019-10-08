package modell;

import org.json.JSONArray;
import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class QuestionAndAnswer {

    public static List<String> getQuestionAndAnswer() {
        List<String> questionAndAnswer = new ArrayList<>();
        String str = apiCall();
        //getting question
        List<String> listQuestion;
        listQuestion = getKeyValue(str, "question");
        for(String s : listQuestion) {
            questionAndAnswer.add(0, decodeString(s));
        }
        //getting answers
        List<String> correctAnswer;
        correctAnswer = getKeyValue(str, "correct_answer");
        for(String s : correctAnswer) {
            questionAndAnswer.add(1, decodeString(s));
        }
        List<String> IncorrectAnswer;
        IncorrectAnswer = getKeyValue(str, "incorrect_answers");
        String[] answers = IncorrectAnswer.get(0).substring(2, IncorrectAnswer.get(0).length()-2).split(",");
        for(int i=0; i<answers.length; i++) {
            questionAndAnswer.add(i+2, decodeString(answers[i].replace("\"", "")));
        }
        return questionAndAnswer;
    }

    private static String apiString() {
        StringBuilder sb = new StringBuilder();
        sb.append("https://opentdb.com/api.php?amount=1&category=");
        if(MultiPlayerGame.getCategory() == null) {
            sb.append("");
        } else {
            sb.append(MultiPlayerGame.getCategory());
        }
        sb.append("&difficulty=");
        if(MultiPlayerGame.getDifficulty() == null) {
            sb.append("easy");
        } else {
            sb.append(MultiPlayerGame.getDifficulty());
        }
        sb.append("&type=multiple&encode=base64");
        return sb.toString();
    }

    private static String apiCall()  {
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
                assert in != null;
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str = response.toString();
        return str.substring(29);
    }

    private static List<String> getKeyValue(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }

    private static String decodeString(String codedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(codedString);
        return new String(decodedBytes);
    }

}
