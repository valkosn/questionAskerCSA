package service;

import dao.CategoryDao;
import dao.QaDao;
import model.QA;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class QaServiceImpl implements QaService {

    private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
    private QaDao qaDAO = context.getBean("qa_dao", QaDao.class);
    private CategoryDao categoryDAO = context.getBean("category_dao", CategoryDao.class);

    public QaServiceImpl() {
    }

    @Override
    public QA find(int id) {
        for (QA question : qaDAO.getQuestions()) {
            if(question.getId() == id)
                return question;
        }
        throw new NoSuchElementException("Question with id - " + id + " not found");
    }

    @Override
    public QA findByQuestion(String questionValue) {
        for (QA question : qaDAO.getQuestions()) {
            if(question.getQuestion().equals(questionValue))
                return question;
        }
        throw new NoSuchElementException("Question with questionValue - " + questionValue + " not found");
    }

    @Override
    public boolean save(QA qa) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Set<QA> findAllQuestionByCategory(String category) {
        return null;
    }

    @Override
    public Set<QA> getAllQuestions() {
        return qaDAO.getQuestions();
    }
}
