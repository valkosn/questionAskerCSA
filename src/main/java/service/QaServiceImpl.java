package service;

import dao.CategoryDao;
import dao.QaDao;
import enteties.QA;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
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
        return qaDAO.getAllQuestions().get(id);
    }

    @Override
    public QA findByQuestion(String questionValue) {
        for (QA question : qaDAO.getAllQuestions()) {
            if (question.getQuestion().equals(questionValue))
                return question;
        }
        throw new NoSuchElementException("Question with questionValue - " + questionValue + " not found");
    }

    @Override
    public List<QA> findAllQuestionByCategory(String category) {
        return qaDAO.getAllQuestions().stream().filter(question ->
                question.getCategory().equals(category)).collect(Collectors.toList());
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
    public List<QA> getAllQuestions() {
        return qaDAO.getAllQuestions();
    }

    @Override
    public List<QA> getRandomQuestions(int amount) {
        return qaDAO.getRandomQuestions(amount);
}

    @Override
    public int getQuestionAmount(){
        return getAllQuestions().size();
    }

    @Override
    public String getCorrectAnswer(int id) {
        return qaDAO.getCorrectAnswer(id);
    }

    public void setQaDAO(QaDao qaDAO) {
        this.qaDAO = qaDAO;
    }

    public void setCategoryDAO(CategoryDao categoryDAO) {
        this.categoryDAO = categoryDAO;
    }
}
