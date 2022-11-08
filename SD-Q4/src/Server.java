import Communication.Enviar;
import Communication.Receber;

import java.io.*;
import java.net.*;


public class Server {
    public static void main(String[] args) {

        try {
            int serverPort = 7896;
            ServerSocket listenClient = new ServerSocket(serverPort);

            while (true) {
                Socket client = listenClient.accept();
                Enviar enviar = new Enviar(client);
                Receber receber = new Receber(client);

                enviar.start();
                receber.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
