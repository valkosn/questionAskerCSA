package service;

import dao.CategoryDao;
import dao.QaDao;
import enteties.QA;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class QaServiceImpl implements QaService {

    private QaDao qaDAO;
    private CategoryDao categoryDAO;

    public QaServiceImpl() {
    }

    @Override
    public QA find(int id) {
        for (QA question : qaDAO.getQuestions()) {
            if (question.getId() == id)
                return question;
        }
        throw new NoSuchElementException("Question with id - " + id + " not found");
    }

    @Override
    public QA findByQuestion(String questionValue) {
        for (QA question : qaDAO.getQuestions()) {
            if (question.getQuestion().equals(questionValue))
                return question;
        }
        throw new NoSuchElementException("Question with questionValue - " + questionValue + " not found");
    }

    @Override
    public Set<QA> findAllQuestionByCategory(String category) {

        return qaDAO.getQuestions().stream().filter(question ->
                question.getCategory().equals(category)).collect(Collectors.toSet());
    }

    @Override
    public boolean save(QA qa) {

        throw new NotImplementedException();
    }

    @Override
    public boolean delete(int id) {

        throw new NotImplementedException();
    }

    @Override
    public Set<QA> getAllQuestions() {
        return qaDAO.getQuestions();
    }

    public void setQaDAO(QaDao qaDAO) {
        this.qaDAO = qaDAO;
    }

    public void setCategoryDAO(CategoryDao categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
}
