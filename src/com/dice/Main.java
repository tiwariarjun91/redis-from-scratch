
package com.dice;

import java.io.IOException;

public class Main {

    public static void main(String[ ] args)  throws IOException{

        System.out.println("Rolling the dice let's go !!!");

        TCPServer server = new TCPServer();

        server.runSyncTCPServer();
       

    }
    
}