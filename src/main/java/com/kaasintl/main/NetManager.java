package main.java.com.kaasintl.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Connects to a telnet client with a password and username.
 * <p/>
 * Created by Tanja, Niek and Kevin on 8-4-2015.
 * No animals were harmed during the creation process
 * Only 1 cheese suffered from injury
 */

public class NetManager
{
	public ArrayList<String> queue = new ArrayList<>();
	public ArrayList<Message> parsedQueue = new ArrayList<>();
	private GameManager gameManager;
	private Thread receiver;
	private Thread parser;
	private BufferedReader in;
	private PrintWriter out;

	public NetManager()
	{
		try {
			Socket sock = new Socket("localhost", 7789);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			receiver = new Thread(new Receiver(this));
			receiver.start();
			parser = new Thread(new Parser(this));
			parser.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

		gameManager = new GameManager();
	}

	public NetManager(GameManager g)
	{
		this.gameManager = g;

		try {
			Socket sock = new Socket("145.37.59.19", 7789);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			receiver = new Thread(new Receiver(this));
			receiver.start();
			parser = new Thread(new Parser(this));
			parser.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public NetManager(GameManager g, String host, int port)
	{
		this.gameManager = g;

		try {
			Socket sock = new Socket(host, port);
			out = new PrintWriter(sock.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			receiver = new Thread(new Receiver(this));
			receiver.start();
			parser = new Thread(new Parser(this));
			parser.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Subscribes to a game
	 *
	 * @param game The game to subscribe to
	 * @return whether or not the action has succeed
	 */
	public boolean subscribe(String game)
	{
		out.println("subscribe " + game);
		out.flush();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String response = null;
		boolean working = true;
		while (working) {
			if (parsedQueue.size() > 0) {

				for (int i = 0; i < parsedQueue.size(); i++) {
					Message m = parsedQueue.get(i);
					if (m.getType().equals("response")) {
						response = (String) m.getContent();
						parsedQueue.remove(i);
						working = false;
						break;
					}
				}
			}
		}

		if (response.contains("OK")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Log the user in to the server
	 *
	 * @param name the desired username
	 */
	public void login(String name)
	{
		out.println("login " + name + "\n");
		out.flush();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    public boolean sendMove(int move) {
        out.println("move " + move + "\n");
        out.flush();

	    try {
		    TimeUnit.MILLISECONDS.sleep(100);
	    } catch (InterruptedException e) {
		    e.printStackTrace();
	    }

        String response = null;
        boolean working = true;
        while (working) {
            if (parsedQueue.size() > 0) {

                for (int i = 0; i < parsedQueue.size(); i++) {
                    Message m = parsedQueue.get(i);
                    if (m.getType().equals("response")) {
                        response = (String) m.getContent();
                        parsedQueue.remove(i);
                        working = false;
                        break;
                    }
                }
            }
        }

        if (response.equalsIgnoreCase("OK")) {
            return true;
        } else {
            return false;
        }
    }

	public boolean challengePlayer(String player, String game)
	{
		out.println("challenge " + "\"" + player + "\" \"" + game + "\"");
        out.flush();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String response = null;
		boolean working = true;
		while (working) {
			if (parsedQueue.size() > 0) {

				for (int i = 0; i < parsedQueue.size(); i++) {
					Message m = parsedQueue.get(i);
					if (m.getType().equals("response")) {
						response = (String) m.getContent();
						parsedQueue.remove(i);
						working = false;
						break;
					}
				}
			}
		}

		if (response.equalsIgnoreCase("OK")) {
			return true;
		} else {
			return false;
		}

	}

	public boolean acceptChallenge(int challengeNumber)
	{
		out.println("challenge accept " + challengeNumber);
        out.flush();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		String response = null;
		boolean working = true;
		while (working) {
			if (parsedQueue.size() > 0) {

				for (int i = 0; i < parsedQueue.size(); i++) {
					Message m = parsedQueue.get(i);
					if (m.getType().equals("response")) {
						response = (String) m.getContent();
						parsedQueue.remove(i);
						working = false;
						break;
					}
				}
			}
		}

		if (response.equalsIgnoreCase("OK")) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Fetches the playerlist from the server
	 *
	 * @return The playerlist from the server
	 */
	public ArrayList<String> fetchPlayerList()
	{
		out.println("get playerlist");
		out.flush();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ArrayList<String> list = new ArrayList<>();
		boolean working = true;
		while (working) {
			if (parsedQueue.size() > 0) {

				for (int i = 0; i < parsedQueue.size(); i++) {
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

	public synchronized String getFromQueue(int index)
	{
		return queue.get(index);
	}

	public synchronized void removeFromQueue(int index)
	{
		queue.remove(index);
	}

	public synchronized void removeFromQueue(String value)
	{
		queue.remove(value);
	}

	public synchronized int getQueueSize()
	{
		return queue.size();
	}

	public synchronized void addToQueue(String s)
	{
		queue.add(s);
	}

	public ArrayList<String> fetchGameList()
	{
		out.println("get gamelist");
		out.flush();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		ArrayList<String> list = new ArrayList<>();
		boolean working = true;
		while (working) {
			if (parsedQueue.size() > 0) {

				for (int i = 0; i < parsedQueue.size(); i++) {
					Message m = parsedQueue.get(i);
					if (m.getType().equals("gameList")) {
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

	public void forfeit()
	{
		out.println("get gamelist");
		out.flush();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private class Receiver implements Runnable
	{
		NetManager netManager;

		public Receiver(NetManager netManager)
		{
			this.netManager = netManager;
		}

		/**
		 * Run-method implementation of the thread that listens continuously to the server socket.
		 */
		@Override
		public void run()
		{
			String line;

			while (true) {
				try {
					line = in.readLine();
					if (line != null) {
						netManager.addToQueue(line);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class Parser implements Runnable
	{
		NetManager netManager;
		boolean firstOk = true;

		public Parser(NetManager netManager)
		{
			this.netManager = netManager;
		}

		/**
		 * Run-method implementation of the thread that continuously looks at the queue to parse received strings.
		 */
		@Override
		public void run()
		{

			while (true) {
				if (netManager.getQueueSize() > 0) {
					String temp = netManager.getFromQueue(0);
					parser(temp);
					netManager.removeFromQueue(temp);
				}
			}
		}

		/**
		 * Parses the messages the server sends to the client, makes them
		 * into Message Objects and puts them into the parsedQueue
		 *
		 * @param s the server message received by the socket
		 */
		public synchronized void parser(String s)
		{
			boolean done = false;
			HashMap<String, String> parsedMap;
			ArrayList<String> parsedList;
			Scanner sc = new Scanner(s);
			while (!done && sc.hasNext()) {

				String temp = sc.next();

				switch (temp) {
					case "OK":
						if (!firstOk) {
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

						switch (temp) {
							case "HELP":
								done = true;
								break;
							case "GAME":
								temp = sc.next();
								switch (temp) {
									case "MATCH":
										temp = "";
										while (sc.hasNext()) {
											temp = temp + sc.next();
										}
										parsedMap = parseMap(temp);
										gameManager.setMatch(parsedMap.get("PLAYERTOMOVE"), parsedMap.get("GAMETYPE"), parsedMap.get("OPPONENT"));
										done = true;
										break;
									case "YOURTURN":
										temp = "";
										while (sc.hasNext()) {
											temp = temp + sc.next();
										}
										parsedMap = parseMap(temp);
                                        gameManager.yourTurn(parsedMap.get("TURNMESSAGE"));
										done = true;
										break;
									case "MOVE":
										temp = "";
										while (sc.hasNext()) {
											temp = temp + sc.next();
										}
										parsedMap = parseMap(temp);
										gameManager.setMove(parsedMap.get("PLAYER"), Integer.parseInt(parsedMap.get("MOVE")), parsedMap.get("DETAILS"));
										done = true;
										break;
									case "CHALLENGE":
										temp = "";
										while (sc.hasNext()) {
											temp = temp + sc.next();
										}
										parsedMap = parseMap(temp);
										gameManager.setChallenge(parsedMap.get("CHALLENGER"), Integer.parseInt(parsedMap.get("CHALLENGENUMBER")), parsedMap.get("GAMETYPE"));
										done = true;
										break;
									case "WIN":
										temp = "";
										while (sc.hasNext()) {
											temp = temp + sc.next();
										}
										parsedMap = parseMap(temp);
										gameManager.endGame(1, Integer.parseInt(parsedMap.get("PLAYERONESCORE")), Integer.parseInt(parsedMap.get("PLAYERTWOSCORE")), parsedMap.get("MESSAGE"));
										done = true;
										break;
									case "LOSS":
										temp = "";
										while (sc.hasNext()) {
											temp = temp + sc.next();
										}
										parsedMap = parseMap(temp);
										gameManager.endGame(-1, Integer.parseInt(parsedMap.get("PLAYERONESCORE")), Integer.parseInt(parsedMap.get("PLAYERTWOSCORE")), parsedMap.get("MESSAGE"));
										done = true;
										break;
									case "DRAW":
										temp = "";
										while (sc.hasNext()) {
											temp = temp + sc.next();
										}
										parsedMap = parseMap(temp);
										gameManager.endGame(0, Integer.parseInt(parsedMap.get("PLAYERONESCORE")), Integer.parseInt(parsedMap.get("PLAYERTWOSCORE")), parsedMap.get("MESSAGE"));
										done = true;
										break;
									default:
										break;
								}
								break;
							case "GAMELIST":
								temp = "";
								while (sc.hasNext()) {
									temp = temp + " " + sc.next();
								}
								parsedList = parseList(temp);
								netManager.parsedQueue.add(new Message("gameList", parsedList));
								done = true;
								break;
							case "PLAYERLIST":
								temp = "";
								while (sc.hasNext()) {
									temp = temp + " " + sc.next();
								}
								parsedList = parseList(temp);
								netManager.parsedQueue.add(new Message("playerList", parsedList));
								done = true;
								break;
							default:
								break;
						}
						break;
					default:
						done = true;
						break;
				}
			}
		}

		/**
		 * Receives a String and cuts it into usable pieces.
		 *
		 * @param s String to be parsed
		 * @return ArrayList with usable sub-strings.
		 */
		public ArrayList<String> parseList(String s)
		{
			ArrayList<String> a = new ArrayList<>();
			s = s.substring(1, (s.length() - 1));
			Scanner scanner = new Scanner(s).useDelimiter(",");
			while (scanner.hasNext()) {
				s = scanner.next();
				s = s.substring(1, s.length() - 1);
				s = s.replace("\"", "");
				a.add(s);
			}
			return a;
		}

		public HashMap<String, String> parseMap(String s)
		{
			HashMap<String, String> m = new HashMap<>();
			String key;
			String value;
			s = s.substring(1, (s.length() - 1));
			Scanner scanner = new Scanner(s).useDelimiter(",|:");
			while (scanner.hasNext()) {
				s = scanner.next();
				key = s;
				s = scanner.next();
				value = s.substring(1, s.length() - 1);
				value = value.replace("\"", "");
				m.put(key, value);
			}
			return m;
		}
	}

	public class Message
	{
		public String type;
		public Object content;

		/**
		 * constructor
		 *
		 * @param type
		 * @param content
		 */
		public Message(String type, Object content)
		{
			setType(type);
			setContent(content);
		}

		/**
		 * Get the value of type
		 *
		 * @return String type
		 */
		public String getType()
		{
			return type;
		}

		/**
		 * Set the type of the message
		 *
		 * @param type
		 */
		private void setType(String type)
		{
			this.type = type;
		}

		/**
		 * Get the content of the message
		 *
		 * @return Object content
		 */
		public Object getContent()
		{
			return content;
		}

		/**
		 * Set the content of the message
		 *
		 * @param content
		 */
		private void setContent(Object content)
		{
			this.content = content;
		}
	}
}