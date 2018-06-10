package service;

import dao.MessageDaoImpl;
import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MessageService{// implements IService<Message> {


    @Autowired
    MessageDaoImpl messageDao;



    public MessageService(){}


    @Transactional
    public List<Message> getAll() {
       return messageDao.getAll();
    }
    @Transactional
    public Message getById(Long id) {
        return messageDao.getById(id);
    }
    @Transactional
    public Message saveOrUpdate(Message message) {
       return messageDao.saveOrUpdate(message);
    }
    @Transactional
    public Message remove(Message message) {
        return messageDao.remove(message);
    }
    @Transactional
    public List<Message> getByQuestion(Long id){
        return messageDao.getByQuestion(id);
    }

    @Transactional
    public List<Message> getByQuestion(Long id, Long lastId){
        return messageDao.getByQuestion(id,lastId);
    }


}
