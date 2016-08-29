package service;

import model.QA;

import java.util.List;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface QaService {

    QA find (int id);
    QA findByQuestion (String question);
    boolean save (QA qa);
    boolean delete (int id);
    List<QA> findAllQuestionByCategory (String category);
}
