import Communication.Enviar;
import Communication.Receber;

import java.io.*;
import java.net.*;


public class Client {

    private Socket s;

    public Client() {
        try {
            this.s = new Socket("localhost", 7896);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getAdress() {
        return this.s;
    }

    public void close() {
        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
