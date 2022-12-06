import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import mot.Grammaire;

public class Serveur {

    public static void main(String[] test) {

        final ServerSocket serveurSocket;
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);
        String IP = "localhost";
        int port = 1892;

        try {
            serveurSocket = new ServerSocket(port);
            System.out.println("En attente de client");
            System.out.println("mon ip : "+IP);
            clientSocket = serveurSocket.accept();
            System.out.println("Client trouve");
            out = new PrintWriter(clientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Thread envoi = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    while (true) {
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });
            envoi.start();

            Thread recevoir = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        // tant que le client est connecté
                        while (msg != null) {
                            System.out.println("Client : " + msg);
                            msg = in.readLine();
                            try {
                                Grammaire grammaire = new Grammaire();
                                Object[][] rep = grammaire.ExecuteSql(msg);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        // sortir de la boucle si le client a déconecté
                        System.out.println("Client deconnecté");
                        // fermer le flux et la session socket
                        out.close();
                        clientSocket.close();
                        serveurSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            recevoir.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}