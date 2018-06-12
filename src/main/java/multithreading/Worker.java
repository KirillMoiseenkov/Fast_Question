package multithreading;
//Prepare to working

import dao.MessageDaoImpl;
import dao.QuestionDaoImpl;
import models.Message;
import models.Question;
import service.MessageService;
import service.QuestionService;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class Worker implements Runnable {

    private Socket socket;
    private List<Question> questionList;
    private List<Message> messageList;
    private MessageService messageService;
    private QuestionService questionService;
    private InputStream in;
    private PrintWriter out;
    public Worker() {}




    public Worker(Socket socket) {

        messageService = new MessageService();
        questionService = new QuestionService();

      this.socket = socket;
        }

    public Worker(Socket socket, MessageService messageService, QuestionService questionService) {

        this.messageService = messageService;
        this.questionService = questionService;
        this.socket = socket;
    }


    public void run() {


        try {
            InputStream in = socket.getInputStream();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String request;
            for (int i = 0; i < 3; i++) {
                Long id = 1L;
                Question question = new Question();

                do {
                    question = questionService.getRandomQuestion(1).get(0);
                } while (question == null);

                out.println(question.getQuestion());

                request = br.readLine();
                Message message = new Message();
                message.setQuestion_id(question);
                message.setMessage(request);
                messageService.saveOrUpdate(message);

            }
            out.println("ask your question");
            request = br.readLine();
            System.out.println("question is " + request);
            Question question = new Question();
            question.setQuestion(request);
            System.out.println(request);

            Long messageId = questionService.saveOrUpdate(question).getId();

            questionList = questionService.getAll();

            while (true) {
                messageList = messageService.getByQuestion(messageId);
                if (messageList.size() > 0)
                    break;
            }
            messageList.forEach(message -> out.println(message.getMessage()));

            Long oldId = messageList.get(messageList.size() - 1).getId();
            Long id;


            while (true) {
                messageList = messageService.getByQuestion(messageId, oldId);

                if (messageList.size() > 0) {
                    messageList.forEach(message -> out.println(message.getMessage()));
                    oldId = messageList.get(messageList.size() - 1).getId();
                }

            }


        } catch (IOException e) {



            e.printStackTrace();
        } finally {

            try {
                in.close();
                out.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }



}
