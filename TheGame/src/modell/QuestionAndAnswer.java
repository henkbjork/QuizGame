package modell;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class QuestionAndAnswer {

    public static List<String> getQuestionAndAnswer() {
        List<String> questionAndAnswer = new ArrayList<>();
        String str = ApiCall.apiCall();
        //getting question
        List<String> listQuestion;
        listQuestion = getKeyValue(str, "question");
        for (String s : listQuestion) {
            questionAndAnswer.add(0, decodeString(s));
        }
        //getting answers
        List<String> correctAnswer;
        correctAnswer = getKeyValue(str, "correct_answer");
        for (String s : correctAnswer) {
            questionAndAnswer.add(1, decodeString(s));
        }
        List<String> IncorrectAnswer;
        IncorrectAnswer = getKeyValue(str, "incorrect_answers");
        String[] answers = IncorrectAnswer.get(0).substring(2, IncorrectAnswer.get(0).length() - 2).split(",");
        for (int i = 0; i < answers.length; i++) {
            questionAndAnswer.add(i + 2, decodeString(answers[i].replace("\"", "")));
        }
        return questionAndAnswer;
    }

    private static List<String> getKeyValue(String jsonArrayStr, String key) {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString(key))
                .collect(Collectors.toList());
    }

    private static String decodeString(String codedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(codedString);
        return new String(decodedBytes);
    }

}
