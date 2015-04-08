package nl.kaasintl.main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;



/**
 * Connects to a telnet client with a password and username.
 *
 * Created by Tanja en Niek on 8-4-2015.
 */


public class NetManager extends Thread {
    private BufferedReader in;
    private BufferedReader stdIn;
    private PrintWriter out;
    private String line;

    public static void main(String args[]) {
        new Thread(new NetManager()).start();
        System.out.println("main test");
    }

    @Override
    public void run() {
        System.out.println("Booooya");
        boolean working = true;

        try {
            Socket sock = new Socket("localhost", 7789);
            out = new PrintWriter(sock.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        out.println("[Server] Connected");
        out.flush();

        while (working) {
            try {
                line = in.readLine();

                try {

                    if(line.equals("close")) {
                        out.println("[Server] Connection closed.");
                        out.flush();

                        in.close();
                        out.close();
                        working = false;
                    } else {
                        out.println("[Server] Hoi.");
                        out.flush();
                    }

                } catch (UnknownHostException e1) {

                    out.print("[Server] Cannot resolve hostname \"" + line + "\".\r\n");
                    out.println("[Server] Connection closed.");
                    out.flush();

                    in.close();
                    out.close();
                    working = false;

                    e1.printStackTrace();

                }

            } catch (IOException e) {

                e.printStackTrace();

            }
        }
    }

//    public static class ThreadBuilder {
//
//
//
//        private Socket client;
//
//        public ThreadBuilder client (Socket client) {
//            this.client = client;
//            return this;
//        }
//
//        public NetManager build() {
//            return new NetManager(this);
//        }
//    }
}