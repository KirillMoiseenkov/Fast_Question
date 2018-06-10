package service;

import dao.QuestionDaoImpl;
import models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class QuestionService implements ISerivce<Question>{

    @Autowired
    QuestionDaoImpl questionDao;// = new QuestionDaoImpl();


    public QuestionService(){}



    @Transactional
    public List<Question> getAll() {
        return questionDao.getAll();
    }

    public Question getById(Long id) {
        return questionDao.getById(id);
    }

    public Question saveOrUpdate(Question question) {
        return questionDao.saveOrUpdate(question);
    }

    public Question remove(Question question) {
        return questionDao.remove(question);
    }

    public List<Question> getRandomQuestion(int count){
        return questionDao.getRandomQuestion(count);
    }
}
