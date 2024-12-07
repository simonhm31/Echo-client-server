package echoserver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EchoClient {
    public static final int PORT_NUMBER = 6013;

        public static void main(String[] args) throws IOException {
        EchoClient client = new EchoClient();
        client.start();
        }

        private void start() throws IOException {
            Socket socket = new Socket("localhost", PORT_NUMBER);
            InputStream socketInputStream = socket.getInputStream();
            OutputStream socketOutputStream = socket.getOutputStream();


        Thread inputThread = new Thread(() -> {
            try {
                int data;
            while ((data = System.in.read()) != -1) {
                socketOutputStream.write(data);
            }
            socket.shutdownOutput(); 
            } catch (IOException e) {
            System.err.println("An error occurred in the input thread: " + e.getMessage());
        }
        });



        Thread outputThread = new Thread(() -> {
        try {
            int data;
        while ((data = socketInputStream.read()) != -1) {
            System.out.write(data);
        }
        System.out.flush(); 
        } catch (IOException e) {
        System.err.println("An error occurred in the output thread: " + e.getMessage());
            }
        });




            inputThread.start();
            outputThread.start();

        try {
            inputThread.join();
            outputThread.join();
        } catch (InterruptedException ex) {
        System.err.println("An interruption occurred in the client threads: " + ex.getMessage());
        }

    }







}