package tn.horizondt.ds.lab01.server;

/**
 * Shared server state across all client sessions.
 */
public class ServerState {
    private final InventoryService inventoryService;
    private final OrderService orderService;

    public ServerState(InventoryService inventoryService, OrderService orderService) {
        this.inventoryService = inventoryService;
        this.orderService = orderService;
    }

    public InventoryService inventory() { return inventoryService; }
    public OrderService orders() { return orderService; }
}
