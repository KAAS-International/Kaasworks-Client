package nl.kaasintl.main;
import org.apache.commons.net.telnet.TelnetClient;
import java.io.InputStream;
import java.io.PrintStream;


/**
 * Connects to a telnet client with a password and username.
 *
 * Created by Tanja en Niek on 8-4-2015.
 */
public class NetManager
{
    private TelnetClient telnet = new TelnetClient();
    private InputStream in;
    private PrintStream out;
    private String prompt = "%";


    public NetManager(String server, String user)
    {
        try {
            // Connect to the specified server
            telnet.connect(server, 23);

            // Get input and output stream references
            in = telnet.getInputStream();
            out = new PrintStream(telnet.getOutputStream());

            // Log the user on
            readUntil("login: ");
            write(user);


            // Advance to a prompt
            readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void su(String password)
    {
        try {
            write("su");
            prompt = "#";
            readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readUntil(String pattern)
    {
        try {
            char lastChar = pattern.charAt(pattern.length() - 1);
            StringBuffer sb = new StringBuffer();
            boolean found = false;
            char ch = (char) in.read();
            while (true) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {
                        return sb.toString();
                    }
                }
                ch = (char) in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void write(String value)
    {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String sendCommand(String command)
    {
        try {
            write(command);
            return readUntil(prompt + " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void disconnect()
    {
        try {
            telnet.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            NetManager telnet = new NetManager(
                    "myserver", "userId");
            System.out.println("Got Connection...");
            telnet.sendCommand("ps -ef ");
            System.out.println("run command");
            telnet.sendCommand("ls ");
            System.out.println("run command 2");
            telnet.disconnect();
            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
