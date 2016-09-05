package dao;

import enteties.QA;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class QaDaoFileDB implements QaDao {

    private List<QA> questions = new ArrayList<>();

    private java.lang.String filePath;

    public QaDaoFileDB(java.lang.String filePath) {
        this.filePath = filePath;
    }

    public List<QA> getQuestions() {

        return questions;
    }

    public void setQuestions(List<QA> questions) {
        this.questions = questions;
    }

    private void init() {
        JSONTokener jsonTokener = null;
        try {
            jsonTokener = new JSONTokener(new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(filePath), "UTF-8")));
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
    }
}
