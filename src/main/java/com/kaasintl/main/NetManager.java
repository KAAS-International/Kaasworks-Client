package main.java.com.kaasintl.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * Connects to a telnet client with a password and username.
 *
 * Created by Tanja, Niek and Kevin on 8-4-2015.
 * No animals were harmed during the creation process
 * Only 1 cheese suffered from injury
 */


public class NetManager extends Thread {
    private BufferedReader in;
    private BufferedReader stdIn;
    private PrintWriter out;
    private String line;
    private String message;

    public static void main(String[] args) {
        new Thread(new NetManager()).start();
    }

    @Override
    public void run() {
        boolean working = true;

        try {
            Socket sock = new Socket("localhost", 7789);
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (working) {
            try {
                if (message != null) {
                    out.println(message);
                    out.flush();
                }

                line = in.readLine();
                System.out.println(line);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String s) {
        message = s;
    }

    public void login(String s) {
        out.println("login " + s);
        out.flush();
    }
}