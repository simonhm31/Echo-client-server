package echoserver;

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
            Thread clientHandler = new Thread(() ->handleClient(clientSocket));
            clientHandler.start();
        }

        
    }
}