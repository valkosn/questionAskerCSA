package service;

import dao.CategoryDao;
import dao.QaDao;
import model.QA;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Valko Serhii on 29-Aug-16.
 */
public class QaServiceImpl implements QaService {

    private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dao.xml");
    private QaDao qaDAO = context.getBean("qa_dao", QaDao.class);
    private CategoryDao categoryDAO = context.getBean("category_dao", CategoryDao.class);

    public QaServiceImpl() {
    }

    public QA find(int id) {
        for (QA question : qaDAO.getQuestions()) {
            if(question.getId() == id)
                return question;
        }
        throw new NoSuchElementException("Question with id - " + id + " not found");
    }

    public QA findByQuestion(String question) {
        return null;
    }

    public boolean save(QA qa) {
        return false;
    }

    public boolean delete(int id) {
        return false;
    }

    public List<QA> findAllQuestionByCategory(String category) {
        return null;
    }
}
