import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class ServerRudi {

    public ServerRudi(){

    }

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
        String msg;
        String typereqt;
        String author_treq;
        ArrayList list_users = new ArrayList<>();
        ArrayList list_ids = new ArrayList<>();

        public Handler(Socket socket) throws IOException {
            this.socketClient = socket;
            this.in = new Scanner(new InputStreamReader(socketClient.getInputStream()));
            typereqt = in.next();
            author_treq = in.nextLine();
            msg = in.nextLine();
            socketClient.close();
            list_users.add(author_treq.substring(author_treq.indexOf("@")));
            list_ids.add(msg.substring(author_treq.indexOf(": ")));
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
            return null;
        }

        public String RCV_REQUEST() {
            return null;
        }

        public void run() {
            //PUBLISHER();
            choice_request(); // permet de choisir la bonne requete et lance la méthode pour
            System.out.println(list_users.get(0));
            System.out.println(list_ids.get(0));
        }
    }
}

//this.out.print pour envoyer su serveur au client
