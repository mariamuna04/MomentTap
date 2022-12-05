import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {
        // Create a Server
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            System.out.println("Connected");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String name = dataInputStream.readUTF();
            String email = dataInputStream.readUTF();
            String password = dataInputStream.readUTF();

            // ##################### Database

            final String DATABASE_URL = "jdbc:mariadb://localhost:3306/cse2118";
            final String DATABASE_USER = "root";
            final String DATABASE_PASSWORD = "";
            ResultSet resultSet;
            Connection connection = null;


            try {

                connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
                connection.createStatement().executeQuery("INSERT INTO users (name, email, password) VALUES ('" + name + "', '" + email + "', '" + password + "')");

            } catch (SQLException e) {
                // FOR DEVELOPERS
                System.err.println(e.getMessage());
            }


        } catch (Exception e) {
            // error
        }

    }
}