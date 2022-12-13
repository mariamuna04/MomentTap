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
            System.err.println("Error in Connection.setConnection()");
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
            System.err.println("Error in Connection.unsetConnection()");
        }
    }

    public static void sendRequestCode(int code) {
        try {
            dataOutputStream.writeInt(code);
        } catch (Exception e) {
            System.err.println("Error in Connection.sendRequestCode()");
        }
    }

    public static int receiveRequestCode() {
        try {
            return dataInputStream.readInt();
        } catch (Exception e) {
            System.err.println("Error in Connection.receiveRequestCode()");
            return -1;
        }
    }

    public static void sendString(String string) {
        try {
            dataOutputStream.writeUTF(string);
        } catch (Exception e) {
            System.err.println("Error in Connection.sendString()");
        }
    }

    public static String receiveString() {
        try {
            return dataInputStream.readUTF();
        } catch (Exception e) {
            System.err.println("Error in Connection.receiveString()");
            return null;
        }
    }

    public static void sendObject(Object object) {
        try {
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            System.err.println("Error in Connection.sendObject()");
        }
    }

    public static Object receiveObject() {
        try {
            return objectInputStream.readObject();
        } catch (Exception e) {
            System.err.println("Error in Connection.receiveObject()");
            return null;
        }
    }

}
