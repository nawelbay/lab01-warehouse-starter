package tn.horizondt.ds.lab01.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Warehouse Gateway TCP server.
 *
 * Parts:
 * - Part 1: single-client PING/PONG
 * - Part 3: accept loop + per-connection session
 * - Part 5: thread pool
 * - Part 6: graceful shutdown
 */
public class WarehouseServer {

    public static void main(String[] args) {
        int port = 9090;
        if (args.length >= 1) {
            port = Integer.parseInt(args[0]);
        }

        // Initial inventory (customize as needed)
        Map<String, Integer> initialInventory = new HashMap<>();
        initialInventory.put("laptop", 12);
        initialInventory.put("mouse", 30);
        initialInventory.put("keyboard", 20);

        // Shared state
        InventoryService inventoryService = new InventoryService(initialInventory);
        OrderService orderService = new OrderService(new HashMap<>());
        ServerState state = new ServerState(inventoryService, orderService);

        CommandRouter router = new CommandRouter();

        // TODO (Part 5): Replace thread-per-connection with a fixed thread pool.
        // Example:
        // ExecutorService pool = Executors.newFixedThreadPool(32);
        ExecutorService pool = null;

        // TODO (Part 6): Add graceful shutdown:
        // - close server socket
        // - shutdown thread pool
        // - stop accept loop

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("WarehouseServer listening on port " + port);

            // TODO (Part 1): Warm-up mode: accept a single client and handle just PING->PONG.
            // TODO (Part 3): Production mode: loop forever accepting clients.
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Client connected: " + client.getRemoteSocketAddress());

                ClientSession session = new ClientSession(client, state, router);

                // Part 3: thread-per-connection
                // new Thread(session).start();

                // Part 5: thread pool
                if (pool != null) {
                    pool.submit(session);
                } else {
                    new Thread(session).start();
                }
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        } finally {
            if (pool != null) pool.shutdownNow();
        }
    }
}
