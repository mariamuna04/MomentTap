// Created by Kishorè Shanto on 12/5/22 at 19:23

import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
    private static final int PORT = 8080;
    private static ServerSocket serverSocket;


    public static void establish() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

    public static void terminate() {
        try {
            serverSocket.close();
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

    public static Socket clientRequestAccept(){
        try {
            return serverSocket.accept();
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
        return null;
    }


}
