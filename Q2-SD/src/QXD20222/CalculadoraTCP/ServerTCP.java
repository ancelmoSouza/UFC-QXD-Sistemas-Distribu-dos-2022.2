package QXD20222.CalculadoraTCP;

import java.io.*;
import java.net.*;
import java.sql.Connection;

public class ServerTCP {
    public static void main(String[] args) {
        try {
            System.out.println("SERVIDOR STARTED...");
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while (true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }
    }

    static class Connection extends Thread {
        DataInputStream in;
        DataOutputStream out;
        Socket clientSocket;

        public Connection(Socket aClientSocket) {
            try{
                clientSocket = aClientSocket;
                in = new DataInputStream(clientSocket.getInputStream());
                out = new DataOutputStream(clientSocket.getOutputStream());
                this.start();
            }catch (IOException e){
                System.out.println("IO: " + e.getMessage());
            }
        }

        public void run() {
            try {
                String data = in.readUTF();

                String [] array = new String[3];
                array = data.split(" ");

                data = "failed request";

                double n1 = Double.valueOf(array[0]);
                double n2 = Double.valueOf(array[2]);
                char op = array[1].toCharArray()[0];

                if(op == '+' || op == '-' || op == '*' || op == '/'){
                    if(op == '/' && n2 == 0)
                        data = "ERROR: division by zero is invalid";
                    else {
                        Calculadora calculadora = new Calculadora();

                        double result = calculadora.operation(n1, n2, op);
                        data = Double.toString(result);
                    }
                }

                out.writeUTF(data);
            } catch (EOFException e){
                System.out.println("EOF: " + e.getMessage());
            } catch (IOException e){
                System.out.println("IO: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                }catch (IOException e){
                    /*Close connection failed*/
                }
            }
        }

    }

}


