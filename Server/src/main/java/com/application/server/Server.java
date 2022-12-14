package com.application.server;// Created by Kishorè Shanto on Dec 4 2022 21:15

import com.application.connection.Connection;
import com.application.database.Database;
import com.application.serialShared.Event;
import com.application.utility.NetworkRequestCodes;

import java.net.Socket;

/**
 * This is the entry point of com.application.server.Server application. It starts the server and accept socket requests using
 * {@link Connection} class.
 */
public class Server {
    public static void main(String[] args) throws Exception {

        Connection.establish();

        while (true) {
            Socket socket = Connection.clientRequestAccept();
            System.out.println("Client Connected");
            assert socket != null;
            User user = new User(socket);

            new Thread(() -> {
                System.out.println(user.getEmail() + " is connected, Thread started");
                try {
                    if (user.getRequest() == NetworkRequestCodes.SIGN_UP_REQUEST) {
                        Database.addUser(user.getName(), user.getEmail(), user.getPassword());
                        user.sendRequestCode(NetworkRequestCodes.SIGN_UP_SUCCESSFUL);
                    } else if (user.getRequest() == NetworkRequestCodes.SIGN_IN_REQUEST) {

                        Database.findUser(user.getEmail(), user.getPassword());

                        if (Database.resultSet.next()) {
                            System.out.println("User Found"); // TODO: pop up dialogue box
                            user.getDataOutputStream().writeInt(NetworkRequestCodes.SIGN_IN_SUCCESSFUL);
                            user.getDataOutputStream().writeUTF(Database.resultSet.getString("name"));
                            user.getDataOutputStream().writeUTF(Database.resultSet.getString("email"));

                            while (true) {
                                int clientRequest = user.receiveRequestCode();
                                if (clientRequest == NetworkRequestCodes.CREATE_EVENT_REQUEST) {
                                    Event event = (Event) user.getObjectInputStream().readObject();
                                    Database.addEvent(event);
                                    user.sendRequestCode(NetworkRequestCodes.CREATE_EVENT_SUCCESSFUL);
                                } else if (clientRequest == NetworkRequestCodes.DELETE_EVENT_REQUEST) {
                                    String name = user.receiveString();
                                    String date = user.receiveString();
                                    Database.deleteEvent(user.getEmail(), name, date);
                                    user.sendRequestCode(NetworkRequestCodes.DELETE_EVENT_SUCCESSFUL);
                                } else if (clientRequest == NetworkRequestCodes.SIGN_OUT_REQUEST) {
                                    user.closeConnection();
                                    System.out.println("Client Disconnected");
                                    break;
                                } else if (clientRequest == NetworkRequestCodes.SEARCH_EVENT_REQUEST) {
                                    String keyword = user.receiveString();
                                    Database.searchEvent(keyword);
                                    if (Database.resultSet.next()) {

                                    } else {

                                    }
                                }
                            }
                        } else {
                            System.out.println("User Not Found");
                            user.getDataOutputStream().writeInt(NetworkRequestCodes.SIGN_IN_UNSUCCESSFUL);
                        }
                    } else {
                        System.err.println("Invalid Request");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
