//Classe 'Accept_Connection' --> permet de v�rifier si en tant que client on est bien connect� au serveur
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
                
                //cr�ation du thread en lancant la fonction 'CheckRegisteredClient'
                t1 = new Thread(new CheckRegisteredClient(socket));
                t1.start();
            }
        } catch (IOException e) {

            System.err.println("Erreur serveur");
        }
    }
}
