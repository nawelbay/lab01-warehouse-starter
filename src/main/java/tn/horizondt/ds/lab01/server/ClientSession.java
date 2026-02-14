package tn.horizondt.ds.lab01.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;


public class ClientSession implements Runnable {

    private final Socket socket;
    private final ServerState state;
   // private final CommandRouter router;
    private final ClientSessionContext sessionContext = new ClientSessionContext();

    /*public ClientSession(Socket socket, ServerState state, CommandRouter router) {
        this.socket = socket;
        this.state = state;
        this.router = router;
    }*/

    public ClientSession(Socket client, ServerState state) {
        this.socket = client;
        this.state = state;
    }

    @Override
    public void run() {

        BufferedReader in = null;
        BufferedWriter out=null;
        String line;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            while ((line = in.readLine()) != null) {


                out.write("\n");
                out.flush();

                // TODO (Part 2): If command BYE received, set sessionContext.requestClose()
                // and then break here.
                if (sessionContext.shouldClose()) {
                    break;
                }

        }} catch (Exception e) {
            throw new RuntimeException(e);
        }
        }
}
