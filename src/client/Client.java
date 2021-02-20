package client;

import java.io.*;
import java.net.Socket;

/**
 * Represents a client.
 *
 * @author Amirparsa Salmankhah
 */
public class Client {

    //Client socket
    private Socket socket;
    //Response of the request
    private String response;

    /**
     * Constructor with no argument.
     */
    public Client() {
        try {
            socket = new Socket("localhost", 8080);

        } catch (IOException e) {
            System.out.println("Server error!");
        }
    }

    /**
     * Sends request to the server.
     *
     * @param infix input infix string to be calculated.
     */
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

    /**
     * Gets response of the request.
     *
     * @return Response of the request.
     */
    public String getResponse() {
        return response;
    }
}
