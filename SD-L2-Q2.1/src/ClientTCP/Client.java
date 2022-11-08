package ClientTCP;
import java.io.*;
import java.net.*;

public class Client {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;

    public Client() {
        try {
            this.client = new Socket("localhost", 7896);
            this.in = new DataInputStream(this.client.getInputStream());
            this.out = new DataOutputStream(this.client.getOutputStream());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public void sendRequest(String req) {
        try {
            this.out.writeUTF(req);
            this.out.flush();
        }catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public String getResponse() {
        String res = "";
        try {
            res = this.in.readUTF();
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }

        return res;
    }


    public void close() {
        try {
            this.out.close();
            this.in.close();
            this.client.close();
        } catch (IOException e) {
            System.out.println("Connection: " + e.getMessage());
        }
    }
}
