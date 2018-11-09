import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ServerReception implements Runnable {
	
    private BufferedReader in;
    private String message = null, login = null;
    public File f;
    
    public ServerReception(BufferedReader in, String login){ 
        this.in = in;
        this.login = login;
    }

    public void run() {
    	//fichier .txt correspondant à l'historique de la conversation 
    	f = new File("conversation.txt");
    	   	
        while(true){
            try {
            	
            	//réception et affichage du message client dans le serveur
                message = in.readLine();
                System.out.println(login+" : "+message);
                
                //écrit dans le fichier les messages de chaque clients
                FileWriter fw = new FileWriter(f,true); 
                fw.write("\n");
                fw.write(login+" : "+message);
                fw.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }

}
