//Classe 'Client' avec le Main --> permet de lancer un nouveau client
import java.io.*;
import java.net.*;

public class Client {
	
    public static Socket socket = null; //D�claration de la socket
 
    public static void main(String[] args) {
    	
        try {

            System.out.println("Demande de connexion au serveur.");
            socket = new Socket("127.0.0.1",2003); //socket (adresse IP + n�port): permet de se connecter au serveur � distante afin de communiquer avec lui
            System.out.println("Connexion �tablie avec le serveur.");

            CheckRegistered t1;
            t1= new CheckRegistered(socket); //on appel le constructeur par d�faut qui se connecte � la socket
        
        //gestion des exceptions si une erreur intervient dans le bloc try
        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter � l'adresse "+socket.getLocalAddress());
            
        } catch (IOException e) {
            System.err.println("Aucun serveur � l'�coute du port "+socket.getLocalPort());
        }
    }
}
