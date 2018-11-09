//Classe 'ClientEmission' --> demande au client d'écrire son message et l'envoi au serveur 
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ClientEmission implements Runnable {

    private PrintWriter out;
    private String login = null, message = null;
    private Scanner sc = null; 

    public ClientEmission(PrintWriter out) {
        this.out = out;
    }

    public void run() {

        sc = new Scanner(System.in); //on récupére ce qu'on a tapé dans la console

        while(true){
        	//écriture du message et l'envoi au serveur
            System.out.println("Ecrire votre message :");
            message = sc.nextLine(); 
            out.println(message); 
            out.flush();          
        }
    }
}
