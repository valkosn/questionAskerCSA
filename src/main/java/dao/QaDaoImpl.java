package dao;

import model.Answer;
import model.QA;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class QaDaoImpl implements QaDao {

    private Set<QA> questions = new HashSet<>();

    private String filePath;

    public QaDaoImpl(String filePath) {
        this.filePath = filePath;
        init();
    }

    public Set<QA> getQuestions() {

        return questions;
    }

    public void setQuestions(Set<QA> questions) {
        this.questions = questions;
    }

    private void init() {
        JSONTokener jsonTokener;
        try {
            jsonTokener = new JSONTokener(new FileReader(filePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONArray allJSONQuestions = new JSONArray(jsonTokener);
        int id = 0;
        for (Object currObj : allJSONQuestions) {
            JSONObject jsonObject = (JSONObject) currObj;
            String question = jsonObject.getString("question");
            JSONArray answers = new JSONArray(jsonObject.getJSONArray("answers").toString());
            List<Answer> answersList = new ArrayList<>(answers.length());
            for (Object currAnswer : answers) {
                answersList.add(new Answer((String) currAnswer));
            }
            questions.add(new QA(id, question, answersList));
            id++;
        }
    }
}
