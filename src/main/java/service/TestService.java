package service;

import dao.TestDAO;
import models.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestService  {

    @Autowired
    TestDAO testDAO;

    @Transactional
    public List<Message> getAll(){
        return testDAO.getAll();
    }

}
