import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ServerRudi {

    static HashMap<String , String> hashMap= new HashMap<>();

    public ServerRudi(){}

    public static void main(String[] args) {
        connexion();


    }


    public static void connexion(){
        try {
            ServerSocket socketServeur = new ServerSocket(12345);
            System.out.println("Lancement du serveur");
            Executor e = Executors.newCachedThreadPool();

            while (true) {
                e.execute(new Thread(new Handler(socketServeur.accept())));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class Handler implements Runnable {
        Socket socketClient;
        Scanner in;
        static String msg;
        String typereqt;
        static String author_treq;

        public Handler(Socket socket) throws IOException {
            this.socketClient = socket;
            this.in = new Scanner(new InputStreamReader(socketClient.getInputStream()));
            typereqt = in.next();
            author_treq = in.nextLine();
            msg = in.nextLine();
            socketClient.close();

        }

        // Ajouter les users comme key et les message comme valeur
        public static void addUsers_TO_HashMap(){
            String auteur  = author_treq.substring(author_treq.indexOf("@")) ;
            String messages = msg.substring(author_treq.indexOf(": "));
            //if(!hashMap.containsKey(auteur) ) {
                hashMap.put(auteur , messages);
            /*}else{
                System.out.println("-------- Auteur existe deja ---------");
                ArrayList list_of_Msg = new ArrayList();
                list_of_Msg.add(messages);
                hashMap.put(auteur , list_of_Msg.toString());
            }

             */

        }

        // afficher la hashmap contenant les auteurs et leurs messages
        public static void AfficherHashMap(){
            System.out.println(hashMap.toString() +"---------"+ hashMap.values());
        }


        public String author() {
            return this.author_treq;
        }

        public String getMsg() {
            return this.msg;
        }

        public void choice_request() {
            switch (typereqt) {
                case ("PUBLISH"):
                    this.PUBLISHER();
                    break;
                case ("RCV_MSG"):
                    this.RCV_REQUEST();
                    break;
                case ("RCV_IDS"):
                    this.RCV_ID_REQUEST();
                    break;
                default:
                    System.out.println(new Error("Invalid request"));
            }

        }

        public void PUBLISHER() {
            System.out.println(new Publish(author(), getMsg()).afficher());
        }


        public String RCV_ID_REQUEST() {
            //TODO
            return "";
        }

        public String MSG_ID() {
            //TODO
            return null;
        }

        public String RCV_REQUEST() {
            //TODO
            return null;
        }

        public String MSG() {
            //TODO
            return null;
        }

        public void run() {

            choice_request();
            addUsers_TO_HashMap();// Ajouter les users comme key et les message comme valeur
            AfficherHashMap();// hshmap contenant les msg de chaque user
            //System.out.println(Follower.RCV_IDS(2));

        }
    }
}


//this.out.print pour envoyer su serveur au client
