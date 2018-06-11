import dao.QuestionDaoImpl;
import dao.TestDAO;
import models.Message;
import models.Question;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.*;

import java.util.List;

public class main {
    public static void main(String[] args) {



        MessageService messageService = new MessageService();
        QuestionService questionService = new QuestionService();
        System.out.println(messageService.getAll().toString());
        System.out.println(questionService.getAll().toString());
    }

}
