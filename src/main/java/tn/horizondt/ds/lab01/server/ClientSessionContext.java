package tn.horizondt.ds.lab01.server;

/**
 * Per-connection/session context.
 * Each client has its own instance.
 */
public class ClientSessionContext {
    private String clientId;
    private volatile boolean shouldClose;

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public boolean shouldClose() { return shouldClose; }
    public void requestClose() { this.shouldClose = true; }
}
