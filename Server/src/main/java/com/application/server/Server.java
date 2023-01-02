package com.application.server;

import com.application.connection.Connection;

import java.net.Socket;
import java.util.Scanner;

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

                new Thread(() -> {
                    String tSignal = new Scanner(System.in).next();
                    if (tSignal.equals("exit()") || tSignal.equals("exit") || tSignal.equals("terminate")) {
                        System.err.println("Terminating server");
                        Connection.terminateServer();
                        System.exit(0);
                    }

                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)
