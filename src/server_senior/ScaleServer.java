package server_senior;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ScaleServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(1128, 10 );
            while ( true ) {
                Socket clientSocket = server.accept();
                UserThread newUser = new UserThread(clientSocket);
                newUser.start();
                System.out.print("Threads Count : ");
                System.out.println(java.lang.Thread.activeCount());
            }
        }
        catch ( IOException e ) {
            e.printStackTrace();
            System.exit( 1 );
        }
    }
}