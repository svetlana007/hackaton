import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
* Written by Martin Ombura Jr. <@martinomburajr>
*/

public class GameServer {
    public static void main(String[] args) {
        connectToServer();
    }
   
    public static void connectToServer() {
        //Try connect to the server on an unused port eg 9991. A successful connection will return a socket
        try {
        	ServerSocket serverSocket = new ServerSocket(9991);
            while(true) {
            	try {
            		Socket socket = serverSocket.accept();
            		new  AcceptThread(socket).start();
            	}catch(IOException e){
            		e.printStackTrace();
            	}
            	
            }

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}