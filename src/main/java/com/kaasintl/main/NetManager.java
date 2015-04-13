package main.java.com.kaasintl.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 * Connects to a telnet client with a password and username.
 *
 * Created by Tanja, Niek and Kevin on 8-4-2015.
 * No animals were harmed during the creation process
 * Only 1 cheese suffered from injury
 */

public class NetManager extends Thread {
    private GameManager gameManager;
    private BufferedReader in;
    private BufferedReader stdIn;
    private PrintWriter out;
    private String line;
    private LinkedList<String> queue = new LinkedList<>();
    private Map<String,String> parsedMap = new HashMap<>();

    public NetManager() {
        try {
            Socket sock = new Socket("localhost", 7789);
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameManager = new GameManager();
    }

    public NetManager(GameManager g) {
        super();
        this.gameManager = g;
        try {
            Socket sock = new Socket("localhost", 7789);
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // demo code
        //login("kees");
        //out.println("get playerlist");
        //end demo code
    }

    /**
     * Run-method implementation of the thread that listens continuously to the server socket.
     * Creates the connection when started
     */
    @Override
    public void run() {
        boolean working = true;

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
        ArrayList<String> parsedList;
        Scanner sc = new Scanner(s);
        while(!done && sc.hasNext()) {
            String temp = sc.next();

            switch(temp) {
                case "OK":
                    //gameManager.receive("ok");
                    done = true;
                    break;
                case "ERR":
                    //gameManager.receive("err");
                    done = true;
                    break;
                case "SVR":
                    temp = sc.next();

                    switch(temp) {
                        case "HELP":
                            done = true;
                            break;
                        case "GAME":
                            temp = sc.next();
                            switch(temp) {
                                case "MATCH":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    parsedMap = parseMap(temp);
                                    //gameManager.setMatch(parsedMap);
                                    break;
                                case "YOURTURN":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    break;
                                case "MOVE":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    break;
                                case "CHALLENGE":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    break;
                                case "WIN":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    break;
                                case "LOSS":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    break;
                                case "DRAW":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "GAMELIST":
                            temp = "";
                            while(sc.hasNext()) {
                                temp = temp + sc.next();
                            }
                            System.out.println(temp);
                            parsedList = parseList(temp);
                            gameManager.setGameList(parsedList);
                            done = true;
                            break;
                        case "PLAYERLIST":
                            temp = "";
                            while(sc.hasNext()) {
                                temp = temp + sc.next();
                            }
                            System.out.println(temp);
                            parsedList = parseList(temp);
                            gameManager.setPlayerList(parsedList);
                            done = true;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }

        }
    }

    /**
     * Receives a String and cuts it into usable pieces.
     * @param s String to be parsed
     * @return ArrayList with usable sub-strings.
     */
    public ArrayList<String> parseList(String s) {
        ArrayList<String> a = new ArrayList<>();
        s = s.substring(1,(s.length()-1));
        Scanner scanner = new Scanner(s).useDelimiter(",");
        while(scanner.hasNext()) {
            s = scanner.next();
            s = s.substring(1,s.length()-1);
            a.add(s);
        }
        return a;
    }

    public HashMap<String,String> parseMap(String s) {
        HashMap<String,String> m = new HashMap<>();


        return m;
    }

    /**
     * Log the user in to the server
     * @param s the desired username
     */
    public void login(String s) {
        out.println("login " + s);
        out.flush();
    }

    public void getPlayerlist() {
        out.println("get playerlist");
        out.flush();
    }
}