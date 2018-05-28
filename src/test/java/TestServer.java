import multithreading.Acceptor;

public class TestServer {
    public static void main(String[] args) {



        new Thread(new Acceptor(8080)).start();


    }
}
