package service;

import dao.MessageDaoImpl;
import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.pkcs11.P11Util;

import java.util.List;


public class MessageService implements ISerivce<Message>{


    MessageDaoImpl messageDao = new MessageDaoImpl();



    public MessageService(){}



    @Override
    public List<Message> getAll() {
       return messageDao.getAll();
    }

    @Override
    public Message getById(Long id) {
        return messageDao.getById(id);
    }

    @Override
    public synchronized Message saveOrUpdate(Message message) {
       return messageDao.saveOrUpdate(message);
    }

    @Override
    public Message remove(Message message) {
        return messageDao.remove(message);
    }

    public List<Message> getByQuestion(Long id){
        return messageDao.getByQuestion(id);
    }

    public List<Message> getByQuestion(Long id, Long lastId){
        return messageDao.getByQuestion(id,lastId);
    }


}
