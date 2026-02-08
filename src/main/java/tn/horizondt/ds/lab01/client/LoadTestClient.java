package tn.horizondt.ds.lab01.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Part 7: Concurrency test / load generator.
 *
 * Goal: create many clients that try to ORDER the same item concurrently,
 * then verify the server remains consistent (no negative stock, correct number of accepted orders).
 *
 * TODO (Part 7): Implement:
 * - Start N threads
 * - Each connects, HELLO, ORDER with unique orderId, then BYE
 * - Collect responses and print summary (accepted vs rejected)
 */
public class LoadTestClient {

    public static void main(String[] args) throws Exception {
        String host = "127.0.0.1";
        int port = 9090;
        int clients = 20;
        String item = "laptop";
        int qty = 1;

        if (args.length >= 1) clients = Integer.parseInt(args[0]);

        CountDownLatch latch = new CountDownLatch(clients);
        List<String> results = new ArrayList<>();

        for (int i = 0; i < clients; i++) {
            final int id = i;
            new Thread(() -> {
                try (Socket socket = new Socket(host, port);
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                     BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))) {

                    // TODO (Part 7): implement HELLO
                    send(out, "HELLO load" + id);
                    in.readLine();

                    // TODO (Part 7): implement ORDER
                    String orderId = "o" + id;
                    send(out, "ORDER " + orderId + " " + item + " " + qty);
                    String resp = in.readLine();

                    synchronized (results) {
                        results.add(resp);
                    }

                    send(out, "BYE");
                    in.readLine();

                } catch (Exception e) {
                    synchronized (results) {
                        results.add("ERR CLIENT " + e.getMessage());
                    }
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        latch.await();

        long ok = results.stream().filter(r -> r != null && r.startsWith("OK")).count();
        long err = results.size() - ok;

        System.out.println("=== Load test summary ===");
        System.out.println("Clients: " + clients);
        System.out.println("OK: " + ok);
        System.out.println("ERR: " + err);
        System.out.println("--- Sample responses ---");
        results.stream().limit(10).forEach(System.out::println);
    }

    private static void send(BufferedWriter out, String line) throws IOException {
        out.write(line);
        out.write("\n");
        out.flush();
    }
}
