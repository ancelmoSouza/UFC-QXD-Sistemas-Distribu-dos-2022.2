package Communication;
import java.io.*;
import java.net.*;

public class Receber extends Thread{

    private Socket s = null;
    private DataInputStream in;

    public Receber(Socket aSocket) {
        try{
            this.s = aSocket;
            this.in = new DataInputStream(this.s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String data = in.readUTF();
            System.out.println("Recebido> " + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
