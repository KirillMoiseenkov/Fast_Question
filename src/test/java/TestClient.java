import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TestClient {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost",8080);

        PrintWriter out = null;

        out = new PrintWriter(socket.getOutputStream(),true);
        InputStream in = socket.getInputStream();

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new InputStreamReader(in));


        while (true) {
            System.out.print("client: ");
            String userInput = stdIn.readLine();
            if ("q".equals(userInput)) {
                break;
            }
            out.println(userInput);
            System.out.println(br.readLine());
        }

        socket.close();
    }
}
