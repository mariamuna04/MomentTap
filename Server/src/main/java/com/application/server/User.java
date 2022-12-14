// Created by Kishorè Shanto on 12/5/22 at 19:23

package com.application.server;

import com.application.database.Database;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.*;
import java.net.Socket;

/**
 * This class holds information of a single user. When a user connects to the server, a new instance
 * of this class is created. Each instance has a socket, input stream, output stream and a request and runs
 * in a separate thread.
 */
public class User {
    /**
     * Specify what type of request the user is making
     */
    private int request;

    /**
     * Holds the name of the user
     */
    private String name;

    /**
     * Holds the email of the user
     */
    private String email;

    /**
     * Holds the password of the user
     */
    private String password;

    private final Socket socket;

    /**
     * Input stream of the user
     */
    private final DataInputStream dataInputStream;
    /**
     * Output stream of the user
     */
    private final DataOutputStream dataOutputStream;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;


    /**
     * Constructor of the class. Takes a socket as a parameter and initializes the input and output streams.
     * Then initialize (see: {@link #initializeUserVariables()} ) other fields through the input stream.
     *
     * @param socket Socket of the user
     * @throws IOException If there is an error while initializing the input and output streams
     */
    public User(@NonNull Socket socket) throws IOException {
        // initialize input and output streams
        this.socket = socket;
        this.dataInputStream = new DataInputStream(this.socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
        this.objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(this.socket.getInputStream());

        // initialize other fields
        initializeUserVariables();
    }

    public void closeConnection() throws IOException {
        this.dataInputStream.close();
        this.dataOutputStream.close();
        this.objectOutputStream.close();
        this.objectInputStream.close();
        socket.close();

    }

    public void sendRequestCode(int code) {
        try {
            dataOutputStream.writeInt(code);
        } catch (Exception e) {
            System.err.println("Error in sendRequestCode");
        }
    }

    public int receiveRequestCode() {
        try {
            return dataInputStream.readInt();
        } catch (Exception e) {
            System.err.println("Error in receiveRequestCode");
            return -1;
        }
    }

    public void sendString(String string) {
        try {
            dataOutputStream.writeUTF(string);
        } catch (Exception e) {
            System.err.println("Error in sendString");

        }
    }

    public String receiveString() {
        try {
            return dataInputStream.readUTF();
        } catch (Exception e) {
            System.err.println("receiveString: Server is not running");
        }
        return null;
    }

    public void sendObject(Object object) {
        try {
            objectOutputStream.writeObject(object);
        } catch (Exception e) {
            System.err.println("sendObject: " + e.getMessage());
        }
    }

    public Object receiveObject() {
        try {
            return objectInputStream.readObject();
        } catch (Exception e) {
            System.err.println("Received object is null");
        }
        return null;
    }

    /**
     * Getter method for variables
     * {@link #request}, {@link #name}, {@link #email}, {@link #password}.
     * {@link #dataInputStream}, {@link #dataOutputStream}
     *
     * @return The value of the variable
     */
    public int getRequest() {
        return request;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    /**
     * This method initializes the variables {@link #request}, {@link #name}, {@link #email}, {@link #password}
     * by reading from the input stream with try-catch block to handle {@link IOException}.
     * This method is called in the constructor {@link #User(Socket)}.
     */
    private void initializeUserVariables() {
        // Try to read user data using the input stream
        try {
            this.request = dataInputStream.readInt();
            this.name = dataInputStream.readUTF();
            this.email = dataInputStream.readUTF();
            this.password = dataInputStream.readUTF();

            // If name is set to an empty string, fetch name from database
            // This happens when the user is signing in because there is no need to send name
            if (name.equals("")) this.name = Database.queryForName(this.email);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
