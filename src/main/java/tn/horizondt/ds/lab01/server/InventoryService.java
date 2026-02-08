package tn.horizondt.ds.lab01.server;

import java.util.Map;

/**
 * Inventory operations.
 *
 * IMPORTANT (Part 4): This class must be thread-safe because multiple client sessions
 * will call it concurrently.
 */
public class InventoryService {

    // TODO (Part 4): Choose a thread-safe strategy:
    // - synchronized blocks around a plain HashMap, OR
    // - ConcurrentHashMap + atomic updates, OR
    // - Map<String, AtomicInteger>, etc.
    private final Map<String, Integer> inventory;

    public InventoryService(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public Map<String, Integer> snapshot() {
        // TODO (Part 4): Return a safe snapshot (copy) to avoid exposing internal mutable state.
        return inventory;
    }

    public int getStock(String item) {
        return inventory.getOrDefault(item, -1);
    }

    /**
     * Atomically reserve qty units for an item.
     *
     * Returns remaining stock if accepted, or -1 if out-of-stock, or -2 if item doesn't exist.
     */
    public int reserve(String item, int qty) {
        // TODO (Part 4): Implement atomic "check then decrement" to avoid races.
        // Hint: synchronize on a lock, or use compute(...) on ConcurrentHashMap.
        Integer cur = inventory.get(item);
        if (cur == null) return -2;
        if (cur < qty) return -1;
        inventory.put(item, cur - qty);
        return cur - qty;
    }

    /**
     * Restore qty units for an item (used for CANCEL).
     */
    public void restore(String item, int qty) {
        // TODO (Part 4): Make this thread-safe + correct (item must exist).
        inventory.put(item, inventory.getOrDefault(item, 0) + qty);
    }
}
