// Created by Kishorè Shanto on Dec 4 2022 21:15

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server {
    private static final int SIGN_UP_REQUEST = 10;
    private static final int SIGN_IN_REQUEST = 20;

    private static final int USER_FOUND_FROM_DATABASE = 30;

    public static void main(String[] args) {
        ArrayList<Socket> sockets = new ArrayList<>();
        // Create a Server
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            while (true) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                int request = dataInputStream.readInt();
                if(request == SIGN_UP_REQUEST) {
                    String name = dataInputStream.readUTF();
                    String email = dataInputStream.readUTF();
                    String password = dataInputStream.readUTF();
                    System.out.println(name + " " + email + " " + password);
                    Database.queryForUserSignUp(name, email, password);
                } else if(request == SIGN_IN_REQUEST) {
                    String email = dataInputStream.readUTF();
                    String password = dataInputStream.readUTF();
                    Database.queryForUserSignIn(email, password);
                    if(Database.resultSet.next()) {
                        System.out.println("User Found");
                        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        dataOutputStream.writeInt(USER_FOUND_FROM_DATABASE);
                    } else {
                        System.out.println("User Not Found");
                    }
                }


                System.out.println("Client Connected");
            }
        } catch (Exception e) {
            // error
        }

    }
}