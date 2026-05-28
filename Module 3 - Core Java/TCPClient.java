import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);
        System.out.println("Connected to server.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out   = new PrintWriter(socket.getOutputStream(), true);
        Scanner sc        = new Scanner(System.in);

        // Thread to continuously read messages from server
        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null)
                    System.out.println("Server: " + msg);
            } catch (IOException e) {
                System.out.println("Server disconnected.");
            }
        }).start();

        System.out.println("Type messages to send to server (Enter to send):");
        while (sc.hasNextLine())
            out.println(sc.nextLine());

        socket.close();
    }
}