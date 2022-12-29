package com.application.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


/**
 * This class is used to establish connection and streams with the server. With host and port number, 
 * this class tries to attempt a connection with the server. If the connection is successful, it initializes
 * the input and output streams needed to communicate with the server.
 */
public class Connection {

    /**
     * Host name of the server
     */
    public static final String HOST = "localhost";
    /**
     * Port number of the server
     */
    public static final int PORT = 8087;

    /**
     * Socket of the client
     */
    private static Socket socket = null;

    
    /**
     * Input and Output Streams of the client
     * DataInputStream and DataOutputStream are used to send and receive primitive data types
     * ObjectInputStream and ObjectOutputStream are used to send and receive objects
     */
    private static DataInputStream dataInputStream = null;
    private static DataOutputStream dataOutputStream = null;
    private static ObjectInputStream objectInputStream = null;
    private static ObjectOutputStream objectOutputStream = null;


    /**
     * This method is used to establish connection with the server. If the connection is successful,
     * it initializes the input and output streams. This method is called in the - <br>
     * {@link com.application.client.Sequence#signInSequence(String, String)} <br>
     * {@link com.application.client.Sequence#signUpSequence(String, String, String)} <br>
     * 
     */
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

    
    /**
     * This method uninitialized the input and output streams and closes the socket.
     * This method is called when the user logs out. This method is called in the - <br>
     * {@link com.application.client.Sequence#signOutSequence()} <br>
     */
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

    /**
     * This method is used to send the request code to the server. This method is called in almost every
     * communication with the server.
     * @param code Request code
     */
    public static void sendRequestCode(int code) {
        try {
            dataOutputStream.writeInt(code);
        } catch (Exception e) {
            System.err.println("Error in Connection.sendRequestCode()");
        }
    }

    /**
     * This method is used to receive the request code from the server.
     * @return Request code that server sent
     */
    public static int receiveRequestCode() {
        try {
            return dataInputStream.readInt();
        } catch (Exception e) {
            System.err.println("Error in Connection.receiveRequestCode()");
            return -1;
        }
    }

    /**
     * This method is used to send primitive data to the server.
     * @param object String to be sent
     */
    public static void sendPrimitiveObject(String object) {
        try {
            dataOutputStream.writeUTF(object);
        } catch (Exception e) {
            System.err.println("Error in Connection.sendString()");
        }
    }

    /**
     * This method is used to receive primitive data from the server.
     * @return Object received from the server
     */
    public static String receivePrimitiveObject() {
        try {
            return dataInputStream.readUTF();
        } catch (Exception e) {
            System.err.println("Error in Connection.receiveString()");
            return null;
        }
    }

    /**
     * This method is used to send Non-Primitive objects to the server.
     * @param object Object to be sent
     */
    public static void sendObject(Object object) {
        try {
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            System.err.println("Error in Connection.sendObject()");
        }
    }

    /**
     * This method is used to receive Non-Primitive objects from the server.
     * @return Object received from the server
     */
    public static Object receiveObject() {
        try {
            return objectInputStream.readObject();
        } catch (Exception e) {
            System.err.println("Error in Connection.receiveObject()");
            return null;
        }
    }

    public static Socket getSocket() {
        return socket;
    }

    public static DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public static DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public static ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public static ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}
