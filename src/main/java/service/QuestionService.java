package service;

import dao.QuestionDaoImpl;
import models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDaoImpl questionDao;

    @Transactional(readOnly = true)
    public Question getByID(long id){

        return questionDao.getById(id);

    }

    @Transactional(readOnly = true)
    public List<Question> getAll(){
        return questionDao.getAll();
    }


}
