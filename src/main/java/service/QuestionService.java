package service;

import dao.QuestionDaoImpl;
import models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class QuestionService {//implements IService<Question> {

    @Autowired
    QuestionDaoImpl questionDao;


    public QuestionService(){}



    @Transactional
    public List<Question> getAll() {
        return questionDao.getAll();
    }

    @Transactional
    public Question getById(Long id) {
        return questionDao.getById(id);
    }
    @Transactional
    public Question saveOrUpdate(Question question) {
        return questionDao.saveOrUpdate(question);
    }
    @Transactional
    public Question remove(Question question) {
        return questionDao.remove(question);
    }
    @Transactional
    public List<Question> getRandomQuestion(int count){
        return questionDao.getRandomQuestion(count);
    }
}
