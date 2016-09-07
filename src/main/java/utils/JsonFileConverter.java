package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import enteties.QA;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valko Serhii on 02-Sep-16.
 */
public class JsonFileConverter {

    public static void toJSONFile(List<QA> qaList, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filePath), qaList);
    }

    public static String toJSONString(List<QA> qaList) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(qaList);
    }

    @SuppressWarnings("unchecked")
    public static List<QA> toJavaObjectViaJakson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), List.class);
    }

    public List<QA> toJavaObject(String filePath){
        List<QA> questions = new ArrayList<>();
        JSONTokener jsonTokener = null;
        try {
            jsonTokener = new JSONTokener(new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filePath), "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!(jsonTokener == null)) {
            JSONArray allJSONQuestions = new JSONArray(jsonTokener);
            int id = 0;
            for (Object currObj : allJSONQuestions) {
                JSONObject jsonObject = (JSONObject) currObj;
                java.lang.String question = jsonObject.getString("question");
                JSONArray answers = new JSONArray(jsonObject.getJSONArray("answers").toString());
                List<String> answersList = new ArrayList<>(answers.length());
                for (Object currAnswer : answers) {
                    answersList.add((String) currAnswer);
                }
                questions.add(new QA(id, question, answersList));
                id++;
            }
        }
        return questions;
    }
}
