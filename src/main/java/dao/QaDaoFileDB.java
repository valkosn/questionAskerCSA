package dao;

import entity.QA;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
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
    public List<QA> getAllQuestions(int categoryID) {
        throw new NotImplementedException();
    }

    @Override
    public QA getQuestion(int id) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteAnswer(int id) {
        throw new NotImplementedException();
    }

    @Override
    public String getCorrectAnswer(int id) {
        return questions.get(id).getAnswers().get(0).getAnswer();
    }

    @Override
    public int getQuestionAmount() {
        return questions.size();
    }

    private void init() throws IOException {
        this.questions = new JsonFileConverter().toJavaObjectViaJackson(filePath);
    }

    @Override
    public void deleteQuestion(int id) {
        throw new NotImplementedException();
    }

    @Override
    public int save(QA qa) {
        throw new NotImplementedException();
    }
}
