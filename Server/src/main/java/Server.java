// Created by Kishorè Shanto on Dec 4 2022 21:15

import com.server.utils.NetworkRequest;

import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {

        Connection.establish();

        while (true) {
            Socket socket = Connection.clientRequestAccept();
            User user = new User(socket);

            if (user.request == NetworkRequest.SIGN_UP_REQUEST) {

                Database.addUser(user.name, user.email, user.password);

            } else if (user.request == NetworkRequest.SIGN_IN_REQUEST) {

                Database.findUser(user.email, user.password);

                if (Database.resultSet.next()) {
                    System.out.println("User Found"); // TODO: pop up dialogue box
                    user.dataOutputStream.writeInt(NetworkRequest.USER_FOUND_FROM_DATABASE);
                } else {
                    System.out.println("User Not Found"); // TODO: pop up dialogue box
                    user.dataOutputStream.writeInt(NetworkRequest.USER_NOT_FOUND_FROM_DATABASE);
                }
            }


            System.out.println("Client Connected");
        }
    }

}
