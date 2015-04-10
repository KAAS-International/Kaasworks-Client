package main.java.com.kaasintl.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;


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
    private LinkedList<String> queue = new LinkedList<String>();

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

        login("kees");
        out.println("get playerlist");

        while (working) {
            try {
                line = in.readLine();
                parser(line);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void parser(String s) {
        if (s.equals("OK")) {
            // Roep iets aan
        } else if (s.substring(0, 2).equals("ERR")) {
            // Doe iets anders
        } else if (s.substring(0, 2).equals("SVR")) {

            if (s.substring(3,6).equals("HEL")) {

            } else if (s.substring(3,6).equals("GAM")) {
                
            } else {
                // Kill a goat
            }

        } else {
            // Kill a kitten
        }
    }

    public void login(String s) {
        out.println("login " + s);
        out.flush();
    }

    public void sendMessage(String s) {
        out.println(s);
        out.flush();
    }
}