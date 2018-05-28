package dao;

import models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageDaoImpl implements IServiceDAO<Message> {

    @PersistenceContext
    EntityManager entityManager;

    public Message getById(long id) {
        return entityManager.find(Message.class, id);
    }

    public List<Message> getAll() {
        return (List<Message>)entityManager.createQuery("SELECT p FROM Message p").getResultList();
    }

    @Override
    public void create(Message message) {


        entityManager.merge(message);

    }

    @Override
    public void update(Message message) {

    }

    @Override
    public void remove(Message message) {

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void removeAll() {

    }

    public List<Message> getByMessage(String message){

        return (List<Message>)entityManager.createQuery("SELECT p FROM Recrord p WHERE p.message = :message")
                .setParameter("message",message)
                .getResultList();

    }


}
