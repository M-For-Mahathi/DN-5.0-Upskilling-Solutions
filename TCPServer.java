import java.net.*;
import java.io.*;
import java.util.Scanner;

// Exercise 35 — TCP Server
// Run this first in one terminal, then run TCPClient in a second terminal
// Compile: javac TCPServer.java
// Run:     java TCPServer

public class TCPServer {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server started on port 5000. Waiting for client...");

        Socket client = server.accept();
        System.out.println("Client connected: " + client.getInetAddress());

        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out   = new PrintWriter(client.getOutputStream(), true);
        Scanner sc        = new Scanner(System.in);

        new Thread(() -> {
            try {
                String msg;
                while ((msg = in.readLine()) != null)
                    System.out.println("Client: " + msg);
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            }
        }).start();

        System.out.println("Type messages to send to client (Enter to send):");
        while (sc.hasNextLine())
            out.println(sc.nextLine());

        client.close();
        server.close();
    }
}