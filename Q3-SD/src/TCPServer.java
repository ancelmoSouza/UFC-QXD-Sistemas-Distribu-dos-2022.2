import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPServer {
    public static void main (String args[]) {
        try{
            int serverPort = 7896; // the server port
            ServerSocket listenSocket = new ServerSocket(serverPort);

            while(true) {
                Socket clientSocket = listenSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintStream out = new PrintStream(clientSocket.getOutputStream());

                Scanner keyboard = new Scanner(System.in);
                String mensagem = keyboard.nextLine();
                out.println(mensagem);

                String data = in.readLine();
                System.out.println("Cliente:" + data);

                //if(data != null && data != "")
                //    System.out.println(data);

               // Scanner scanner = new Scanner(System.in);
                //String mensagem = "Ola";

                //System.out.println("Cliente: " + data);
                //out.println(mensagem);
            }
        } catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
    }
}
class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;
            in = new DataInputStream( clientSocket.getInputStream());
            out =new DataOutputStream( clientSocket.getOutputStream());
            this.start();
        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
    }
    public void run(){
        try {			                 // an echo server

            String data = in.readUTF();	                  // read a line of data from the stream
            out.writeUTF(data);
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {System.out.println("readline:"+e.getMessage());
        } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}


    }
}
