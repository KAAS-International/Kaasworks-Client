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
 * Created by Tanja, Niek and Kevin on 8-4-2015.
 * No animals were harmed during the creation process
 * Only 1 cheese suffered from injury
 */


public class NetManager extends Thread {
    private BufferedReader in;
    private BufferedReader stdIn;
    private PrintWriter out;
    private String line;

    public static void main(String args[]) {
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
                line = in.readLine();

                try {
                    if (line.equals("close")) {
                        out.println("exit"); // Zet hier tekens in die server verwacht volgens protocol om verbinding te stoppen. TODO: Teken invullen om verbinding te verbreken.
                        out.flush();
                        in.close();
                        out.close();
                        // working = false; TODO: Create method to stop program from running
                    } else {
                        out.println(line);
                        out.flush();
                        this.wait();
                    }

                } catch (UnknownHostException e) {

                    out.println("exit");
                    out.flush();

                    in.close();
                    out.close();
                    working = false;

                    e.printStackTrace();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* public void setLine(String s) { TODO: Instead of using out.readline() use this method to send message to server
        line = s;
    } */
}