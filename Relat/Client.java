import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Fonction.Fonction;
import mot.Vocabulaire;

import java.io.*;

public class Client {

    public static void main(String[] args) throws Exception{
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);
        String IP = "localhost";
        int port = 1892;

        try {
            clientSocket = new Socket(IP, port);
            System.out.println("serveur trouve : "+IP);

            Vocabulaire Vocabulaire = new Vocabulaire();
            
            // flux pour envoyer
            out = new PrintWriter(clientSocket.getOutputStream());
            // flux pour recevoir
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread envoyer = new Thread(new Runnable() {
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
            envoyer.start();

            Thread recevoir = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        InputStream is = clientSocket.getInputStream(); //lire le message envoyé

                        ObjectInputStream relationObjIs = new ObjectInputStream(is);

                        while (relationObjIs != null) {
                            try {
                                Object relationObj  = relationObjIs.readObject(); //lecture de l'objet envoyé

                                Object[][] rep = (Object[][]) relationObj;

                                Fonction f = new Fonction();
                                f.Afficher(rep);
                                Vocabulaire Vocabulaire = new Vocabulaire();

                            } catch (Exception e) {
                                System.err.println(e);
                            }
                        }

                        System.out.println("Serveur deconnecté");
                        out.close();
                        relationObjIs.close();
                        clientSocket.close();

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