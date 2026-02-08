package tn.horizondt.ds.lab01.server;

import java.util.Map;

/**
 * Stores accepted orders and supports cancellation.
 *
 * IMPORTANT (Part 4): Must be thread-safe.
 */
public class OrderService {

    // TODO (Part 4): Choose thread-safe structure (ConcurrentHashMap recommended).
    private final Map<String, Order> orders;

    public OrderService(Map<String, Order> orders) {
        this.orders = orders;
    }

    public boolean exists(String orderId) {
        return orders.containsKey(orderId);
    }

    public void put(Order order) {
        // TODO (Part 4): Consider idempotency extension later.
        orders.put(order.getOrderId(), order);
    }

    public Order get(String orderId) {
        return orders.get(orderId);
    }
}
