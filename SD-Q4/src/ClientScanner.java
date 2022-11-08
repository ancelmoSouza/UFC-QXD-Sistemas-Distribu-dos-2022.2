import Communication.Enviar;
import Communication.Receber;

import java.net.Socket;
import java.util.Scanner;

public class ClientScanner {
    public static void main(String[] args) {
        Client c = new Client();
        Enviar out = new Enviar(c.getAdress());
        Receber in = new Receber(c.getAdress());

        while (true) {
            out.start();
            in.start();
           // c.close();
        }
    }
}
