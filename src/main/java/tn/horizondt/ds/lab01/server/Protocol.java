package tn.horizondt.ds.lab01.server;

import java.util.List;

/**
 * Protocol helper utilities.
 *
 * NOTE: TCP is a byte stream; message boundaries are defined by the application.
 * In this lab we use LINE-BASED framing: 1 request = 1 line ending with '\n'.
 */
public final class Protocol {

    private Protocol() {}

    public static final String OK = "OK";
    public static final String DATA = "DATA";
    public static final String ERR = "ERR";

    // --- Error codes (extend if needed) ---
    public static final String BAD_REQUEST = "BAD_REQUEST";
    public static final String UNKNOWN_COMMAND = "UNKNOWN_COMMAND";
    public static final String OUT_OF_STOCK = "OUT_OF_STOCK";
    public static final String NOT_FOUND = "NOT_FOUND";
    public static final String TIMEOUT = "TIMEOUT";
    public static final String INTERNAL = "INTERNAL";

    public static String ok(String message) {

        return OK + " " + message;
    }

    public static String data(String payload) {
        return DATA + " " + payload;
    }

    public static String err(String code, String message) {
        return ERR + " " + code + " " + message;
    }

    /**
     * Split a command line by spaces (simple tokenization).
     * For this lab, we assume no quoted strings.
     *
     * TODO (Part 2): Improve parsing if you want to support item names with spaces.
     */
    public static List<String> tokens(String line) {

        return List.of(line.trim().split("\\s+"));
    }
}
