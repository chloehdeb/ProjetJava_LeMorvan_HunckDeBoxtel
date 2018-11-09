//Classe 'Authendication' 
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Authentication implements Runnable {

    private Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String login = "zero", pass =  null;
    public boolean authentifier = false;
    public Thread t2;

    public Authentication(Socket s){
        socket = s;
    }
    public void run() {

        try {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            while(!authentifier){

                out.println("Entrez votre login :");
                out.flush();
                login = in.readLine(); //on récupère le login entrée par le client 


                out.println("Entrez votre mot de passe :");
                out.flush();
                pass = in.readLine(); //on récupère le mot de passe entrée par le client
                
                //si le login et mot de passe sont correct alors :
                if(isValid(login, pass)){
                    out.println("connecte");
                    System.out.println(login +" vient de se connecter ");
                    out.flush();
                    authentifier = true;
                }
                //sinon : 
                else if (!authentifier){
                out.println("erreur"); 
                out.flush();
                }
            }

            t2 = new Thread(new ServerClientChat(socket,login));
            t2.start();

        } catch (IOException e) {

            System.err.println(login+" ne repond pas !");
        }
    }
    
  //on vérifie si le login et le mot de passe son identique dans le fichier .txt
    private static boolean isValid(String login, String pass) {

        boolean connexion = false; //variable 
        
        try {
            Scanner sc = new Scanner(new File("dataBase.txt"));  


            while(sc.hasNext()){ //tant que je lis dans le fichier alors 
                if(sc.nextLine().equals(login+" "+pass)){ //si une ligne est égale à login et mode de passe
                    connexion=true;
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Le fichier n'existe pas !");
        }
        return connexion;
    }
}
