package dao;

import HibernateUtil.HibernateUtil;
import models.Message;
import models.Question;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


public class MessageDaoImpl implements IDAO<Message> {

    private static final Logger log = Logger.getLogger(MessageDaoImpl.class);



    public MessageDaoImpl() { }


    public Message getById(long id) {
        Session session;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            Message message = (Message) session.get(Message.class, id);
            transaction.commit();
            log.info("message get : " + message.toString());
            return message;
        }catch (HibernateException e)
        {
            log.error("hibernate exeption : " + e);
            if(transaction != null)
                transaction.rollback();
            return null;
        }
    }

    public List<Message> getByQuestion(Question question) {
        Session session;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.getTransaction();
        transaction.begin();
        List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p WHERE p.question_id = :id")
                .setParameter("id", question).list();
        transaction.commit();
        log.info("message list get : " + messageList.toString());
        return messageList;
        }catch (HibernateException e)
        {
            log.error("hibernate exeption : " + e);
            if(transaction != null)
                transaction.rollback();
            return null;
        }
    }

    public List<Message> getByQuestion(Long question_id) {

        Session session;
        Transaction transaction = null;


        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            Question question = new Question();
            question.setId(question_id);
            List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p WHERE p.question_id = :question_id")
                    .setParameter("question_id", question).list();
            transaction.commit();
            log.info("message list get : " + messageList.toString());
            return messageList;
        }catch (HibernateException e)
        {
            log.error("hibernate exeption : " + e);
           if(transaction != null)
            transaction.rollback();
            return null;
        }
    }

    public List<Message> getByQuestion(Long question_id, Long lastId) {
        Session session;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            Question question = new Question();
            question.setId(question_id);
            List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p WHERE p.question_id = :id and p.id > :LastId")
                    .setParameter("id", question)
                    .setParameter("LastId", lastId)
                    .list();
            log.info("message list get : " + messageList.toString());
            transaction.commit();
            return messageList;
        }catch (HibernateException e){
        if(transaction!=null)
            transaction.rollback();
        log.error(e);
        return null;
    }
    }

    public List<Message> getAll() {
        Session session;
        Transaction transaction = null;
        try{
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.getTransaction();
        transaction.begin();
        List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p").list();
        transaction.commit();
        log.info("message list get : " + messageList.toString());
        return messageList;
    }catch (HibernateException e){
        if(transaction!=null)
            transaction.rollback();
        log.error(e);
        return null;
    }
    }

    @Override
    public Message getById(Long id) {
        Session session;
        Transaction transaction = null;
        try{
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.getTransaction();
        transaction.begin();
        Message message = (Message) session.get(Message.class, id);
        transaction.commit();
        log.info("message get : " + message.toString());
        return message;
    }catch (HibernateException e){
        if(transaction!=null)
        transaction.rollback();
        log.error(e);
        return null;
        }
    }


    @Override
    public Message remove(Message message) {
        return null;
    }

    public List<Message> getLastMessage(Long id) {

        Session session;
        Transaction transaction = null;
        try{
        session = HibernateUtil.getSessionFactory().getCurrentSession();
        transaction = session.getTransaction();
        transaction.begin();
        List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p WHERE p.id > :id")
                .setParameter("id", id).list();
        transaction.commit();

        log.info("message list get : " + messageList.toString());

        return messageList;
    }catch (HibernateException e){
        if(transaction!=null)
            transaction.rollback();
        log.error(e);
        return null;
    }
    }


    public List<Message> getByMessage(String message) {

        Session session;
        Transaction transaction = null;
       try {


           session = HibernateUtil.getSessionFactory().getCurrentSession();
           transaction = session.getTransaction();
           transaction.begin();
           List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Recrord p WHERE p.message = :message")
                   .setParameter("message", message)
                   .list();
           transaction.commit();
           log.info("message get : " + message.toString());

           return messageList;
       }catch (HibernateException e){
           if(transaction!=null)
               transaction.rollback();
           log.error(e);
           return null;
       }
    }

    @Override
    public Message saveOrUpdate(Message message) {

        Session session;
        Transaction transaction = null;

        try {


            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.getTransaction();
            transaction.begin();
            Long id = (Long) session.save(message);
            transaction.commit();
            log.info("message try be create : " + message.toString());
            return getById(id);
        }catch (HibernateException e){
            if(transaction!=null)
            transaction.rollback();
            log.error(e);
            return null;
        }
        }

}

