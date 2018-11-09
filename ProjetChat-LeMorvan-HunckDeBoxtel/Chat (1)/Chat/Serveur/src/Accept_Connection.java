//Classe 'Accept_Connection' --> permet de vérifier si en tant que client on est bien connecté au serveur
import java.io.*;
import java.net.*;

public class Accept_Connection implements Runnable{

    private ServerSocket socketserver = null;
    private Socket socket = null;
    public Thread t1;
    
    public Accept_Connection(ServerSocket ss){
        socketserver = ss;
    }

    public void run() {

        try {
            while(true){
                socket = socketserver.accept();
                
                //création du thread en lancant la fonction 'CheckRegisteredClient'
                t1 = new Thread(new CheckRegisteredClient(socket));
                t1.start();
            }
        } catch (IOException e) {

            System.err.println("Erreur serveur");
        }
    }
}
