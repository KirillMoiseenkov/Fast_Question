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


        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");

        TestServices testServices = context.getBean(TestServices.class);

        System.out.println(testServices.testService.getAll().toString());

    }

}
