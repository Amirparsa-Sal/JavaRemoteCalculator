package server;

import logic.MathExpressionParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static int DEFAULT_PORT = 8080;
    private ServerSocket serverSocket;

    public Server() {
        try {
            serverSocket = new ServerSocket(DEFAULT_PORT);
            while (true) {
                serverSocket.setReuseAddress(true);
                Socket socket = serverSocket.accept();
                RequestHandler rh = new RequestHandler(socket);
                new Thread(rh).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class RequestHandler implements Runnable {

        private Socket socket;

        public RequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                String infix = in.readUTF();
                out.writeUTF(MathExpressionParser.calculatePostfix(MathExpressionParser.infixToPostfix(infix)).toString());
                out.flush();
                socket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
