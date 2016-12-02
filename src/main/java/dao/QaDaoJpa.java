package dao;

import entity.QA;

import java.util.List;

/**
 * Created by Valko Serhii on 11/29/2016.
 */
public class QaDaoJpa implements QaDao{
    @Override
    public List<QA> getRandomQuestions(int amount, String[] categories) {
        return null;
    }

    @Override
    public List<QA> getAllQuestions() {
        return null;
    }

    @Override
    public List<QA> getAllQuestions(int categoryID) {
        return null;
    }

    @Override
    public int getQuestionAmount() {
        return 0;
    }

    @Override
    public QA getQuestion(int id) {
        return null;
    }

    @Override
    public String getCorrectAnswer(int id) {
        return null;
    }

    @Override
    public void deleteQuestion(int id) {

    }

    @Override
    public void deleteAnswer(int id) {

    }

    @Override
    public int save(QA qa) {
        return 0;
    }
}
