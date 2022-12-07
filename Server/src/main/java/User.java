// Created by Kishorè Shanto on 12/5/22 at 19:23

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class User {
    String name;
    String email;
    String password;
    Socket socket;
    DataInputStream dataInputStream;
    DataOutputStream dataOutputStream;
    int request;

    boolean isLoggedIn = false;

    public User(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        initUser();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    private void initUser() {
        // Read User Data
        try {
            this.request = dataInputStream.readInt();
            this.name = dataInputStream.readUTF();
            this.email = dataInputStream.readUTF();
            this.password = dataInputStream.readUTF();

            if (name.equals("")){
                this .name = Database.queryForName(this.email);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
