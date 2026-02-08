# Lab 01 — TCP/IP Sockets & Multi-Threaded Server (Starter)

**Scenario:** Distributed Order & Inventory Gateway (TCP).

This is a **starter skeleton**. Your job is to implement each part in order by completing the `TODO` blocks.

## Quick start
Requirements:
- Java 17+
- Maven 3.8+

Build:
```bash
mvn -q -DskipTests package
```

Run server (default port 9090):
- From IDE: run `tn.horizondt.ds.lab01.server.WarehouseServer`
- Or from terminal (if you add the exec plugin yourself): `mvn -q exec:java ...`

Test quickly with netcat:
```bash
nc localhost 9090
PING
```

Run client (from IDE):
- `tn.horizondt.ds.lab01.client.WarehouseClient`

## Parts mapping
- Part 1: Single-client PING/PONG
- Part 2: Protocol parsing + commands (HELLO/LIST/GET/ORDER/CANCEL/BYE)
- Part 3: Multi-client server (accept loop + per-connection session)
- Part 4: Thread safety (atomic ORDER/CANCEL, shared state correctness)
- Part 5: Thread pool / backpressure
- Part 6: Timeouts, heartbeats, graceful shutdown
- Part 7: Load test client + concurrency test

## Deliverables
- Code + README updates
- PROTOCOL.md updates if you extend the protocol
- Short report: design choices + concurrency strategy + tests
