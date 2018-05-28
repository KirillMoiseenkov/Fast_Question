package service;

import dao.MessageDaoImpl;
import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageDaoImpl messageDao;

    @Transactional(readOnly = true)
    public Message getByID(long id){

        return messageDao.getById(id);

    }

    @Transactional(readOnly = true)
    public List<Message> getAll(){

       return messageDao.getAll();
    }

    @Transactional()
    public void create(Message message){
        messageDao.create(message);
    }

}
