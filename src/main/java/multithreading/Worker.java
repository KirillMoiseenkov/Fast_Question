package multithreading;
//Prepare to working

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Worker implements Runnable{



    private Socket socket;
    private int id;
    private List<String> questions;
    public Worker(Socket socket){

        this.socket = socket;
        id = createQuestion();

    }


    public void run() {

        try {
            InputStream in = socket.getInputStream();
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);


            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String request;
            while ((request = br.readLine()) != null) {
                System.out.println("Message received:" + request);
                    out.println(request + " kak");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private synchronized int createQuestion(){


        return 1;
    }

    private boolean sendAnswer(){

        return true;
    }

    private synchronized String getAnswers(){

        return "answer";

    }

    private synchronized List<String> getQuestion(){

        List<String> list = new ArrayList<String>();

        return list;
    }

}
