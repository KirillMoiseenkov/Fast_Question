package dao;

import HibernateUtil.HibernateUtil;
import models.Message;
import models.Question;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDaoImpl implements IDAO<Message> {

    private static final Logger log = Logger.getLogger(MessageDaoImpl.class);

    @Autowired
    SessionFactory sessionFactory;

    public MessageDaoImpl() { }


    public Message getById(long id) {
        Session session = sessionFactory.openSession();
        Message message = (Message) session.get(Message.class, id);
        log.info("message get : " + message.toString());
        session.close();
        return message;
    }

    public List<Message> getByQuestion(Question question) {
        Session session = sessionFactory.openSession();
        List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p WHERE p.question_id = :id")
                .setParameter("id", question).list();
        log.info("message list get : " + messageList.toString());
        session.close();
        return messageList;
    }

    public List<Message> getByQuestion(Long question_id) {




           Session session = sessionFactory.openSession();
            Question question = new Question();
            question.setId(question_id);
            List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p WHERE p.question_id = :question_id")
                    .setParameter("question_id", question).list();
            log.info("message list get : " + messageList.toString());
           session.close();
            return messageList;

    }

    public List<Message> getByQuestion(Long question_id, Long lastId) {
        Session session = sessionFactory.openSession();
        Question question = new Question();
        question.setId(question_id);
        List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p WHERE p.question_id = :id and p.id > :LastId")
                .setParameter("id", question)
                .setParameter("LastId", lastId)
                .list();
        log.info("message list get : " + messageList.toString());
        session.close();
        return messageList;
    }

    public List<Message> getAll() {
        Session session = sessionFactory.openSession();
        List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p").list();
        log.info("message list get : " + messageList.toString());
        session.close();
        return messageList;
    }

    @Override
    public Message getById(Long id) {

        Session session = sessionFactory.openSession();
        Message message = (Message) session.get(Message.class, id);
        log.info("message get : " + message.toString());
        session.close();
        return message;
    }


    @Override
    public Message remove(Message message) {
        return null;
    }

    public List<Message> getLastMessage(Long id) {
        Session session = sessionFactory.openSession();
        List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Message p WHERE p.id > :id")
                .setParameter("id", id).list();
        log.info("message list get : " + messageList.toString());
        session.close();
        return messageList;
    }


    public List<Message> getByMessage(String message) {


        Session session = sessionFactory.openSession();
           List<Message> messageList = (List<Message>) session.createQuery("SELECT p FROM Recrord p WHERE p.message = :message")
                   .setParameter("message", message)
                   .list();
           log.info("message get : " + message.toString());
        session.close();
           return messageList;

    }

    @Override
    public Message saveOrUpdate(Message message) {

        Session session = sessionFactory.openSession();
            Long id = (Long) session.save(message);
            log.info("message try be create : " + message.toString());
        session.close();
            return getById(id);

        }

}

