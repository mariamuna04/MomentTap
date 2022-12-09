// Created by Kishorè Shanto on Dec 4 2022 21:15

import com.application.client.Event;
import com.server.utils.NetworkRequest;

import java.net.Socket;

/**
 * This is the entry point of Server application. It starts the server and accept socket requests using
 * {@link Connection} class.
 */
public class Server {
    public static void main(String[] args) throws Exception {

        Connection.establish();

        while (true) {
            Socket socket = Connection.clientRequestAccept();
            assert socket != null;
            User user = new User(socket);

            if (user.getRequest() == NetworkRequest.SIGN_UP_REQUEST)
                Database.addUser(user.getName(), user.getEmail(), user.getPassword());
            else if (user.getRequest() == NetworkRequest.SIGN_IN_REQUEST) {

                Database.findUser(user.getEmail(), user.getPassword());

                if (Database.resultSet.next()) {
                    System.out.println("User Found"); // TODO: pop up dialogue box
                    user.getDataOutputStream().writeInt(NetworkRequest.USER_FOUND_FROM_DATABASE);
                    user.getDataOutputStream().writeUTF(Database.resultSet.getString("name"));
                    user.getDataOutputStream().writeUTF(Database.resultSet.getString("email"));
                    Event event = (Event) user.getObjectInputStream().readObject();
                    System.out.println(event.getEvent_name());
                    System.out.println(event.getEvent_description());
                    System.out.println(event.getEvent_category());
                    System.out.println(event.getEvent_date());
                    Database.addEvent(event);

                } else {
                    System.out.println("User Not Found"); // TODO: pop up dialogue box
                    user.getDataOutputStream().writeInt(NetworkRequest.USER_NOT_FOUND_FROM_DATABASE);
                }
            }


            System.out.println("Client Connected");
        }
    }

}
