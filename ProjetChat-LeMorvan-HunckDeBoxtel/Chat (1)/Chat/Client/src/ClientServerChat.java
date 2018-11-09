//Classe 'ClientServerChat' 
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientServerChat {

    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc;

    public ClientServerChat(Socket s){
        socket = s;

        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            sc = new Scanner(System.in);
            
            //on créé deux 'thread' afin de gérer en parrallèle l'émission et la réception de message par le client
            Thread t4 = new Thread(new ClientEmission(out)); 
            t4.start();
            Thread t3 = new Thread(new ClientReception(in));
            t3.start();

        } catch (IOException e) {
            System.err.println("Le serveur distant s'est deconnectee !");
        }
    }
}
