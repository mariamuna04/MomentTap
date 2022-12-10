package com.application.connection;// Created by Kishorè Shanto on 12/5/22 at 19:23

import java.net.ServerSocket;
import java.net.Socket;


/**
 * This class is responsible for establishing and terminating a connection with the client.
 * It creates a server socket and waits for a client to connect.
 * When a client connects, it returns the socket of the client.
 */
public class Connection {

    /**
     * com.application.server.Server port number
     */
    private static final int PORT = 8080;

    /**
     * com.application.server.Server socket that listens for client requests
     */
    private static ServerSocket serverSocket;


    /**
     * This method creates a server socket and keeps it open until the server is terminated.
     * The server socket listens for client requests.
     * Handles exceptions by printing the stack trace when an IOException occurs.
     */
    public static void establish() {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (Exception e) {
        }
    }

    /**
     * This method closes the server socket when the server is terminated.
     */
    public static void terminate() {
        try {
            serverSocket.close();
        } catch (Exception e) {
        }
    }

    /**
     * This method accepts a client request and returns the socket of the client.
     *
     * @return Socket of the client
     */
    public static Socket clientRequestAccept() {
        try {
            return serverSocket.accept();
        } catch (Exception e) {
        }
        return null;
    }
}
