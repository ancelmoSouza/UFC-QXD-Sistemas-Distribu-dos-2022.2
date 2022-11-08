import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket s = null;

        try{
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Scanner scan = new Scanner(System.in);
            System.out.println("Insira a requisição no Seguinte Modelo: ");
            System.out.println("Requisição( [c] - Calculadora) / [t] - Conversor de Moeda) " +
                    "[Valor 1] [Operação](Para a Calculadora: [+] = s, [-] = m, [*] = p, [/] = d) [Valor 2](Para a Calculadora)");

            String req = scan.nextLine();

            out.writeUTF(req);
            String res = in.readUTF();
            System.out.println("Resultado> " + res);


        }catch (UnknownHostException e){
            System.out.println("Sock: " + e.getMessage());
        }catch (EOFException e){
            System.out.println("EOF: " + e.getMessage());
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        } finally {
            if(s != null){
                try {
                    s.close();
                }catch (IOException e){
                    System.out.println("IO: " + e.getMessage());
                }
            }
        }
    }
}
