package echoserver;

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

            });

            Thread outputThread = new Thread(() -> {

            });


            inputThread.start();
            outputThread.start();
        }







}