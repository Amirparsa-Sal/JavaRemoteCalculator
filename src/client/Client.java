package client;

import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private String response;

    public Client() {
        try {
            socket = new Socket("localhost", 8080);

        } catch (IOException e) {
            System.out.println("Server error!");
        }
    }

    public void sendRequest(String infix) {
        try {
            DataOutputStream out = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out.writeUTF(infix);
            out.flush();
            response = in.readUTF();
            socket.close();
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        return response;
    }
}
