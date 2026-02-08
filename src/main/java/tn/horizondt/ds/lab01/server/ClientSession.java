package tn.horizondt.ds.lab01.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

/**
 * Handles one TCP connection.
 *
 * TODO (Part 3): This should run concurrently for many clients (thread-per-connection initially).
 * TODO (Part 5): Later, run this via a thread pool (ExecutorService).
 */
public class ClientSession implements Runnable {

    private final Socket socket;
    private final ServerState state;
    private final CommandRouter router;
    private final ClientSessionContext sessionContext = new ClientSessionContext();

    public ClientSession(Socket socket, ServerState state, CommandRouter router) {
        this.socket = socket;
        this.state = state;
        this.router = router;
    }

    @Override
    public void run() {
        // TODO (Part 6): Add socket timeouts to avoid hanging connections:
        // socket.setSoTimeout(60_000);

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8))
        ) {
            // TODO (Part 1): For warm-up, you may temporarily implement only PING->PONG here.
            // Then move logic to router in Part 2.

            String line;
            while ((line = in.readLine()) != null) {
                //String response = router.handle(line, sessionContext, state);

                //out.write(response);
                out.write("\n");
                out.flush();

                // TODO (Part 2): If command BYE received, set sessionContext.requestClose()
                // and then break here.
                if (sessionContext.shouldClose()) {
                    break;
                }
            }
        } catch (SocketTimeoutException e) {
            // TODO (Part 6): Decide behavior on timeout (send ERR TIMEOUT then close).
            // Note: sending requires output stream, so you might handle it inside loop.
        } catch (IOException e) {
            // Client disconnected or IO problem. Don't crash the server.
        } finally {
            try { socket.close(); } catch (IOException ignored) {}
        }
    }
}
