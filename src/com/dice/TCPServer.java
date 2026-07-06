package com.dice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private static int PORT = 7379;
    private static String HOST = "0.0.0.0";
    private static int CONNECTIONS = 50;
    private int concurrentClients = 0;


    public void runSyncTCPServer()  throws IOException{

        ServerSocket serverSocket = new ServerSocket(
            PORT, 
            CONNECTIONS, 
            InetAddress.getByName(HOST));

        System.out.println("Listening on "+ HOST + ":" + PORT);

        while(true){
            Socket clientSocket = serverSocket.accept();

            concurrentClients++;

            System.out.println("Client connected: "
                + clientSocket.getRemoteSocketAddress()
                + " | concurrentClients = " 
                + concurrentClients
            );

            while (true) {

                String command  = readCommand(clientSocket);

                System.out.println("Received command: " + command);

                if(command == null || command.isEmpty()){
                    concurrentClients--;

                    System.out.println("Client disconnected: "
                    + clientSocket.getRemoteSocketAddress()
                    + " | concurrentClients = " 
                    + concurrentClients
                    );

                    clientSocket.close();

                    break;
                }

                writeOutPut(clientSocket, command);

            }
        }
    }

    private static String readCommand(Socket clienSocket) throws IOException {
        InputStream inputStream = clienSocket.getInputStream();

        byte[] buffer = new byte[1024];

        int bytesRead = inputStream.read(buffer); //blocking

        if (bytesRead == -1 ) {
            return null;
        }

        return new String(buffer, 0 , bytesRead);
    }

    private static void writeOutPut(Socket clienSocket, String command) throws IOException {
        OutputStream outputStream = clienSocket.getOutputStream();

        outputStream.write(command.getBytes());

        outputStream.flush();
    }
}