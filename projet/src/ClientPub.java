import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientPub {
    public static void main(String argv[]) throws Exception {
        String modifiedSentence;
        Scanner inFromUser = new Scanner(new InputStreamReader(System.in));
        Socket clientSocket = new Socket(InetAddress.getByName("localhost"), 12345);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("Entrer votre pseudo :");
        String name = inFromUser.nextLine();
        User user = new User(name);
        System.out.println("votre pseudo est :" + user.nom);

        while (true) {
            do {
                System.out.print("Entrez votre message: ");
                while (inFromUser.hasNext()) {

                    String content = inFromUser.nextLine();
                    Message msg = new Message(content);

                    Requetes requetes = new Requetes(user.nom, msg.contenu , Type_Requetes.PUBLISH);
                    outToServer.writeBytes(requetes.afficher());
                    //outToServer.flush();
                }

                modifiedSentence = inFromServer.readLine();
                System.out.println("Du serveur: " + modifiedSentence);

            }
            while (!modifiedSentence.equals("EOF"));
            System.out.println(modifiedSentence);
            clientSocket.close();


        }
    }

    public void connexion(){
        //TODO THIS NIGHT NOT TOMORROW
    }
}