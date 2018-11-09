//Classe Connextion --> c�t� client on rentre notre login et mot de passe
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Connection {//implements Runnable {

    private Socket socket = null;
    public static String login = null, pass = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;
    private boolean connect = false;
    private String connectorerror = null;

    public Connection(Socket s){

        socket = s;

        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in); //on scanne le flux rentrant

            while(!connect ){
            	
            	//on r�cup�re la question du serveur 'Entrer votre login' ensuite le client �crit et envoi le login au serveur
                System.out.println(in.readLine()); 
                login = sc.nextLine();
                out.println(login);
                out.flush();
                
                //on r�cup�re la question du serveur 'Entrer votre mot de passe' ensuite le client �crit et envoi le motde passe au serveur
                System.out.println(in.readLine());
                pass = sc.nextLine();
                out.println(pass);
                out.flush();
                
                
                connectorerror = in.readLine(); //variable pour savoir si le serveur nous renvoi 'connecte' ou 'erreur'
                
              //en fonction de se qu'on re�oit par le serveur on : 
                if(connectorerror.equals("connecte")){
                	
                    System.out.println("Connection au serveur reussi "); //affiche le message pr�c�dent
                    connect = true; //on sort de la boucle
                }

                else if (connectorerror.equals("erreur")) {
                    System.err.println("Le login ou le mot de passe est incorrecte"); //affiche le message d'erreur pr�c�dent
                }

            }
            
            ClientServerChat t1;
            t1= new ClientServerChat(socket);  //appel la fonction ClientServerChat

        } catch (IOException e) {
            System.err.println("Le serveur ne repond plus ");
        }
    }
}
