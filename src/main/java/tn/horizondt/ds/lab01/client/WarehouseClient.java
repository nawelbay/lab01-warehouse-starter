package tn.horizondt.ds.lab01.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Simple interactive TCP client for manual testing.
 */
public class WarehouseClient {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 9090;

        if (args.length >= 1) host = args[0];
        if (args.length >= 2) port = Integer.parseInt(args[1]);

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
             Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to " + host + ":" + port);
            System.out.println("Type commands (e.g., HELLO storeA, LIST, GET laptop, ORDER o1 laptop 1, CANCEL o1, PING, BYE)");

            while (true) {
                System.out.print("> ");
                String line = sc.nextLine();
                out.write(line);
                out.write("\n");
                out.flush();

                String resp = in.readLine();
                if (resp == null) break;
                System.out.println(resp);

                if (line.trim().equalsIgnoreCase("BYE")) {
                    break;
                }
            }
        }
    }
}
