//Classe 'ClientReception'
import java.io.*;
import java.util.Scanner;

public class ClientReception implements Runnable {

    private BufferedReader in;
    private String message = null;
    private String line;

    public ClientReception(BufferedReader in){
        this.in = in;
        
        try{
        	// lit la base de donnée (fichier .txt) pour afficher l'historique de conversation 
        	System.out.println("Historique de la conversation :");
        	
        	InputStream flux=new FileInputStream("conversation.txt"); 
        	InputStreamReader lecture=new InputStreamReader(flux);
        	BufferedReader buff=new BufferedReader(lecture);
        	String ligne;
        	while ((ligne=buff.readLine())!=null){
        		System.out.println(ligne);
        	}
        	buff.close(); 
        	}		
        	catch (Exception e){
        	System.out.println(e.toString());
        	}    
        }

    public void run() {

        while(true){
            try {
            	//lit le message reçu par le serveur
                message = in.readLine();
                System.out.println("Le serveur vous dit :" +message);
                
            } catch (IOException e) {
                e.printStackTrace(); //traitement exception
            }
        }
    }
}
