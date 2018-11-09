//Classe CreateAccount --> on créé un compte avec notre login et mot de passe
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CreateAccount {
	
	private Socket socket = null;
	private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc = null;
    private boolean newaccount = false;
    private String login=null;
    private String pass=null;
    
    public CreateAccount (Socket s) {
    	socket = s;
		
		try {
			out = new PrintWriter(socket.getOutputStream());
	        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        sc = new Scanner(System.in);
			
			while(!newaccount) {
				System.out.println("Demande d'authentification");
				
				//on récupère la question du serveur 'Choisissez votre login' ensuite le client écrit et envoi le login au serveur
                System.out.println(in.readLine());
                login = sc.nextLine();
                out.println(login);
                out.flush();
                
              //on récupère la question du serveur 'Choisissez votre mot de passe' ensuite le client écrit et envoi le motde passe au serveur
                System.out.println(in.readLine());
                pass = sc.nextLine();
                out.println(pass);
                out.flush();
                
                newaccount = true; //permet de sortir de la boucle
			}
			
			Connection t1;
            t1= new Connection(socket); //appel la fonction Connection
				
		}catch(IOException e) {
			e.printStackTrace();
		}	
    }
}