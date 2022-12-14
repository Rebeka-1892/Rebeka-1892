import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Fonction.Fonction;
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

            Thread recevoir = new Thread(new Runnable() {
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        // tant que le client est connecté
                        OutputStream os = clientSocket.getOutputStream(); // send message au client

                        ObjectOutputStream mpSent = new ObjectOutputStream(os); // pour l'envoie de l'objet

                        while (msg != null) {
                            try {
                                System.out.println("Client : " + msg);

                                Grammaire grammaire = new Grammaire();
                                Object[][] rep = grammaire.ExecuteSql(msg);

                                mpSent.writeObject(rep);

                                System.out.println("Server : relation sent");

                            } catch (Exception e) {
                                System.err.println(e);
                            }
                            msg = in.readLine();
                        }
                        // sortir de la boucle si le client a déconecté
                        System.out.println("Client deconnecté");
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