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


public class QuestionDaoImpl implements IDAO<Question> {

    private static final Logger log = Logger.getLogger(QuestionDaoImpl.class);

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public QuestionDaoImpl() {
    }


    public List<Question> getAll() {

        Transaction transaction = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            List<Question> questionList = session.createQuery("SELECT p FROM Question p").list();
            if(!transaction.wasCommitted())
            transaction.commit();
            return questionList;
        } catch (HibernateException e) {
            log.error(e);
            if (transaction != null)
                transaction.rollback();
            return null;
        }
    }

    @Override
    public Question getById(Long id) {
        Transaction transaction = null;
        try {

            Session session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            Question question = (Question) session.get(Question.class, id);
            if(!transaction.wasCommitted())
            transaction.commit();
            return question;
        } catch (HibernateException e) {
            log.error(e);
            if (transaction != null)
                transaction.rollback();

            return null;
        }
    }


    public Question create(Question question) {
        Transaction transaction = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            Question newQuestion = (Question) session.merge(question);
            if(!transaction.wasCommitted())
            transaction.commit();
            return newQuestion;
        } catch (HibernateException e) {
            log.error(e);
            if (transaction != null)
                transaction.rollback();
            return null;
        }
    }

    public List<Question> getRandomQuestion(int count) {
        Transaction transaction = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            List<Question> questionList = (List<Question>) session.createQuery("SELECT p FROM Question p order by rand()")
                    .setMaxResults(count)
                    .list();
            if(!transaction.wasCommitted())
            transaction.commit();
            return questionList;
        } catch (HibernateException e) {
            log.error(e);
            if (transaction != null)
                transaction.rollback();
            return null;
        }
    }

    public List<Question> getByQuestion(String question) {
        Transaction transaction = null;

        try {

            Session session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            List<Question> questionList = (List<Question>) session.createQuery("SELECT p FROM Question p WHERE p.question = :question")
                    .setParameter("question", question)
                    .list();
            if(!transaction.wasCommitted())
            transaction.commit();
            return questionList;
        } catch (HibernateException e) {
            log.error(e);
            if (transaction != null)
                transaction.rollback();
            return null;
        }
    }

    @Override
    public Question saveOrUpdate(Question question) {
        Transaction transaction = null;
        Session session = sessionFactory.getCurrentSession();
        try {

            transaction = session.getTransaction();
            transaction.begin();
            Long id = (Long) session.save(question);
            if(!transaction.wasCommitted())
            transaction.commit();
            return getById(id);
        } catch (HibernateException e) {
            log.error(e);
            if (transaction != null)
                transaction.rollback();
            return null;
        }
    }

    @Override
    public Question remove(Question question) {
        return null;
    }

    public Long saveOrUpdateId(Question question) {
        Transaction transaction = null;

        try {
            Session session = sessionFactory.getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            Long id = (Long) session.save(question);
            if(!transaction.wasCommitted())
                transaction.commit();
            return id;
        } catch (HibernateException e) {
            log.error(e);
            if (transaction != null)
                transaction.rollback();
            return null;
        }
    }
}
