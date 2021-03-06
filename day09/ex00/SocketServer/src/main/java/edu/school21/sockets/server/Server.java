package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

@Component
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private int port;

    public void setPort(int port) {
        this.port = port;
    }

    public void start() {
        try {
            try {
                serverSocket = new ServerSocket(port);

                clientSocket = serverSocket.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    out.write("Hello from Server!\n");
                    out.flush();
                    String word = in.readLine();
                    if (word.equals("signUp")) {
                        out.write("Enter username:\n");
                        out.flush();
                        String userName = in.readLine();
                        out.write("Enter password:\n");
                        out.flush();
                        String password = in.readLine();
                        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
                        UsersService usersService = context.getBean(UsersService.class);
                        if (usersService.signUp(userName, password)) {
                            out.write("Successful!\n");
                        } else {
                            out.write("Failure :(\n");
                        }
                        out.flush();
                    } else {
                        out.write("close\n");
                        out.flush();
                    }
                    out.flush();
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                serverSocket.close();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
