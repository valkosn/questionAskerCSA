package service;

import enteties.QA;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface QaService {

    QA find (int id);

    QA findByQuestion (String question);

    boolean save (QA qa);

    void delete (int id);

    List<QA> findAllQuestionByCategory (String category);

    List<QA> getAllQuestions();

    List<QA> getRandomQuestions(int amount, String[] categories);

    int getQuestionAmount();

    String getCorrectAnswer(int id);
}
