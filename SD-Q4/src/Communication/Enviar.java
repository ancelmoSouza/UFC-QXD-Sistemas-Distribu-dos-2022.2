package Communication;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Enviar extends Thread {
    private Socket s = null;
    private DataOutputStream out;

    public Enviar(Socket aSocket) {
        try {
            this.s = aSocket;
            this.out = new DataOutputStream(this.s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Mensagem para enviar> ");
            String data = in.nextLine();

            this.out.writeUTF(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
