package tn.horizondt.ds.lab01.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        try {
        ServerSocket serverSocket =new ServerSocket(port);
            System.out.println("WarehouseServer listening on port " + port);
            Socket client = serverSocket.accept();
            System.out.println("Client connected: " + client.getRemoteSocketAddress());

            ClientSession session = new ClientSession(client, state);
            new Thread(session).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        }



}
