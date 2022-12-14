// Created by Kishorè Shanto on Dec 4 2022 21:15

package com.application.server;

import com.application.connection.Connection;

import java.net.Socket;

/**
 * This is the entry point of com.application.server.Server application. It starts the server and accept socket requests using
 * {@link Connection} class.
 */
public class Server {
    public static void main(String[] args) {
        Connection.establishServer();
        startServer();
    }

    private static void startServer() {
        try {
            while (true) {
                System.out.println("Waiting for client request ...");
                Socket socket = Connection.clientRequestAccept();
                assert socket != null;
                User user = new User(socket);
                Thread thread = new Thread(new ClientThread(user));
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
