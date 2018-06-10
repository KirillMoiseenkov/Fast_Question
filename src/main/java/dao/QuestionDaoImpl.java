package dao;

import HibernateUtil.HibernateUtil;
import models.Question;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class QuestionDaoImpl implements IDAO<Question>{

    private static final Logger log = Logger.getLogger(QuestionDaoImpl.class);

    @Autowired
    SessionFactory sessionFactory;// = HibernateUtil.getSessionFactory();



    public QuestionDaoImpl(){ }


    public List<Question> getAll() {
        Session session = sessionFactory.openSession();
        List<Question> questionList = session.createQuery("SELECT p FROM Question p").list();
        session.close();
        return questionList;
    }

    @Override
    public Question getById(Long id) {
        Session session = sessionFactory.openSession();
        Question question = (Question) session.get(Question.class, id);
        session.close();
        return question;
    }


    public Question create(Question question) {
        Session  session = sessionFactory.openSession();
        Question newQuestion = (Question) session.merge(question);
        session.close();
        return newQuestion;
    }

    public List<Question> getRandomQuestion(int count){
        Session  session = sessionFactory.openSession();
        List<Question> questionList = (List<Question>) session.createQuery("SELECT p FROM Question p order by rand()")
                .setMaxResults(count)
                .list();
        session.close();
        return questionList;
    }

    public List<Question> getByQuestion(String question){
        Session   session = sessionFactory.openSession();
        List<Question> questionList = (List<Question>)session.createQuery("SELECT p FROM Question p WHERE p.question = :question")
                .setParameter("question",question)
                .list();
        session.close();
        return questionList;
    }

    @Override
    public Question saveOrUpdate(Question question){
        Session  session =sessionFactory.openSession();
        Long id = (Long) session.save(question);
        session.close();
        return getById(id);
    }

    @Override
    public Question remove(Question question) {
        return null;
    }

    public Long saveOrUpdateId(Question question){


            Session   session = sessionFactory.openSession();
            Long id = (Long) session.save(question);
             session.close();
            return id;

        }
}
