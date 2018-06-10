import multithreading.Acceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestServer {
    public static void main(String[] args) {


        Acceptor acceptor = new Acceptor(8080);
        new Thread(acceptor).start();



    }
}
