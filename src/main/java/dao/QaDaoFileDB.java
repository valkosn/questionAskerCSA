package dao;

import enteties.QA;
import utils.JsonFileConverter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class QaDaoFileDB implements QaDao {

    private List<QA> questions;

    private String filePath;

    public QaDaoFileDB(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<QA> getAllQuestions() {

        return questions;
    }

    @Override
    public List<QA> getRandomQuestions(int amount, String[] categories) {
        List<QA> shuffledList = new ArrayList<>(questions);
        Collections.shuffle(shuffledList);
        return shuffledList.subList(0, amount);
    }

    @Override
    public void setQuestions(List<QA> questions) {
        this.questions = questions;
    }

    @Override
    public String getCorrectAnswer(int id) {
        return questions.get(id).getAnswers().get(0);
    }

    @Override
    public int getQuestionAmount() {
        return questions.size();
    }

    private void init() throws IOException {
        this.questions = new JsonFileConverter().toJavaObjectViaJackson(filePath);
    }
}
