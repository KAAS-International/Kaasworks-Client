package main.java.com.kaasintl.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

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
    private LinkedList<String> queue = new LinkedList();
    private ArrayList<String> parsedList = new ArrayList();

    /**
     * Method implementation of the thread that listens continuously to the server socket.
     * Creates the connection when started
     */
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

        // demo code
        login("kees");
        out.println("get playerlist");
        //end demo code

        while (working) {
            try {
                line = in.readLine();
                parser(line);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Parses the messages the server can send to the client
     * @param s the server message received by the socket
     */
    public void parser(String s) {
        boolean done = false;
        Scanner sc = new Scanner(s);
        while(!done && sc.hasNext()) {
            String temp = sc.next();

            switch(temp) {
                case "OK":
                    done = true;
                    break;
                case "ERR":
                    done = true;
                    break;
                case "SVR":
                    temp = sc.next();

                switch(temp) {
                    case "HELP":
                        done = true;
                        break;
                    case "GAME":
                        //iets
                        break;
                    case "GAMELIST":
                        temp = "";
                        while(sc.hasNext()) {
                            temp = temp + sc.next();
                        }
                        System.out.println(temp);
                        parsedList = parseList(temp);
                        break;
                }
            }
        }
    }

    /**
     * Receives a String and cuts it into usable pieces.
     * @param s String to be parsed
     * @return ArrayList with usable sub-strings.
     */
    public ArrayList<String> parseList(String s) {
        s = s.substring(1,(s.length()-1));
        Scanner scanner = new Scanner(s).useDelimiter(",");
        while(scanner.hasNext()) {
            s = scanner.next();
            s = s.substring(1,s.length()-1);
            System.out.println(s);
        }
        return parsedList;
    }

    /**
     * Log the user in to the server
     * @param s the desired username
     */
    public void login(String s) {
        out.println("login " + s);
        out.flush();
    }

    /**
     * Called by the game manager, sends a message to the server
     * @param s the desired message
     */
    public void sendMessage(String s) {
        out.println(s);
        out.flush();
    }
}