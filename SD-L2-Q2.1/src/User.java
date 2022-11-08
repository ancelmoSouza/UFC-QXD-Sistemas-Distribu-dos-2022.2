import ClientTCP.Client;

import java.util.Scanner;

public class User {
    public static void main(String[] args) {
        String msg = "";
        String res = "";
        Scanner scan = new Scanner(System.in);
        while (true) {
            Client client = new Client();
            System.out.println("Insira uma requisição no seguinte formato:");
            System.out.println("[Valor 1];[operação aritmética]; [Valor 2]");
            System.out.println("[operação aritmética] --> Soma(+) / Subtração(-) /" +
                    " Multiplicação(*) / Divisão()");


            System.out.print("Operação >> ");
            msg = scan.nextLine();

            if(msg.equals("sair"))
                break;

            client.sendRequest(msg);

            res = client.getResponse();

            System.out.println("Servidor >> " + res);

            client.close();
        }
    }
}