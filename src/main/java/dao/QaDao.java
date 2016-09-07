package dao;

import enteties.QA;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface QaDao {

    List<QA> getRandomQuestions(int amount);

    List<QA> getAllQuestions();

    void setQuestions(List<QA> questions);

    String getCorrectAnswer(int id);
}
