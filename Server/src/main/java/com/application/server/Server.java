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

            // ----------------------------------------------------------------
            assert socket != null;
            User user = new User(socket);


            new Thread(() -> {
                try {

                    if (user.getRequest() == NetworkRequestCodes.SIGN_UP_REQUEST) {
                        Database.addUser(user.getName(), user.getEmail(), user.getPassword());
                        user.sendRequestCode(NetworkRequestCodes.USER_ADDED_TO_DATABASE);
                    } else if (user.getRequest() == NetworkRequestCodes.SIGN_IN_REQUEST) {

                        Database.findUser(user.getEmail(), user.getPassword());

                        if (Database.resultSet.next()) {
                            System.out.println("User Found"); // TODO: pop up dialogue box
                            user.getDataOutputStream().writeInt(NetworkRequestCodes.USER_FOUND_FROM_DATABASE);
                            user.getDataOutputStream().writeUTF(Database.resultSet.getString("name"));
                            user.getDataOutputStream().writeUTF(Database.resultSet.getString("email"));

                            while (true) {
                                int r = user.receiveRequestCode();
                                if (r == NetworkRequestCodes.CREATE_EVENT) {
                                    Event event = (Event) user.getObjectInputStream().readObject();
                                    Database.addEvent(event);
                                    user.sendRequestCode(NetworkRequestCodes.CREATE_EVENT_CONFIRMATION);
                                } else if (r == NetworkRequestCodes.DELETE_EVENT) {
                                    String name = user.receiveString();
                                    String date = user.receiveString();
                                    Database.deleteEvent(user.getEmail(),name, date);
                                    user.sendRequestCode(NetworkRequestCodes.DELETE_EVENT_CONFIRMATION);
                                } else if(r == NetworkRequestCodes.LOG_OUT) {
                                    user.closeConnection();
                                    System.out.println("Client Disconnected");
                                    break;
                                } else if(r == NetworkRequestCodes.SEARCH_EVENT) {
                                    String keyword = user.receiveString();
                                    Database.searchEvent(keyword);
                                    if(Database.resultSet.next()) {
                                        user.sendRequestCode(NetworkRequestCodes.SEARCH_EVENT_CONFIRMATION);
                                        user.sendObject(Database.resultSet);
                                        System.out.println("Event Found");
                                    } else {
                                        user.sendRequestCode(NetworkRequestCodes.SEARCH_EVENT_CONFIRMATION);
                                        user.sendObject(null);
                                        System.out.println("Event Not Found");
                                    }
                                }
                            }


                        } else {
                            System.out.println("User Not Found"); // TODO: pop up dialogue box
                            user.getDataOutputStream().writeInt(NetworkRequestCodes.USER_NOT_FOUND_FROM_DATABASE);
                        }
                    } else {
                        System.err.println("Invalid Request");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();


            System.out.println("Client Connected");
        }
    }

}
