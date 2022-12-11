package com.application.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {

    public static final String HOST = "localhost";
    public static final int PORT = 8080;

    private static Socket socket = null;

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;
    private static ObjectOutputStream objectOutputStream = null;
    private static ObjectInputStream objectInputStream = null;

    public static void setConnection() {
        try {
            socket = new Socket(HOST, PORT);
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(socket.getInputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

    public static void unsetConnection() {
        try {
            dataOutputStream.close();
            dataInputStream.close();
            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

    public static void sendRequestCode(int code) {
        try {
            dataOutputStream.writeInt(code);
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

    public static int receiveRequestCode() {
        try {
            return dataInputStream.readInt();
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
        return -1;
    }

    public static void sendString(String string) {
        try {
            dataOutputStream.writeUTF(string);
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

    public static String receiveString() {
        try {
            return dataInputStream.readUTF();
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
        return null;
    }

    public static void sendObject(Object object) {
        try {
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

    public static void receiveObject(){
        try {
            objectInputStream.readObject();
        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

}
