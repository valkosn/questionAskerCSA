package service;

import dao.CategoryDao;
import dao.QaDao;
import model.QA;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class QaServiceImpl implements QaService {

    private ApplicationContext context = new ClassPathXmlApplicationContext("spring/dao.xml");
    private QaDao qaDAO = context.getBean("qa_dao", QaDao.class);
    private CategoryDao categoryDAO = context.getBean("category_dao", CategoryDao.class);

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
}
