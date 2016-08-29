package service;

import model.QA;

import java.util.List;
import java.util.Set;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface QaService {

    QA find (int id);
    QA findByQuestion (String question);
    boolean save (QA qa);
    boolean delete (int id);
    Set<QA> findAllQuestionByCategory (String category);
    Set<QA> getAllQuestions();
}
