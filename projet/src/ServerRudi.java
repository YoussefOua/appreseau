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


    public static void main(String[] args) {
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


    public void connexion() throws IOException {
       //TODO
    }


    public static class Handler implements Runnable {
        Socket socketClient;
        Scanner in;
        String msg;
        String typereqt;
        String author_treq;
        ArrayList list_ids;

        public Handler(Socket socket) throws IOException {
            this.socketClient = socket;
            this.in = new Scanner(new InputStreamReader(socketClient.getInputStream()));
            typereqt = in.next();
            author_treq = in.nextLine();
            msg = in.nextLine();
            socketClient.close();

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
                case ("RCV_MSG") :
                    this.RCV_REQUEST();
                    break;
                case ("RCV_IDS") :
                    this.RCV_ID_REQUEST();
                    break;
                default :
                    throw new IllegalStateException("Invalid request: " + typereqt);
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

        }
    }
            /*String typereqt = in.next();
            String author_treq = in.nextLine();
            ArrayList list_ids = new ArrayList();


            while (in.hasNext()) {
                try {
                    msg = in.nextLine();
                    Message content = new Message(msg);
                    System.out.println("entete :" + typereqt + author_treq + '\n' + content.contenu
                            + '\n' + "réponse : OK");
                    socketClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

             */
    }



    /*    Scanner in = new Scanner(new InputStreamReader(socketClient.getInputStream()));
        String typereqt =in.next();
        String author_treq = in.nextLine();
        ArrayList list_ids = new ArrayList();

        while(in.hasNext()) {
        msg = in.nextLine();
        Message content = new Message(msg);
        list_ids.add(content.contenu);
        System.out.println("entete :" + typereqt + author_treq + '\n' + content.contenu
        + '\n' + "réponse : OK");

        socketClient.close();

     */