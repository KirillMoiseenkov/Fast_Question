package dao;

import models.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<Message> getAll(){
        Session session = sessionFactory.getCurrentSession();
        return (List<Message>) session.createQuery("SELECT p FROM Message p").list();
    }

}
