import Application.Conversor;

import java.io.*;
import java.net.*;


public class Server {
    public static void main(String[] args) {
        try {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true) {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
                c.start();
            }
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    public static class Connection extends Thread {
        private DataInputStream in;

        private DataOutputStream out;

        Socket client;

        public Connection(Socket aClient) {
            try {
                this.client = aClient;
                this.in = new DataInputStream(this.client.getInputStream());
                this.out = new DataOutputStream(this.client.getOutputStream());
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }

        public void run() {
            try {
                String data = in.readUTF();
                String [] array = new String[4];
                array = data.split(" ");

                data = "Failed Operation";

                if(array[0].toCharArray()[0] == 'c'){
                    double n1 = Double.valueOf(array[1]);
                    double n2 = Double.valueOf(array[3]);
                    char op = array[2].toCharArray()[0];

                    if(op == 's' || op == 'm' || op == 'p' || op == 'd'){
                        if(op == '/' && n2 == 0)
                            data = "ERROR: division by zero is invalid";
                        else {
                            Calculadora calculadora = new Calculadora();

                            double result = calculadora.operation(op, n1, n2);
                            data = Double.toString(result);
                        }
                    }
                    out.writeUTF(data);
                } else if(array[0].toCharArray()[0] == 't') {
                    double n1 = Double.valueOf(array[1]);

                    Conversor conversor = new Conversor();

                    double result = conversor.converter(n1);
                    data = Double.toString(result);
                    out.writeUTF(data);
                }

            } catch (EOFException e){
                System.out.println("EOF: " + e.getMessage());
            } catch (IOException e){
                System.out.println("IO: " + e.getMessage());
            } finally {
                try {
                    client.close();
                }catch (IOException e){
                    /*Close connection failed*/
                }
            }
        }
    }
}
