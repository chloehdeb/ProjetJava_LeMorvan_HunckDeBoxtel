//Classe CreateAccountClient --> on créer un compte en implémentant la base de donnée
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class CreateAccountClient implements Runnable {

    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String login = null, pass =  null;
    public Thread t2;
    public File f;
    private boolean newaccount = false;

    public CreateAccountClient(Socket s){
        socket = s;
    }
    public void run() {
    	//fichier .txt correspondant au login et mot de passe
    	f = new File("dataBase.txt");

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            

            while(!newaccount){

                out.println("Choisissez votre login :");
                out.flush();
                login = in.readLine(); //on récupère le login entrée par le client 


                out.println("Choisissez votre mot de passe :");
                out.flush();
                pass = in.readLine(); //on récupère le mot de passe entrée par le client
                
                FileWriter fw = new FileWriter(f,true); //ecrire a la suite des users deja presents
                fw.write("\n"); // saut ded ligne
                fw.write(login+ " "+pass);//ecrire le login et le mot-de-passe dans le fichier dataBase
                fw.close();
                
                newaccount = true;
                
            }
            
            t2 = new Thread(new Authentication(socket));
            t2.start();

        } catch (IOException e) {

            System.err.println(login+" ne repond pas !");
        }
    }
}

