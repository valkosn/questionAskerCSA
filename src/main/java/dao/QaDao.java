package dao;

import enteties.QA;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface QaDao {

    List<QA> getRandomQuestions(int amount, String[] categories);

    List<QA> getAllQuestions();

    int getQuestionAmount();

    void setQuestions(List<QA> questions);

    String getCorrectAnswer(int id);
}
