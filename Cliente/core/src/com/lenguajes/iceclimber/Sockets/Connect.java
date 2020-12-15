package com.lenguajes.iceclimber.Sockets;


import java.net.*;
import java.io.*;
public class Connect {
    private Socket clientSocket;
    public static PrintWriter out;
    public static BufferedReader in;
    public static Connect conexion;

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("1 1 1 ");
    }
    public static void setUp(){
        conexion = new Connect();
        conexion.startConnection("127.0.0.1", 8888);
    }
    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }
    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void tearDown() {
        conexion.stopConnection();
    }
}