package tn.horizondt.ds.lab01.server;

public class Order {
    private final String orderId;
    private final String item;
    private final int qty;
    private final String clientId;
    private boolean cancelled;

    public Order(String orderId, String item, int qty, String clientId) {
        this.orderId = orderId;
        this.item = item;
        this.qty = qty;
        this.clientId = clientId;
        this.cancelled = false;
    }

    public String getOrderId() { return orderId; }
    public String getItem() { return item; }
    public int getQty() { return qty; }
    public String getClientId() { return clientId; }
    public boolean isCancelled() { return cancelled; }
    public void setCancelled(boolean cancelled) { this.cancelled = cancelled; }
}
