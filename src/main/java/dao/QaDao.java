package dao;

import enteties.QA;

import java.util.Set;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public interface QaDao {

    Set<QA> getQuestions();
    void setQuestions(Set<QA> questions);
}
