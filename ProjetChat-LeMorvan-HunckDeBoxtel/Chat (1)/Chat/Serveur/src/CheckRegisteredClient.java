//Classe CheckRegistered --> demande au client si il a déjà un compte ou non
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class CheckRegisteredClient implements Runnable {

    private Socket socket;
    public static Thread t2,t3;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String yesnoanswer = null;
    public boolean authentifier = false, answer = false;

    public CheckRegisteredClient(Socket s){
        socket = s;
    }
    
    public void run() {

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            while(!answer){
            	//demande au client si il a déjà un compte ou non
                out.println("Avez vous un compte ? [oui][non]");
                out.flush();
                yesnoanswer = in.readLine(); //on récupère la réponse du client
                
                //si oui on lance 'Authentication' pour s'identifier
                if(yesnoanswer.equals("oui")){
                	
                    out.println("Authentification");
                    out.flush();
                    
                    t2 = new Thread(new Authentication(socket));
                    t2.start();
                    
                    answer = true;
                }
                
              //si non on lance 'CreateAccountClient' pour créer un compte
                else if(yesnoanswer.equals("non")) {
                	
                	out.println("CreationCompte");
                	out.flush();
                	
                	t3 = new Thread(new CreateAccountClient(socket));
                    t3.start();
                	
                	answer = true;
                }        
            }
        } catch (IOException e) {

            System.err.println("Erreur");
        }
    }
}

