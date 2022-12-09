package com.application.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private static Socket socket = null;

    public static DataOutputStream dataOutputStream = null;
    public static DataInputStream dataInputStream= null;

    public static ObjectOutputStream objectOutputStream = null;

    public static ObjectInputStream objectInputStream = null;

    public static void setConnection(){
        try {
            socket = new Socket("localhost", 8080);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

}
