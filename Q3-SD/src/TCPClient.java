import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main (String args[]) {
        // arguments supply message and hostname
        Socket s = null;
        try{
            int serverPort = 7896;

            while (true){
                s = new Socket("127.0.0.1", serverPort);
                PrintStream out = new PrintStream(s.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String mensagem = in.readLine();

                System.out.println("Servidor: " + mensagem);

                Scanner inTeclado = new Scanner(System.in);
                String data = inTeclado.nextLine();

                out.println(data);
            }
            //String mensagem = in.readLine();
            //System.out.println("Servidor: " + mensagem);

        }catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("readline:"+e.getMessage());
        }finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }
}
