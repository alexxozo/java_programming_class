package space.harbour.java.hw11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import org.bson.Document;

public class ChatHandler extends Thread {
    Socket client;
    ChatServer server;
    PrintWriter out;
    MongoExecutor executor;

    public ChatHandler(ChatServer server, Socket client) {
        this.server = server;
        this.client = client;
        this.executor = new MongoExecutor();

    }

    public void run() {
        try {
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()))) {
            out.println("Enter a username: ");
            String name = in.readLine();
            setName(name);
            while (true) {
                String str = in.readLine();
                server.broadcast(name + ": " + str);
                if (str == null) {
                    break;
                }
                out.flush();
                if (str.trim().equals("BYE")) {
                    server.clientDisconnected(this);
                    break;
                }
            }
            in.close();
            out.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMessageHistory() {
        for (Document message : executor.execGetAllMessages(x -> x)) {
            out.println("message.toJson()");
        }
    }

    public void sendMessage(String message) {
        out.println(message);
        out.flush();
    }
}