package edu.school21.sockets.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
    private BufferedReader reader;
    private BufferedReader in;
    private BufferedWriter out;
    private int port;

    public void setPort(int port) {
        this.port = port;
    }

    public void start() {
        try {
            try {
                Scanner scan = new Scanner(System.in);
                clientSocket = new Socket("localhost", port);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String serverWord = in.readLine();
                System.out.println(serverWord);
                out.write(scan.nextLine() + "\n");
                out.flush();
                serverWord = in.readLine();
                if (serverWord.equals("close")) {
                    clientSocket.close();
                    in.close();
                    out.close();
                    return;
                }
                System.out.println(serverWord);
                out.write(scan.nextLine() + "\n");
                out.flush();
                serverWord = in.readLine();
                System.out.println(serverWord);
                out.write(scan.nextLine() + "\n");
                out.flush();
                serverWord = in.readLine();
                System.out.println(serverWord);
            } finally {
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}

