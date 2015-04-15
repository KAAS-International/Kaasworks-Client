package main.java.com.kaasintl.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
    private Thread parser;
    private BufferedReader in;
    private PrintWriter out;
    public ArrayList<String> queue = new ArrayList<>();
    public ArrayList<Message> parsedQueue = new ArrayList<>();

    public NetManager() {
        try {
            Socket sock = new Socket("localhost", 7789);
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            receiver = new Thread(new Reciever(this));
            receiver.start();
            parser = new Thread(new Parser(this));
            parser.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameManager = new GameManager();
    }

    public NetManager(GameManager g) {
        this.gameManager = g;

        try {
            Socket sock = new Socket("localhost", 7789);
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            receiver = new Thread(new Reciever(this));
            receiver.start();
            parser = new Thread(new Parser(this));
            parser.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log the user in to the server
     * @param name the desired username
     */
    public void login(String name) {
        out.println("login " + name + "\n");
        out.flush();
    }

    public ArrayList<String> fetchPlayerList() {
        out.println("get playerlist");
        out.flush();
        System.out.println("sent");

        ArrayList<String> list = new ArrayList<>();
        boolean working = true;
        while(working) {
            if(parsedQueue.size() > 0) {

                for(int i = 0; i < parsedQueue.size(); i++) {
                    Message m = parsedQueue.get(i);
                    if (m.getType().equals("playerList")) {
                        list = (ArrayList<String>) m.getContent();
                        parsedQueue.remove(i);
                        working = false;
                        break;
                    }
                }
            }
        }
        return list;
    }

    public synchronized String getFromQueue(int index) {
        return queue.get(index);
    }

    public synchronized void removeFromQueue(int index) {
        queue.remove(index);
    }

    public synchronized void removeFromQueue(String value) {
        queue.remove(value);
    }

    public synchronized int getQueueSize() {
        return queue.size();
    }

    public synchronized void addToQueue(String s) {
        queue.add(s);
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

            while (true) {
                try {
                    line = in.readLine();
                    if(line != null) {
                        netManager.addToQueue(line);
                        System.out.println("read " + line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class Parser implements Runnable {
        NetManager netManager;
        boolean firstOk = true;

        public Parser(NetManager netManager) {
            this.netManager = netManager;
        }

        /**
         * Run-method implementation of the thread that continuously looks at the queue to parse received strings.
         */
        @Override
        public void run() {

            while (true) {
                if(netManager.getQueueSize() > 0) {
                    String temp = netManager.getFromQueue(0);
                    System.out.println("parse " + temp);
                    parser(temp);
                    netManager.removeFromQueue(temp);
                }
            }
        }

        /**
         * Parses the messages the server sends to the client, makes them
         * into Message Objects and puts them into the parsedQueue
         * @param s the server message received by the socket
         */
        public synchronized void parser(String s) {
            boolean done = false;
            HashMap<String,String> parsedMap;
            ArrayList<String> parsedList;
            Scanner sc = new Scanner(s);
            while(!done && sc.hasNext()) {

                String temp = sc.next();

                System.out.println("parsing " + temp);

                switch(temp) {
                    case "OK":
                        if(!firstOk) {
                            netManager.parsedQueue.add(new Message("response", "ok"));
                        }
                        firstOk = false;
                        done = true;
                        break;
                    case "ERR":
                        netManager.parsedQueue.add(new Message("response", "err"));
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
                                        netManager.parsedQueue.add(new Message("match", parsedMap));
                                        done = true;
                                        break;
                                    case "YOURTURN":
                                        temp = "";
                                        while(sc.hasNext()) {
                                            temp = temp + sc.next();
                                        }
                                        parsedMap = parseMap(temp);
                                        netManager.parsedQueue.add(new Message("yourTurn", parsedMap));
                                        done = true;
                                        break;
                                    case "MOVE":
                                        temp = "";
                                        while(sc.hasNext()) {
                                            temp = temp + sc.next();
                                        }
                                        parsedMap = parseMap(temp);
                                        netManager.parsedQueue.add(new Message("move", parsedMap));
                                        done = true;
                                        break;
                                    case "CHALLENGE":
                                        temp = "";
                                        while(sc.hasNext()) {
                                            temp = temp + sc.next();
                                        }
                                        parsedMap = parseMap(temp);
                                        netManager.parsedQueue.add(new Message("challenge", parsedMap));
                                        done = true;
                                        break;
                                    case "WIN":
                                        temp = "";
                                        while(sc.hasNext()) {
                                            temp = temp + sc.next();
                                        }
                                        parsedMap = parseMap(temp);
                                        netManager.parsedQueue.add(new Message("win", parsedMap));
                                        done = true;
                                        break;
                                    case "LOSS":
                                        temp = "";
                                        while(sc.hasNext()) {
                                            temp = temp + sc.next();
                                        }
                                        parsedMap = parseMap(temp);
                                        netManager.parsedQueue.add(new Message("loss", parsedMap));
                                        done = true;
                                        break;
                                    case "DRAW":
                                        temp = "";
                                        while(sc.hasNext()) {
                                            temp = temp + sc.next();
                                        }
                                        parsedMap = parseMap(temp);
                                        netManager.parsedQueue.add(new Message("draw", parsedMap));
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
                                netManager.parsedQueue.add(new Message("gameList", parsedList));
                                done = true;
                                break;
                            case "PLAYERLIST":
                                temp = "";
                                while(sc.hasNext()) {
                                    temp = temp + sc.next();
                                }
                                System.out.println(temp + "omg");
                                parsedList = parseList(temp);
                                netManager.parsedQueue.add(new Message("playerList", parsedList));
                                done = true;
                                break;
                            default:
                                break;
                        }
                        break;
                    default:
                        System.out.println("ignored " + temp);
                        done = true;
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