//Classe 'Server' avec le Main --> permet de lancer le serveur
import java.io.*;
import java.net.*;

public class Server {
	
    public static ServerSocket ss = null;
    public static Thread t;


    public static void main(String[] args) {

        try {
            ss = new ServerSocket(2003); //socket : attend de recevoir un message d'un client
            System.out.println("Le serveur est a l'ecoute du port "+ss.getLocalPort()); //Affichage du texte + numéro de port récupéré avec 'getLocalPort'
            System.out.println("Attente connexion d'un client");
            
            t = new Thread(new Accept_Connection(ss)); //on spécifie la fonction que le thread doit implémenter
            t.start(); //on lance le thread java avec la méthode 'start'

        } catch (IOException e) {
        	System.out.println("Erreur Port");
        	System.err.println("Le port "+ss.getLocalPort()+" est déjà  utilisé !");
        }
    }
}
