//Classe 'Server' avec le Main --> permet de lancer le serveur
import java.io.*;
import java.net.*;

public class Server {
	
    public static ServerSocket ss = null;
    public static Thread t;


    public static void main(String[] args) {

        try {
            ss = new ServerSocket(2003); //socket : attend de recevoir un message d'un client
            System.out.println("Le serveur est a�l'ecoute du port "+ss.getLocalPort()); //Affichage du texte + num�ro de port r�cup�r� avec 'getLocalPort'
            System.out.println("Attente connexion d'un client");
            
            t = new Thread(new Accept_Connection(ss)); //on sp�cifie la fonction que le thread doit impl�menter
            t.start(); //on lance le thread java avec la m�thode 'start'

        } catch (IOException e) {
        	System.out.println("Erreur Port");
        	System.err.println("Le port "+ss.getLocalPort()+" est d�j� utilis� !");
        }
    }
}
