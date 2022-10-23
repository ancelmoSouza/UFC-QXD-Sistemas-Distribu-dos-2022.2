package QXD20222.CalculadoraTCP;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ClientTCP {
    public static void main(String args[]){
        Socket s = null;
        try{
            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            Scanner scan = new Scanner(System.in);
            String request = scan.nextLine();

            out.writeUTF(request);
            String response = in.readUTF();
            System.out.println("Server response: " + response);

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
