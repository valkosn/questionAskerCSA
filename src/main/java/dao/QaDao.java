package dao;

import enteties.QA;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface QaDao {

    List<QA> getRandomQuestions(int amount, String[] categories);

    List<QA> getAllQuestions();

    List<QA> getAllQuestions(int categoryID);

    int getQuestionAmount();

    QA getQuestion(int id);

    String getCorrectAnswer(int id);

    void deleteQuestion(int id);

    void deleteAnswer(int id);

    int save(QA qa);
}
