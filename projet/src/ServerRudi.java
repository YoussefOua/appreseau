import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static java.lang.System.out;


public class ServerRudi {

    static ArrayList<String> list_of_users = new ArrayList<>();
    static ArrayList<String> list_of_msg = new ArrayList<>();
    static ArrayList<String> list_of_tag = new ArrayList<>();
    static Map<String, List<String>> map_of_users = new HashMap<String, List<String>>();
    public ServerRudi(){}

    public static void main(String[] args) {
        connexion();
    }




    public static void connexion(){
        try {
            ServerSocket socketServeur = new ServerSocket(12345);
            out.println("Lancement du serveur");
            Executor e = Executors.newCachedThreadPool();
            while (true) {
                e.execute(new Thread(new Handler(socketServeur.accept())));
               /* PrintWriter out = new PrintWriter(socketServeur.accept().getOutputStream());
                String s =" seeer";
                out.println(s);
                out.flush();
                */
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
        public static void addUsers_TO_List(){
            String auteur  = author_treq.substring(author_treq.indexOf("@")) ;
            String messages = msg.substring(author_treq.indexOf(": "));
            list_of_users.add(auteur);
            list_of_msg.add(messages);

            for(String s :list_of_users){
                for(String m : list_of_msg){
                    ArrayList<String> msgs = new ArrayList<>();
                    msgs.add(m);
                    map_of_users.put(s , msgs);
                }
            }

                System.out.println(map_of_users.toString());
            //addTags();
        }


            // traitement de msg contenant des tags
            public static void addTags(){
                for(int i = 0 ; i < list_of_msg.size() ; i++){
                    if(list_of_msg.get(i).toString().contains("#")) {
                        list_of_tag.add(new Tag(">>> user : " + list_of_users.get(i) + " -TAGS-" + list_of_msg.get(i)).afficher());
                    }
                }
            }


        // affichage de user et son message
        public void afficher_msg() {
            for (int i = 0; i < list_of_users.size(); i++) {
                out.println(">>> user :" + list_of_users.get(i) + " ----- " + "son message :" + list_of_msg.get(i));
            }
        }
        public void afficher_Tag() {
            for (int i = 0; i < list_of_tag.size(); i++) {
                out.println(list_of_tag.get(i));
            }
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
                    out.println(new Error("Invalid request"));
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
            addUsers_TO_List();// Ajouter les users comme key et les message comme valeur
            //afficher_msg();
            //
            //afficher_Tag();

        }
    }
}


//this.out.print pour envoyer su serveur au client
