package com.application.connection;

import java.net.ServerSocket;
import java.net.Socket;


/**
 * This class is responsible for establishing and terminating a connection with the client.
 * It creates a server socket and waits for a client to connect.
 * When a client connects, it returns the socket of the client.
 */
public class Connection {

    /**
     * Server port number
     */
    private static final int PORT = 8087;

    /**
     * Server socket that listens for client requests
     */
    private static ServerSocket serverSocket;


    /**
     * This method creates a server socket and keeps it open until the server is terminated.
     * The server socket listens for client requests.
     * Handles exceptions by printing the stack trace when an IOException occurs.
     */
    public static void establishServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server Started at port " + PORT);
        } catch (Exception e) {
            System.err.println("Error while establishing connection [Connection.java:36]");
            // DEBUG    :   e.printStackTrace();
            System.err.println("Possible reasons: Port is already in use, or the server is already running");
            System.out.println("Possible Solution: Change the port number in Connection.java");
        }
    }

    /**
     * This method closes the server socket when the server is terminated.
     */
    public static void terminateServer() {
        try {
            serverSocket.close();
            System.out.println("Server terminated");
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Error while terminating connection [Connection.java:51]");
            // DEBUG    :   e.printStackTrace();
            System.err.println("Possible reasons: Server is not running");
        }
    }

    /**
     * This method accepts a client request and returns the socket of the client.
     *
     * @return Socket of the client
     */
    public static Socket clientRequestAccept() {
        try {
            Socket socket = serverSocket.accept();
            if (socket != null) {
                System.out.println("Client Connected" + socket.getInetAddress());
                return socket;
            } else {
                System.err.println("Client connection refused");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error while accepting client request [Connection.java:74]");
            // DEBUG    :   e.printStackTrace();
            System.err.println("Possible reasons: Server is not running, error in clientRequestAccept()");
            return null;
        }
    }
}
