import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        final Socket clientSocket;
        final BufferedReader in;
        final PrintWriter out;
        final Scanner sc = new Scanner(System.in);// pour lire à partir du clavier
        String IP = "localhost";
        int port = 1892;

        try {
            clientSocket = new Socket(IP, port);
            System.out.println("serveur trouve : "+IP);

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
                String msg;

                @Override
                public void run() {
                    try {
                        msg = in.readLine();
                        while (msg != null) {
                            System.out.println("Serveur : " + msg);
                            msg = in.readLine();
                        }
                        System.out.println("Serveur deconnecté");
                        out.close();
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