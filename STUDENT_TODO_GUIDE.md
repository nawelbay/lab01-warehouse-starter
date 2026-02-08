# Student TODO Guide — Lab 01

Implement in order:

## Part 1 — Single-client PING/PONG
- `ClientSession.run()` or `WarehouseServer` warm-up: support `PING -> PONG`.

## Part 2 — Protocol commands
- Implement in `CommandRouter.handle(...)`:
  - HELLO, LIST, GET, ORDER, CANCEL, BYE, PING
- Use `Protocol.ok/data/err(...)`.

## Part 3 — Multi-client server
- Ensure `WarehouseServer` uses accept loop and starts a session per client.

## Part 4 — Thread safety
- Make `InventoryService` atomic for reserve/restore.
- Make `OrderService` thread-safe.
- Ensure ORDER and CANCEL are correct and consistent.

## Part 5 — Thread pool
- Use `ExecutorService` in `WarehouseServer`.

## Part 6 — Robustness
- Add socket timeouts, handle idle clients.
- Add graceful shutdown hook.

## Part 7 — Load test
- Complete `LoadTestClient` and demonstrate consistent results.
