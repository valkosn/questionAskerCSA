package service;

import dao.QaDao;
import entity.Answer;
import entity.QA;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class QaServiceImpl implements QaService {

    private QaDao qaDAO;

    public void setQaDAO(QaDao qaDAO) {
        this.qaDAO = qaDAO;
    }

    public QaServiceImpl() {
    }

    @Override
    public QA findById(int id) {
        return qaDAO.getQuestion(id);
    }

    @Override
    public QA findByQuestion(String questionValue) {
        for (QA question : qaDAO.getAllQuestions()) {
            if (question.getQuestion().equals(questionValue))
                return question;
        }
        throw new NoSuchElementException("Question with questionValue - " + questionValue + " not found");
    }

    // TODO: 10/29/2016 it should be refactored
    @Override
    public List<QA> getAllQuestionByCategory(String category) {
        return qaDAO.getAllQuestions().stream().filter(question ->
                question.getCategory().equals(category)).collect(Collectors.toList());
    }

    @Override
    public List<QA> getAllQuestionByCategory(int id) {
        return qaDAO.getAllQuestions(id);
    }

    @Override
    public int save(QA qa) {
        return qaDAO.save(qa);
    }

    @Override
    public List<QA> getAllQuestions() {
        return qaDAO.getAllQuestions();
    }

    @Override
    public List<QA> getRandomQuestions(int amount, String[] categories) {
        return qaDAO.getRandomQuestions(amount, categories);
    }

    @Override
    public int getQuestionAmount() {
        return qaDAO.getQuestionAmount();
    }

    @Override
    public String getCorrectAnswer(int id) {
        return qaDAO.getCorrectAnswer(id);
    }

    @Override
    public void deleteQuestion(int id) {
        QA qa = findById(id);
        if (!(qa == null)) {
            for (Answer answer : qa.getAnswers()) {
                deleteAnswer(answer.getAnswerId());
            }
            qaDAO.deleteQuestion(qa.getQuestionId());
        }
    }

    @Override
    public void deleteAnswer(int id) {
        qaDAO.deleteAnswer(id);
    }
}
