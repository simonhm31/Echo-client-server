package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoServer {
    public static final int PORT_NUMBER = 6013;
    public static void main(String[] args) throws IOException, InterruptedException {
        EchoServer server = new EchoServer();
        server.start();
    }

    private void start() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            Thread clientHandler = new Thread(() -> handleClient(clientSocket));
            clientHandler.start();
        }
    }

    private void ManagesClient(Socket clientSocket) {
    try (InputStream input = clientSocket.getInputStream();
         OutputStream output = clientSocket.getOutputStream()) {

        byte[] buffer = new byte[1024];  
        int bytesRead;

 
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
        output.flush();  

    } catch (IOException e) {
        System.err.println("Error handling client: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            clientSocket.close();  
        } catch (IOException e) {
            System.err.println("Error closing client socket: " + e.getMessage());
        }
    }
}

}