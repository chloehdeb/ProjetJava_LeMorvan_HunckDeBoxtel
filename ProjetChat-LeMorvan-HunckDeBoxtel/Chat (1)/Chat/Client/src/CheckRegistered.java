//Classe CheckRegistered --> on dit si on a déjà un compte ou pas
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class CheckRegistered {

    private Socket socket = null;
    public static String check = null; 
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;
    private boolean oui = false;
    private String authenticationorcreataccount = null; 

    public CheckRegistered(Socket s){ //constructor par défaut qui va permettre de se connecter à la socket
        socket = s;

        try {
        	
            out = new PrintWriter(socket.getOutputStream()); 
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            sc = new Scanner(System.in);

            while(true){

                System.out.println(in.readLine()); //Affichage question serveur au client : "Avez vous un compte ?"
                check = sc.nextLine(); //méthode de récupération de données pour les String
                out.println(check); //println affiche l'argument transmis à la console standard et une nouvelle ligne
                out.flush(); //provoque l'envoi de tous les caractères restants en attente dans un flux (vide le buffer dans le flux)  
                
                authenticationorcreataccount = in.readLine(); //variable pour savoir si le serveur nous renvoi 'Authentification' ou 'CreationCompte'
                
                //en fonction de se qu'on reçoit par le serveur on : 
                if(authenticationorcreataccount.equals("Authentification")){ 
                	Connection t1;
                    t1= new Connection(socket); //appel la fonction Connection
                }
                
                else if (authenticationorcreataccount.equals("CreationCompte")){ 
                	CreateAccount t2;
                    t2= new CreateAccount(socket); //appel la fonction CreateAccount
                }
                
                else {
                    System.err.println("Erreur connexion ou creation compte");
                }
            }
            
        } catch (IOException e) {

            System.err.println("Le serveur ne repond plus ");
        }
    }
}
