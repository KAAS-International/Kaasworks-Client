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

public class NetManager {
    private GameManager gameManager;
    private Thread receiver;
    private BufferedReader in;
    private PrintWriter out;
    private ArrayList<String> queue = new ArrayList<>();
    private Map<String,String> parsedMap = new HashMap<>();

    public NetManager() {
        try {
            Socket sock = new Socket("localhost", 7789);
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            receiver = new Thread(new Reciever(this));
            receiver.start();
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
            receiver = new Thread(new Reciever(this));
            receiver.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the messages the server can send to the client
     * @param s the server message received by the socket
     */
    public synchronized void parser(String s) {
        boolean done = false;
        ArrayList<String> parsedList;
        Scanner sc = new Scanner(s);
        while(!done && sc.hasNext()) {
            String temp = sc.next();

            switch(temp) {
                case "OK":
                    gameManager.setValidation("ok");
                    done = true;
                    break;
                case "ERR":
                    gameManager.setValidation("err");
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
                                    //gameManager.setMatch(parsedMap.get("PLAYERTOMOVE"), parsedMap.get("GAMETYPE"), parsedMap.get("OPPONENT"));
                                    done = true;
                                    break;
                                case "YOURTURN":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    parsedMap = parseMap(temp);
                                    //gameManager.setTurn(parsedMap.get("TURNMESSAGE"));
                                    done = true;
                                    break;
                                case "MOVE":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    parsedMap = parseMap(temp);
                                    //gameManager.setMove(parsedMap.get("PLAYER"), Integer.parseInt(parsedMap.get("MOVE")), parsedMap.get("DETAILS"));
                                    done = true;
                                    break;
                                case "CHALLENGE":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    parsedMap = parseMap(temp);
                                    //gameManager.setMove(parsedMap.get("CHALLENGER"), Integer.parseInt(parsedMap.get("CHALLENGENUMBER")), parsedMap.get("GAMETYPE"));
                                    done = true;
                                    break;
                                case "WIN":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    parsedMap = parseMap(temp);
                                    //gameManager.endGame(1, Integer.parseInt(parsedMap.get("PLAYERONESCORE")), Integer.parseInt(parsedMap.get("PLAYERTWOSCORE")), parsedMap.get("COMMENT"));
                                    done = true;
                                    break;
                                case "LOSS":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    parsedMap = parseMap(temp);
                                    //gameManager.endGame(-1, Integer.parseInt(parsedMap.get("PLAYERONESCORE")), Integer.parseInt(parsedMap.get("PLAYERTWOSCORE")), parsedMap.get("COMMENT"));
                                    done = true;
                                    break;
                                case "DRAW":
                                    temp = "";
                                    while(sc.hasNext()) {
                                        temp = temp + sc.next();
                                    }
                                    parsedMap = parseMap(temp);
                                    //gameManager.endGame(0, Integer.parseInt(parsedMap.get("PLAYERONESCORE")), Integer.parseInt(parsedMap.get("PLAYERTWOSCORE")), parsedMap.get("COMMENT"));
                                    done = true;
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
        String key;
        String value;
        s = s.substring(1,(s.length()-1));
        Scanner scanner = new Scanner(s).useDelimiter(",|:");
        while(scanner.hasNext()) {
            s = scanner.next();
            key = s;
            s = scanner.next();
            value = s.substring(1,s.length() - 1);
            m.put(key,value);
        }
        return m;
    }

    /**
     * Log the user in to the server
     * @param name the desired username
     */
    public void login(String name) {
        out.println("login " + name);
        out.flush();
    }

    public void fetchPlayerList() {
        out.println("get playerlist");
        out.flush();
    }

    public void fetchGameList() {
        out.println("get gamelist");
        out.flush();
    }

    public void forfeit() {
        out.println("get gamelist");
        out.flush();
    }

    private class Reciever implements Runnable {
        NetManager netManager;

        public Reciever(NetManager netManager) {
            this.netManager = netManager;
        }

        /**
         * Run-method implementation of the thread that listens continuously to the server socket.
         */
        @Override
        public void run() {
            String line;
            boolean working = true;

            while (working) {
                try {
                    line = in.readLine();
                    if(line != null) {
                        queue.add(line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Message {
        public String type;
        public Object content;

        /**
         * constructor
         * @param type
         * @param content
         */
        public Message(String type, Object content) {
            setType(type);
            setContent(content);
        }

        /**
         * Get the value of type
         * @return String type
         */
        public String getType() {
            return type;
        }

        /**
         * Set the type of the message
         * @param type
         */
        private void setType(String type) {
            this.type = type;
        }

        /**
         * Get the content of the message
         * @return Object content
         */
        public Object getContent() {
            return content;
        }

        /**
         * Set the content of the message
         * @param content
         */
        private void setContent(Object content) {
            this.content = content;
        }
    }
}