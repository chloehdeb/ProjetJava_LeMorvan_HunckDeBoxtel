//Classe 'ServerClientChat' 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerClientChat implements Runnable {

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String login = "zero";
    private Thread t3, t4;


    public ServerClientChat(Socket s, String log){

        socket = s;
        login = log;
    }
    public void run() {

        try {
        	
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            //on cr�� deux 'thread' afin de g�rer en parrall�le l'�mission et la r�ception de message par le serveur
            Thread t3 = new Thread(new ServerReception(in,login));
            t3.start();
            Thread t4 = new Thread(new ServerEmission(out));
            t4.start();

        } catch (IOException e) {
            System.err.println(login +"s'est d�connect�e ");
        }
    }
}
