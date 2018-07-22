package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class mainServer {
    private  Vector<ClientHandler> clients;



    public mainServer() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");
            // 1 сокет это точка соединения
            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился");
                // добавляем клиента в список
                clients.add(new ClientHandler(this,socket));
            }
        }catch (IOException e) {
            System.out.println("Ошибка инициал сервера");
        } finally{
            try {
                server.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void broadcastMsg(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }
}

