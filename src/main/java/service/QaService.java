package service;

import entity.QA;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface QaService {

    QA findById(int id);

    QA findByQuestion (String question);

    int save (QA qa);

    List<QA> getAllQuestionByCategory(String category);

    List<QA> getAllQuestionByCategory(int id);

    List<QA> getAllQuestions();

    List<QA> getRandomQuestions(int amount, String[] categories);

    int getQuestionAmount();

    String getCorrectAnswer(int id);

    void deleteQuestion(int id);

    void deleteAnswer(int id);
}
