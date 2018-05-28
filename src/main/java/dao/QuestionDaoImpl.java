package dao;

import models.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionDaoImpl implements IServiceDAO<Question> {


    @PersistenceContext
    EntityManager entityManager;


    public Question getById(long id) {
        return  entityManager.find(Question.class, id);
    }

    public List<Question> getAll() {
        return entityManager.createQuery("SELECT p FROM Question p").getResultList();
    }

    @Override
    public void create(Question question) {

    }

    @Override
    public void update(Question question) {

    }

    @Override
    public void remove(Question question) {

    }

    @Override
    public void remove(long id) {

    }

    @Override
    public void removeAll() {

    }

    public List<Question> getByQuestion(String question){


        return (List<Question>)entityManager.createQuery("SELECT p FROM Question p WHERE p.question = :question")
                .setParameter("question",question)
                .getResultList();
    }
}
