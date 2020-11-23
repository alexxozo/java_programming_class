package space.harbour.java.hw11;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatServer {
    private Set<ChatHandler> clients = ConcurrentHashMap.newKeySet();

    public ChatServer(int port) {
        try (ServerSocket s = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);
            while (true) {
                ChatHandler client = new ChatHandler(this, s.accept());
                clients.add(client);
                client.start();
            }
        } catch (IOException e) {
            System.out.println("Server failed on port " + port);
        }
    }

    public synchronized void broadcast(String message) {
        System.out.println("New message -> " + message);
        for (ChatHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public void clientDisconnected(ChatHandler client) {
        clients.remove(client);
        broadcast("Chat member " + client.getName() + " left");
    }

    public static void main(String[] args) {
        new ChatServer(8008);
    }
}