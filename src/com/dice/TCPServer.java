package com.dice;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

class TCPServer {

    private static int PORT = 7379;
    private static String HOST = "0.0.0.0";
    private static int CONNECTIONS = 50;


    public void runSyncTCPServer()  throws IOException{

         System.out.println("Rolling the dice let's go !!!");

        ServerSocket serverSocket = new ServerSocket(
            PORT, 
            CONNECTIONS, 
            InetAddress.getByName(HOST));

        System.out.println("Listening on "+ HOST + ":" + PORT);

    }
}