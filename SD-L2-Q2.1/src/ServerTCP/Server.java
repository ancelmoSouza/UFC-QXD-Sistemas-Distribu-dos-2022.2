package ServerTCP;
import Service.Calculator;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 7896;
        ServerSocket server = null;

        try {
            server = new ServerSocket(port);

            while (true) {
                Socket client = server.accept();
                Connection c = new Connection(client);
            }
        }catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }
    }

    private static class Connection extends Thread{
        private Socket s;
        private DataOutputStream out;
        private DataInputStream in;
        Connection(Socket aClient) {
            this.s = aClient;

            try {
                this.in = new DataInputStream(this.s.getInputStream());
                this.out = new DataOutputStream(this.s.getOutputStream());
                this.start();
            }catch (IOException e) {
                System.out.println("Connection: " + e.getMessage());
            }
        }

        public void run() {
            String req = this.getRequest();

            String [] msg = new String[3];
            msg = req.split(" ");

            double n1 = Double.valueOf(msg[0]);
            double n2 = Double.valueOf(msg[2]);
            char op = msg[1].toCharArray()[0];

            Calculator calc = Calculator.getInstance();

            switch (op){
                case '+':
                    req = Double.toString(calc.add(n1, n2));
                break;
                case '-':
                    req = Double.toString(calc.sub(n1, n2));
                    break;
                case '*':
                    req = Double.toString(calc.mult(n1, n2));
                    break;
                case '/':
                    if(n2 == 0)
                        req = "Invalid Division: Division by 0";
                    else
                        req = Double.toString(calc.div(n1, n2));
                    break;
                default:
                    req = "failed operation";
            }

            this.sendResponse(req);
        }


        private String getRequest() {
            String req = "";
            try {
                req = this.in.readUTF();
            } catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
            return req;
        }

        private void sendResponse(String msg) {
            try{
                this.out.writeUTF(msg);
                this.out.flush();
            }catch (IOException e) {
                System.out.println("IO: " + e.getMessage());
            }
        }
//        private void closeConnection() throws IOException{
//            this.out.close();
//            this.in.close();
//            this.s.close();
//        }
    }
}
