package tn.horizondt.ds.lab01.server;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CommandRouter {

    /**
     * Handle a single request line for a given session.
     *
     * @param line raw request line (without \n)
     * @param session mutable per-client session context
     * @param state shared server state (inventory + orders)
     * @return response line (single line, no \n)
     */
    public String handle(String line, ClientSessionContext session, ServerState state) {
        if (line == null || line.trim().isEmpty()) {
            return Protocol.err(Protocol.BAD_REQUEST, "empty request");
        }

        List<String> t = Protocol.tokens(line);
        String cmd = t.get(0).toUpperCase();

        try {
            // PING -> PONG
            if ("PING".equals(cmd)) {
                //
            }

            // HELLO <clientId> -> OK welcome <clientId>
            if ("HELLO".equals(cmd)) {
                if (t.size() != 2) return Protocol.err(Protocol.BAD_REQUEST, "usage: HELLO <clientId>");
                String clientId = t.get(1).trim();
                if (clientId.isEmpty())
                    return Protocol.err(Protocol.BAD_REQUEST, "clientId cannot be empty");
                session.setClientId(clientId);
                //return
            }


            // GET <item> -> DATA item=qty OR ERR NOT_FOUND
            if ("GET".equals(cmd)) {
                if (t.size() != 2) return Protocol.err(Protocol.BAD_REQUEST, "usage: GET <item>");
                String item = t.get(1);
                int stock = state.inventory().getStock(item);

                //Implement
                //if (stock < 0)
               // else
            }
} catch (Exception e) {
            // Never crash server on bad input.
            return Protocol.err(Protocol.INTERNAL, "server error");
        }
        return cmd;
    }
protected static String formatInventory(Map<String, Integer> inv) {
        return inv.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining(","));
    }
}
