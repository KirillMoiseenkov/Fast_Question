import models.Message;
import service.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.QuestionService;

import java.util.List;

public class main {
    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");

        List<Message> messageList;

        MessageService messageService = context.getBean(MessageService.class);
        QuestionService questionService = context.getBean(QuestionService.class);


       Message perMessage = new Message(7L,questionService.getByID(2L),"Victor");

        System.out.println(questionService.getByID(2L).toString());

        messageService.create(perMessage);

        messageList = messageService.getAll();

        messageList.forEach(i-> System.out.println(i.toString()));


    }

}
